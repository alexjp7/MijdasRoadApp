/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mijdas.RoadApp.spring.Views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.RouterLink;
import java.util.ArrayList;




@StyleSheet("frontend://styles/navbar.css")
public class Navbar extends Div
{

    
    public Navbar(ArrayList<RouterLink> links)
    {
        setId("navbar");
        setSizeFull();
      
        //Home page
        links.get(0).add(new Icon(VaadinIcon.CAR), new Text("Home"));
        links.get(0).setClassName("navElement");
   
        //Login Page
        links.get(1).add(new Icon(VaadinIcon.USER), new Text("Login"));
        links.get(1).setClassName("navElement");
        
        //Register Page
        links.get(2).add(new Icon(VaadinIcon.USERS),new Text("Register"));
        links.get(2).addClassName("navElement");
        
        
        links.forEach((type) -> add(type));
     

    }
 
    
}
