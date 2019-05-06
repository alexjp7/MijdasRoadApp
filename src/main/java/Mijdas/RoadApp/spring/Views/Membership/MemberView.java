
package Mijdas.RoadApp.spring.Views.Membership;

import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="membership", layout=MainLayout.class)
@PageTitle("Membership")

public class MemberView extends Div
{
    public MemberView()
    {
        MemberForm form = new MemberForm();
        form.setId("memberForm");
        add(form);
    }
}
