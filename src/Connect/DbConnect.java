/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connect;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Asus
 */
public class DbConnect {
    private static Connection con=null;

    public static void main(String[] args) {
        DbConnect db=new DbConnect();
        System.out.println("START...");
        db.getConnect();
    }
    public static Connection getConnect(){
        try{
            String user="sa";
            String pass="123456";
            String url="jdbc:sqlserver://localhost:1433;databaseName=SocialChat;encrypt=true;trustServerCertificate=true";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con= DriverManager.getConnection(url,user,pass);
//            System.out.println("connect success");
        }catch (Exception ex){
            System.out.println("connect fail!!!");
            System.out.println(ex);
        }
        return con;
    }
}
