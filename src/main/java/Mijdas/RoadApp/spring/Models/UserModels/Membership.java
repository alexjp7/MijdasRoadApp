
package Mijdas.RoadApp.spring.Models.UserModels;

import java.util.ArrayList;

public class Membership
{
    private final static float VEHICLE_FEE = 110;
    private int licenseNum;
    private ArrayList<Vehicle> vehicles;
    
    /************************************************************
     * @param licenseNum - license number of membership holder
    **********************************************************/
    public Membership(int licenseNum)
    {
        this.licenseNum = licenseNum;
        this.vehicles = new ArrayList<>();
    }
    /*******************************************************************
     * @param membership inits members from pre-constructed membership
     ***************************************************************/
    public Membership(Membership membership)
    {
        this.licenseNum = membership.licenseNum;
        this.vehicles = new ArrayList<>(membership.vehicles);
    }

    public void addVehicle(Vehicle v)
    {
        vehicles.add(v);
    }
    
    /***************************************************
     * Removes vehicle based on matching registration
     * @param v - a vehicle to be removed
     ***************************************************/
    public void removeVehicle(Vehicle v)
    {   //Remove if registrations match
        vehicles.removeIf(vehicleChecked -> (v.getRegistration().equals(vehicleChecked.getRegistration())));
    }
    /***************************************************
     * Removes vehicle based on matching registration
     * @param rego - String for vehicles registration
     ***************************************************/
    public void removeVehicle(String rego)
    {
         vehicles.removeIf(vehicleChecked -> (rego.equals(vehicleChecked.getRegistration()))); 
    }
    
    /************************************************************
     * @return - the list of valid vehicles covered by a membership
     ***************************************************************/        
    public ArrayList<Vehicle> getVehicles()
    {
        return vehicles;
    }
    
    /***********************************
     * @return - payable amount for membership holder
     *********************************************/
    public float getPremium()
    {
        return getPremiumPrice(vehicles.size());
    }

    private static float getPremiumPrice(int vehicleNumber)
    {
        return VEHICLE_FEE * vehicleNumber;
    }
 
}
