
package Mijdas.RoadApp.spring.Controllers;
//Local Imports
import Mijdas.RoadApp.spring.Views.Home.HomeView;
import Mijdas.RoadApp.spring.Views.Login.LoginView;
import Mijdas.RoadApp.spring.Views.Register.RegisterView;

import com.vaadin.flow.router.RouterLink;
import java.util.ArrayList;

/********************************
 *
 *  Provides the sites Navigation links
 *  to the views of the system
 */
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
                navController.links   = new ArrayList<>();

                return navController;
            }
           return INSTANCE;
        }
    }

    private  ArrayList<RouterLink> links;

    private NavigationController(){} //Private constructor to stop instantiation
    //returns current instance of nav controller
    public static NavigationController getInstance()
    {
        return ControllerInstance.INSTANCE;
    }

    /***************
     *
     * @return  the up-to date navigation links
       based on login-in status
     */

    private ArrayList<RouterLink> getNavigationList()
    {
        if(!links.isEmpty())
        {
             links.removeAll(links);
        }

        /***********************************************
        TODO:
        * Have navigation bars for different user types

        ************************************************/
        //Check to see if logincontroller has set login value
        if(!SessionController.getInstance().isLogin())
        {
           links.add(new RouterLink(null,HomeView.class));
           links.add(new RouterLink(null,LoginView.class));
           links.add(new RouterLink(null,RegisterView.class));
        }
        else
        {
            //Login links (my profile, check balance, subscription, make a service request etc.
            links.add(new RouterLink(null,HomeView.class));
        }
        return links;
    }

     //Returns current list of navigation links
    public ArrayList<RouterLink> getLinks()
    {
        links = getNavigationList();
        return links;
    }
}
