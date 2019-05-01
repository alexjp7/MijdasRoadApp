
package Mijdas.RoadApp.spring.Views.Register;

import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

@StyleSheet("frontend://styles/registerForm.css")
class RegisterForm extends FormLayout 
{
    private Select<String> userType   = new Select<>("Motorist","Mechanic");
    private TextField firstName     = new TextField("First Name");
    private TextField lastName      = new TextField("Last Name");
    private TextField username      = new TextField("Username");
    private TextField email         = new TextField("Email");
    private PasswordField password  = new PasswordField("Password");
    private PasswordField password2 = new PasswordField("Re-Type Password");
    private Button register         = new Button("Register");
    private Button clear            = new Button ("Clear");
    
    public RegisterForm()
    {
        //Component Styling
        email.setSizeFull();
        register.setId("formButton");
        clear.setId("formButton");
        
        userType.setPlaceholder("I am a..");
        userType.setLabel("Pick one");
        
        //Form Layout

        VerticalLayout formLayout = new VerticalLayout();
        HorizontalLayout topLayer = new HorizontalLayout(username,userType);
        HorizontalLayout nameGroup = new HorizontalLayout(firstName,lastName);
        HorizontalLayout passwordGroup = new HorizontalLayout(password,password2);
        HorizontalLayout buttonGroup = new HorizontalLayout(register,clear);

        buttonGroup.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        buttonGroup.setWidthFull();
        
        username.addBlurListener(e->validateUsername());
        //Listeners to check when user focuses off password fields.
        password.addBlurListener(e->validatePassword());
        password2.addBlurListener(e->validatePassword());
        
       
        //Form submission and clearing
        register.addClickListener(e->submitForm());
        clear.addClickListener(e->clearForm());
        register.setEnabled(false);
        
        
        //Component ordering
        formLayout.add(topLayer,nameGroup,email,passwordGroup,buttonGroup);
        add(formLayout);
     

    }
    
    public void validateUsername()
    {
        //TO-DO: Make call to databse to check if username is unique
    }
    public void validatePassword()
    {
        if(!password.isEmpty())
        {
            if(!password2.isEmpty())
            {
                if(!password.getValue().equals(password2.getValue()))
                {
                    MainLayout.displayInformationPrompt("Passwords do not match!");
                    register.setEnabled(false);
                }
                else
                {
                    register.setEnabled(true);
                }
            }
           
        }
     
    }
    
    public void submitForm()
    {
        
    }
    
    public void clearForm()
    {
        username.clear();
        firstName.clear();
        lastName.clear();
        email.clear();
        password.clear();
        password2.clear();
    }

}
