package Mijdas.RoadApp.spring.Views.Membership;

import Mijdas.RoadApp.spring.Controllers.MembershipController;
import Mijdas.RoadApp.spring.Controllers.RegoController;
import Mijdas.RoadApp.spring.Controllers.SessionController;
import Mijdas.RoadApp.spring.Models.UserModels.User;
import Mijdas.RoadApp.spring.Models.UserModels.Vehicle;
import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@StyleSheet("frontend://styles/MembershipForm.css")
class MemberForm extends FormLayout
{
    private RegoController regoController;
    private Vehicle vehicle;
    private MembershipController membershipController;
    private User loggedInUser = SessionController.getInstance().getUser();
    private Select<String> vehicleType = new Select<>("");
    private TextField licenseNumber = new TextField("License Number");
    private Select<String> creditCardType = new Select<>("VISA","MASTERCARD");
    private TextField creditCardName = new TextField("Name On Card");
    private TextField creditCardNumber = new TextField("Card Number");
    private TextField creditCardCVV = new TextField("Card CVV");
    private Button submitPayment = new Button("Submit Payment");
    private Button clear = new Button ("Clear");

    public MemberForm()
    {
        regoController = new RegoController();
        vehicle = new Vehicle("", "", "", "");
        vehicle = regoController.getRego(loggedInUser.getLicenseNum().toString());
        membershipController = new MembershipController();
        setFieldProperties();
        setEventListeners();
        setFormLayout();
    }

    private void setFieldProperties()
    {
        vehicleType.setLabel("Choose A Vehicle");
        vehicleType.setPlaceholder("Vehicle...");
        creditCardType.setLabel("Card Type");
        creditCardType.setPlaceholder("Card Type...");
        creditCardName.setSizeFull();
        creditCardName.setPlaceholder("John Smith");
        creditCardNumber.setSizeFull();
        creditCardNumber.setPlaceholder("xxxx xxxx xxxx xxxx");
        creditCardCVV.setPlaceholder("xxx");
        submitPayment.setId("formButton");
        clear.setId("formButton");

        licenseNumber.setEnabled(false);
        creditCardName.setRequired(true);
        creditCardNumber.setRequired(true);
        creditCardCVV.setRequired(true);
        licenseNumber.setValue(loggedInUser.getLicenseNum().toString());
    }

    private void setEventListeners()
    {
        submitPayment.addClickListener(e->submitForm());
        clear.addClickListener(e->clearForm());
    }

    private void setFormLayout()
    {
       /* ArrayList<Vehicle> vehicles = regoController.getRegoList(lNum);
        for (Vehicle vehicle1 : vehicles) 
        {
            System.out.println(vehicles);
        }    */

        vehicleType.setItems(vehicle.getRegistration());
        VerticalLayout formLayout = new VerticalLayout();
        HorizontalLayout layerOne = new HorizontalLayout(vehicleType, licenseNumber);
        HorizontalLayout layerTwo = new HorizontalLayout(creditCardName, creditCardNumber);
        HorizontalLayout layerThree = new HorizontalLayout(creditCardCVV, creditCardType);
        HorizontalLayout buttonGroup = new HorizontalLayout(submitPayment, clear);

        buttonGroup.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        buttonGroup.setWidthFull();

        formLayout.add(layerOne, layerTwo, layerThree, buttonGroup);
        add(formLayout);
    }

    public void submitForm()
    {
        /*if(membershipController.submitMemberShipPayment(vehicleType.getValue(), creditCardType.getValue(), creditCardName.getValue(), creditCardNumber.getValue(), creditCardCVV.getValue()))
        {
            getUI().ifPresent(ui-> ui.getPage().executeJavaScript("window.location.href = '' "));
        }
        else
        {
            MainLayout.displayInformationPrompt("Fields are empty!");
        }*/
        //membershipController.membershipForm(registrationNumber.getValue(), licenseNumber.getValue(), creditCardType.getValue(), creditCardName.getValue(), creditCardNumber.getValue(), creditCardCVV.getValue());
        //membershipController.vehicleForm(licenseNumber.getValue(), manufacturer.getValue(), model.getValue(), color.getValue());

        getUI().ifPresent(ui-> ui.getPage().executeJavaScript("window.location.href = '' "));
    }

    public void clearForm()
    {
        licenseNumber.clear();
        creditCardName.clear();
        creditCardNumber.clear();
        creditCardCVV.clear();
    }
}
