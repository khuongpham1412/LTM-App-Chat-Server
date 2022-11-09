/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.RequestModel;

import Models.Message;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class SendFileRequest implements Serializable {

    private byte[] fileContentByte;
    private Message message;
    private String fileName;

    public SendFileRequest() {
    }

    public SendFileRequest(byte[] fileContentByte, Message message, String fileName) {
        this.fileContentByte = fileContentByte;
        this.message = message;
        this.fileName = fileName;
    }

    public byte[] getFileContentByte() {
        return fileContentByte;
    }

    public void setFileContentByte(byte[] fileContentByte) {
        this.fileContentByte = fileContentByte;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
