
package Mijdas.RoadApp.spring.Views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value="register", layout=MainLayout.class)
@PageTitle("Register")

public class RegisterView extends Div
{
    public RegisterView()
    {
        setText("Register");
    }
    
}
