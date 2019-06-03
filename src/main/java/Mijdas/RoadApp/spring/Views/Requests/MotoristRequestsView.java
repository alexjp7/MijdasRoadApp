package Mijdas.RoadApp.spring.Views.Requests;

import Mijdas.RoadApp.spring.Controllers.ProfileController;
import Mijdas.RoadApp.spring.Controllers.SessionController;
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
    SessionController session = SessionController.getInstance();
    String username = "";
    public MotoristRequestsView()
    {
        if(session.isLogin()) {
            username = SessionController.getInstance().getUser().getUsername();
        }
        
        Button searchButton = new Button("Search", clickEvent->{updateList(searchField.getValue());});
        searchField.setPlaceholder("Street Name, City, or Postcode..");
        searchField.setWidth("260px"); //fit in placeholder text
        add(searchField);
        add(searchButton);
        //setSizeFull();
        setHeight("50%");
        grid.setHeightFull();
        grid.setColumns("nearestAddress","details");
       
       
       
        add(grid);
        updateList();
        addActionColumn();
        
        Button refresh = new Button("Refresh");
        refresh.addClickListener( clickEvent -> {
            service.refreshData();
            updateList(); 
        });
        add(refresh);
        //This line below breaks the view:

        //grid.addComponentColumn(button -> new Button("Cancel",clickEvent ->{System.out.println("Cancel");}));

       
    }
    
    private void updateList(){
        grid.setItems(service.findAll(username));
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
            String requestNum = Integer.toString(request.getRequestNum());
            System.out.println(" Req #: " + requestNum);
            String mechanic = request.getMechanicUsername();
            System.out.println("Mechanic: " + mechanic);
            cancel.addClickListener( clickEvent ->{
                service.deleteData(requestNum);
                updateList();
            });
            VerticalLayout layout = new VerticalLayout();
            message.setWidth("100%");
            cancel.setWidth("100%");
            layout.add(message, cancel);
            if(mechanic != null && !request.getIsAccepted()) {
                Button accept = new Button("Accept");
                accept.addClickListener(clickEvent -> {
                    service.setIsAccepted(requestNum, "true");
                    updateList();
                });
                accept.setWidth("100%");
                layout.add(accept);
            }
            return layout;
        }).setHeader("Actions");
    }
}

