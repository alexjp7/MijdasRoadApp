/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mijdas.RoadApp.spring.Models.MessageModels;

import Mijdas.RoadApp.spring.Controllers.DBQueryProcessor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Joel
 * 
 * IF YOU CHANGE A LOT OF STUFF CALL REFRESHDATA
 * 
 * IF YOU COMPLETE A JOB CALL UPON CHANGEBOOL
 * 
 * 
 */
public class MessageService {
    private static MessageService instance;
    
    private int nextId=0;
    private final HashMap<Integer,Message> allMessages = new HashMap<>();
    private MessageService(){
        
    }
    public static MessageService getInstance(){
        if(instance==null){
            instance= new MessageService();
            instance.updateTable();
        }
        return instance;
    }
    public List<Message> findAll(){
        return findAll(null);
    }
    public List<Message> findAll(String stringFilter){//USE THIS TO FIND ALL REQUESTS FROM A USERNAME
        updateTable();//compare whats in the singleton to whats in the db note::only checks for rowcounts, updatse to data need to be refreshed
        ArrayList<Message> arrayList = new ArrayList<>();
        for(Message re : allMessages.values()){
            boolean passesFilter = (stringFilter == null || stringFilter.isEmpty()) || (re.getMotoristUsername().toLowerCase().contains(stringFilter.toLowerCase())|| re.getMechanicUsername().toLowerCase().contains(stringFilter.toLowerCase()));
            if (passesFilter) {
                arrayList.add(re);
            }
        }
        
        return arrayList;
    }
    public List<Message> findOnce(String stringFilter){//USE THIS TO FIND ALL REQUESTS FROM A USERNAME
        updateTable();//compare whats in the singleton to whats in the db note::only checks for rowcounts, updatse to data need to be refreshed
        ArrayList<Message> arrayList = new ArrayList<>();
        List<String> otherUsers = new ArrayList<>();
        for(Message re : allMessages.values()){
            boolean passesFilter = (stringFilter == null || stringFilter.isEmpty()) || (re.getMotoristUsername().toLowerCase().contains(stringFilter.toLowerCase())|| re.getMechanicUsername().toLowerCase().contains(stringFilter.toLowerCase()));
            if (passesFilter) {
                if(!(otherUsers.contains(re.getMechanicUsername()) || otherUsers.contains(re.getMotoristUsername()))){
                    if(re.getMotoristUsername().toLowerCase().contains(stringFilter.toLowerCase())){
                        otherUsers.add(re.getMechanicUsername());
//                        System.out.println("mechanic added");
                    }
                    else{
                        otherUsers.add(re.getMotoristUsername());
//                        System.out.println("motorist added");
                    }
                    arrayList.add(re);
//                    System.out.println(re.getMessageText());
                }
            }
        }
        
        return arrayList;
    }
    public List<Message> findFromUser(String myUser,String otherUser,boolean isMechanic){
        List<Message> arrayList = new ArrayList<>();
        arrayList = findAll(myUser);
        boolean listHasContent=false;
        ArrayList<Message> finalList = new ArrayList<>();
        for(Message me : arrayList){
            boolean passesFilter = (otherUser == null || otherUser.isEmpty()) || (me.getMotoristUsername().toLowerCase().contains(otherUser.toLowerCase())|| me.getMechanicUsername().toLowerCase().contains(otherUser.toLowerCase()));
            //System.out.println("bbbA");
            if (passesFilter) {
                    finalList.add(me);
                    listHasContent=true;
                   // System.out.println("AAAAAAAAAAAAAAAA"+me.getMessageText());
            }
        }
        if(!listHasContent){
            Message fake;
            if(isMechanic){
                fake = new Message(1, myUser, otherUser, "Hi! It looks like theres no messages here, start the conversation!", false);
            }
            else{
                fake = new Message(1, otherUser, myUser, "Hi! It looks like theres no messages here, start the conversation!", true);
            }
            finalList.add(fake);
        }
        return finalList;
    }
    public boolean checkReq(Message req){//check requirement is already in table
        
        for(Message re : allMessages.values()){
            if(req.getId()==re.getId()){
                return true;//return this already exists
            }
        }
        
        return false;
    }
    
    public void save(Message request){
        if(request==null){
            return;
        }
        allMessages.put(request.getId(),request);
    }

    public void updateTable(){
        Message r;
        int tableCount=DBQueryProcessor.getInstance().countMessage();
//        System.out.println(tableCount+" number of rows");
        if(tableCount==allMessages.size()){
            return;
        }
        else if(tableCount<allMessages.size()){
            refreshData();
            return;
        }
        for (int i = 1; i <=tableCount; i++) {
            r=DBQueryProcessor.getInstance().getMessage(Integer.toString(i));
            if(!checkReq(r)){
                save(r);//if it doesnt exist save it as a new
            }
            
        }
    }
    public void refreshData(){
        allMessages.clear();
        updateTable();
    }
}
