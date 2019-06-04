
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
    
    public boolean submitFeedback(String username, String text, int rating)
    {  
        return DBQueryProcessor.getInstance().writeReview(username, text, rating);
    }
    
    public boolean updateMechanicStars(String text)
    {  
        return DBQueryProcessor.getInstance().updateMechanicStars(text);
    }
    
    public ArrayList<String> getMechanicNames()
    {  
        return DBQueryProcessor.getInstance().getMechanicNames();
    }
    
}
