
package Mijdas.RoadApp.spring.Controllers;

/*********************************************
 * '
 * This class processes the registration of a user account
 **********************************************/
public class RegistrationProcessor 
{
    public RegistrationProcessor()
    {
        
    }
    
    //Performs a check for valid usernames after username is entered in registration form.
    public boolean isUsernameValid(String username)
    {
        String queryResult = DBQueryProcessor.getInstance().queryUsername(username);
        System.out.println("Result  "+queryResult);
        return  !username.equals(queryResult);
    }
}
