
package Mijdas.RoadApp.spring.Views;

//Local Import

//Vaadin Imports
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;

/*********************************************
 * PWA: Progressive Web Applications
 * https://vaadin.com/pwa
 *******************************************/
@StyleSheet("frontend://styles/common-styles.css")
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainLayout extends Div implements RouterLayout
{
    /******************************************************************
     * Main layout class used for header template for subsequent views
 
     ************************(********************************************/

    
    //System Prompts to user

    private static Notification infoPrompt;
    //Components
    private Div header;
    
    private static Navbar navigation;
    
    public MainLayout()
    {
        //Common Layout Styles
        
        setSizeFull();
        setId("main-layout"); //Common layout themes
        header = new Div();
        
  
        //Info Prompt
        infoPrompt = new Notification();
        infoPrompt.setId("infoPrompt");
        infoPrompt.setDuration(3000);
        infoPrompt.setPosition(Notification.Position.TOP_CENTER);
        
        
        //Navigation Bar 
        System.out.println("In home view constructor!");
        navigation = new Navbar();

        header.add(navigation);
        add(header);
    }
    
    public static void reload()
    {
        //resets navigation for when relevant site navigations are made (login,logout etc).
        if(navigation == null)
        {   //TEST PRINT
            System.out.println("Navigation is set null ??? ?");
        }
        navigation.setNavigation();
    }
    
 
    
    //TO-DO: Style this  based on error/prompt/etc.
    public static void displayInformationPrompt(String msg)
    {
       infoPrompt.setText(msg);
       infoPrompt.open();
    }
   
}
