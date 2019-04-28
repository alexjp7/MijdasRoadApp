
package Mijdas.RoadApp.spring.Controllers;
//Local Imports
import Mijdas.RoadApp.spring.Views.Home.HomeView;
import Mijdas.RoadApp.spring.Views.Login.LoginView;
import Mijdas.RoadApp.spring.Views.Register.RegisterView;

import com.vaadin.flow.router.RouterLink;
import java.util.ArrayList;



//Provides the sites navigation 
public class NavigationController
{
   //Singleton controller 
    private static class ControllerInstance
    {
        static NavigationController INSTANCE = getInstance();
        private ControllerInstance(){} //This class is not to be instantiated
        
        private static NavigationController getInstance()
        {  //Static instance of navigation
            if(INSTANCE == null)
            {   
                //Home, Login and Registration views are pre-loaded into navbar
                NavigationController navController = new  NavigationController();
                navController.links = new ArrayList<>();
                //Navigation Links
                navController.links.add(new RouterLink(null,HomeView.class)); //HOME
                navController.links.add(new RouterLink(null,LoginView.class));//LOGIN
                navController.links.add(new RouterLink(null,RegisterView.class));// REGISTER
                
                return navController;
            }
           return INSTANCE;
        
        }
    }
    
    private  ArrayList<RouterLink> links;
    private boolean isLogin;
    
    
    //returns current instance of nav controller
    public static NavigationController getInstance()
    {
        return ControllerInstance.INSTANCE;
    }
    
  
    

     //Returns current list of navigation links
    public ArrayList<RouterLink> getLinks()
    {
        return links;
    }
  
    public void setLoginNavigation(boolean isLogin)
    {
        this.isLogin = isLogin;
    }
    
    public boolean isLogin()
    {
        return isLogin;
    }
}
