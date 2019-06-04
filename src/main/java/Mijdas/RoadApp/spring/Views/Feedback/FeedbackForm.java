/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mijdas.RoadApp.spring.Views.Feedback;

import Mijdas.RoadApp.spring.Controllers.DBQueryProcessor;
import Mijdas.RoadApp.spring.Controllers.FeedbackController;
import Mijdas.RoadApp.spring.Controllers.ProfileController;
import Mijdas.RoadApp.spring.Controllers.SessionController;
import Mijdas.RoadApp.spring.Controllers.UserType;
import Mijdas.RoadApp.spring.Models.UserModels.Mechanic;
import Mijdas.RoadApp.spring.Models.UserModels.User;
import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout.Orientation;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.data.renderer.IconRenderer;
import com.vaadin.ui.Alignment;
import java.util.ArrayList;

/**
 *
 * @author Mitch
 */

@StyleSheet("frontend://styles/Feedback.css")
class FeedbackForm extends Div{
    
    private FeedbackController feedbackController;
    
    //Used Fields+Buttons
    private RadioButtonGroup<String> ratingButtons = new RadioButtonGroup<>();
    private ComboBox<String> mechanicList = new ComboBox<>("Mechanic Name");
    private ComboBox cbox = new ComboBox();
    private TextArea feedbackMsgField = new TextArea("Leave some feedback here!");
    private Button submitButton = new Button("Submit");
    
    public FeedbackForm()
    {
        feedbackController = new FeedbackController();
        setFormLayout();
        setEventListeners();
    }
    
    private void setFormLayout()
    {
        //Layout
        VerticalLayout mainLayout = new VerticalLayout();
        SplitLayout topLayout = new SplitLayout();
        SplitLayout middleLayout = new SplitLayout();
        SplitLayout bottomLayout = new SplitLayout();
        
        //Styling
        topLayout.addClassNames("w3-cell-middle", "w3-display-container", "w3-animate-opacity", "w3-center");
        middleLayout.addClassNames("w3-cell-middle", "w3-display-container", "w3-animate-opacity", "w3-center");
        bottomLayout.addClassNames("w3-cell-middle", "w3-display-container", "w3-animate-opacity", "w3-center");
        ratingButtons.addClassNames("w3-large", "w3-animate-top", "w3-center", "w3-cell-middle");
        feedbackMsgField.addClassNames("w3-large", "w3-animate-top");
        mechanicList.addClassNames("w3-large", "w3-animate-top");
        submitButton.addClassNames("w3-button", "w3-center", "w3-dark-grey");
        
        //Rating Buttons
        ratingButtons.setItems("1", "2", "3", "4", "5");
        ratingButtons.setRenderer(new IconRenderer<>(i -> {
            Icon ico = new Icon(VaadinIcon.STAR);
            ico.setColor("orange");
            ico.setSize("20px");
            ico.getStyle().set("float", "left");
            ico.getStyle().set("marginRight", "1px");
            ico.getStyle().set("marginTop", "4px");
            return ico;
        }));
        ratingButtons.setRequired(true);
        
        //Mechanic List + Feedback
        mechanicList.setItems(feedbackController.getMechanicNames());
        mechanicList.setPlaceholder("Start Typing To Filter..");
        mechanicList.setRequired(true);
        feedbackMsgField.setPlaceholder("(Optional)");
        
        //Set BottomLayout
        bottomLayout.setOrientation(Orientation.VERTICAL);
        bottomLayout.addToPrimary(feedbackMsgField);
        bottomLayout.addToSecondary(submitButton);
        bottomLayout.setPrimaryStyle("minHeight", "125px");
        bottomLayout.setPrimaryStyle("maxHeight", "125px");
        bottomLayout.setSecondaryStyle("minHeight", "45px");
        bottomLayout.setSecondaryStyle("maxHeight", "45px");
        
        //Set MiddleLayout
        middleLayout.setOrientation(Orientation.VERTICAL);
        middleLayout.addToPrimary(ratingButtons);
        middleLayout.addToSecondary(bottomLayout);
        middleLayout.setPrimaryStyle("minHeight", "40px");
        middleLayout.setPrimaryStyle("maxHeight", "40px");
        middleLayout.setSecondaryStyle("minHeight", "220px");
        middleLayout.setSecondaryStyle("maxHeight", "220px");
        
        //Set TopLayout
        topLayout.setOrientation(Orientation.VERTICAL);
        topLayout.addToPrimary(mechanicList);
        topLayout.addToSecondary(middleLayout);
        topLayout.setPrimaryStyle("minHeight", "85px");
        topLayout.setPrimaryStyle("maxHeight", "85px");
        topLayout.setSecondaryStyle("minHeight", "280px");
        topLayout.setSecondaryStyle("maxHeight", "280px");
        
        //Set MainLayout
        mainLayout.setSizeFull();
        mainLayout.add(topLayout);
        mainLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        
        //End
        add(mainLayout);
    }
    
    private void setEventListeners()
    {
        submitButton.addClickListener(e->submitFeedback());
    }
    
    public void submitFeedback()
    {
        //args = (mechanic username, mesage text, rating)
        if(mechanicList.getValue() == null && ratingButtons.getValue() == null){
            MainLayout.displayInformationPrompt("Missing Information! You Must Include Both Mechanic Name AND Desired Rating.");
        } else if(mechanicList.getValue() == null){
            MainLayout.displayInformationPrompt("Missing Information! You Must Include Mechanic Name.");
        } else if(ratingButtons.getValue() == null){
            MainLayout.displayInformationPrompt("Missing Information! You Must Include Desired Rating.");
        } else {
            feedbackController.submitFeedback(mechanicList.getValue(), feedbackMsgField.getValue(), Integer.parseInt(ratingButtons.getValue()));
            feedbackController.updateMechanicStars(mechanicList.getValue());
            MainLayout.displayInformationPrompt("Review Submitted! Thanks For Your feedback!");
        }
    }
}
