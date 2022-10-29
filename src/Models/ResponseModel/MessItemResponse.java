/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.ResponseModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class MessItemResponse implements Serializable{
    private String accountId;
    private String roomId;
    private String username;
    private String newMessage;
    private String status;
    private String type;
    public MessItemResponse(){
        
    }

    public MessItemResponse(String accountId, String roomId, String username, String newMessage, String status, String type) {
        this.accountId = accountId;
        this.roomId = roomId;
        this.username = username;
        this.newMessage = newMessage;
        this.status = status;
        this.type = type;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
