package Mijdas.RoadApp.spring.Views.Home;

//Local Imports
import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.dependency.StyleSheet;

//Vaadin Imports
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@StyleSheet("frontend://styles/home.css")
@Route(value="", layout=MainLayout.class)
@PageTitle("Home")
public class HomeView extends Div
{
    public HomeView()
    {
        
        setWidthFull();
        setHeightFull();
        Div imgBkg = new Div();
   
        imgBkg.setWidthFull();
        imgBkg.setId("imgBkg");

        add(imgBkg);
        
        
    }

    
}
