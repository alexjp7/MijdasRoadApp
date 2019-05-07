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
    private String motoristUsername,details;
    private float latitude,longitude;
    
    public Requests(int requestNum,String motoristUsername,String details,float latitude, float longitude){
        this.requestNum=requestNum;
        this.motoristUsername=motoristUsername;
        this.details=details;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public Requests(int requestNum, String motoristUsername, String details) {
        this.requestNum = requestNum;
        this.motoristUsername = motoristUsername;
        this.details = details;
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

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setRequestNum(int requestNum) {
        this.requestNum = requestNum;
    }

    public void setMotoristUsername(String motoristUsername) {
        this.motoristUsername = motoristUsername;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
    
}
