package Mijdas.RoadApp.spring.Views.Requests;

import Mijdas.RoadApp.spring.Controllers.RegoController;
import Mijdas.RoadApp.spring.Models.RequestModels.RequestService;
import Mijdas.RoadApp.spring.Models.RequestModels.Requests;
import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "requestsMotorist", layout = MainLayout.class)
@PageTitle("Requests")

public class MotoristRequestsView extends Div {
    private RegoController regoController = new RegoController();
    private RequestService service = RequestService.getInstance();
    Grid<Requests> grid = new Grid<>(Requests.class);
    TextField searchField = new TextField();
    public MotoristRequestsView()
    {
       Button searchButton = new Button("Search", clickEvent->{updateList(searchField.getValue());});
       searchField.setPlaceholder("Street Name, City, or Postcode..");
       searchField.setWidth("260px"); //fit in placeholder text
       add(searchField);
       add(searchButton);
       setSizeFull();
       grid.setHeightFull();
       grid.setColumns("nearestAddress","details");
       
       
       
       add(grid);
       updateList();
       
       //This line below breaks the view:
       grid.addComponentColumn(car -> new Label(regoController.getRego("5225").getModel())).setHeader("Car Model");
//       grid.addComponentColumn(button -> new Button("Message",clickEvent ->{System.out.println("Message");})).setHeader("Actions");
        grid.addComponentColumn(button -> new Button("Message",clickEvent ->{System.out.println("Message");})).setHeader("Actions");
    }
    
    private void updateList(){
        grid.setItems(service.findAll());
    }
    private void updateList(String s){
        grid.setItems(service.findAllPostCode(s));
    }
}

