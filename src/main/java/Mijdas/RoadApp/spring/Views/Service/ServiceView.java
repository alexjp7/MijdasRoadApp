package Mijdas.RoadApp.spring.Views.Service;

import Mijdas.RoadApp.spring.Views.MainLayout;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="service", layout=MainLayout.class)
@StyleSheet("frontend://styles/common-styles.css")
@PageTitle("Service")

/**
 *
 * @author Dyl
 */
public class ServiceView extends Div {
    
    public ServiceView() {
        setSizeFull();
        ServiceForm form = new ServiceForm();
        form.setWidth("100%");
        form.setId("registerForm");
        add(form);
    }
    
}
