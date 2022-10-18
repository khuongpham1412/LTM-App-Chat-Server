/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.RequestModel;

import Enum.Status;
import java.io.Serializable;

/**
 *
 * @author Asus
 */
public class DataRequest<T> implements Serializable {
    private String name;
    private T request;
    private Status status;
    
    public DataRequest(){
        
    }

    public DataRequest(String name, T request, Status status) {
        this.name = name;
        this.request = request;
        this.status = status;
    }

    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
