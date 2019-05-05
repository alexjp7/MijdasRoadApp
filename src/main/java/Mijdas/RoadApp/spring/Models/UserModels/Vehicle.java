
package Mijdas.RoadApp.spring.Models.UserModels;


public class Vehicle
{
    private String registration;
    private String manufacterer;
    private String model;
    private String color;

    public Vehicle(String registration, String manufacterer, String model, String color)
    {
        this.registration = registration;
        this.manufacterer = manufacterer;
        this.model = model;
        this.color = color;
    }
    
    /******************SETTERS***********************/
    public void setRegistration(String registration)
    {
        this.registration = registration;
    }

    public void setManufacterer(String manufacterer)
    {
        this.manufacterer = manufacterer;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public void setColor(String color)
    {
        this.color = color;
    }
    /******************GETTERS***********************/
    public String getRegistration()
    {
        return registration;
    }

    public String getManufacterer()
    {
        return manufacterer;
    }

    public String getModel()
    {
        return model;
    }

    public String getColor()
    {
        return color;
    }

    
}
