
package Mijdas.RoadApp.spring.Controllers;

import Mijdas.RoadApp.spring.Controllers.DatabaseControllers.MijdasTables;
import Mijdas.RoadApp.spring.Controllers.DatabaseControllers.SQLDatabase;
import java.sql.ResultSet;
import java.sql.SQLException;


/**********************************************************************8
 * 
 * This class acts a service provider for the main
 * controllers of the system through processing queries and
 * interfacing with the MySQL database.
 * 
 * The focus of the DBQueryProcessor is to provide 
 * public member functions that return the relevant result sets
 * of particular queries such as; logging in, registration,
 * user management etc.
 *************************************************************************/
public class DBQueryProcessor 
{  
    private static class SingletonHolder
    {
        private static DBQueryProcessor INSTANCE = createInstance();
        private SingletonHolder(){}
        
        private static DBQueryProcessor createInstance()
        {
            if(INSTANCE == null)
            {
                DBQueryProcessor queryProcessor = new DBQueryProcessor();
                //Conenction info.
                String HOST    = "localhost";
                String DB_NAME = "mijdasroadapp";
                String USER    = "root";
                String PASSWD  = "csit115";
                int PORT       = 3306;
                //Create new instance of SQLDatabase.
                queryProcessor.database = new SQLDatabase(HOST,PORT,DB_NAME,USER,PASSWD); 
                
                return queryProcessor;
            }
            
            return INSTANCE;
        }
    }
    
    
    //The active database of the query processor
    private SQLDatabase database;
        
     
    private DBQueryProcessor() {} // This class is a service class, and should 
                                  // not be instanciated
    
    public static DBQueryProcessor getInstance()
    {
        return SingletonHolder.INSTANCE;
    }
    
    
    /*******************************************************
     * 
     * @param username - passed in from login controller
     * @param password - passed in from login controller
     * @return [0] = username, [1] password.
     ********************************************************/
    public String [] makeLoginRequest(String username, String password)
    {
    
        ResultSet rs = null;
        String [] loginInfo = new String[2];
        
        String whereClause = "username = '"+username+"' AND password = '"+password+"';";
        try
        {
            if(!database.open())
            {   //Unsuccesful connection has occured-
                throw new SQLException("Connection To SQL instance Aborted");
            }
            else
            {
                rs =  database.readData(MijdasTables.USERS, whereClause , "username","password");
                while(rs.next()) //Get information from result set
                {
                    loginInfo[0] = rs.getString(1); // Username
                    loginInfo[1] = rs.getString(2); // Password
                }
            }
            database.close();
        }
        catch(SQLException e){e.printStackTrace();}
       
        return loginInfo;
    }
    
 
    
    public String queryUsername(String username)
    {
        ResultSet rs = null;
        String queryReturn = null;
        String whereClase = "username = '" + username +"';";
        
        try
        {
            if(!database.open())
            {
                 throw new SQLException("Connection To SQL instance Aborted");
            }
            else
            {
                rs = database.readData(MijdasTables.USERS, whereClase,"username");
                if(rs.next() != false)
                {
                    queryReturn = rs.getString(1);
                }
               
            }
        }
        catch(SQLException e){e.printStackTrace();}
        
        return queryReturn;
    }
    
    
    public void makeRegistration()
    {
        
    }
    
    
    /* Write data models/Database
        public User getLoginedUser()
        {
        }
    */
    
    
    
}
