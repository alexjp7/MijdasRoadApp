package Mijdas.RoadApp.spring.Controllers;

import Mijdas.RoadApp.spring.Models.UserModels.Vehicle;

public class RegoController 
{
    public RegoController()
    {

    }
    
    public boolean saveVehicleUpdates(String licenseNumber, String registrationNumber, String manufacturer, String model, String color)
    {  
        return DBQueryProcessor.getInstance().writeVehicleUpdate(licenseNumber, registrationNumber, manufacturer, model, color);
    }
    
    public Vehicle getRego(String lNum)
    {  
        return DBQueryProcessor.getInstance().getVehicle(lNum);
    }
}