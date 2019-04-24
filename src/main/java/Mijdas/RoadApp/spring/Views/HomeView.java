/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mijdas.RoadApp.spring.Views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *
 * @author Alex
 */
@Route(value="", layout=MainLayout.class)
@PageTitle("Home")
public class HomeView extends Div
{
    public HomeView()
    {
        setText("Home page");
    }
    
}
