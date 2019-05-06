
package Mijdas.RoadApp.spring.Views.Login;

//Local Imports
import Mijdas.RoadApp.spring.Controllers.SessionController;
import Mijdas.RoadApp.spring.Views.MainLayout;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.AbstractLogin.LoginEvent;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;

import com.vaadin.flow.router.Route;

@Route(value="login", layout=MainLayout.class)
@StyleSheet("frontend://styles/loginForm.css")
@PageTitle("Login")

public class LoginView extends Div
{
    private static LoginForm form;
    public LoginView()
    {
        form = new LoginForm();
        form.addLoginListener(e ->handleLogin(e));

        Div formLayout = new Div(form);
        formLayout.setId("loginForm");
        add(formLayout);
    }

    /**************************************
     * Handles when user attempts to login
     **************************************/
    public void handleLogin(LoginEvent  e)
    {
        if(!SessionController.getInstance().checkLogin(e.getUsername(),e.getPassword()))
        {
            refreshLogin();
            //Notification.show("Incorrect Username or Password",3000,Notification.Position.TOP_CENTER);
        }
        else
        {   //Re-direct user to home page
            getUI().ifPresent(ui-> ui.getPage().executeJavaScript("window.location.href = '' "));
        }
    }

    public static void refreshLogin()
    {
        form.setEnabled(true);
    }
}
