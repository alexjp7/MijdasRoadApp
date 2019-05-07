package Mijdas.RoadApp.spring.Views.Membership;

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
class MemberForm extends FormLayout
{
    private MembershipController membershipController;

    private TextField registrationNumber = new TextField("Registration Number");
    private TextField licenseNumber = new TextField("License Number");
    private Select<String> creditCardType = new Select<>("VISA","MASTERCARD");
    private TextField creditCardName = new TextField("Name On Card");
    private TextField creditCardNumber = new TextField("Card Number");
    private TextField creditCardCVV = new TextField("Card CVV");
    private Button submitPayment = new Button("Submit Payment");
    private Button clear = new Button ("Clear");

    public MemberForm()
    {
        membershipController = new MembershipController();

        setFieldProperties();
        setEventListeners();
        setFormLayout();
        formFilled();
    }

    private void setFieldProperties()
    {
        creditCardType.setLabel("Card Type");
        creditCardType.setPlaceholder("VISA");
        creditCardName.setSizeFull();
        creditCardNumber.setSizeFull();
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
        HorizontalLayout layerOne = new HorizontalLayout(registrationNumber, licenseNumber);
        HorizontalLayout layerTwo = new HorizontalLayout(creditCardName);
        HorizontalLayout layerThree = new HorizontalLayout(creditCardNumber);
        HorizontalLayout layerFour = new HorizontalLayout(creditCardCVV, creditCardType);
        HorizontalLayout buttonGroup = new HorizontalLayout(submitPayment, clear);

        buttonGroup.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        buttonGroup.setWidthFull();

        formLayout.add(layerOne, layerTwo, layerThree, layerFour, buttonGroup);
        add(formLayout);
    }

    private void formFilled()
    {
        if(registrationNumber.isEmpty() && licenseNumber.isEmpty() && creditCardType.isEmpty() && creditCardName.isEmpty() && creditCardNumber.isEmpty() && creditCardCVV.isEmpty())
        {
            submitPayment.setEnabled(true);
        }
    }

    public void submitForm()
    {
        /*if(membershipController.submitMemberShipPayment(registrationNumber.getValue(), licenseNumber.getValue(), creditCardType.getValue(), creditCardName.getValue(), creditCardNumber.getValue(), creditCardCVV.getValue()))
        {
            getUI().ifPresent(ui-> ui.getPage().executeJavaScript("window.location.href = '' "));
        }
        else
        {
            MainLayout.displayInformationPrompt("Fields are empty!");
        }*/
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
