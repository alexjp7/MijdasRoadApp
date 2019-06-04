package Mijdas.RoadApp.spring.Models.UserModels;

public class Vehicle
{
    private String registration;
    private String manufacturer;
    private String model;
    private String color;

    public Vehicle(String registration, String manufacturer, String model, String color)
    {
        this.registration = registration;
        this.manufacturer = manufacturer;
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

    @Override
    public String toString() {
        return registration;
    }
}
