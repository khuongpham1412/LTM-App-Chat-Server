/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.Serializable;

/**
 *
 * @author Asus
 */
public class UserRoom implements Serializable{
    private String userSendId;
    private String roomId;
    private String userReceivedId;
    public UserRoom(){
        
    }

    public UserRoom(String userSendId, String roomId, String userReceivedId) {
        this.userSendId = userSendId;
        this.roomId = roomId;
        this.userReceivedId = userReceivedId;
    }

    public String getUserSendId() {
        return userSendId;
    }

    public void setUserSendId(String userSendId) {
        this.userSendId = userSendId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUserReceivedId() {
        return userReceivedId;
    }

    public void setUserReceivedId(String userReceivedId) {
        this.userReceivedId = userReceivedId;
    }

    
    
}
