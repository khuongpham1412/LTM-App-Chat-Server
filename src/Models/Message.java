/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Enum.StatusMessage;
import Enum.TypeMessage;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class Message implements Serializable{
    private String id;
    private String message;
    private String type;
    private Date dateSend;
    private String status;
    private String idRoom;
    private String user_send;
    private String user_receive;

    public String getUser_send() {
        return user_send;
    }

    public void setUser_send(String user_send) {
        this.user_send = user_send;
    }

    public String getUser_receive() {
        return user_receive;
    }

    public void setUser_receive(String user_receive) {
        this.user_receive = user_receive;
    }
    
    public Message(){
        
    }

    public Message(String id, String message, String type, Date dateSend, String status, String idRoom, String user_send, String user_receive) {
        this.id = id;
        this.message = message;
        this.type = type;
        this.dateSend = dateSend;
        this.status = status;
        this.idRoom = idRoom;
        this.user_send = user_send;
        this.user_receive = user_receive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateSend() {
        return dateSend;
    }

    public void setDateSend(Date dateSend) {
        this.dateSend = dateSend;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }
    
    
}
