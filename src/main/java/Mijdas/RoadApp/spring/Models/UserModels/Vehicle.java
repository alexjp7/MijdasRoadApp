package Mijdas.RoadApp.spring.Models.UserModels;

public class Vehicle
{
    private String registration;
    private String manufacturer;
    private String model;
    private String color;
    private String membership;
    //private Membership membership;
    
    public Vehicle(String registration, String manufacturer, String model, String color, String hasMembership)
    {
        this.registration = registration;
        this.manufacturer = manufacturer;
        this.model = model;
        this.color = color;
        this.membership = hasMembership;
        
    }

    /******************SETTERS***********************/
    public void setRegistration(String registration)
    {
        this.registration = registration;
    }

    public void setManufacterer(String manufacterer)
    {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public void setColor(String color)
    {
        this.color = color;
    }
    
    public void setMembership(String hasMembership)
    {
        this.membership = hasMembership;
    }
    /******************GETTERS***********************/
    public String getRegistration()
    {
        return registration;
    }

    public String getManufacterer()
    {
        return manufacturer;
    }

    public String getModel()
    {
        return model;
    }

    public String getColor()
    {
        return color;
    }
    
    public String getMembership()
    {
        return membership;
    }

    @Override
    public String toString() {
        return registration;
    }
}
