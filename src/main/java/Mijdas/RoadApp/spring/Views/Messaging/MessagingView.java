
package Mijdas.RoadApp.spring.Views.Messaging;

import Mijdas.RoadApp.spring.Models.MessageModels.MessageService;
import Mijdas.RoadApp.spring.Models.MessageModels.Message;
import Mijdas.RoadApp.spring.Controllers.SessionController;
import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "messaging", layout = MainLayout.class)
@PageTitle("Messages")

public class MessagingView extends Div
{
    private MessageService service = MessageService.getInstance();
    SessionController session = SessionController.getInstance();
    Grid<Message> grid = new Grid<>(Message.class);
    public MessagingView()
    {
       setSizeFull();
       grid.setHeightFull();
       grid.setColumns("messageText");
       add(grid);
       
       updateList(session.getUser().getUsername());
    }
    
    private void updateList(){
        grid.setItems(service.findAll());
    }
    private void updateList(String s){
        grid.setItems(service.findAll(s));
    }
    //DO STUFF WITH service.findFromUser(me,them)
    
   
}

