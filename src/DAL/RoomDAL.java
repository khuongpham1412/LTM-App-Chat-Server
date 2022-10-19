/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Connect.DbConnect;
import Models.Account;
import Models.Room;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class RoomDAL {
    Connection conn= DbConnect.getConnect();
    Statement st=null;
    ResultSet rs=null;
//    public AccountDAL(){
//        if(conn==null){
//            try {
//                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//                conn=DbConnect.getConnect();
//            }catch (ClassNotFoundException ex){
//                System.out.println("SVDAO");
//            }
//        }
//    }
    
    public boolean add(Room room){
        try{
           
            st=conn.createStatement();
            st.executeUpdate( "INSERT INTO tbl_room"+ "VALUES(" + room.getRoomId() + "," +
                    room.getSendId() +"," + room.getReceiveId() +","+room.getMessage() +
                    "," + room.getStatus() +")");
            return st.getUpdateCount() > 0;
        }catch(SQLException ex){
            System.out.println("SVDAO docDSSV");
        }
        
        return false;
    }
    
    public ArrayList<Account> getAllAccount(){
        ArrayList<Account> listAccount=new ArrayList<>();
        try{
            String query="Select * from tbl_account";
            st=conn.createStatement();
            rs=st.executeQuery(query);
            while (rs.next()){
                Account account=new Account();
                account.setId(rs.getString(1));
                account.setUsername(rs.getString(2));
                account.setPassword(rs.getString(3));
                listAccount.add(account);
            }
        }catch (SQLException ex){
            System.out.println("SVDAO docDSSV");
        }
        return listAccount;
    }
    
}
