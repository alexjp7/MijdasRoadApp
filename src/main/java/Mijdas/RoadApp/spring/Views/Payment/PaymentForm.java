package Mijdas.RoadApp.spring.Views.Payment;

//import Mijdas.RoadApp.spring.Views.Payment.*;
import Mijdas.RoadApp.spring.Controllers.MembershipController;
import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

@StyleSheet("frontend://styles/MembershipForm.css")
class PaymentForm extends FormLayout
{
    private MembershipController membershipController;

    private TextField registrationNumber = new TextField("Vehicle Registration");
    private TextField licenseNumber = new TextField("License Number");
    private TextField manufacturer = new TextField("Manufacturer");
    private TextField model = new TextField("Model");
    private TextField color = new TextField("Color");
    private Select<String> creditCardType = new Select<>("VISA","MASTERCARD");
    private TextField creditCardName = new TextField("Name On Card");
    private TextField creditCardNumber = new TextField("Card Number");
    private TextField creditCardCVV = new TextField("Card CVV");
    private Button submitPayment = new Button("Submit Payment");
    private Button clear = new Button ("Clear");

    public PaymentForm()
    {
        membershipController = new MembershipController();
        setFieldProperties();
        setEventListeners();
        setFormLayout();
    }

    private void setFieldProperties()
    {
        registrationNumber.setPlaceholder("xxxxxx");
        licenseNumber.setPlaceholder("xxxxxxxx");
        manufacturer.setPlaceholder("Subaru");
        model.setPlaceholder("2002 WRX Impreza");
        color.setPlaceholder("Subaru Blue");
        creditCardType.setLabel("Card Type");
        creditCardType.setPlaceholder("VISA");
        creditCardName.setSizeFull();
        creditCardName.setPlaceholder("John Smith");
        creditCardNumber.setSizeFull();
        creditCardNumber.setPlaceholder("xxxx xxxx xxxx xxxx");
        creditCardCVV.setPlaceholder("xxx");
        submitPayment.setId("formButton");
        clear.setId("formButton");

        registrationNumber.setRequired(true);
        licenseNumber.setRequired(true);
        creditCardName.setRequired(true);
        creditCardNumber.setRequired(true);
        creditCardCVV.setRequired(true);
    }

    private void setEventListeners()
    {
        submitPayment.addClickListener(e->submitForm());
        clear.addClickListener(e->clearForm());
    }

    private void setFormLayout()
    {
        VerticalLayout formLayout = new VerticalLayout();
        HorizontalLayout layerFive = new HorizontalLayout(creditCardName);
        HorizontalLayout layerSix = new HorizontalLayout(creditCardNumber);
        HorizontalLayout layerSeven = new HorizontalLayout(creditCardCVV, creditCardType);
        HorizontalLayout buttonGroup = new HorizontalLayout(submitPayment, clear);

        buttonGroup.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        buttonGroup.setWidthFull();

        formLayout.add(layerFive, layerSix, layerSeven, buttonGroup);
        add(formLayout);
    }

    public void submitForm()
    {
        /*if(membershipController.submitMemberShipPayment(registrationNumber.getValue(), licenseNumber.getValue(), manufacturer.getValue(), model.getValue(), color.getValue(), creditCardType.getValue(), creditCardName.getValue(), creditCardNumber.getValue(), creditCardCVV.getValue()))
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
        registrationNumber.clear();
        licenseNumber.clear();
        creditCardName.clear();
        creditCardNumber.clear();
        creditCardCVV.clear();
    }
}
