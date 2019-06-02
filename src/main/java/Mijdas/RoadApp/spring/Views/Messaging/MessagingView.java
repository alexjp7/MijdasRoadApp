
package Mijdas.RoadApp.spring.Views.Messaging;

import Mijdas.RoadApp.spring.Models.MessageModels.MessageService;
import Mijdas.RoadApp.spring.Models.MessageModels.Message;
import Mijdas.RoadApp.spring.Controllers.SessionController;
import Mijdas.RoadApp.spring.Controllers.UserType;
import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "messaging", layout = MainLayout.class)
@PageTitle("Messages")

public class MessagingView extends Div
{
    private MessageService service = MessageService.getInstance();
    SessionController session = SessionController.getInstance();
    Grid<Message> grid = new Grid<>(Message.class);
    Grid<Message> gridDetails = new Grid<>(Message.class);
    HorizontalLayout horizontal = new HorizontalLayout();
    
    //SESSION stuff for motorist or mechanic
    public MessagingView()
    {
       
       boolean isMech;
       setSizeFull();
       //grid.setHeightFull();
       
       grid.setSelectionMode(Grid.SelectionMode.NONE);
       gridDetails.setColumns("messageText");
       if(session.getUserType() == UserType.MECHANIC){
           grid.setColumns("motoristUsername");
           grid.addItemClickListener(
                event -> updateListPrivate(session.getUser().getUsername(), event.getItem().getMotoristUsername()));
       }
       else{
           grid.setColumns("mechanicUsername");
           grid.addItemClickListener(
               event -> updateListPrivate(session.getUser().getUsername(), event.getItem().getMechanicUsername()));
       }
        
       updateList(session.getUser().getUsername());
       
       horizontal.add(grid);
       horizontal.add(gridDetails);
       
       add(horizontal);
       
    }
    public MessagingView(String me, String them)
    {
       
       boolean isMech;
       setSizeFull();
       //grid.setHeightFull();
       
       grid.setSelectionMode(Grid.SelectionMode.NONE);
       gridDetails.setColumns("messageText");
       if(session.getUserType() == UserType.MECHANIC){
           grid.setColumns("motoristUsername");
           grid.addItemClickListener(
                event -> updateListPrivate(session.getUser().getUsername(), event.getItem().getMotoristUsername()));
       }
       else{
           grid.setColumns("mechanicUsername");
           grid.addItemClickListener(
               event -> updateListPrivate(session.getUser().getUsername(), event.getItem().getMechanicUsername()));
       }
        
       updateList(session.getUser().getUsername());
       updateListPrivate(me,them);
       horizontal.add(grid);
       horizontal.add(gridDetails);
       
       add(horizontal);
       
    }
    private void updateList(){
        grid.setItems(service.findAll());
    }
    private void updateList(String s){
        grid.setItems(service.findOnce(s));
    }
    //DO STUFF WITH service.findFromUser(me,them)
    private void updateListPrivate(String me, String them){
        gridDetails.setItems(service.findFromUser(me,them));
    }
    
    public void jumpToMsgs(String from, String to){
        getUI().ifPresent(ui-> ui.getPage().executeJavaScript("window.location.href = 'messaging' "));
        updateListPrivate(from, to);
    }
   
}

