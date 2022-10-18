/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.ResponseModel;

import Enum.Status;
import java.io.Serializable;

/**
 *
 * @author Asus
 */
public class DataResponse<T> implements Serializable{
    private String name;
    private T data;
    private Status status;

    public DataResponse() {
    }

    public DataResponse(String name, T data, Status status) {
        this.name = name;
        this.data = data;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    
}
