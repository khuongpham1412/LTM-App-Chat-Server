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
    
    public ArrayList<UserRoom> getAllRoomByRoomId(String roomId, String userId){
        return userRoomDAL.getAllRoomByRoomId(roomId, userId);
    }
    public ArrayList<String> getRoomIdByUserId(String userId){
        return userRoomDAL.getRoomIdByUserId(userId);
    }
     public String getUserIdByRoomId(String userId,String roomId){
         return userRoomDAL.getUserIdByRoomId(userId, roomId);
     }
}
