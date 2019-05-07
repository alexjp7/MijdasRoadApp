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
            instance.ensureTestData();
        }
        return instance;
    }
    public List<Requests> findAll(){
        return findAll(null);
    }
    public List<Requests> findAll(String stringFilter){
        ArrayList<Requests> arrayList = new ArrayList<>();
        for(Requests re : allReq.values()){
            boolean passesFilter = (stringFilter == null || stringFilter.isEmpty()) || allReq.toString().toLowerCase().contains(stringFilter.toLowerCase());
            if (passesFilter) {
                    arrayList.add(re);
            }
        }
        
        return arrayList;
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
            /*
            for(int ind=1;ind<4;ind++){
                r=DBQueryProcessor.getInstance().getRequest(Integer.toString(ind));
                save(r);
                System.out.println(r.getDetails()+"AAAAAAAAA");
            }
            */
            while((DBQueryProcessor.getInstance().getRequest(Integer.toString(ind)).getDetails())!=""){
                r=DBQueryProcessor.getInstance().getRequest(Integer.toString(ind));
                System.out.println(r.getDetails()+"AAAAAAAAA");
                save(r);
                ind++;
            }
        }
    }
}
