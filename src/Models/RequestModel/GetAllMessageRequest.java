/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.RequestModel;

import java.io.Serializable;

/**
 *
 * @author Asus
 */
public class GetAllMessageRequest implements Serializable{
    private String roomId;
    private String userSendId;
    private String type;
    
    public GetAllMessageRequest(){
        
    }

    public GetAllMessageRequest(String roomId, String userSendId, String type) {
        this.roomId = roomId;
        this.userSendId = userSendId;
        this.type = type;
    }

    

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUserSendId() {
        return userSendId;
    }

    public void setUserSendId(String userSendId) {
        this.userSendId = userSendId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
