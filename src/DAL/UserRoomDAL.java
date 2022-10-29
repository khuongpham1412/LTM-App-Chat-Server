/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.*;
import Connect.DbConnect;
import Models.UserRoom;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class UserRoomDAL {
    Connection conn= DbConnect.getConnect();
    PreparedStatement pts = null;
    ResultSet rs = null;
    
    public boolean add(UserRoom user){
        try{
            String sql = "Insert into tbl_user_room values(?,?,?,?,?)";
            pts = conn.prepareStatement(sql);
            pts.setString(1,user.getUserId());
            pts.setString(2,user.getRoomId());
            pts.setString(3,user.getType());
            pts.setString(4,user.getImage());
            pts.setString(5,user.getName());
            return pts.executeUpdate() > 0;
        }catch(SQLException e){
            System.out.print(e);
        }
        return false;
    }
    
    public ArrayList<UserRoom> getAllRoomByUserId(String id){
        ArrayList<UserRoom> list = new ArrayList<>();
        try{
            String sql = "select * from tbl_user_room where user_id = ? and type ='PRIVATE'";
            pts = conn.prepareStatement(sql);
            pts.setString(1,id);
            rs = pts.executeQuery();
            while(rs.next()){
                UserRoom item = new UserRoom();
                item.setUserId(rs.getString(1));
                item.setRoomId(rs.getString(2));
                item.setType(rs.getString(3));
                item.setImage(rs.getString(4));
                item.setName(rs.getString(5));
                list.add(item);
            }
            return list;
        }catch(SQLException e){
            System.out.print(e);
        }
        return null;
    }
    
    public ArrayList<UserRoom> getAllRoomByRoomId(String roomId, String userId){
        ArrayList<UserRoom> list = new ArrayList<>();
        try{
            String sql = "select * from tbl_user_room where room_id = ? and user_id != ? and type ='PRIVATE'";
            pts = conn.prepareStatement(sql);
            pts.setString(1,roomId);
            pts.setString(2,userId);
            rs = pts.executeQuery();
            while(rs.next()){
                UserRoom item = new UserRoom();
                item.setUserId(rs.getString(1));
                item.setRoomId(rs.getString(2));
                item.setType(rs.getString(3));
                item.setImage(rs.getString(4));
                item.setName(rs.getString(5));
                list.add(item);
            }
            return list;
        }catch(SQLException e){
            System.out.print(e);
        }
        return null;
    }
    
    //xem lai
    public boolean checkRoomExist(int userSendId, int userReceivedId){
        try{
            String sql = "select * from tbl_user_room where (user_send_id = ? and user_received_id = ?) or (user_send_id = ? and user_received_id = ?) ";
            pts = conn.prepareStatement(sql);
            pts.setInt(1,userSendId);
            pts.setInt(2,userReceivedId);
            pts.setInt(3,userReceivedId);
            pts.setInt(4,userSendId);
            rs = pts.executeQuery();
            
            return rs.next();
        }catch(SQLException e){
            System.out.print(e);
        }
        return false;
    }
    
    public ArrayList<UserRoom> getRoomIdByUserId(String userId){
        ArrayList<UserRoom> list = new ArrayList<>();
        try{
            String sql = "select * from tbl_user_room where user_id = ? ";
            pts = conn.prepareStatement(sql);
            pts.setString(1,userId);         
            rs = pts.executeQuery();
            while(rs.next()){
                UserRoom item = new UserRoom();
                item.setUserId(rs.getString(1));
                item.setRoomId(rs.getString(2));
                item.setType(rs.getString(3));
                item.setImage(rs.getString(4));
                item.setName(rs.getString(5));
                list.add(item);
            }
            return list;
        }catch(SQLException e){
            System.out.print(e);
        }
        return null;
    }
    public ArrayList<String> getUserIdByRoomId(String userId,String roomId){
        ArrayList<String> result = new ArrayList<>();
        try{
            String sql = "select user_id from tbl_user_room where user_id != ? and room_id = ?";
            pts = conn.prepareStatement(sql);
            pts.setString(1,userId);  
            pts.setString(2,roomId);
            rs = pts.executeQuery();
            while(rs.next()){
                result.add(rs.getString(1));
            }
            return result;
        }catch(SQLException e){
            System.out.print(e);
        }
        return null;
    }
}
