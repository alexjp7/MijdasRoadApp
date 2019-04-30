
package Mijdas.RoadApp.spring.Controllers;
//Local Imports
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
        {  //Static instance of login controller
            if(INSTANCE == null)
            {   
                SessionController loginController = new  SessionController();
                loginController.isLogin = false; //Assume the default login-status
                return loginController;
            }
           return INSTANCE;
        
        }
    }
    
    //flags whether a login session has started
    private  boolean isLogin;
    //private User loggedinUser;  TO-DO: make user data-model
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
        String checkUsername = DBQueryProcessor.getInstance().makeLoginRequest(username, password)[0];
        String checkPassword = DBQueryProcessor.getInstance().makeLoginRequest(username, password)[1];
        
        //Check entered values against database values
        if(username.equals(checkUsername) && password.equals(checkPassword))
        {       
            isLogin = true;
            //TO-DO: request user info to store in User object.
            MainLayout.reload();//set navigation bar to login view
            return true;
        }
                
        return false;
    }
    
    public void logOut()
    {
         isLogin = false;
         LoginView.refreshLogin(); //re-enable login button
         MainLayout.reload(); // reset navigation bar to loggeed-out view
 
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
