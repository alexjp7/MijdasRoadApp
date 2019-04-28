
package Mijdas.RoadApp.spring.Views;

//Local Import
import Mijdas.RoadApp.spring.Controllers.NavigationController;

//Vaadin Imports
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;



@StyleSheet("frontend://styles/common-styles.css")

@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainLayout extends Div implements RouterLayout
{
    /*****************************************
     * This class is used for 
     * 
     *****************************************/
    private Div header = new Div();
    private static Navbar navigation;
    
    public MainLayout()
    {
        //Common Layout Styles
        setSizeFull();
        setId("main-layout"); //Common layout themes
             
        //Navigation Bar 
        navigation = new Navbar(NavigationController.getInstance().getLinks());

        header.add(navigation);
        add(header);
    }
    public static void reload()
    {
        navigation.setNavigation();
    }
    
}
