package Mijdas.RoadApp.spring.Controllers;

/**
 *
 * @author Dyl
 */
public class ServiceProcessor {
    public ServiceProcessor() {}
    
    public boolean isUsernameValid(String username)
    {
        return !username.equals(DBQueryProcessor.getInstance().queryUsername(username));
    }
    
    public boolean submitService(String username, String address, String details)
    {  
        return DBQueryProcessor.getInstance().writeService(username, address, details);
    }
}
