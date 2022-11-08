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
   public boolean add(UserRoom user){
       return userRoomDAL.add(user);
   }
   public boolean deleteUserByRoomId(String userId, String roomId){
       return  userRoomDAL.deleteUserByRoomId(userId, roomId);
   }
    
    public ArrayList<UserRoom> getAllRoomByRoomId(String roomId, String userId){
        return userRoomDAL.getAllRoomByRoomId(roomId, userId);
    }
    public ArrayList<UserRoom> getRoomIdByUserId(String userId){
        return userRoomDAL.getRoomIdByUserId(userId);
    }
     public ArrayList<String> getUserIdByRoomId(String userId,String roomId){
         return userRoomDAL.getUserIdByRoomId(userId, roomId);
     }
     public String getTypeFromRoomId(String roomId){
         return userRoomDAL.getTypeFromRoomId(roomId);
     }
}
