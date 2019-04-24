/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mijdas.RoadApp.spring.Views;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.router.PageTitle;

import com.vaadin.flow.router.Route;

@Route(value="login", layout=MainLayout.class)
@StyleSheet("frontend://styles/loginForm.css")
@PageTitle("Login")
public class LoginView extends Div
{
    public LoginView()
    {
        LoginForm form = new LoginForm();
        
        Div formLayout = new Div(form);
        formLayout.setId("loginForm");
        add(formLayout);
    }

    
}
