package Mijdas.RoadApp.spring.Views.Profile;

import Mijdas.RoadApp.spring.Controllers.ProfileController;
import Mijdas.RoadApp.spring.Controllers.SessionController;
import Mijdas.RoadApp.spring.Models.UserModels.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

//basic CSS form for now
@StyleSheet("frontend://styles/RegisterForm.css")
        
class ProfileForm extends FormLayout
{
    private ProfileController profileController;
    private User loggedInUser = SessionController.getInstance().getUser();
    
    //Used Fields
    private TextField usernameText = new TextField("Username");
    private TextField firstNameText = new TextField("First Name");
    private TextField lastNameText = new TextField("Last Name");
    private TextField emailText = new TextField("Email");
    private TextField licenseText = new TextField("License Number");
    
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
        setFormLayout();
        fillFieldProperties();
        setEventListeners();
    }
    
    private void setFormLayout()
    {
        VerticalLayout formLayout = new VerticalLayout();
        HorizontalLayout rowOne = new HorizontalLayout(usernameText, licenseText);
        HorizontalLayout rowTwo = new HorizontalLayout(firstNameText, lastNameText);
        HorizontalLayout rowThree = new HorizontalLayout(emailText);
        HorizontalLayout buttonRow = new HorizontalLayout(saveButton);

        buttonRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        buttonRow.setWidthFull();
        
        //Styling the fields
        saveButton.addClassNames("w3-button", "w3-dark-grey");
        
        
        //styling layouts
        formLayout.addClassNames("w3-display-container", "w3-animate-opacity");
        rowOne.addClassNames("w3-large", "w3-animate-top");
        rowTwo.addClassNames("w3-large", "w3-animate-top");
        rowThree.addClassNames("w3-large", "w3-animate-top");
        buttonRow.addClassNames("w3-large", "w3-center", "w3-animate-top");
        
        

        //Component ordering
        formLayout.add(rowOne, rowTwo, rowThree, buttonRow);
        add(formLayout);
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
