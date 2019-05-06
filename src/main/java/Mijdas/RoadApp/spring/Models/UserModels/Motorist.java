
package Mijdas.RoadApp.spring.Models.UserModels;

public class Motorist extends User
{
    private Integer licenseNum;
    private Membership membership;
    //private PaymentOption payOption;
    
    public Motorist(String username, String firstName, String lastName, String email, Integer licenseNum)
    {
        super(username,firstName,lastName,email);
        this.licenseNum = licenseNum;
        membership = null; 
    }
    
    public Motorist(String username, String firstName, String lastName, String email,Integer licenseNum, Membership membership)
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
    public Integer getLicenseNum()
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
