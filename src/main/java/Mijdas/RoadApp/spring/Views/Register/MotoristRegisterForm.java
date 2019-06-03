
package Mijdas.RoadApp.spring.Views.Register;

import Mijdas.RoadApp.spring.Controllers.RegistrationProcessor;
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


class MotoristRegisterForm extends FormLayout
{
    //Controller
    private  RegistrationProcessor regProcessor;
    //Flags
    private boolean isValidUsername;
    private boolean isValidPassword;
    //Componenets
    private Select<String> userType = new Select<>("Motorist","Mechanic");
    private TextField firstName     = new TextField("First Name");
    private TextField lastName      = new TextField("Last Name");
    private TextField username      = new TextField("Username");
    private TextField email         = new TextField("Email");
    private PasswordField password  = new PasswordField("Password");
    private PasswordField password2 = new PasswordField("Re-Type Password");
    private Button register         = new Button("Register");
    private Button clear            = new Button ("Clear");

    public MotoristRegisterForm()
    {
        regProcessor = new RegistrationProcessor();
        //Assume invalid inputs on page construction
        isValidUsername = false;
        isValidPassword = false;
        setFieldProperties();
        setEventListeners();
        setFormLayout();
    }

    private void setFormLayout()
    {
        //Form Layout
        VerticalLayout formLayout = new VerticalLayout();
        HorizontalLayout topLayer = new HorizontalLayout(username);
        HorizontalLayout nameGroup = new HorizontalLayout(firstName,lastName);
        HorizontalLayout passwordGroup = new HorizontalLayout(password,password2);
        HorizontalLayout buttonGroup = new HorizontalLayout(register,clear);

        buttonGroup.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        buttonGroup.setWidthFull();
        topLayer.setWidthFull();
        username.setSizeFull();

        //Component ordering
        formLayout.add(topLayer,nameGroup,email,passwordGroup,buttonGroup);
        add(formLayout);
    }

    private void setFieldProperties()
    {
        //Component Styling
        email.setSizeFull();
        register.setId("formButton");
        clear.setId("formButton");
        userType.setPlaceholder("I am a..");
        userType.setLabel("Pick one");

        //Mark fields as requried
        username.setRequired(true);
        firstName.setRequired(true);
        lastName.setRequired(true);
        email.setRequired(true);
        password.setRequired(true);
        password2.setRequired(true);
    }

    private void setEventListeners()
    {
        //Makes a call to db to check for a unique username
        username.addBlurListener(e->validateUsername());
        //Listeners to check when user focuses off password fields.
        password.addBlurListener(e->validatePassword());
        password2.addBlurListener(e->validatePassword());

        //Form submission and clearing
        register.addClickListener(e->submitForm());
        clear.addClickListener(e->clearForm());
        register.setEnabled(false);
    }

    /*************************************************
     * checks if username is already taken.
     **************************************************/
    private void validateUsername()
    {
        if(!username.getValue().trim().equals(""))
        {
            if(!regProcessor.isUsernameValid(username.getValue()))
            {
                isValidUsername = false;
                MainLayout.displayInformationPrompt("The name "+username.getValue()+" is already taken!");
                username.focus();
            }
            else
            {
                isValidUsername = true;
            }
        }
        setRegisterEnabled();
    }

    /*************************************************
     * checks if password fields are the same values.
    **************************************************/
    private void validatePassword()
    {
        if(!password.isEmpty())
        {
            if(!password2.isEmpty())
            {
                if(!password.getValue().equals(password2.getValue()))
                {
                    MainLayout.displayInformationPrompt("Passwords do not match!");
                    isValidPassword = false;
                }
                else
                {
                    isValidPassword = true;
                }
                setRegisterEnabled();
            }
        }
    }

    private void setRegisterEnabled()
    {
        if(isValidUsername && isValidPassword)
        {
            register.setEnabled(true);
        }
        else
        {
           register.setEnabled(false);
        }
    }

    public void submitForm()
    {
        //Submit form
        if(regProcessor.submitRegistration(username.getValue(), "Motorist",firstName.getValue(),
                                           lastName.getValue(), email.getValue(), password.getValue()))
        {
            getUI().ifPresent(ui-> ui.getPage().executeJavaScript("window.location.href = 'login' "));
        }
        else
        {
            //dispaly prompt
        }
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
