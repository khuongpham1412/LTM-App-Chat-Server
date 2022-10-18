/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import GUI.ServerThread;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

/**
 *
 * @author Asus
 */
public class AccountOnline {
    ObjectInputStream is;
    ObjectOutputStream os;
    private int id;

    public AccountOnline() {
    }

    public AccountOnline(ObjectInputStream is, ObjectOutputStream os, int id) {
        this.is = is;
        this.os = os;
        this.id = id;
    }

    public ObjectInputStream getIs() {
        return is;
    }

    public void setIs(ObjectInputStream is) {
        this.is = is;
    }

    public ObjectOutputStream getOs() {
        return os;
    }

    public void setOs(ObjectOutputStream os) {
        this.os = os;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
}
