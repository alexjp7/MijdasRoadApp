package Mijdas.RoadApp.spring.Models.UserModels;

public abstract class User
{
    protected String username;
    protected String firstName;
    protected String lastName;
    protected String email;

    public User(String username, String firstName, String lastName, String email)
    {
        this.username = username;
//        System.out.println(this.username);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /******************SETTERS***********************/
    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public abstract void setLicenseNum(int licenseNum);

       /*********************GETTERS*********************/
    public String getUsername()
    {
        return username;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getEmail()
    {
        return email;
    }
    
    public abstract Integer getLicenseNum();
    
    @Override
    public String toString()
    {
        return "User{" + "username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + '}';
    }          
}
