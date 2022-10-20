/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.*;
import Connect.DbConnect;
import Models.Message;
import Models.UserRoom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Asus
 */
public class MessageDAL {
    Connection conn= DbConnect.getConnect();
    PreparedStatement pts = null;
    ResultSet rs = null;
    
    public boolean add(Message mes){
        try{
            String sql = "Insert into tbl_message values(?,?,?,?,?,?,?,?)";
            pts = conn.prepareStatement(sql);
            pts.setString(1,mes.getId());
            pts.setString(2,mes.getMessage());
            pts.setString(3,mes.getType().toString());
            java.sql.Timestamp timestamp = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
            pts.setTimestamp(4, timestamp);
            pts.setString(5,mes.getStatus().toString());
            pts.setString(6,mes.getIdRoom());
            pts.setString(7,mes.getUser_send());
            pts.setString(8,mes.getUser_receive());
            return pts.executeUpdate() > 0;
        }catch(SQLException e){
            System.out.print(e);
        }
        return false;
    }
    
    public ArrayList<Message> getInfomationRoomByUserId(int id){
        ArrayList<Message> list = new ArrayList<>();
        try{
            String sql = "select * from tbl_user_room where user_id = ?";
            pts = conn.prepareStatement(sql);
            pts.setInt(1,id);
            rs = pts.executeQuery();
            while(rs.next()){
                Message item = new Message();
                
                list.add(item);
            }
            return list;
        }catch(SQLException e){
            System.out.print(e);
        }
        return null;
    }
    
    public ArrayList<Message> getAllMessageByRoomId(String roomId){
        ArrayList<Message> list = new ArrayList<>();
        try{
            String sql = "select * from tbl_message where room_id = ? order by date_send asc";
            pts = conn.prepareStatement(sql);
            pts.setString(1,roomId);
            rs = pts.executeQuery();
            while(rs.next()){
                Message item = new Message();
                item.setId(rs.getString(1));
                item.setMessage(rs.getString(2));
                item.setType(rs.getString(3));
                item.setDateSend(rs.getTimestamp(4));
                item.setStatus(rs.getString(5));
                item.setIdRoom(rs.getString(6));
                item.setUser_send(rs.getString(7));
                item.setUser_receive(rs.getString(8));
                list.add(item);
            }
            return list;
        }catch(SQLException e){
            System.out.print(e);
        }
        return null;
    }
    
    public String getNewMessageByRoomId(String roomId){
        String mess=null;
        try{
            String sql = "select top 1 message from tbl_message where room_id = ? order by date_send desc";
            pts = conn.prepareStatement(sql);
            pts.setString(1,roomId);
            rs = pts.executeQuery();
            while(rs.next()){
                mess = rs.getString(1);
            }
            return mess;
        }catch(SQLException e){
            System.out.print(e);
        }
        return null;
    }
}
