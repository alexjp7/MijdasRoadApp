package Mijdas.RoadApp.spring.Views;

import Mijdas.RoadApp.spring.Controllers.UserType;
import Mijdas.RoadApp.spring.Controllers.SessionController;
import Mijdas.RoadApp.spring.Controllers.NavigationController;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.RouterLink;
import java.util.ArrayList;

/*******************************************************************
 *
 * Renders navigation elements and their links
   to the view
 *******************************************************************/
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

    /*******
     * TODO: add other navigation bar displays
     */
    public void setNavigation()
    {
        clearAndReset();
        //Home page
        links.get(0).add(new Icon(VaadinIcon.CAR), new Text("Home"));

        if(!SessionController.getInstance().isLogin())
        {
            //Login Page
            links.get(1).add(new Icon(VaadinIcon.USER), new Text("Login"));
            //Register Page
            links.get(2).add(new Icon(VaadinIcon.USERS), new Text("Register"));
        }
        else if(SessionController.getInstance().isLogin())
        {
            if(SessionController.getInstance().getUserType() == UserType.MOTORIST)
            {
                //Membership Page
                links.get(1).add(new Icon(VaadinIcon.GROUP), new Text("Membership"));
                //Profile Page
                links.get(3).add(new Icon(VaadinIcon.COGS), new Text("Profile"));
                //Service request page
                links.get(2).add(new Icon(VaadinIcon.COGS), new Text("Service"));
            }
            else if(SessionController.getInstance().getUserType() == UserType.MECHANIC)
            {
                //Profile Page
                links.get(1).add(new Icon(VaadinIcon.COGS), new Text("Profile"));
                //View requests
                links.get(2).add(new Icon(VaadinIcon.COGS), new Text("View Requests"));
                
                links.get(3).add(new Icon(VaadinIcon.CHAT), new Text("Messages"));
            }
            add(logOut);
        }
        addToNavigation();
    }
    /*****************************************************
     * Clears components and allows them to be reset
     * for when changes to navigation are required
     **********************************/
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

        if(SessionController.getInstance().isLogin())
        {
            add(logOut);
        }
    }

    /***********************************************************************
     *notifies controller of when user has clicked the logout button
     *******************************************************************/
    private void handleLogout()
    {
        if(SessionController.getInstance().isLogin())
        {
            SessionController.getInstance().logOut();
            getUI().ifPresent(ui-> ui.getPage().executeJavaScript("window.location.href = '' "));
        }
    }
}
