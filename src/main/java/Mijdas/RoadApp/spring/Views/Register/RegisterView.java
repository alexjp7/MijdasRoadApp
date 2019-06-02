
package Mijdas.RoadApp.spring.Views.Register;

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
    private H3 introText = new H3();
    private H4 directive1 = new H4();
    private Button backButton = new Button();
    
    
    
    public RegisterView()
    {
        headerLayout.setSizeFull();
        header.setText("Welcome to Assistr!");
       
             
        
        setDefaultSetUp();
        
        sethandlers();
        
    }
    
    private void sethandlers()
    {
        motoristButton.addClickListener(e -> motoristPressed());
        mechanicButton.addClickListener(e -> mechanicPressed());
        backButton.addClickListener(e -> navigateBack());
    }
    private void motoristPressed()
    {
        directive1.setId("directiveText");
        directive1.setText("Fill in your details!");
        MotoristRegisterForm form = new MotoristRegisterForm();
        backButton = new Button();
        
        backButton.setText("Back");
        backButton.setId("backButton");
        backButton.setIcon(new Icon(VaadinIcon.ARROW_LEFT));
        backButton.addClickListener(e ->setDefaultSetUp());
        
        form.setId("registerForm");
        headerLayout.removeAll();
        contentDiv.removeAll();
        //add(backButton);
        headerLayout.add(introText,directive1);
        contentDiv.add(form);
    }
    
    private void mechanicPressed()
    {
        directive1.setId("directiveText");
        directive1.setText("Fill in your details!");
        MechanicRegisterForm form = new MechanicRegisterForm();
        
        
        backButton.setText("Back");
        backButton.setId("backButton");
        backButton.setIcon(new Icon(VaadinIcon.ARROW_LEFT));
       
        
        form.setId("registerForm");
        //add(backButton);
        headerLayout.removeAll();
        contentDiv.removeAll();
        headerLayout.add(introText,directive1);
        contentDiv.add(form);
    }

    private void setDefaultSetUp()
    {
        headerLayout.removeAll();
        contentDiv.removeAll();
        headerLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        H3 introText = new H3();
        H4 directive1 = new H4();
        introText.setText("Lets get you started with your account..");
        directive1.setText("I am a...");
        headerLayout.add(header,introText, directive1);
        directive1.setId("directive1");

       
        contentDiv.setAlignItems(FlexComponent.Alignment.CENTER);

        contentDiv.add(motoristButton,mechanicButton);

        motoristButton.setClassName("userButton");
        mechanicButton.setClassName("userButton");
        motoristButton.setId("motoristButton");
        mechanicButton.setId("mechanicButton");

        motoristButton.setIcon(new Icon((VaadinIcon.CAR)));
        mechanicButton.setIcon(new Icon((VaadinIcon.WRENCH)));

        motoristButton.setText("Motorist");
        mechanicButton.setText("Mechanic");

        add(headerLayout,contentDiv);
        
    }
    
    private void navigateBack()
    {
        removeAll();
        setDefaultSetUp();
        
    }
}
