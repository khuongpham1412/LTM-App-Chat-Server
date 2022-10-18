/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Enum.StatusMessage;
import Enum.TypeMessage;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Asus
 */
public class Message implements Serializable{
    private UUID id;
    private String message;
    private TypeMessage type;
    private Date daySend;
    private StatusMessage status;
    private String idRoom;
    private int user_send;
    private int user_receive;

    public int getUser_send() {
        return user_send;
    }

    public void setUser_send(int user_send) {
        this.user_send = user_send;
    }

    public int getUser_receive() {
        return user_receive;
    }

    public void setUser_receive(int user_receive) {
        this.user_receive = user_receive;
    }
    
    public Message(){
        
    }

    public Message(UUID id, String message, TypeMessage type, Date daySend, StatusMessage status, String idRoom, int user_send, int user_receive) {
        this.id = id;
        this.message = message;
        this.type = type;
        this.daySend = daySend;
        this.status = status;
        this.idRoom = idRoom;
        this.user_send = user_send;
        this.user_receive = user_receive;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TypeMessage getType() {
        return type;
    }

    public void setType(TypeMessage type) {
        this.type = type;
    }

    public Date getDaySend() {
        return daySend;
    }

    public void setDaySend(Date daySend) {
        this.daySend = daySend;
    }

    public StatusMessage getStatus() {
        return status;
    }

    public void setStatus(StatusMessage status) {
        this.status = status;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }
    
    
}
