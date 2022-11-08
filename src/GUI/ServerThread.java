/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUL.AccountBUL;
import BUL.MessageBUL;
import BUL.UserRoomBUL;
import DAL.AccountDAL;
import Enum.Status;
import Models.Account;
import Models.RequestModel.DataRequest;
import Models.ResponseModel.DataResponse;
import Models.Message;
import Models.AccountOnline;
import Models.RequestModel.CreateGroupChatRequest;
import Models.RequestModel.GetAllMessageRequest;
import Models.RequestModel.UpdateUserRoomRequest;
import Models.ResponseModel.GetAllMessageResponse;
import Models.ResponseModel.MessItemResponse;
import Models.UserRoom;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class ServerThread implements Runnable{
    
    protected Socket socket;
    ServerThreadBUL serverBUL;
    private ObjectInputStream is;
    private ObjectOutputStream os;
    
    public ServerThread(Socket socketClient, ServerThreadBUL serverBUL){
        this.socket = socketClient;
        this.serverBUL = serverBUL;
    }
    
    public void write(Object obj) throws IOException{
        os.writeObject(obj);
        os.flush();
    }
    
    @Override
    public void run() {
        System.out.println("START THREAD...");
        try {
            is = new ObjectInputStream(socket.getInputStream());
            os = new ObjectOutputStream(socket.getOutputStream());
//            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            DataRequest message;
            while(true){
                try {
                    message = (DataRequest) is.readObject();
                    switch(message.getName()){
                        case "LOGIN_REQUEST" ->  {
//                            Account request = (Account) message.getRequest();
//                            Account account = new AccountBUL().checkLogin(request.getUsername(), request.getPassword());
//                            //Neu tai khoan dung
//                            if(account.getId() != null){
//                                for(AccountOnline item : serverBUL.listClient){
//                                    if(account.getId().equals(item.getId())){
//                                        DataResponse response = new DataResponse();
//                                        response.setName("LOGIN_RESPONSE");
//                                        response.setData("Tai khoan dang dang nhap tai noi khac!!!");
//                                        response.setStatus(Status.WARNING);
//
//                                        write(response);
//                                        return;
//                                    }
//                                }
//                                //Tai khoan login thanh cong
//                                if(account.getActive() == 1){
//                                    AccountOnline accountOnline = new AccountOnline(is, os, account.getId());
//                                    serverBUL.addItem(accountOnline);
//                                    DataResponse response = new DataResponse();
//                                    response.setName("LOGIN_RESPONSE");
//                                    response.setData(account);
//                                    response.setStatus(Status.SUCCESS);
//
//                                    write(response);
//                                    return;
//                                }
//                                //Tai khoan bi khoa
//                                else if(account.getActive() == 0){
//                                    DataResponse response = new DataResponse();
//                                    response.setName("LOGIN_RESPONSE");
//                                    response.setData("Tai khoan dang bi khoa!!!");
//                                    response.setStatus(Status.WARNING);
//                                    write(response);
//                                }
//                            }else{
//                                DataResponse response = new DataResponse();
//                                response.setName("LOGIN_RESPONSE");
//                                response.setData("Tai khoan hoac mat khau khong chinh xac!!!");
//                                response.setStatus(Status.ERROR);
//
//                                write(response);
//                                return;
//                            }

                            Account request = (Account) message.getRequest();
                            Account account = new AccountBUL().getAccountByUsername(request.getUsername());
                            
                            AccountOnline accountOnline = new AccountOnline(is, os, account.getId());
                            serverBUL.addItem(accountOnline);
                            
                            DataResponse response = new DataResponse();
                            response.setName("LOGIN_RESPONSE");
                            response.setData(account);
                            response.setStatus(Status.SUCCESS);
                            
                            write(response);
                        }
                        //Lay tat ca account da tung nhan tin
                        case "GET_ALL_ACCOUNTS_MESSAGGETED_REQUEST" -> {
                            System.out.println("hi 1");
                            String userId = (String) message.getRequest();
                            ArrayList<MessItemResponse> messItems = new ArrayList<>();
                            //Toan bo room cua id account
                            ArrayList<UserRoom> rooms = new UserRoomBUL().getRoomIdByUserId(userId);
                            if(rooms != null){
                                for(UserRoom room : rooms){
                                    if(room.getType().equals("PRIVATE")){
                                        ArrayList<UserRoom> accId = new UserRoomBUL().getAllRoomByRoomId(room.getRoomId(), userId);
                                        ArrayList<Message> newMess = new MessageBUL().getNewMessageByRoomId(room.getRoomId());
                                        messItems.add(new MessItemResponse(accId.get(0).getUserId(), room.getRoomId(), room.getName(), newMess.get(0).getMessage(), newMess.get(0).getStatus(),room.getType()));
                                       
                                    }else{
                                        messItems.add(new MessItemResponse("", room.getRoomId(), room.getName(), "", "", room.getType()));   
                                    }
                                }
                                    
                            }
                            DataResponse response = new DataResponse();
                            response.setName("GET_ALL_ACCOUNTS_MESSAGGETED_RESPONSE");
                            response.setData(messItems);
                            response.setStatus(Status.SUCCESS);
                            
                            write(response);
                        }
                        case "GET_ALL_ACCOUNTS_ONLINE_REQUEST" -> {
                            ArrayList<String> accId = new ArrayList<>();
                            for(AccountOnline item : serverBUL.getListClient()){
                                accId.add(item.getId());
                            }
                            DataResponse response = new DataResponse();
                            response.setName("GET_ALL_ACCOUNTS_ONLINE_RESPONSE");
                            response.setData(accId);
                            response.setStatus(Status.SUCCESS);
                            
                            serverBUL.sendAccountOnline(response);
                        }
                        case "UPDATE_ACCOUNT_ONLINE_REQUEST" -> {
//                            DataResponse response = new DataResponse();
//                            response.setName("GET_ALL_ACCOUNTS_ONLINE_RESPONSE");
//                            response.setData(accId);
//                            response.setStatus(Status.SUCCESS);
//                            
//                            write(response);
                        }
                        //Lay tat ca account
                        case "GET_ALL_ACCOUNTS_REQUEST" ->  {
                            ArrayList<Account> accounts = new AccountDAL().getAllAccount();
                            DataResponse response = new DataResponse();
                            response.setName("GET_ALL_ACCOUNTS_RESPONSE");
                            response.setStatus(Status.SUCCESS);
                            response.setData(accounts);
                            
                            write(response);
                        }
                        case "RESET_PANEL_LEFT_REQUEST" -> {
                            String userId = (String) message.getRequest();
                            ArrayList<MessItemResponse> messItems = new ArrayList<>();
                            //Toan bo room cua id account
                            ArrayList<UserRoom> rooms = new UserRoomBUL().getRoomIdByUserId(userId);
                            if(rooms != null){
                                for(UserRoom room : rooms){
                                    if(room.getType().equals("PRIVATE")){
                                        ArrayList<UserRoom> accId = new UserRoomBUL().getAllRoomByRoomId(room.getRoomId(), userId);
                                        ArrayList<Message> newMess = new MessageBUL().getNewMessageByRoomId(room.getRoomId());
                                        messItems.add(new MessItemResponse(accId.get(0).getUserId(), room.getRoomId(), room.getName(), newMess.get(0).getMessage(), newMess.get(0).getStatus(),room.getType()));
                                       
                                    }else{
                                        messItems.add(new MessItemResponse("", room.getRoomId(), room.getName(), "", "", room.getType()));   
                                    }
                                }
                                    
                            }
                            
                            DataResponse response = new DataResponse();
                            response.setName("RESET_PANEL_LEFT_RESPONSE");
                            response.setData(messItems);
                            response.setStatus(Status.SUCCESS);
                            
//                            serverBUL.sendAccountOnline(userId, response);
                        }
                        //Lay tat ca tin nhan tu phong chat
                        case "GET_ALL_MESSAGE_BY_ROOM_ID_REQUEST" -> {
                            GetAllMessageRequest request = (GetAllMessageRequest) message.getRequest();
                            
                            //Get all messages have status not SEEN
                            ArrayList<Message> messExceptStatusSeen = new MessageBUL().getAllMessageByRoomIdExceptSeenStatus(request.getRoomId());
                            if(!messExceptStatusSeen.isEmpty()){
                                //Update all messages status to SEEN
                                for(Message item : messExceptStatusSeen){
                                    new MessageBUL().updateStatus("SEEN", item.getId());
                                }
                            }
                            
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException ex) {
                            }
                            
                            //Get all messages of room chat
                            ArrayList<Message> messages = new MessageBUL().getAllMessageByRoomId(request.getRoomId());
                            ArrayList<GetAllMessageResponse> res = new ArrayList<>();
                            for(Message item : messages){
                                Account account = new AccountBUL().getAccountById(item.getUser_send());
                                String type = new UserRoomBUL().getTypeFromRoomId(item.getIdRoom());
                                GetAllMessageResponse model = new GetAllMessageResponse();
                                
                                model.setMessage(item);
                                model.setUsername(account.getUsername());
                                model.setRoomType(type);
                                res.add(model);
                            }
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException ex) {
                            }
                            DataResponse response = new DataResponse();
                            response.setName("GET_ALL_MESSAGE_BY_ROOM_ID_RESPONSE");
                            response.setStatus(Status.SUCCESS);
                            response.setData(res);
                            serverBUL.sendPrivate(request.getUserSendId(), request.getUserReceivedId(), response);
                        }
                        //Gui tin nhan private
                        case "SEND_MESSAGE_PRIVATE_REQUEST" ->  {
                            Message mess = (Message) message.getRequest();
                            //Kiem tra 2 user da co phong chat chua (neu chua thi tao phong chat)
                            if(mess.getIdRoom().equals("")){
                                Account acc1 = new AccountBUL().getAccountById(mess.getUser_receive());
                                String room = UUID.randomUUID().toString();
                                new UserRoomBUL().add(new UserRoom(mess.getUser_send(),  room, "PRIVATE", "NULL", acc1.getUsername()));
                                acc1 = new AccountBUL().getAccountById(mess.getUser_send());
                                new UserRoomBUL().add(new UserRoom(mess.getUser_receive(), room, "PRIVATE", "NULL", acc1.getUsername()));
                                mess.setIdRoom(room);
                            }
                            //Them tin nhan vao phong chat
                            if(new MessageBUL().add(mess)){
                                Account account = new AccountBUL().getAccountById(mess.getUser_send());
                                GetAllMessageResponse res = new GetAllMessageResponse();
                                res.setMessage(mess);
                                res.setUsername(account.getUsername());
                                res.setRoomType("PRIVATE");
                                DataResponse response = new DataResponse();
                                response.setName("SEND_MESSAGE_PRIVATE_RESPONSE");
                                response.setStatus(Status.SUCCESS);
                                response.setData(res);
                                System.out.println("user received: "+ mess.getUser_receive());
                                serverBUL.sendPrivate(mess.getUser_send(), mess.getUser_receive(), response);
                            }
                        }
                        //Gui tin nhan public
                        case "SEND_MESSAGE_GROUP_REQUEST" ->  {
                            Message mess = (Message) message.getRequest();
                            if(new MessageBUL().add(mess)){
                                Account account = new AccountBUL().getAccountById(mess.getUser_send());
                                GetAllMessageResponse res = new GetAllMessageResponse();
                                res.setMessage(mess);
                                res.setUsername(account.getUsername());
                                res.setRoomType("PUBLIC");
                                DataResponse response = new DataResponse();
                                response.setName("SEND_MESSAGE_GROUP_RESPONSE");
                                response.setStatus(Status.SUCCESS);
                                response.setData(res);
                                ArrayList<String> accId = new UserRoomBUL().getUserIdByRoomId(mess.getUser_send(), mess.getIdRoom());
                                serverBUL.sendMultiple(mess.getUser_send(), accId, response);
                            }
                        }
                        case "SEND_FILE_PRIVATE_REQUEST" ->  {
                            Message mess = (Message) message.getRequest();
                            //Kiem tra 2 user da co phong chat chua (neu chua thi tao phong chat)
                            if(mess.getIdRoom().equals("")){
                                Account acc1 = new AccountBUL().getAccountById(mess.getUser_receive());
                                String room = UUID.randomUUID().toString();
                                new UserRoomBUL().add(new UserRoom(mess.getUser_send(),  room, "PRIVATE", "NULL", acc1.getUsername()));
                                acc1 = new AccountBUL().getAccountById(mess.getUser_send());
                                new UserRoomBUL().add(new UserRoom(mess.getUser_receive(), room, "PRIVATE", "NULL", acc1.getUsername()));
                                mess.setIdRoom(room);
                            }
                            //Them tin nhan vao phong chat
                            if(new MessageBUL().add(mess)){
                                Account account = new AccountBUL().getAccountById(mess.getUser_send());
                                GetAllMessageResponse res = new GetAllMessageResponse();
                                res.setMessage(mess);
                                res.setUsername(account.getUsername());
                                res.setRoomType("PRIVATE");
                                DataResponse response = new DataResponse();
                                response.setName("SEND_MESSAGE_PRIVATE_RESPONSE");
                                response.setStatus(Status.SUCCESS);
                                response.setData(res);
                                System.out.println("user received: "+ mess.getUser_receive());
                                serverBUL.sendPrivate(mess.getUser_send(), mess.getUser_receive(), response);
                            }
                        }
                        //Gui tin nhan public
                        case "SEND_FILE_GROUP_REQUEST" ->  {
                            Message mess = (Message) message.getRequest();
                            if(new MessageBUL().add(mess)){
                                Account account = new AccountBUL().getAccountById(mess.getUser_send());
                                GetAllMessageResponse res = new GetAllMessageResponse();
                                res.setMessage(mess);
                                res.setUsername(account.getUsername());
                                res.setRoomType("PUBLIC");
                                DataResponse response = new DataResponse();
                                response.setName("SEND_MESSAGE_GROUP_RESPONSE");
                                response.setStatus(Status.SUCCESS);
                                response.setData(res);
                                ArrayList<String> accId = new UserRoomBUL().getUserIdByRoomId(mess.getUser_send(), mess.getIdRoom());
                                serverBUL.sendMultiple(mess.getUser_send(), accId, response);
                            }
                        }
                        case "SEND_STICKER_PRIVATE_REQUEST" ->  {
                            Message mess = (Message) message.getRequest();
                            //Kiem tra 2 user da co phong chat chua (neu chua thi tao phong chat)
                            if(mess.getIdRoom().equals("")){
                                Account acc1 = new AccountBUL().getAccountById(mess.getUser_receive());
                                String room = UUID.randomUUID().toString();
                                new UserRoomBUL().add(new UserRoom(mess.getUser_send(),  room, "PRIVATE", "NULL", acc1.getUsername()));
                                acc1 = new AccountBUL().getAccountById(mess.getUser_send());
                                new UserRoomBUL().add(new UserRoom(mess.getUser_receive(), room, "PRIVATE", "NULL", acc1.getUsername()));
                                mess.setIdRoom(room);
                            }
                            //Them tin nhan vao phong chat
                            if(new MessageBUL().add(mess)){
                                Account account = new AccountBUL().getAccountById(mess.getUser_send());
                                GetAllMessageResponse res = new GetAllMessageResponse();
                                res.setMessage(mess);
                                res.setUsername(account.getUsername());
                                res.setRoomType("PRIVATE");
                                DataResponse response = new DataResponse();
                                response.setName("SEND_MESSAGE_PRIVATE_RESPONSE");
                                response.setStatus(Status.SUCCESS);
                                response.setData(res);
                                System.out.println("user received: "+ mess.getUser_receive());
                                serverBUL.sendPrivate(mess.getUser_send(), mess.getUser_receive(), response);
                            }
                        }
                        //Gui tin nhan public
                        case "SEND_STICKER_GROUP_REQUEST" ->  {
                            Message mess = (Message) message.getRequest();
                            if(new MessageBUL().add(mess)){
                                Account account = new AccountBUL().getAccountById(mess.getUser_send());
                                GetAllMessageResponse res = new GetAllMessageResponse();
                                res.setMessage(mess);
                                res.setUsername(account.getUsername());
                                res.setRoomType("PUBLIC");
                                DataResponse response = new DataResponse();
                                response.setName("SEND_MESSAGE_GROUP_RESPONSE");
                                response.setStatus(Status.SUCCESS);
                                response.setData(res);
                                ArrayList<String> accId = new UserRoomBUL().getUserIdByRoomId(mess.getUser_send(), mess.getIdRoom());
                                serverBUL.sendMultiple(mess.getUser_send(), accId, response);
                            }
                        }
                        //cap nhat trang thai tin nhan
                        case "UPDATE_STATUS_MESSAGE_REQUEST" -> {
                            GetAllMessageResponse request = (GetAllMessageResponse) message.getRequest();
                            new MessageBUL().updateStatus(request.getMessage().getStatus(), request.getMessage().getId());
                            DataResponse response = new DataResponse();
                            response.setName("UPDATE_STATUS_MESSAGE_RESPONSE");
                            response.setStatus(Status.SUCCESS);
                            response.setData(request);
                            
                            serverBUL.sendPrivate(request.getMessage().getUser_send(), request.getMessage().getUser_receive(), response);
                        }
                        //Tao phong chat nhom
                        case "CREATE_GROUP_CHAT_REQUEST" -> {
                            CreateGroupChatRequest request = (CreateGroupChatRequest) message.getRequest();
                            for(String item : request.getAccountsId()){
                                new UserRoomBUL().add(new UserRoom(item, request.getRoomId(), "PUBLIC","","TEST Name"));
                            }
                            MessItemResponse mess = new MessItemResponse("ALL", request.getRoomId(), "TEST Name", "", "SENT", "PUBLIC");
                            DataResponse response = new DataResponse();
                            response.setName("CREATE_GROUP_CHAT_RESPONSE");
                            response.setStatus(Status.SUCCESS);
                            response.setData(mess);
                            
                            serverBUL.sendMultiple("", request.getAccountsId(), response);
                        }
                        case "LEAVE_GROUP_REQUEST" -> {
                            UpdateUserRoomRequest request = (UpdateUserRoomRequest) message.getRequest();
                            if(new UserRoomBUL().deleteUserByRoomId(request.getUserId(), request.getRoomId())){
                                MessItemResponse mess = new MessItemResponse("ALL", request.getRoomId(), "", "", "", "PUBLIC");
                                DataResponse response = new DataResponse();
                                response.setName("LEAVE_GROUP_RESPONSE");
                                response.setStatus(Status.SUCCESS);
                                response.setData(mess);

                                write(response);
                            }
                            
                        }
                        default ->  {
                            System.out.println("Option not exists !!!");
                        }
                    }
                } catch (IOException e) {
                    System.out.println("loi: "+ e.getMessage());
                    break;
                }
                
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("jiserverh");
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
