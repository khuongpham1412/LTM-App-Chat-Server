/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUL;

import DAL.MessageDAL;
import Models.Message;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class MessageBUL {
    MessageDAL messageDAL;
    public MessageBUL(){
        if(messageDAL == null){
            messageDAL = new MessageDAL();
        }
    }
    public boolean add(Message mess){
        return messageDAL.add(mess);
    }
    public ArrayList<Message> getAllMessageByRoomId(String roomId){
        return messageDAL.getAllMessageByRoomId(roomId);
    }
}