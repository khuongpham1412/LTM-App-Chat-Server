/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;
import Connect.DbConnect;
import Models.Account;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author Asus
 */
public class AccountDAL {
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
    
    public Account getAccountByUsername(String username){
        Account account=new Account();
        try{
            String query="Select * from tbl_account where username = '"+username+"'";
            st=conn.createStatement();
            rs= st.executeQuery(query);
            while(rs.next()){
                account.setId(rs.getString(1));
                account.setUsername(rs.getString(2));
                account.setPassword(rs.getString(3));
            }
        }catch (SQLException ex){
            System.out.println("SVDAO docDSSV");
        }
        return account;
    }
    
    public Account getAccountById(String id){
        Account account=new Account();
        try{
            String query="Select * from tbl_account where id = '"+id+"'";
            st=conn.createStatement();
            rs= st.executeQuery(query);
            while(rs.next()){
                account.setId(rs.getString(1));
                account.setUsername(rs.getString(2));
                account.setPassword(rs.getString(3));
            }
        }catch (SQLException ex){
            System.out.println("SVDAO docDSSV");
        }
        return account;
    }
    
//    public void them(SVDTO sv){
//        System.out.println("test: "+sv.getIdSv()+"-"+sv.getnSv());
//        try{
//            String qry="Insert into SVIEN values ('"+sv.getIdSv()+"','"+sv.getnSv()+"')";
//            st=conn.createStatement();
//            st.executeUpdate(qry);
//        }catch (SQLException ex){
//            System.out.println("SVDAO them");
//        }
//    }
//    public void xoa(String idSv){
//        try {
//            String qry="Delete from SVIEN where idSv='"+idSv+"'";
//            st=conn.createStatement();
//            st.executeUpdate(qry);
//        }catch (SQLException ex){
//            System.out.println("SVDAO xoa");
//        }
//    }
//    public void sua(SVDTO sv){
//        try{
//            String qry="Update SVIEN set nSv='"+sv.getnSv()+"'  where idSv='"+sv.getIdSv()+"'";
//            st=conn.createStatement();
//            st.executeUpdate(qry);
//        }catch (SQLException ex){
//            System.out.println("SVDTO sua");
//        }
//    }
}