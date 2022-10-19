/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUL;

import DAL.AccountDAL;
import Models.Account;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class AccountBUL {
    AccountDAL accountDAL;
    public AccountBUL(){
        if(accountDAL == null){
            accountDAL = new AccountDAL();
        }
    }
    
    public ArrayList<Account> getAllAccount(){
        return accountDAL.getAllAccount();
    }
    
    public Account getAccountByUsername(String username){
        return accountDAL.getAccountByUsername(username);
    }
    
    public Account getAccountById(String id){
        return accountDAL.getAccountById(id);
    }
}
