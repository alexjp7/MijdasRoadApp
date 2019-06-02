package Mijdas.RoadApp.spring.Views.Requests;

import Mijdas.RoadApp.spring.Controllers.ProfileController;
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
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@Route(value = "requestsMotorist", layout = MainLayout.class)
@PageTitle("Requests")

public class MotoristRequestsView extends Div {
    private RegoController regoController = new RegoController();
    private ProfileController profileController = new ProfileController();
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
        addActionColumn();
        //This line below breaks the view:

        //grid.addComponentColumn(button -> new Button("Cancel",clickEvent ->{System.out.println("Cancel");}));

       
    }
    
    private void updateList(){
        grid.setItems(service.findAll());
    }
    private void updateList(String s){
        grid.setItems(service.findAllPostCode(s));
    }
    
    private void addActionColumn() {
        grid.addComponentColumn(car -> new Label(regoController.getRego(profileController.getUser(car.getMotoristUsername()).getLicenseNum().toString()).getModel())).setHeader("Car Model");
        grid.addComponentColumn(request -> {
            Button message = new Button("Message",clickEvent ->{
                
            });
            Button cancel = new Button("Cancel");
            System.out.print("Req #: " + request.getRequestNum());
            String requestNum = Integer.toString(request.getRequestNum());
            System.out.println(" Req #: " + requestNum);
            System.out.println("Request to be deleted: " + requestNum);
            cancel.addClickListener( clickEvent ->{
                service.deleteData(requestNum);
                updateList();
            });
            VerticalLayout layout = new VerticalLayout();
            message.setWidth("100%");
            cancel.setWidth("100%");
            layout.add(message, cancel);
            return layout;
        }).setHeader("Actions");
    }
}

