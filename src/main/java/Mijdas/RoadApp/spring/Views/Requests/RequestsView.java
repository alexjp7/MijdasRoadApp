
package Mijdas.RoadApp.spring.Views.Requests;

import Mijdas.RoadApp.spring.Controllers.MessagingController;
import Mijdas.RoadApp.spring.Controllers.ProfileController;
import Mijdas.RoadApp.spring.Controllers.RegoController;
import Mijdas.RoadApp.spring.Controllers.SessionController;
import Mijdas.RoadApp.spring.Models.RequestModels.RequestService;
import Mijdas.RoadApp.spring.Models.RequestModels.Requests;
import Mijdas.RoadApp.spring.Models.UserModels.User;
import Mijdas.RoadApp.spring.Views.MainLayout;
import Mijdas.RoadApp.spring.Views.Messaging.MessagingView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@StyleSheet("frontend://styles/w3.css")
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
       setSizeFull();
       grid.setHeightFull();
       grid.setColumns("motoristUsername","nearestAddress","details");
       
          grid.setSelectionMode(Grid.SelectionMode.NONE);
      grid.setItemDetailsRenderer(TemplateRenderer.<Requests> of(
            "button on-click='handleClick'< Button </button>")
             .withEventHandler("handleClick", c -> {
                 grid.getDataProvider().refreshItem(c);
                 System.out.println("TESTTTTT");              }));
           grid.setDetailsVisibleOnClick(false);
       
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
               
       
       
       
       grid.addComponentColumn(button -> new Button("Message",clickEvent ->{
           System.out.println("Message Clicked.");
           //go to messages
           getUI().ifPresent(ui-> ui.getPage().executeJavaScript("window.location.href = 'messaging' "));
           MessagingController.getInstance().setBoth(loggedInUser.getUsername(),button.getMotoristUsername());
       })).setHeader("Actions");
       
       
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

