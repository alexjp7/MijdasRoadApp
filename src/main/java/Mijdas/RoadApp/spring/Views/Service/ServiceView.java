package Mijdas.RoadApp.spring.Views.Service;

import Mijdas.RoadApp.spring.Views.MainLayout;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
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
