
package Mijdas.RoadApp.spring.Models.UserModels;

public class Motorist extends User
{
    private int licenseNum;
    private Membership membership;
    //private PaymentOption payOption;
    
    public Motorist(String username, String firstName, String lastName, String email)
    {
        super(username,firstName,lastName,email);
        membership = null; 
    }
    
    public Motorist(String username, String firstName, String lastName, String email, Membership membership)
    {
        super(username,firstName,lastName,email);
        membership = new Membership(membership); 
    }
    /******************SETTERS***********************/
    public void setLicenseNum(int licenseNum)
    {
        this.licenseNum = licenseNum;
    }

    public void setMembership(Membership membership)
    {
        this.membership = membership;
    }
    
    /*********************GETTERS*********************/
    public int getLicenseNum()
    {
        return licenseNum;
    }

    public Membership getMembership()
    {
        return membership;
    }

    @Override
    public String toString()
    {
        return  super.toString() + "Motorist{" + "licenseNum=" + licenseNum + ", membership=" + membership + '}';
    }
    
    
  
    
    
}
