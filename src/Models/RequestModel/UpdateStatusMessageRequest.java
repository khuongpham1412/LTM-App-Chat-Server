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
public class UpdateStatusMessageRequest implements Serializable{
    private String roomId;
    private String messageId;
    private String status;
    
    public UpdateStatusMessageRequest(){
        
    }

    public UpdateStatusMessageRequest(String roomId, String messageId, String status) {
        this.roomId = roomId;
        this.messageId = messageId;
        this.status = status;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
