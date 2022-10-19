/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUL.AccountBUL;
import BUL.MessageBUL;
import DAL.AccountDAL;
import DAL.MessageDAL;
import DAL.RoomDAL;
import DAL.UserRoomDAL;
import Enum.Status;
import Enum.StatusMessage;
import Models.Account;
import Models.RequestModel.DataRequest;
import Models.ResponseModel.DataResponse;
import Models.Message;
import Models.AccountOnline;
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
                            ArrayList<UserRoom> userRooms = new UserRoomDAL().getAllRoomByUserId(userId);
                            if(!userRooms.isEmpty()){
                                for(UserRoom item : userRooms){
                                    if(!item.getUserSendId().equals(userId)){
                                        Account acc = new AccountBUL().getAccountById(item.getUserSendId());
                                        messItems.add(new MessItemResponse(acc.getId(), item.getRoomId(), acc.getUsername(), "TEXT NEW MESS", StatusMessage.SEEN.toString()));
                                    }else{
                                        Account acc = new AccountBUL().getAccountById(item.getUserReceivedId());
                                        messItems.add(new MessItemResponse(acc.getId(), item.getRoomId(), acc.getUsername(), "TEXT NEW MESS", StatusMessage.SEEN.toString()));
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
                        case "GET_ALL_ACCOUNTS_REQUEST" ->  {
                            ArrayList<Account> accounts = new AccountDAL().getAllAccount();
                            DataResponse response = new DataResponse();
                            response.setName("GET_ALL_ACCOUNTS_RESPONSE");
                            response.setStatus(Status.SUCCESS);
                            response.setData(accounts);
                            
                            write(response);
                        }
                        case "GET_ALL_MESSAGE_BY_ROOM_ID_REQUEST" -> {
                            String roomId = (String) message.getRequest();
                            ArrayList<Message> messages = new MessageBUL().getAllMessageByRoomId(roomId);
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
                                MessageDAL messageDAL = new MessageDAL();
                                messageDAL.add(mess);
                                serverBUL.sendPrivate(mess.getUser_send(), mess.getUser_receive(), response);
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
