
package Mijdas.RoadApp.spring.Controllers;

public class ProfileController {
    
    public ProfileController()
    {

    }
    
    public boolean saveProfileUpdates(String username, String fName, String lName, String email, String lnum)
    {  
        return DBQueryProcessor.getInstance().writeProfileUpdate(username, fName, lName, email, lnum);
    }
    
}
