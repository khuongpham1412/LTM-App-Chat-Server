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
public class UpdateUserRoomRequest implements Serializable{
    private String roomId;
    private String userId;
    
    public UpdateUserRoomRequest(){
        
    }

    public UpdateUserRoomRequest(String roomId, String userId) {
        this.roomId = roomId;
        this.userId = userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
}
