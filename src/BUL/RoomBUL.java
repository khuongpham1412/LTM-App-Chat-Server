/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUL;

import DAL.AccountDAL;
import DAL.RoomDAL;
import Models.Account;
import Models.Room;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class RoomBUL {
    RoomDAL roomDAL;
    public RoomBUL(){
        if(roomDAL == null){
            roomDAL = new RoomDAL();
        }
    }
    public boolean add(Room room){
        return roomDAL.add(room);
    }
}
