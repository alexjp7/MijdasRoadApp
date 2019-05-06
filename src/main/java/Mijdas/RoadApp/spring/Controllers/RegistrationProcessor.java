
package Mijdas.RoadApp.spring.Controllers;

/*********************************************
 * '
 * This class processes the registration of a user account
 **********************************************/
public class RegistrationProcessor
{
    public RegistrationProcessor(){ }

    //Performs a check for valid usernames after username is entered in registration form.
    public boolean isUsernameValid(String username)
    {
        return !username.equals(DBQueryProcessor.getInstance().queryUsername(username));
    }

    public boolean submitRegistration(String username, String type, String fName, String lName, String email, String password)
    {  
        return DBQueryProcessor.getInstance().writeRegistration(username,type,fName,lName,email,password);
    }
}
