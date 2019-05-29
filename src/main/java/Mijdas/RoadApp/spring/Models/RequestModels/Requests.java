/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mijdas.RoadApp.spring.Models.RequestModels;

/**
 *
 * @author Joel
 */
public class Requests {
    private int requestNum;
    private String nearestAddress,motoristUsername,details;
    private boolean isComplete;

    public Requests(int requestNum, String nearestAddress, String motoristUsername, String details, boolean isComplete) {
        this.requestNum = requestNum;
        this.nearestAddress = nearestAddress;
        this.motoristUsername = motoristUsername;
        this.details = details;
        this.isComplete = isComplete;
    }


    public Requests(int requestNum, String nearestAddress, String motoristUsername, String details) {
        this.requestNum = requestNum;
        this.nearestAddress = nearestAddress;
        this.motoristUsername = motoristUsername;
        this.details = details;
        this.isComplete = false;
    }
    
    public int getRequestNum() {
        return requestNum;
    }

    public String getMotoristUsername() {
        return motoristUsername;
    }

    public String getDetails() {
        return details;
    }


    public String getNearestAddress() {
        return nearestAddress;
    }
    public boolean isIsComplete() {
        return isComplete;
    }
    public void setRequestNum(int requestNum) {
        this.requestNum = requestNum;
    }
    
    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
    
    public void setNearestAddress(String nearestAddress) {
        this.nearestAddress = nearestAddress;
    }

    public void setMotoristUsername(String motoristUsername) {
        this.motoristUsername = motoristUsername;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public Requests clone() throws CloneNotSupportedException {
        return (Requests) super.clone();
    }
}
