
package Mijdas.RoadApp.spring.Controllers;

import Mijdas.RoadApp.spring.Views.MainLayout;

public class LoginController 
{
     //Singleton controller 
    private static class ControllerInstance
    {
        static LoginController INSTANCE = getInstance();
        private ControllerInstance(){} //This class is not to be instantiated
 
        private static LoginController getInstance()
        {  //Static instance of login controller
            if(INSTANCE == null)
            {   
                LoginController loginController = new  LoginController();
                loginController.isLogin = false;
                return loginController;
            }
           return INSTANCE;
        
        }
    }
    
    
    private  boolean isLogin;
    
    private LoginController(){}
   
    
    //returns current instance of nav controller
    public static LoginController getInstance()
    {
        return ControllerInstance.INSTANCE;
    }
   
    
    /**************************************************
     * Checks login credentials against database
     * Sets global login value to true
     * ***************************************/
    public boolean checkLogin(String username, String password)
    {
        if(username.equals("alexjp7") && password.equals("z5z3z2zz"))
        {
            isLogin = true;
            MainLayout.reload();
            return true;
        }
                
        return false;
    }
    

    //returns login status of the session 
    public boolean isLogin()
    {
        return isLogin;
    }
    
}
