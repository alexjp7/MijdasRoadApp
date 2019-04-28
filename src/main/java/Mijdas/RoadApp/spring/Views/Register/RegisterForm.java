
package Mijdas.RoadApp.spring.Views.Register;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

@StyleSheet("frontend://styles/registerForm.css")
class RegisterForm extends FormLayout 
{

    private TextField firstName     = new TextField("First Name");
    private TextField lastName      = new TextField("Last Name");
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
  
       
        //Form Layout
        VerticalLayout formLayout = new VerticalLayout();
        HorizontalLayout nameGroup = new HorizontalLayout(firstName,lastName);
        HorizontalLayout passwordGroup = new HorizontalLayout(password,password2);
        HorizontalLayout buttonGroup = new HorizontalLayout(register,clear);
       
        buttonGroup.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        buttonGroup.setWidthFull();
  
  
   
        //Component ordering
        formLayout.add(nameGroup,email,passwordGroup,buttonGroup);
        add(formLayout);
     

    }
}
