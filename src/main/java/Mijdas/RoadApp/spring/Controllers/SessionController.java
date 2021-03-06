
package Mijdas.RoadApp.spring.Controllers;
//Local Imports
import Mijdas.RoadApp.spring.Models.UserModels.User;
import Mijdas.RoadApp.spring.Views.Login.LoginView;
import Mijdas.RoadApp.spring.Views.MainLayout;

/**********************************
 *
 * Maintains meta information about login status and
 * user-session data
 *
 */
public class SessionController
{
    //Singleton instance holder
    private static class ControllerInstance
    {
        static SessionController INSTANCE = getInstance();
        private ControllerInstance(){} //This class is not to be instantiated

        private static SessionController getInstance()
        {  // Instance of SessionController
            if(INSTANCE == null)
            {
                SessionController loginController = new SessionController();
                loginController.isLogin = false; //Assume the default login-status
                loginController.loggedinUser = null;
                return loginController;
            }
           return INSTANCE;
        }
    }

    private boolean isLogin;   //flags whether a login session has started
    private User loggedinUser;  //User is created when succesful login attempt is made
    private UserType userType;  // Enumerated value for user types (motorist/mechanic)
    private SessionController(){}//Private constructor to stop instantiation

    //returns current instance of nav controller
    public static SessionController getInstance()
    {
        return ControllerInstance.INSTANCE;
    }
     /**************************************************
     * Checks login credentials against database
     * Sets global login value to true
     *
     * @param username - username entered from loginform
     * @param password - password entered from loginform
     * @return  - whether or not a valid input was entered
     ***************************************/
    public boolean checkLogin(String username, String password)
    {
        //Request username and password from Database
        String[] loginInfo = DBQueryProcessor.getInstance().makeLoginRequest(username, password);
        //Check entered values against database values
        if(username.equals(loginInfo[0]) && password.equals(loginInfo[1]))
        {
            isLogin = true;
            initUser(username);
            MainLayout.reload();//set navigation bar to login view
            //TO-DO: get this prompt to show (might need to stop-and-wait/thread sleep.
            MainLayout.displayInformationPrompt("Login Succesful!");
            return true;
        }

        MainLayout.displayInformationPrompt("Invalid User name or Password!");
        return false;
    }

    public void logOut()
    {
         isLogin = false;
         loggedinUser = null;
         LoginView.refreshLogin(); //re-enable login button
         MainLayout.reload(); // reset navigation bar to loggeed-out view
    }

    /********************************************
     *  Constructs the appropriate user type based on table data
     * @param username username entered from login form
     *************************************************************/
    public void initUser(String username)
    {
        //Check if Motorist
        if(DBQueryProcessor.getInstance().isMotorist(username))
        {
            userType = UserType.MOTORIST;
            loggedinUser = DBQueryProcessor.getInstance().getMotorist(username);
        }
        //Check if for Mechanic
        else if(DBQueryProcessor.getInstance().isMechanic(username))
        {
            userType = UserType.MECHANIC;
            loggedinUser = DBQueryProcessor.getInstance().getMechanic(username);
        }
        else
        {
            System.out.println("not motorist/mechanic");
        }
       //TEST PRINT
//       System.out.println(loggedinUser.toString());
    }

    public UserType getUserType()
    {
        return userType;
    }

    public User getUser()
    {
        return loggedinUser;
    }
  /*********************
   *
   * @return the login status
   * of the session
   ******************/
    public boolean isLogin()
    {
        return isLogin;
    }
}
