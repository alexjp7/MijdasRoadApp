/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mijdas.RoadApp.spring.Models.MessageModels;

/**
 *
 * @author Joel
 */
public class Message {
    private int id;
    private String mechanicUsername,motoristUsername,messageText;
    private boolean motoristSent;

    public Message(int id, String mechanicUsername, String motoristUsername, String messageText, boolean motoristSent) {
        this.id = id;
        this.mechanicUsername = mechanicUsername;
        this.motoristUsername = motoristUsername;
        this.messageText = messageText;
        this.motoristSent = motoristSent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMechanicUsername() {
        return mechanicUsername;
    }

    public void setMechanicUsername(String mechanicUsername) {
        this.mechanicUsername = mechanicUsername;
    }

    public String getMotoristUsername() {
        return motoristUsername;
    }

    public void setMotoristUsername(String motoristUsername) {
        this.motoristUsername = motoristUsername;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public boolean isMotoristSent() {
        return motoristSent;
    }

    public void setMotoristSent(boolean motoristSent) {
        this.motoristSent = motoristSent;
    }

    @Override
    public Message clone() throws CloneNotSupportedException {
        return (Message) super.clone();
    }
}
