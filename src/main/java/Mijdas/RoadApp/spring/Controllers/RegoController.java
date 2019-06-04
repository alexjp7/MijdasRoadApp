package Mijdas.RoadApp.spring.Controllers;

import Mijdas.RoadApp.spring.Models.UserModels.Vehicle;
import java.util.ArrayList;

public class RegoController 
{
    private Vehicle vehicle;
    public RegoController()
    {
        
    }
    
    public boolean saveVehicleUpdates(String licenseNumber, String registrationNumber, String manufacturer, String model, String color)
    {  
        return DBQueryProcessor.getInstance().writeVehicleUpdate(licenseNumber, registrationNumber, manufacturer, model, color);
    }
    
    public boolean saveVehicleInsert(String licenseNumber, String registrationNumber, String manufacturer, String model, String color)
    {  
        return DBQueryProcessor.getInstance().writeVehicleInsert(licenseNumber, registrationNumber, manufacturer, model, color);
    }
    
    public Vehicle getRego(String lNum)
    {  
        return DBQueryProcessor.getInstance().getVehicle(lNum);
    }
    
    public String getRegoRefresh(String regoNum)
    {  
        return DBQueryProcessor.getInstance().getVehicleRead(regoNum);
    }
    
    public ArrayList<Vehicle> getRegoList(String lNum)
    {  
        return DBQueryProcessor.getInstance().getVehicleList(lNum);
    }
}