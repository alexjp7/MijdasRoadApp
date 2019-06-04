package Mijdas.RoadApp.spring.Views.Service;

import Mijdas.RoadApp.spring.Controllers.DBQueryProcessor;
import Mijdas.RoadApp.spring.Controllers.ServiceProcessor;
import Mijdas.RoadApp.spring.Controllers.SessionController;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import java.util.ArrayList;

@StyleSheet("frontend://styles/service.css")
/**
 *
 * @author Dyl
 */
public class ServiceForm extends FormLayout {

    private Span label = new Span("Create a service request");
    private TextField username = new TextField("Username");
    private TextField address = new TextField("Address");
    private TextArea details = new TextArea("Details");
    private RadioButtonGroup<String> radios = new RadioButtonGroup<>();
    private String radioValue;
    private Button submit = new Button("Submit");
    private ServiceProcessor serviceProcessor = new ServiceProcessor();
    private Div div = new Div();
    private VerticalLayout formLayout = new VerticalLayout();
    
    private DBQueryProcessor db = DBQueryProcessor.getInstance();
    boolean hasMembership;
    boolean paymentFormAdded;
    private HorizontalLayout hPay = new HorizontalLayout();
    private HorizontalLayout hPay2 = new HorizontalLayout();
    private TextField cardNumber = new TextField("Card Number");
    private TextField cardExpiry = new TextField("Expiry");
    private TextField cardCVV = new TextField("CVV");
    private HorizontalLayout hOne = new HorizontalLayout();
    private HorizontalLayout hTwo = new HorizontalLayout();
    private HorizontalLayout hThree = new HorizontalLayout();
    private HorizontalLayout hFour = new HorizontalLayout();
    private HorizontalLayout hFive = new HorizontalLayout();
    private HorizontalLayout hSix = new HorizontalLayout();
    private VerticalLayout payment = new VerticalLayout();
    private Span error = new Span();
    
    public ServiceForm() {
        hasMembership = true;
        paymentFormAdded = false;
        setFormLayout();
        setEventListeners();
    }
    public void setFormLayout() {
        details.setMaxLength(120);
        
        try {
            String user = SessionController.getInstance().getUser().getUsername();
            username.setValue(user);
            username.setEnabled(false);
        } catch(Exception e) { }

//      div.addClassNames("w3-display-container", "w3-center");
//      div.setSizeFull("100%");
        div.setWidth("100%");
        div.addClassNames("content-center");
        formLayout.addClassNames("form-center");
        label.addClassNames("label");

        radioValue = "";
        radios.setItems("Membership", "Pay now");
        radios.setItemEnabledProvider(item -> (!item.equals("Membership") || (item.equals("Membership") && hasMembership) ));
        radios.setLabel("Payment Method");
        radios.addValueChangeListener(event -> {
            radioValue = event.getValue();
            if(radioValue.equals("Pay now") && !paymentFormAdded) {
                hPay.add(cardNumber);
                hPay2.add(cardExpiry, cardCVV);
                payment.add(hPay, hPay2);
                paymentFormAdded = true;
            }
        });
          
        hOne.add(label);
        hTwo.add(username);
        hThree.add(address);
        hFour.add(details);
        payment.add(radios);
        hFive.add(payment);
        hSix.add(submit);
        //Component ordering
        formLayout.add(hOne, hTwo, hThree, hFour, hFive, hSix);
        div.add(formLayout);
        add(div);
    }
    
    public void setEventListeners() {
        submit.addClickListener(e -> submitForm() );
    }    
    
    public void submitForm() {
        if(radioValue.equals("")) {
            error.setText("Please select a payment method");
            HorizontalLayout hError = new HorizontalLayout();
            hError.add(error);
            formLayout.add(hError);
        } else if(radioValue.equals("Membership")) {
            serviceProcessor.submitService(username.getValue(), address.getValue(), details.getValue());
            clearForm();
            error.setText("");
        } else if(radioValue.equals("Pay now")) {
            if(!cardNumber.getValue().equals("") && !cardExpiry.getValue().equals("") && !cardCVV.getValue().equals("")) {
                serviceProcessor.submitService(username.getValue(), address.getValue(), details.getValue());
                clearForm();
                error.setText("");
            } else {
                error.setText("Please fill out payment form");
            }
        }
    }
    
    public void clearForm() {
        username.clear();
        address.clear();
        details.clear();
        cardNumber.clear();
        cardExpiry.clear();
        cardCVV.clear();
    }
    
    public void setPaymentFormAdded(boolean value) {
        paymentFormAdded = value;
    }
}
