/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.RequestModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class CreateGroupChatRequest implements Serializable{
    private String roomId;
    private ArrayList<String> accountsId;
    
    public CreateGroupChatRequest(){
        
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public ArrayList<String> getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(ArrayList<String> accountsId) {
        this.accountsId = accountsId;
    }

    public CreateGroupChatRequest(String roomId, ArrayList<String> accountsId) {
        this.roomId = roomId;
        this.accountsId = accountsId;
    }
    
}
