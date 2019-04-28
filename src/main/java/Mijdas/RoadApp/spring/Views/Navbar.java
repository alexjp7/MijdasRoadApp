
package Mijdas.RoadApp.spring.Views;

import Mijdas.RoadApp.spring.Controllers.LoginController;
import Mijdas.RoadApp.spring.Controllers.NavigationController;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.RouterLink;
import java.util.ArrayList;




@StyleSheet("frontend://styles/navbar.css")
public class Navbar extends Div
{
    private ArrayList<RouterLink> links;
  

    public Navbar(ArrayList<RouterLink> links)
    {
        this.links = links;
        setId("navbar");
        setSizeFull();
        setNavigation();
   
    }

    public void setNavigation()
    {
        
        clearNavigation();
       
        
        
        if(!LoginController.getInstance().isLogin())
        {
            //Home page
            links.get(0).add(new Icon(VaadinIcon.CAR), new Text("Home"));
            //Login Page
            links.get(1).add(new Icon(VaadinIcon.USER), new Text("Login"));   
            //Register Page
            links.get(2).add(new Icon(VaadinIcon.USERS),new Text("Register"));  
        }
        else
        {
            links.get(0).add(new Icon(VaadinIcon.CAR), new Text("Home"));
            //Login Page
            links.get(1).add(new Icon(VaadinIcon.USER), new Text("Login"));   
        }
       
        
        for(RouterLink rl: links)
        {
            rl.setClassName("navElement");
            add(rl);
        }
    }
    
    public void clearNavigation()
    {
         removeAll(); // Clear components to be set 
         for(RouterLink rl:links)
         {
             rl.removeAll();
         }
    }
 
    
}
