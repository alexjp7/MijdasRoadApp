
package Mijdas.RoadApp.spring.Views.Login;

//Local Imports
import Mijdas.RoadApp.spring.Controllers.LoginController;
import Mijdas.RoadApp.spring.Views.MainLayout;


import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.AbstractLogin.LoginEvent;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.router.PageTitle;

import com.vaadin.flow.router.Route;
import java.util.logging.Level;
import java.util.logging.Logger;

@Route(value="login", layout=MainLayout.class)
@StyleSheet("frontend://styles/loginForm.css")
@PageTitle("Login")
public class LoginView extends Div  
{
    private static LoginForm form; 
    public LoginView()
    {
        form = new LoginForm();
        form.addLoginListener(e -> {
            try {
                handleLogin(e);
            } catch (InterruptedException ex) {
                Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Div formLayout = new Div(form);
        formLayout.setId("loginForm");
        add(formLayout);
    }
    
    public void handleLogin(LoginEvent  e) throws InterruptedException
    {
        if(!LoginController.getInstance().checkLogin(e.getUsername(),e.getPassword()))
        {
            /*****************************
            TODO:
             * add error 
            * checking/login attempts 
            *****************************/ 
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
