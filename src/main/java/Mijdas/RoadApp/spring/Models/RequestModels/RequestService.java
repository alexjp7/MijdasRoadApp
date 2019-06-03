/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mijdas.RoadApp.spring.Models.RequestModels;

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
public class RequestService {
    private static RequestService instance;
    
    private int nextId=0;
    private final HashMap<Integer,Requests> allReq = new HashMap<>();
    private RequestService(){
        
    }
    public static RequestService getInstance(){
        if(instance==null){
            instance= new RequestService();
            instance.updateTable();
        }
        return instance;
    }
    public List<Requests> findAll(){
        return findAll(null);
    }
    public List<Requests> findAll(String stringFilter){//USE THIS TO FIND ALL REQUESTS FROM A USERNAME
        updateTable();//compare whats in the singleton to whats in the db note::only checks for rowcounts, updatse to data need to be refreshed
        ArrayList<Requests> arrayList = new ArrayList<>();
        for(Requests re : allReq.values()){
            boolean passesFilter = (stringFilter == null || stringFilter.isEmpty()) || re.getMotoristUsername().toLowerCase().contains(stringFilter.toLowerCase());
            if (passesFilter && re.isIsComplete() == false) {
                    arrayList.add(re);
                    //System.out.println(re.getRequestNum());
            }
        }
        
        return arrayList;
    }
    public List<Requests> findAllPostCode(String stringFilter){//USE THIS TO FIND ALL REQUESTS FROM A postcode
        updateTable();//compare whats in the singleton to whats in the db note::only checks for rowcounts, updatse to data need to be refreshed
        ArrayList<Requests> arrayList = new ArrayList<>();
        for(Requests re : allReq.values()){
            boolean passesFilter = (stringFilter == null || stringFilter.isEmpty()) || re.getNearestAddress().toLowerCase().contains(stringFilter.toLowerCase());
//            System.out.println(passesFilter);
            if (passesFilter && re.isIsComplete() == false) {
                    arrayList.add(re);
                   // System.out.println(re.getRequestNum());
            }
        }
        
        return arrayList;
    }
    
    
    public void changeBool(int reqNum){//flips the bool both ways
        for(Requests re : allReq.values()){
            if(re.getRequestNum()==reqNum){
                re.setIsComplete(!re.isIsComplete());
            }
        }
        
    }
    public boolean checkReq(Requests req){//check requirement is already in table
        
        for(Requests re : allReq.values()){
            if(req.getRequestNum()==re.getRequestNum()){
                return true;//return this already exists
            }
        }
        
        return false;
    }
    
    public void save(Requests request){
        if(request==null){
            return;
        }
        allReq.put(request.getRequestNum(),request);
    }
    public void ensureTestData(){
        if(findAll().isEmpty()){
            Requests r;
            int ind=1;

            while((DBQueryProcessor.getInstance().getRequest(Integer.toString(ind)).getDetails())!=""){
                r=DBQueryProcessor.getInstance().getRequest(Integer.toString(ind));
               // System.out.println(r.getDetails()+"AAAAAAAAA");
                save(r);
                ind++;
            }
        }
    }
    public void updateTable(){
        Requests r;
        int tableCount=DBQueryProcessor.getInstance().countRequest();
//        System.out.println(tableCount+" number of rows");
        if(tableCount==allReq.size()){
            return;
        }
        else if(tableCount<allReq.size()){
            refreshData();
            return;
        }
        for (int i = 1; i <=tableCount; i++) {
            r=DBQueryProcessor.getInstance().getRequest(Integer.toString(i));
            if(!checkReq(r)){
                save(r);//if it doesnt exist save it as a new
            }
            
        }
    }
    public void refreshData(){
        allReq.clear();
        updateTable();
    }
}
