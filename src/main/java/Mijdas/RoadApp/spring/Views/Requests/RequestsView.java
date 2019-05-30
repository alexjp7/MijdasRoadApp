
package Mijdas.RoadApp.spring.Views.Requests;

import Mijdas.RoadApp.spring.Models.RequestModels.RequestService;
import Mijdas.RoadApp.spring.Models.RequestModels.Requests;
import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "requests", layout = MainLayout.class)
@PageTitle("Requests")

public class RequestsView extends Div
{
    private RequestService service = RequestService.getInstance();
    Grid<Requests> grid = new Grid<>(Requests.class);
    TextField searchField = new TextField();
    public RequestsView()
    {
       Button searchButton = new Button("Search", clickEvent->{updateList(searchField.getValue());});
       searchField.setPlaceholder("Street name, city, or postcode");
       add(searchField);
       add(searchButton);
       
       grid.setColumns("motoristUsername","nearestAddress","details");
       add(grid);
       setSizeFull();
       updateList();
       grid.addComponentColumn(button -> new Button("Message",clickEvent ->{System.out.println("Message");})).setHeader("Actions");
    }
    
    private void updateList(){
        grid.setItems(service.findAll());
    }
    private void updateList(String s){
        grid.setItems(service.findAllPostCode(s));
    }
}

