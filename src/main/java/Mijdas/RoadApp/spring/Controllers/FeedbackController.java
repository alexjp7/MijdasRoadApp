
package Mijdas.RoadApp.spring.Controllers;

import java.util.ArrayList;

/**
 *
 * @author Mitch
 */
public class FeedbackController implements abstractController {
    
    public FeedbackController()
    {

    }
    
//    public boolean saveProfileUpdates(String username, String fName, String lName, String email, String lnum)
//    {  
//        return DBQueryProcessor.getInstance().writeProfileUpdate(username, fName, lName, email, lnum);
//    }
    
    public ArrayList<String> getMechanicNames()
    {  
        return DBQueryProcessor.getInstance().getMechanicNames();
    }
    
}
