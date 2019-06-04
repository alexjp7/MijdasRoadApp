package Mijdas.RoadApp.spring.Controllers;

public class MembershipController
{
    //private Membership membership;
    //private Vehicle vehicle;

    public MembershipController()
    {
        //membership = new Membership();
        //vehicle = new Vehicle();
    }

    // Finish this off soon so that it goes into a database.
    public boolean submitMemberShipPayment(String vehicleType, String lNum, boolean member)
    {
       return DBQueryProcessor.getInstance().writeVehicleMembership(vehicleType, lNum, member);
    }

    public void membershipForm(String licenseNumber, String creditCardType, String creditCardName, String creditCardNumber, String creditCardCVV)
    {
        //membership.Membership(registrationNumber, licenseNumber, creditCardType, creditCardName, creditCardNumber, creditCardCVV);
    }
}
