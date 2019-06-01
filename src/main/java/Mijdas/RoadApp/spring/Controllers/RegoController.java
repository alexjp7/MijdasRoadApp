
package Mijdas.RoadApp.spring.Controllers;

import Mijdas.RoadApp.spring.Models.UserModels.Vehicle;

/**
 *
 * @author Mitch
 */
public class RegoController {
    
    public RegoController()
    {

    }
    
    public Vehicle getRego(String lNum)
    {  
        return DBQueryProcessor.getInstance().getVehicle(lNum);
    }
    
}
