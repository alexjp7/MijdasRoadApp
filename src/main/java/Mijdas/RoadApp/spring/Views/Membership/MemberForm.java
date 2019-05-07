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

<<<<<<< HEAD
@StyleSheet("frontend://styles/MembershipForm.css")
=======
@StyleSheet("frontend://styles/registerForm.css")
>>>>>>> cf521fc92a30fe210fa816fb1d24b458c63ca265
class MemberForm extends FormLayout
{
    private TextField registrationNumber = new TextField("Registration Number");
    private TextField licenseNumber = new TextField("License Number");
    private Select<String> creditCardType = new Select<>("VISA","MASTERCARD");
    private TextField creditCardName = new TextField("Name On Card");
    private TextField creditCardNumber = new TextField("Card Number");
    private TextField creditCardCVV = new TextField("Card CVV");
    private Button submitPayment = new Button("Submit Payment");
    private Button clear = new Button ("Clear");

    public void submitForm()
    {

    }

    private void setFieldProperties()
    {
        registrationNumber.setRequired(true);
        licenseNumber.setRequired(true);
        //creditCardType.setRequired(true);
        creditCardName.setRequired(true);
        creditCardNumber.setRequired(true);
        creditCardCVV.setRequired(true);
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
