
package Mijdas.RoadApp.spring.Views.Requests;

import Mijdas.RoadApp.spring.Models.RequestModels.RequestService;
import Mijdas.RoadApp.spring.Models.RequestModels.Requests;
import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "requests", layout = MainLayout.class)
@PageTitle("Requests")

public class RequestsView extends Div
{
    private RequestService service = RequestService.getInstance();
    Grid<Requests> grid = new Grid<>(Requests.class);
    public RequestsView()
    {

       grid.setColumns("motoristUsername","nearestAddress","details");
       add(grid);
       setSizeFull();
       updateList();
    }
    public void updateList(){
        grid.setItems(service.findAll());
    }
}

