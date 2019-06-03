package Mijdas.RoadApp.spring.Views.Profile;

import Mijdas.RoadApp.spring.Controllers.ProfileController;
import Mijdas.RoadApp.spring.Controllers.SessionController;
import Mijdas.RoadApp.spring.Controllers.UserType;
import Mijdas.RoadApp.spring.Models.UserModels.User;
import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

//basic CSS form for now
@StyleSheet("frontend://styles/Profile.css")
class ProfileForm extends Div
{
    private ProfileController profileController;
    private User loggedInUser = SessionController.getInstance().getUser();
    
    //Used Fields
    private TextField usernameText = new TextField("Username");
    private TextField firstNameText = new TextField("First Name");
    private TextField lastNameText = new TextField("Last Name");
    private TextField emailText = new TextField("Email");
    private TextField licenseText = new TextField("License Number");
    
    //Accordion
    private Accordion accordion = new Accordion();
    
    //Mechanic Fields
//    private TextField qualityText = new TextField("Quality Rating");
    
    //Motorist Fields
//    private TextField membershipStatusText = new TextField("Membership Status");
    
    //Buttons
    private Button saveButton = new Button("Save");
//    private Button revertButton = new Button ("Revert");
    
    
    public ProfileForm()
    {
     
        profileController = new ProfileController();
        setFieldProperties();
        setSizeFull();
        setFormLayout();
//        accordion.setSizeFull();
        accordion.setId("accordion");
        accordion.setSizeUndefined();
        fillFieldProperties();
        setEventListeners();
   
      
    }
    
    private void setFormLayout()
    {
        setCommonProfile();
      
        if(SessionController.getInstance().getUserType() == UserType.MOTORIST)
        {
            setMotoristProfile();
        }
        else if(SessionController.getInstance().getUserType() == UserType.MECHANIC)
        {
            setMechanicProfile();
        }
     
        add(accordion);
    }
    
    private void setCommonProfile()
    {
        Div profileComponents = new Div();
        
        VerticalLayout formLayout = new VerticalLayout();
        
        
        HorizontalLayout rowOne = new HorizontalLayout(usernameText, licenseText);
        HorizontalLayout rowTwo = new HorizontalLayout(firstNameText, lastNameText);
        HorizontalLayout rowThree = new HorizontalLayout(emailText);
        HorizontalLayout buttonRow = new HorizontalLayout(saveButton);
 
        
        //styling layouts
        formLayout.addClassNames("w3-display-container", "w3-animate-opacity", "w3-center");
        rowOne.addClassNames("w3-large", "w3-animate-top");
        rowTwo.addClassNames("w3-large", "w3-animate-top");
        rowThree.addClassNames("w3-large", "w3-animate-top");
        buttonRow.addClassNames("w3-large", "w3-center", "w3-animate-top");
        
        
        //Styling the fields
        saveButton.addClassNames("w3-button", "w3-dark-grey");
        
        
        buttonRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        buttonRow.setWidthFull();
        accordion.add("Personal", profileComponents);

        //Component ordering
        formLayout.add(rowOne, rowTwo, rowThree, buttonRow);
        profileComponents.add(formLayout);
    }

    private void setMotoristProfile()
    {
        Div vehicleComponents = new Div();
        vehicleComponents.setSizeFull();
        accordion.add("Vehicles", vehicleComponents);
        
        Div memberShipComponents = new Div();
        accordion.add("Membership", memberShipComponents);
        
        
        Div paymentComponents = new Div();
        accordion.add("Billing", paymentComponents);
        
        
      
    }

    private void setMechanicProfile()
    {
        Div qualificationsComponents = new Div();
        accordion.add("Certificates", qualificationsComponents);
    }

    public void submitForm()
    {
        //args = (username, firstname, lastname, email, lnumber)
        profileController.saveProfileUpdates(usernameText.getValue(), 
                firstNameText.getValue(), lastNameText.getValue(), 
                emailText.getValue(), licenseText.getValue());
        
        loggedInUser.setFirstName(firstNameText.getValue());
        loggedInUser.setLastName(lastNameText.getValue());
        loggedInUser.setEmail(emailText.getValue());
        loggedInUser.setLicenseNum(Integer.parseInt(licenseText.getValue()));
        
    }

    private void setFieldProperties()
    {
        firstNameText.setRequired(true);
        lastNameText.setRequired(true);
        emailText.setRequired(true);
        licenseText.setRequired(true);
        
        usernameText.setEnabled(false);
    }
    
    private void fillFieldProperties() 
    {
        //If user not set, i.e. session has been restarted, this is not accesible
        if(loggedInUser == null)
        {
            MainLayout.reload();
            MainLayout.displayInformationPrompt("Session Expired! Please Login again...");
            getUI().ifPresent(ui-> ui.getPage().executeJavaScript("window.location.href = 'login' "));
            return;
        }
        
        usernameText.setValue(loggedInUser.getUsername());
        firstNameText.setValue(loggedInUser.getFirstName());
        lastNameText.setValue(loggedInUser.getLastName());
        emailText.setValue(loggedInUser.getEmail());
        licenseText.setValue(loggedInUser.getLicenseNum().toString());

       
        
        
       
      
      
  
    }
    
    private void setEventListeners()
    {
        saveButton.addClickListener(e->submitForm());
        
    }
    

}
