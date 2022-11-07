/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.ResponseModel;

import Models.Message;
import java.io.Serializable;

/**
 *
 * @author Asus
 */
public class GetAllMessageResponse implements Serializable{
    private Message message;
    private String username;
    private String roomType;
    public GetAllMessageResponse(){
        
    }

    public GetAllMessageResponse(Message message, String username, String roomType) {
        this.message = message;
        this.username = username;
        this.roomType = roomType;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

}
