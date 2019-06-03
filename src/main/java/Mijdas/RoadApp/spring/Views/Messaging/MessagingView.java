
package Mijdas.RoadApp.spring.Views.Messaging;

import Mijdas.RoadApp.spring.Models.MessageModels.MessageService;
import Mijdas.RoadApp.spring.Models.MessageModels.Message;
import Mijdas.RoadApp.spring.Controllers.SessionController;
import Mijdas.RoadApp.spring.Controllers.MessagingController;
import Mijdas.RoadApp.spring.Controllers.UserType;
import Mijdas.RoadApp.spring.Views.Footer;
import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "messaging", layout = MainLayout.class)
@PageTitle("Messages")

public class MessagingView extends Div
{
    private MessageService service = MessageService.getInstance();
    SessionController session = SessionController.getInstance();
    MessagingController messages = MessagingController.getInstance();
    Grid<Message> grid = new Grid<>(Message.class);
    Grid<Message> gridDetails = new Grid<>(Message.class);
    HorizontalLayout horizontal = new HorizontalLayout();
    HorizontalLayout horizontal2 = new HorizontalLayout();
    VerticalLayout vertical = new VerticalLayout();
    TextField messageText = new TextField();
    
    Button messageButton = new Button("Send", clickEvent->{
        SendMessage(messageText.getValue());
        messageText.clear();
    });
    
    //SESSION stuff for motorist or mechanic
    public MessagingView()
    {
       
        boolean isMech;
        setSizeFull();
        //grid.setHeightFull();


        grid.setHeight("100%");
        gridDetails.setColumns("messageText");
        if(session.getUserType() == UserType.MECHANIC){
            grid.setColumns("motoristUsername");
            grid.addItemClickListener(
                event -> {updateListPrivate(session.getUser().getUsername(), event.getItem().getMotoristUsername());
                });
        }
        else{
            grid.setColumns("mechanicUsername");
            grid.addItemClickListener(
                event -> {updateListPrivate(session.getUser().getUsername(), event.getItem().getMechanicUsername());
                });
        }
        
        updateList(session.getUser().getUsername());
       
        if(messages.getInstance().getMe()!=null){
            updateListPrivate(messages.getInstance().getMe(),messages.getInstance().getThem());
        }
        else updateListPrivate();
        
        //DO NOT TOUCH THIS ITS IRREVERSIBLE!!~!
        messageText.setWidth("85%");
        messageButton.setWidth("15%");
        horizontal2.add(messageText);
        horizontal2.add(messageButton);
        horizontal2.setWidth("90%");
        vertical.add(gridDetails);
        vertical.add(horizontal2);
        vertical.setWidth("50%");
      // vertical.add(messageButton);
        horizontal.setWidth("90%");
        horizontal.add(grid,vertical);
        horizontal.setHeight("80%");
        add(horizontal);
        setId("background");
        add(new Footer());
       
    }
    
    private void updateList(){
        grid.setItems(service.findAll());
    }
    private void updateList(String s){
        grid.setItems(service.findOnce(s));
    }
    //DO STUFF WITH service.findFromUser(me,them)
    private void updateListPrivate(String me, String them){
        messages.setBoth(me, them);
        gridDetails.setItems(service.findFromUser(me,them,(session.getUserType() == UserType.MECHANIC)));
        
    }
    private void updateListPrivate(){
        Message fake = new Message(1, "", "", "Click on a profile to the left to see your messages", true);
        gridDetails.setItems(fake);
    }
    public void jumpToMsgs(String from, String to){
        getUI().ifPresent(ui-> ui.getPage().executeJavaScript("window.location.href = 'messaging' "));
        updateListPrivate(from, to);
    }

    private void SendMessage(String s) {
//        System.out.println("MESSAGE: To["+messages.getThem()+"] From["+messages.getMe()+"]."); //To change body of generated methods, choose Tools | Templates.
        
        if(SessionController.getInstance().getUserType() == UserType.MOTORIST){
            messages.sendMsg(messages.getMe(), messages.getThem(), s);
        }else if(SessionController.getInstance().getUserType() == UserType.MECHANIC){
            messages.sendMsg(messages.getThem(), messages.getMe(), s);
        }
        updateList();
        updateListPrivate(messages.getMe(), messages.getThem());
    }
   
}

