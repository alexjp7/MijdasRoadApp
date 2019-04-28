
package Mijdas.RoadApp.spring.Views;

import Mijdas.RoadApp.spring.Controllers.LoginController;
import Mijdas.RoadApp.spring.Controllers.NavigationController;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.RouterLink;
import java.util.ArrayList;




@StyleSheet("frontend://styles/navbar.css")
public class Navbar extends Div
{
    private Div navbar;
    private ArrayList<RouterLink> links;
    private Button logOut; 
  

    public Navbar()
    {   //Components
        navbar = new Div();
        links  = new ArrayList<>();
        logOut = new Button("Logout");
              
        setId("navbar");
        logOut.setId("logOut");
        logOut.addClassName("navElement");
        logOut.addClickListener(e->handleLogout());
 
        setSizeFull();
        setNavigation();
   
    }

    public void setNavigation()
    {
        clearAndReset();
        //Home page
        links.get(0).add(new Icon(VaadinIcon.CAR), new Text("Home"));
        if(!LoginController.getInstance().isLogin())
        {
            //Login Page
            links.get(1).add(new Icon(VaadinIcon.USER), new Text("Login"));   
            //Register Page
            links.get(2).add(new Icon(VaadinIcon.USERS),new Text("Register"));  
            addToNavigation();
        }
        else
        { 
            /*******
             * TODO: add other navigation bar displays
             */
            addToNavigation();
            add(logOut);
        }    
    }
    
    private void clearAndReset()
    {
        removeAll(); // Clear components to be set  
        links.forEach((rl) -> rl.removeAll() );
        //Get most recent list of navigation links
        this.links = NavigationController.getInstance().getLinks(); 
    }
 
    private void addToNavigation()
    {
        for(RouterLink rl: links)
        {
            rl.setClassName("navElement");
            add(rl);
        }
    }
    
    private void handleLogout()
    {
        if(LoginController.getInstance().isLogin())
        {
            LoginController.getInstance().logOut();
        }
    }
}
