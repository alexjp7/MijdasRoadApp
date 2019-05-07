
package Mijdas.RoadApp.spring.Models.UserModels;

public class Motorist extends User
{
    private Integer licenseNum;
    private Membership membership;
    //private PaymentOption payOption;
//<<<<<<< HEAD

    public Motorist(String username, String firstName, String lastName, String email)
    {
        super(username,firstName,lastName,email);
        membership = null;
    }

 //   public Motorist(String username, String firstName, String lastName, String email, Membership membership)
//=======
    
    public Motorist(String username, String firstName, String lastName, String email, Integer licenseNum)
    {
        super(username,firstName,lastName,email);
        this.licenseNum = licenseNum;
        membership = null; 
    }
    
    public Motorist(String username, String firstName, String lastName, String email,Integer licenseNum, Membership membership)
//>>>>>>> 343cce9bbd98f4acd115fe8d62ce0661f68fba64
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
