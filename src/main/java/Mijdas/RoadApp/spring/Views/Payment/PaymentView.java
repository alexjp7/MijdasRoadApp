
package Mijdas.RoadApp.spring.Views.Payment;

//import Mijdas.RoadApp.spring.Views.Payment.*;
import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "payment", layout = MainLayout.class)
@PageTitle("Payment")

public class PaymentView extends Div
{
    public PaymentView()
    {
        PaymentForm form = new PaymentForm();
        form.setId("memberForm");
        add(form);
    }
}
