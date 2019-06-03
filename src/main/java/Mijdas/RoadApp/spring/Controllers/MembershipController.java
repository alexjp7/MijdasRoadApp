package Mijdas.RoadApp.spring.Controllers;

public class MembershipController implements abstractController
{
    //private Membership membership;
    //private Vehicle vehicle;

    public MembershipController()
    {
        //membership = new Membership();
        //vehicle = new Vehicle();
    }

    // Finish this off soon so that it goes into a database.
    /*public boolean submitMemberShipPayment(String registrationNumber, String licenseNumber, String manufacturer, String model, String color, String creditCardType, String creditCardName, String creditCardNumber, String creditCardCVV)
    {
        return DBQueryProcessor.getInstance().writeRegistration(registrationNumber, licenseNumber, manufacturer, model, color, creditCardType, creditCardName, creditCardNumber, creditCardCVV);
    }*/

    public void membershipForm(String registrationNumber, String licenseNumber, String creditCardType, String creditCardName, String creditCardNumber, String creditCardCVV)
    {
        //membership.Membership(registrationNumber, licenseNumber, creditCardType, creditCardName, creditCardNumber, creditCardCVV);
    }

    public void vehicleForm(String licenseNumber, String manufacturer, String model, String color)
    {
        //vehicle.Vehicle(licenseNumber, manufacturer, model, color);
    }
}
