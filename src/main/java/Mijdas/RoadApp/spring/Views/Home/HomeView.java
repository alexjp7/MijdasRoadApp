package Mijdas.RoadApp.spring.Views.Home;

//Local Imports
import Mijdas.RoadApp.spring.Views.Footer;
import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.dependency.StyleSheet;

//Vaadin Imports
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@StyleSheet("frontend://styles/home.css")
@StyleSheet("frontend://styles/common-styles.css")
@Route(value="", layout=MainLayout.class)
@PageTitle("Home")

public class HomeView extends Div
{
    public HomeView()
    {
       
        setWidthFull();
        setHeightFull();
        Div imgBkg = new Div();
        Div bannerDiv = new Div();
        imgBkg.addClassNames("w3-display-container", "w3-animate-opacity");
        
        H1 bannerText = new H1();
        H3 mottoText = new H3();
     
        bannerText.setId("homePageText1");
        mottoText.setId("homePageText2");
        
        
        bannerText.setText("Assistr");
        mottoText.setText("Paving the way for your better day... ");
        bannerDiv.setId("textSpace");
        bannerDiv.add(bannerText, mottoText);
        
        imgBkg.add(bannerDiv);
        imgBkg.setSizeFull();
        bannerDiv.setSizeFull();
        imgBkg.setId("imgBkg");
        

        add(imgBkg, new Footer());
    }  
}
