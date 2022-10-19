/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Models.ResponseModel.DataResponse;
import Models.AccountOnline;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class ServerThreadBUL {
    ArrayList<AccountOnline> listClient = new ArrayList<>();
    ArrayList<ServerThread> listClient1 = new ArrayList<>();
    
    public ServerThreadBUL(){
    }
    
    public void sendPrivate(String id, String id_receive, DataResponse data){
        for(AccountOnline item : this.listClient){
            if(item.getId().equals(id) || item.getId().equals(id_receive)){
                try {
                item.getOs().writeObject(data);
                item.getOs().flush();
            } catch (IOException ex) {
                Logger.getLogger(ServerThreadBUL.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }
    
//    public void sendMultiple(String mess){
//        for(AccountOnline item : this.listClient){
//            try {
//                item.getOs().writeObject(data);
//                item.getOs().flush();
//                System.out.println("info: "+item.getId()+" "+item.getOs());
//            } catch (IOException ex) {
//                Logger.getLogger(ServerThreadBUL.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
    
    public void addItem(AccountOnline accountOnline){
        listClient.add(accountOnline);
    }
    public ArrayList<AccountOnline> getListClient(){
        return this.listClient;
    }
    
    public void addItem1(ServerThread test){
        listClient1.add(test);
    }
    public ArrayList<ServerThread> getListClient1(){
        return this.listClient1;
    }
    
}
