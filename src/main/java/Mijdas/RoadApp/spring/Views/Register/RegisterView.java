
package Mijdas.RoadApp.spring.Views.Register;

import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="register", layout=MainLayout.class)
@PageTitle("Register")
public class RegisterView extends Div
{
    public RegisterView()
    {
        RegisterForm form = new RegisterForm();
        form.setId("registerForm");
        add(form);
    }
}
