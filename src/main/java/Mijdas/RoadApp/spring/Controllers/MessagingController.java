/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mijdas.RoadApp.spring.Controllers;


/**
 *
 * @author Joel
 */
public class MessagingController {
    private static MessagingController instance;
    
    
    private String me,them;

    public String getMe() {
        return me;
    }

    public void setMe(String me) {
        this.me = me;
    }

    public String getThem() {
        return them;
    }

    public void setThem(String them) {
        this.them = them;
    }
    
    public static MessagingController getInstance(){
        if(instance==null){
            instance= new MessagingController();
        }
        return instance;
    }
    public void setBoth(String me, String them){
        this.them = them;
        this.me= me;
    }
    
    public boolean sendMsg(String motorist, String mechanic, String messageTxt){
//        System.out.println("sendMsg("+motorist+", "+mechanic+", "+messageTxt+") called.");
        return DBQueryProcessor.getInstance().writeMessage(motorist, mechanic, messageTxt);
    }
    
}
