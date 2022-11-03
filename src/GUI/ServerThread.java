/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUL.AccountBUL;
import BUL.MessageBUL;
import BUL.UserRoomBUL;
import DAL.AccountDAL;
import DAL.MessageDAL;
import DAL.UserRoomDAL;
import Enum.Status;
import Models.Account;
import Models.RequestModel.DataRequest;
import Models.ResponseModel.DataResponse;
import Models.Message;
import Models.AccountOnline;
import Models.RequestModel.CreateGroupChatRequest;
import Models.RequestModel.GetAllMessageRequest;
import Models.ResponseModel.MessItemResponse;
import Models.UserRoom;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
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
                        case "GET_ALL_ACCOUNTS_MESSAGGETED_REQUEST" -> {
                            String userId = (String) message.getRequest();
                            ArrayList<MessItemResponse> messItems = new ArrayList<>();
                            
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
                            
                        }
                        case "RESET_PANEL_LEFT_REQUEST" -> {
                            String userId = (String) message.getRequest();
                            ArrayList<MessItemResponse> messItems = new ArrayList<>();
                            ArrayList<UserRoom> rooms = new UserRoomDAL().getRoomIdByUserId(userId);
                            if(!rooms.isEmpty()){
                                for(UserRoom roomId : rooms){
                                    ArrayList<String> accId = new UserRoomDAL().getUserIdByRoomId(userId, roomId.getRoomId());
                                    ArrayList<Message> newMess = new MessageBUL().getNewMessageByRoomId(roomId.getRoomId());
                                    Account acc = new AccountBUL().getAccountById(accId.get(0));
                                    messItems.add(new MessItemResponse("", roomId.getRoomId(), acc.getUsername(), newMess.get(0).getMessage(), newMess.get(0).getStatus(),""));
                                }
                            }
                            
                            DataResponse response = new DataResponse();
                            response.setName("RESET_PANEL_LEFT_RESPONSE");
                            response.setData(messItems);
                            response.setStatus(Status.SUCCESS);
                            
                            write(response);
                        }
                        case "GET_ALL_ACCOUNTS_REQUEST" ->  {
                            ArrayList<Account> accounts = new AccountDAL().getAllAccount();
                            DataResponse response = new DataResponse();
                            response.setName("GET_ALL_ACCOUNTS_RESPONSE");
                            response.setStatus(Status.SUCCESS);
                            response.setData(accounts);
                            
                            write(response);
                        }
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
                            
                            //Get all messages of room chat
                            ArrayList<Message> messages = new MessageBUL().getAllMessageByRoomId(request.getRoomId());
                            //Check room have message
                            DataResponse response = new DataResponse();
                            response.setName("GET_ALL_MESSAGE_BY_ROOM_ID_RESPONSE");
                            response.setStatus(Status.SUCCESS);
                            response.setData(messages);
                            write(response);
                            
                        }
                        case "SEND_MESSAGE_PRIVATE_REQUEST" ->  {
                            Message mess = (Message) message.getRequest();
                            if(new MessageBUL().add(mess)){
                                DataResponse response = new DataResponse();
                                response.setName("SEND_MESSAGE_PRIVATE_RESPONSE");
                                response.setStatus(Status.SUCCESS);
                                response.setData(mess);
//                                MessageDAL messageDAL = new MessageDAL();
//                                messageDAL.add(mess);
                                serverBUL.sendPrivate(mess.getUser_send(), mess.getUser_receive(), response);
                            }
                        }
                        case "SEND_MESSAGE_GROUP_REQUEST" ->  {
                            Message mess = (Message) message.getRequest();
                            if(new MessageBUL().add(mess)){
                                DataResponse response = new DataResponse();
                                response.setName("SEND_MESSAGE_GROUP_RESPONSE");
                                response.setStatus(Status.SUCCESS);
                                response.setData(mess);
                                ArrayList<String> accId = new UserRoomBUL().getUserIdByRoomId(mess.getUser_send(), mess.getIdRoom());
                                serverBUL.sendMultiple(mess.getUser_send(), accId, response);
                            }
                        }
                        case "UPDATE_STATUS_MESSAGE_REQUEST" -> {
                            Message request = (Message) message.getRequest();
                            new MessageBUL().updateStatus(request.getStatus(), request.getId());
                            DataResponse response = new DataResponse();
                            response.setName("UPDATE_STATUS_MESSAGE_RESPONSE");
                            response.setStatus(Status.SUCCESS);
                            response.setData(request);
                            
                            serverBUL.sendPrivate(request.getUser_send(), request.getUser_receive(), response);
                        }
                        case "CREATE_GROUP_CHAT_REQUEST" -> {
                            CreateGroupChatRequest request = (CreateGroupChatRequest) message.getRequest();
                            System.out.println("xin chao: "+request.getAccountsId().size());
                            for(String item : request.getAccountsId()){
                                new UserRoomBUL().add(new UserRoom(item, request.getRoomId(), "PUBLIC","","TEST Name"));
                            }
                            DataResponse response = new DataResponse();
                            response.setName("CREATE_GROUP_CHAT_RESPONSE");
                            response.setStatus(Status.SUCCESS);
                            response.setData("TAO GROUP THANH CONG");
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
