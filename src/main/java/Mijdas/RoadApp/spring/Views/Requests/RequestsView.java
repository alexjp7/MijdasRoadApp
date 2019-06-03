
package Mijdas.RoadApp.spring.Views.Requests;

import Mijdas.RoadApp.spring.Controllers.MessagingController;
import Mijdas.RoadApp.spring.Controllers.ProfileController;
import Mijdas.RoadApp.spring.Controllers.RegoController;
import Mijdas.RoadApp.spring.Controllers.SessionController;
import Mijdas.RoadApp.spring.Models.RequestModels.RequestService;
import Mijdas.RoadApp.spring.Models.RequestModels.Requests;
import Mijdas.RoadApp.spring.Models.UserModels.User;
import Mijdas.RoadApp.spring.Views.Footer;
import Mijdas.RoadApp.spring.Views.MainLayout;
import Mijdas.RoadApp.spring.Views.Messaging.MessagingView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import Mijdas.RoadApp.spring.Controllers.DBQueryProcessor;
import Mijdas.RoadApp.spring.Controllers.SessionController;


@Route(value = "requests", layout = MainLayout.class)
@PageTitle("Requests")

public class RequestsView extends Div
{
    private RegoController regoController = new RegoController();
    private ProfileController profileController = new ProfileController();
    private User loggedInUser = SessionController.getInstance().getUser();
    private MessagingView messageView = new MessagingView();
    private RequestService service = RequestService.getInstance();
    Grid<Requests> grid = new Grid<>(Requests.class);
    TextField searchField = new TextField();
    public RequestsView()
    {
       Button searchButton = new Button("Search", clickEvent->{updateList(searchField.getValue());});
       searchField.setPlaceholder("Street Name, City, or Postcode..");
       searchField.setWidth("260px"); //fit in placeholder text
       add(searchField);
       add(searchButton);
//       setSizeFull();
        setHeight("50%");
       grid.setHeightFull();
       grid.setColumns("motoristUsername","nearestAddress","details");
       DBQueryProcessor db = DBQueryProcessor.getInstance();
       SessionController sc = SessionController.getInstance();
       
//          grid.setSelectionMode(Grid.SelectionMode.NONE);
//      grid.setItemDetailsRenderer(TemplateRenderer.<Requests> of(
//            "button on-click='handleClick'< Button </button>")
//             .withEventHandler("handleClick", c -> {
//                 grid.getDataProvider().refreshItem(c);
//                 System.out.println("TESTTTTT");              }));
//           grid.setDetailsVisibleOnClick(false);
       
       add(grid);
       updateList();
       
       //This line below breaks the view:
       grid.addComponentColumn(car -> new Label(regoController.getRego(profileController.getUser(car.getMotoristUsername()).getLicenseNum().toString()).getModel())).setHeader("Car Model");
       grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
//       grid.addColumn(TemplateRenderer.<Requests> of(
//                "<div id='msgDiv' class='w3-container'>"
//                + "<button id='button' class='w3-button w3-dark-grey' on-click='handleMessage'>Message</button>"
//                + "</div>")
//               .withEventHandler("handleMessage", button -> {
//                    System.out.println("Button Clicked!!");
//                    
//               })).setHeader("Actions");
               
       
       
       
        grid.addComponentColumn(request -> {
            Button message = new Button("Message",clickEvent ->{
                System.out.println("Message Clicked.");
                //go to messages
                getUI().ifPresent(ui-> ui.getPage().executeJavaScript("window.location.href = 'messaging' "));
                MessagingController.getInstance().setBoth(loggedInUser.getUsername(),request.getMotoristUsername());
            });
            VerticalLayout layout = new VerticalLayout();
            message.setWidth("100%");
            layout.add(message);
            String mechanic = sc.getUser().getUsername();
            String requestNum = Integer.toString(request.getRequestNum());
            String mechanicUsername = db.getRequest(requestNum).getMechanicUsername();
            if(mechanicUsername == null && !request.getIsAccepted()) {
                Button accept = new Button("Accept");
                accept.addClickListener(clickEvent -> {
                    System.out.println("mechanic: " + mechanic + "\nmechanicUsername: " + mechanicUsername + "\nrequestNum: " + requestNum);
                    String mechanic1 = "'" + mechanic + "'";
                    service.setMechanic(requestNum, mechanic1);
                    updateList();
                });
                accept.setWidth("100%");
                layout.add(accept);
            }
            if(mechanicUsername != null) {
                if(mechanicUsername.equals(mechanic) && request.getIsAccepted()) {
                    Button complete = new Button("Complete");
                    complete.addClickListener(clickEvent -> {
                        db.updateServiceComplete(requestNum, "true");
                        service.refreshData();
                        updateList();
                    });
                    complete.setWidth("100%");
                    layout.add(complete);
                }
            }
            return layout;
        }).setHeader("Actions");
       
        Button refresh = new Button("Refresh");
        refresh.addClickListener( clickEvent -> {
            service.refreshData();
            updateList(); 
        });
        add(refresh);
       
       setId("baclground");
       //Adds Footer
       add(new Footer());
    }
    
//    private void handleClick(){
//        System.out.println("SOMETHING CLICKED");
//    }
    
    private void updateList(){
        grid.setItems(service.findAll());
    }
    private void updateList(String s){
        grid.setItems(service.findAllPostCode(s));
    }
}

