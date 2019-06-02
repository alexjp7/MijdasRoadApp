package Mijdas.RoadApp.spring.Views.Service;

import Mijdas.RoadApp.spring.Controllers.ServiceProcessor;
import Mijdas.RoadApp.spring.Controllers.SessionController;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.Text;
/**
 *
 * @author Dyl
 */
public class ServiceForm extends FormLayout {

    private Text label = new Text("Create a service request");
    private TextField username = new TextField("Username");
    private TextField address = new TextField("Address");
    private TextArea details = new TextArea("Details");
    private Button submit = new Button("Submit");
    private ServiceProcessor serviceProcessor = new ServiceProcessor();
    
    public ServiceForm() {
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
        
        VerticalLayout formLayout = new VerticalLayout();

        //Component ordering
        formLayout.add(label, username, address, details, submit);
        add(formLayout);
    }
    
    public void setEventListeners() {
        submit.addClickListener(e -> submitForm() );
    }    
    
    public void submitForm() {
        serviceProcessor.submitService(username.getValue(), address.getValue(), details.getValue());
        username.clear();
        address.clear();
        details.clear();
    }
}
