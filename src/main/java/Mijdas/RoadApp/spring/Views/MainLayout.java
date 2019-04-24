/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mijdas.RoadApp.spring.Views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;



@StyleSheet("frontend://styles/common-styles.css")
@StyleSheet("frontend://styles/navbar.css")
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainLayout extends Div implements RouterLayout
{
    private Div navigation;
    private Div header;
    
    public MainLayout()
    {
        //Root divs
        navigation  = new Div();
        header      = new Div();
        
        //Common Layout
        setSizeFull();
        setId("main-layout"); //Common layout themes
        navigation.setId("navbar");
        
        
        
        //Home page
        RouterLink home  = new RouterLink(null,HomeView.class);
        home.add(new Icon(VaadinIcon.CAR), new Text("Home"));
        home.setClassName("navElement");
   
        //Login Page
        RouterLink login = new RouterLink(null,LoginView.class);
        login.add(new Icon(VaadinIcon.USER), new Text("Login"));
        login.setClassName("navElement");
        
        //Register Page
        RouterLink register = new RouterLink(null,RegisterView.class);
        register.add(new Icon(VaadinIcon.USERS),new Text("Register"));
        register.addClassName("navElement");
        
        navigation.add(home,login,register);
        header.add(navigation);

        add(header);
    }
    
}
