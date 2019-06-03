
package Mijdas.RoadApp.spring.Views.Register;

import Mijdas.RoadApp.spring.Views.Footer;
import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="register", layout=MainLayout.class)
@PageTitle("Register")
@StyleSheet("frontend://styles/registerForm.css")
public class RegisterView extends Div
{
    private Button motoristButton = new Button();
    private Button mechanicButton = new Button();
    private VerticalLayout headerLayout = new VerticalLayout();
    private H1 header = new H1();
    private VerticalLayout contentDiv = new VerticalLayout();
    private Div bodyDiv = new Div();
    private H3 introText = new H3();
    private H4 directive1 = new H4();
    private Button backButton = new Button();
    
    
    
    public RegisterView()
    {
        contentDiv.setSizeFull();
        setDefaultSetUp();
        sethandlers();
        setSizeFull();
        bodyDiv.setSizeFull();
        header.setText("Welcome to Assistr!");
//        setId("registerView");
//        bodyDiv.setId("background");
        this.addClassNames("registerView");
        bodyDiv.addClassNames("background");
        bodyDiv.addClassNames("w3-display-container", "w3-animate-opacity");
        add(new Footer());
        
        
    }
    
    private void sethandlers()
    {
        motoristButton.addClickListener(e -> motoristPressed());
        mechanicButton.addClickListener(e -> mechanicPressed());
        backButton.addClickListener(e -> navigateBack());
    }
    private void motoristPressed()
    {   //Instantiate form component
        MotoristRegisterForm form = new MotoristRegisterForm();
        //Remove current contents 
        headerLayout.removeAll();
        contentDiv.removeAll();
        //Styles
//        directive1.setId("directiveText");
        directive1.addClassNames("directiveText");
        directive1.setText("Fill in your details!");
        backButton.setText("Back");
        backButton.addClassNames("backButton");
//        backButton.setId("backButton");
        form.addClassNames("registerForm");
//        form.setId("registerForm");
        
        backButton.setIcon(new Icon(VaadinIcon.ARROW_LEFT));
        backButton = new Button();

 
        headerLayout.add(introText,directive1);
        contentDiv.add(form);
    }
    
    private void mechanicPressed()
    {
        MechanicRegisterForm form = new MechanicRegisterForm();
        //Remove current contents 
        headerLayout.removeAll();
        contentDiv.removeAll();
        //Styles
//        directive1.setId("directiveText");
        directive1.addClassNames("directiveText");
        directive1.setText("Fill in your details!");
        backButton.setText("Back");
        backButton.addClassNames("backButton");
        form.addClassNames("registerForm");
//        backButton.setId("backButton");
//        form.setId("registerForm");
        backButton.setIcon(new Icon(VaadinIcon.ARROW_LEFT));
    
        //add(backButton);
    
        headerLayout.add(introText,directive1);
        contentDiv.add(form);;
    }

    private void setDefaultSetUp()
    {
        bodyDiv.removeAll();
        headerLayout.removeAll();
        contentDiv.removeAll();
        headerLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        H3 introText = new H3();
        H4 directive1 = new H4();
        introText.setText("Lets get you started with your account..");
        directive1.setText("I am a...");
        headerLayout.add(header,introText, directive1);
        directive1.addClassNames("directive1");
//        directive1.setId("directive1");

       
        contentDiv.setAlignItems(FlexComponent.Alignment.CENTER);;

        contentDiv.add(motoristButton,mechanicButton);

        motoristButton.setClassName("userButton");
        mechanicButton.setClassName("userButton");
        motoristButton.addClassNames("motoristButton");
        mechanicButton.addClassNames("mechanicButton");
//        motoristButton.setId("motoristButton");
//        mechanicButton.setId("mechanicButton");

        motoristButton.setIcon(new Icon((VaadinIcon.CAR)));
        mechanicButton.setIcon(new Icon((VaadinIcon.WRENCH)));

        motoristButton.setText("Motorist");
        mechanicButton.setText("Mechanic");
        
        bodyDiv.add(headerLayout, contentDiv);
        add(bodyDiv);
        
    }
    
    private void navigateBack()
    {
        removeAll();
        setDefaultSetUp();
        
    }
}
