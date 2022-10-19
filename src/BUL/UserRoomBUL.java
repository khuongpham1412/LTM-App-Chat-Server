/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUL;

import DAL.UserRoomDAL;
import Models.UserRoom;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class UserRoomBUL {
    UserRoomDAL userRoomDAL;
    public UserRoomBUL(){
        if(userRoomDAL == null){
            userRoomDAL = new UserRoomDAL();
        }
    }
    public ArrayList<UserRoom> add(String id){
        return userRoomDAL.getAllRoomByUserId(id);
    }
}
