
package Mijdas.RoadApp.spring.Views;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.spring.annotation.UIScope;

@StyleSheet("frontend://styles/home.css")

public class Footer extends Div
{
   
    public Footer()
    {
        Div mediaIcons = new Div();
        Div footer = new Div();
        
        mediaIcons.setSizeFull();
        
        Icon fb = new Icon(VaadinIcon.FACEBOOK_SQUARE);
        Icon twtr = new Icon(VaadinIcon.TWITTER_SQUARE);
        Icon yt = new Icon(VaadinIcon.YOUTUBE_SQUARE);
 
        mediaIcons.setId("iconGroup");
        fb.setId("f_icon");
        twtr.setId("t_icon");
        yt.setId("y_icon");
        
        
        fb.setClassName("m_icons");
        twtr.setClassName("m_icons");
        yt.setClassName("m_icons");
        
        footer.add(fb, new Label("Assitr"),twtr,new Label("@assistr"),yt, new Label("Assitr_Official"));

        footer.setId("footer");
        add(footer);
    }
}
