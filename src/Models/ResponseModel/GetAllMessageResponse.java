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
    public GetAllMessageResponse(){
        
    }

    public GetAllMessageResponse(Message message, String username) {
        this.message = message;
        this.username = username;
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
    
}
