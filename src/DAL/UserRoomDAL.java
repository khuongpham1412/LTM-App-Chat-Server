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
    
//    public boolean add(UserRoom model){
//        try{
//            String sql = "Insert into tbl_user_room values(?,?)";
//            pts = conn.prepareStatement(sql);
//            pts.setInt(1,model.getUserId());
//            pts.setString(2,model.getRoomId());
//            
//            return pts.executeUpdate() > 0;
//        }catch(SQLException e){
//            System.out.print(e);
//        }
//        return false;
//    }
    
    public ArrayList<UserRoom> getAllRoomByUserId(String id){
        ArrayList<UserRoom> list = new ArrayList<>();
        try{
            String sql = "select * from tbl_user_room where user_send_id = ? or user_received_id = ?";
            pts = conn.prepareStatement(sql);
            pts.setString(1,id);
            pts.setString(2,id);
            rs = pts.executeQuery();
            while(rs.next()){
                UserRoom item = new UserRoom();
                item.setUserSendId(rs.getString(1));
                item.setRoomId(rs.getString(2));
                item.setUserReceivedId(rs.getString(3));
                list.add(item);
            }
            return list;
        }catch(SQLException e){
            System.out.print(e);
        }
        return null;
    }
    
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
    
    
}
