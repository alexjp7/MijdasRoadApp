package Mijdas.RoadApp.spring.Views.Feedback;


import Mijdas.RoadApp.spring.Views.Footer;
import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *
 * @author Mitch
 */

@Route(value = "feedback", layout = MainLayout.class)
@PageTitle("Feedback")

public class FeedbackView extends Div
{
    public FeedbackView()
    {
        setSizeFull();
        FeedbackForm form = new FeedbackForm();
        setId("background");
        form.setId("feedbackForm");
        add(form);
        add(new Footer());
    }
}
