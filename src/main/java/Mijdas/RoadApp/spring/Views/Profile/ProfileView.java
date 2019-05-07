
package Mijdas.RoadApp.spring.Views.Profile;

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
        ProfileForm form = new ProfileForm();
        form.setId("profileForm");
        add(form);
    }
}
