
package Mijdas.RoadApp.spring.Views.Requests;

import Mijdas.RoadApp.spring.Controllers.ProfileController;
import Mijdas.RoadApp.spring.Controllers.RegoController;
import Mijdas.RoadApp.spring.Models.RequestModels.RequestService;
import Mijdas.RoadApp.spring.Models.RequestModels.Requests;
import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.ui.Component;
import com.vaadin.ui.PopupView;


@Route(value = "requests", layout = MainLayout.class)
@PageTitle("Requests")

public class RequestsView extends Div
{
    private RegoController regoController = new RegoController();
    private ProfileController profileController = new ProfileController();
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
       
//       grid.setSelectionMode(Grid.SelectionMode.NONE);
//       grid.setItemDetailsRenderer(TemplateRenderer.<Requests> of(
//               "<div class='custom-details' style='border: 1px solid gray; padding: 10px; width: 100%; box-sizing: border-box;'>"
//                       +"<div>Hi Test!</div>"
//                       +"</div>")
//               .withEventHandler("handleClick", c -> {
//                   grid.getDataProvider().refreshItem(c);
//                   System.out.println("TESTTTTT");
//               }));
//       grid.setDetailsVisibleOnClick(false);
       
//       
//       VerticalLayout popupContent = new VerticalLayout(); 
//       popupContent.addComponentAsFirst(new TextField("TextField"));
//       popupContent.addComponentAtIndex(1, new Button("Send"));
//       PopupView popup = new PopupView("", (Component) popupContent);
       

       add(grid);
       updateList();
       
       //This line below breaks the view:
       grid.addComponentColumn(car -> new Label(regoController.getRego(profileController.getUser(car.getMotoristUsername()).getLicenseNum().toString()).getModel())).setHeader("Car Model");
       grid.addComponentColumn(button -> new Button("Message",clickEvent ->{
           System.out.println("Message Clicked.");
//           grid.setDetailsVisible(button, !grid.isDetailsVisible(button));
       })).setHeader("Actions");
       
       
    }
    
    private void updateList(){
        grid.setItems(service.findAll());
    }
    private void updateList(String s){
        grid.setItems(service.findAllPostCode(s));
    }
}

