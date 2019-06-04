package Mijdas.RoadApp.spring.Models.UserModels;

public class Vehicle
{
    private String registration;
    private String manufacturer;
    private String model;
    private String color;
    private Membership membership;
    
    
    public Vehicle(String registration, String manufacturer, String model, String color, String hasMembership)
    {
        this.registration = registration;
        this.manufacturer = manufacturer;
        this.model = model;
        this.color = color;
        membership = null;
        hasMembership = "false";
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
    
    public void setMembership(Membership membership)
    {
        this.membership = membership;
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
    
    public Membership getMembership()
    {
        return membership;
    }

    @Override
    public String toString() {
        return registration;
    }
}
