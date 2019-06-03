
package Mijdas.RoadApp.spring.Views.Profile;

import Mijdas.RoadApp.spring.Views.Footer;
import Mijdas.RoadApp.spring.Views.MainLayout;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "profile", layout = MainLayout.class)
@PageTitle("Profile")

public class ProfileView extends Div
{
    public ProfileView()
    {
        setSizeFull();
        ProfileForm form = new ProfileForm();
        setId("background");
        form.setId("profileForm");
        add(form);
        add(new Footer());
    }
}
