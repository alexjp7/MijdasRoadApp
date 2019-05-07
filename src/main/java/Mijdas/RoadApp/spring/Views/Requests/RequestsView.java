
package Mijdas.RoadApp.spring.Views.Requests;

import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "requests", layout = MainLayout.class)
@PageTitle("Requests")

public class RequestsView extends Div
{
    public RequestsView()
    {
       Grid grid = new Grid();
       
       
       
       
    }
}

