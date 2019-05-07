package Mijdas.RoadApp.spring.Views.Service;

import Mijdas.RoadApp.spring.Controllers.SessionController;
import Mijdas.RoadApp.spring.Views.MainLayout;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.AbstractLogin.LoginEvent;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="service", layout=MainLayout.class)
@StyleSheet("frontend://styles/registerForm.css")
@PageTitle("Service")

/**
 *
 * @author Dyl
 */
public class ServiceView extends Div {
    
    public ServiceView() {
        ServiceForm form = new ServiceForm();
        form.setId("registerForm");
        add(form);
    }
    
}
