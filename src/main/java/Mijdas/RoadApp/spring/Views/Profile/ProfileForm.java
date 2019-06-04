package Mijdas.RoadApp.spring.Views.Profile;

import Mijdas.RoadApp.spring.Controllers.MembershipController;
import Mijdas.RoadApp.spring.Controllers.ProfileController;
import Mijdas.RoadApp.spring.Controllers.RegoController;
import Mijdas.RoadApp.spring.Controllers.SessionController;
import Mijdas.RoadApp.spring.Controllers.UserType;
import Mijdas.RoadApp.spring.Models.UserModels.User;
import Mijdas.RoadApp.spring.Models.UserModels.Vehicle;
import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import java.util.ArrayList;

//basic CSS form for now
@StyleSheet("frontend://styles/Profile.css")
class ProfileForm extends Div
{
    private ProfileController profileController;
    private MembershipController membershipController;
    private RegoController regoController;
    private Vehicle vehicle;
    private User loggedInUser = SessionController.getInstance().getUser();
    
    //Used Fields
    //Fields for personal
    private TextField usernameText = new TextField("Username");
    private TextField firstNameText = new TextField("First Name");
    private TextField lastNameText = new TextField("Last Name");
    private TextField emailText = new TextField("Email");
    private TextField licenseText = new TextField("License Number");
    
    //Fields for Vehicle
    private TextField registrationNumber = new TextField("Vehicle Registration");
    private TextField licenseNumber = new TextField("License Number");
    private TextField manufacturer = new TextField("Manufacturer");
    private TextField model = new TextField("Model");
    private TextField color = new TextField("Color");
    
    //Fields for Membership
    //private TextField memberRego = new TextField("Current Membership Active on:");
    private Select<String> vehicleType = new Select<>("\"Current Membership Active on:\"");
    
    //Accordion
    private Accordion accordion = new Accordion();
    
    //Mechanic Fields
//    private TextField qualityText = new TextField("Quality Rating");
    
    //Motorist Fields
    private TextField membershipStatusText = new TextField("Membership Status");
    
    //Buttons
    private Button savePersonalButton = new Button("Save");
    private Button updateVehicleButton = new Button("Update");
    //private Button addVehicleButton = new Button("Add");
    private Button refreshButton = new Button("Refresh");
    private Button cancelMembership = new Button("Cancel");
    
//  private Button revertButton = new Button ("Revert");
    
    public ProfileForm()
    {
        membershipController = new MembershipController();
        profileController = new ProfileController();
        regoController = new RegoController();
        vehicle = new Vehicle("", "", "", "", "");
        vehicle = regoController.getRego(loggedInUser.getLicenseNum().toString());
        setFieldProperties();
        setSizeFull();
        setFormLayout();
//      accordion.setSizeFull();
        accordion.setId("accordion");
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
        HorizontalLayout buttonRow = new HorizontalLayout(savePersonalButton);
 
        //styling layouts
        formLayout.addClassNames("w3-display-container", "w3-animate-opacity", "w3-center");
        rowOne.addClassNames("w3-large", "w3-animate-top");
        rowTwo.addClassNames("w3-large", "w3-animate-top");
        rowThree.addClassNames("w3-large", "w3-animate-top");
        buttonRow.addClassNames("w3-large", "w3-center", "w3-animate-top");
        
        //Styling the fields
        savePersonalButton.addClassNames("w3-button", "w3-dark-grey");
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
        VerticalLayout formLayout = new VerticalLayout();
        
        HorizontalLayout layerOne = new HorizontalLayout(registrationNumber, licenseNumber);
        HorizontalLayout layerTwo = new HorizontalLayout(manufacturer, model);   
        HorizontalLayout layerThree = new HorizontalLayout(color);
        HorizontalLayout buttonRow = new HorizontalLayout(updateVehicleButton);
        
        formLayout.addClassNames("w3-display-container", "w3-animate-opacity", "w3-center");
        layerOne.addClassNames("w3-large", "w3-animate-top");
        layerTwo.addClassNames("w3-large", "w3-animate-top");
        layerThree.addClassNames("w3-large", "w3-animate-top");
        buttonRow.addClassNames("w3-large", "w3-center", "w3-animate-top");
        
        //Styling the fields
        updateVehicleButton.addClassNames("w3-button", "w3-dark-grey");
        //addVehicleButton.addClassNames("w3-button", "w3-dark-grey");
        buttonRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        buttonRow.setWidthFull();

        //Component ordering
        formLayout.add(layerOne, layerTwo, layerThree, buttonRow);
        vehicleComponents.add(formLayout);
        
        vehicleComponents.setSizeFull();
        accordion.add("Vehicles", vehicleComponents);
        ////////////////////////////////////////////////////////////////////////////////////////////////
        ArrayList<Vehicle> vehicles = regoController.getRegoList(loggedInUser.getLicenseNum().toString());
        ArrayList<String> stringList = new ArrayList<String>();
        for (Vehicle vehicle1 : vehicles) 
        {
           stringList.add(vehicle1.toString());
        }    

        vehicleType.setItems(stringList);
        
        Div memberShipComponents = new Div();
        VerticalLayout formLayout1 = new VerticalLayout();
        
        HorizontalLayout layerOneMember = new HorizontalLayout(vehicleType);
        HorizontalLayout layerTwoMember = new HorizontalLayout(membershipStatusText);
        HorizontalLayout buttonRowMember = new HorizontalLayout(refreshButton, cancelMembership);
        
        formLayout1.addClassNames("w3-display-container", "w3-animate-opacity", "w3-center");
        layerOneMember.addClassNames("w3-large", "w3-animate-top");
        layerTwoMember.addClassNames("w3-large", "w3-animate-top");
        buttonRowMember.addClassNames("w3-large", "w3-animate-top");
        cancelMembership.addClassNames("w3-button", "w3-dark-grey");
        refreshButton.addClassNames("w3-button", "w3-dark-grey");
        
        buttonRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        buttonRow.setWidthFull();
        formLayout1.add(layerOneMember, layerTwoMember, buttonRowMember);
        memberShipComponents.add(formLayout1);
        memberShipComponents.setSizeFull();
        
        accordion.add("Membership", memberShipComponents);
        formLayout.add(layerOne, layerTwo, layerThree, buttonRow);
        ////////////////////////////////////////////////////////////////////////////////////////////////
        Div paymentComponents = new Div();
        accordion.add("Billing", paymentComponents);
    }

