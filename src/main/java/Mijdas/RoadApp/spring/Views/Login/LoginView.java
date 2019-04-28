/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

@Route(value="login", layout=MainLayout.class)
@StyleSheet("frontend://styles/loginForm.css")
@PageTitle("Login")
public class LoginView extends Div
{
    LoginForm form; 
    public LoginView()
    {
        form = new LoginForm();
        form.addLoginListener(e -> handleLogin(e));
        
        Div formLayout = new Div(form);
        formLayout.setId("loginForm");
        add(formLayout);
    }
    
    public void handleLogin(LoginEvent  e)
    {
        if(!LoginController.checkLogin(e.getUsername(),e.getPassword()))
        {
            System.out.println("Incorrect");
        }
        else
        {
            System.out.println("correct");
        }
    }
}