    private void setMechanicProfile()
    {
        Div qualificationsComponents = new Div();
        accordion.add("Certificates", qualificationsComponents);
    }

    public void submitPersonalForm()
    {
        //args = (username, firstname, lastname, email, lnumber)
        profileController.saveProfileUpdates(usernameText.getValue(), firstNameText.getValue(), lastNameText.getValue(), emailText.getValue(), licenseText.getValue());
        loggedInUser.setFirstName(firstNameText.getValue());
        loggedInUser.setLastName(lastNameText.getValue());
        loggedInUser.setEmail(emailText.getValue());
        loggedInUser.setLicenseNum(Integer.parseInt(licenseText.getValue())); 
    }
    
    public void submitVehicleForm()
    {
        regoController.saveVehicleUpdates(licenseNumber.getValue(), registrationNumber.getValue(), manufacturer.getValue(), model.getValue(), color.getValue());
        //registrationNumber.setValue(vehicle.getRegistration());
        //manufacturer.setValue(vehicle.getManufacterer());
        //model.setValue(vehicle.getModel());
        //color.setValue(vehicle.getColor());
        fillFieldProperties();
    }
    
    /*public void addVehicleForm()
    {
        regoController.saveVehicleInsert(licenseNumber.getValue(), registrationNumber.getValue(), manufacturer.getValue(), model.getValue(), color.getValue());
        //registrationNumber.setValue(vehicle.getRegistration());
        //manufacturer.setValue(vehicle.getManufacterer());
        //model.setValue(vehicle.getModel());
        //color.setValue(vehicle.getColor());
        //fillFieldProperties();
    }*/
    
    private void setFieldProperties()
    {
        //Personal
        firstNameText.setRequired(true);
        lastNameText.setRequired(true);
        emailText.setRequired(true);
        licenseText.setRequired(true);
        usernameText.setEnabled(false);
        
        //Vehicle
        registrationNumber.setRequired(true);
        manufacturer.setRequired(true);
        model.setRequired(true);
        color.setRequired(true);
        licenseNumber.setEnabled(false);
        
        //Membership
        membershipStatusText.setEnabled(true);
        vehicleType.setLabel("Choose A Vehicle");
        vehicleType.setPlaceholder("Vehicle...");
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
        else
        {
        //Personal Fill
        usernameText.setValue(loggedInUser.getUsername());
        firstNameText.setValue(loggedInUser.getFirstName());
        lastNameText.setValue(loggedInUser.getLastName());
        emailText.setValue(loggedInUser.getEmail());
        licenseText.setValue(loggedInUser.getLicenseNum().toString());
        }
        
        if(vehicle == null)
        {
            licenseNumber.setValue(loggedInUser.getLicenseNum().toString());    
            registrationNumber.setValue("");
            manufacturer.setValue("");
            model.setValue("");
            color.setValue("");
        }
        else
        {
        //Vehicle Fill
            vehicle = regoController.getRego(loggedInUser.getLicenseNum().toString());
            licenseNumber.setValue(loggedInUser.getLicenseNum().toString());
            registrationNumber.setValue(vehicle.getRegistration());
            manufacturer.setValue(vehicle.getManufacterer());
            model.setValue(vehicle.getModel());
            color.setValue(vehicle.getColor());
        }   
        //Membership
        membershipStatusText.setValue(vehicle.getMembership());
    }
    
    private void setEventListeners()
    {
        savePersonalButton.addClickListener(e->submitPersonalForm());
        updateVehicleButton.addClickListener(e->submitVehicleForm());
        //addVehicleButton.addClickListener(e->addVehicleForm());
        cancelMembership.addClickListener(e->cancelMembership());
        refreshButton.addClickListener(e->refreshMembership());
    }
    
    public void cancelMembership()
    {
        boolean member = false;
        membershipController.submitMemberShipPayment(vehicleType.getValue(), loggedInUser.getLicenseNum().toString(), member);
        membershipStatusText.setValue(regoController.getRegoRefresh(vehicleType.getValue()));
        MainLayout.displayInformationPrompt("Membership will not renew after expiration.");
    }
    
    public void refreshMembership()
    {
        membershipStatusText.setValue(regoController.getRegoRefresh(vehicleType.getValue()));
    }
}
