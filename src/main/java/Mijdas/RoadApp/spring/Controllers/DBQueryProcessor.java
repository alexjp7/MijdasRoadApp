package Mijdas.RoadApp.spring.Controllers;

import Mijdas.RoadApp.spring.Controllers.DatabaseControllers.Insertable;
import Mijdas.RoadApp.spring.Controllers.DatabaseControllers.MijdasDB;
import Mijdas.RoadApp.spring.Controllers.DatabaseControllers.SQLDatabase;
import Mijdas.RoadApp.spring.Models.MessageModels.Message;
import Mijdas.RoadApp.spring.Models.RequestModels.Requests;
import Mijdas.RoadApp.spring.Models.ReviewModels.MechanicReview;
import Mijdas.RoadApp.spring.Models.UserModels.Mechanic;
import Mijdas.RoadApp.spring.Models.UserModels.Motorist;
import Mijdas.RoadApp.spring.Models.UserModels.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
                String USER    = "MijdasUser";
                String PASSWD  = "mijdas123";
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
                /*******************************************************************
                 * MijdasDB.Tables.User - Table to be read from
                 * whereClause - pre-built where clause to read a specific row
                 * MijdasDB.User.Username - a value to be read from table
                 * MijdasDB.User.Password -   a value to be read from table
                     NOTE: after whereClause; parameter length is of a variadic length.
                 *****************************************************************/
                rs =  database.readData(MijdasDB.Tables.USER, whereClause, MijdasDB.User.USERNAME, MijdasDB.User.PASSWORD);
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
                rs = database.readData(MijdasDB.Tables.USER, whereClase, MijdasDB.User.USERNAME);
                if(rs.next() != false)
                {
                    queryReturn = rs.getString(1);
                }
            }
            database.close();
        }
        catch(SQLException e){e.printStackTrace();}
        return queryReturn;
    }

    /**************************************************************
     * Writes the registration form details to persistent storage.
     * -----------------------------------------------------------
     * @param formData - variadic string parameter representing the
     *                   values entered from login.
     * @return - whether a successful write to storage has occurred
     ***************************************************************/
    public boolean writeRegistration(String...formData)
    {   //Insertable interface to enable a polymorphic behaviour for the table enums
        Insertable[] fields; //fields- to go between INSERT INTO(fields) VALUES(formData);
        try
        {
            if(!database.open()){return false;}
            else
            {
                /******************************COMMON USER INFORMATION********************************/
                //Write User information to User SQL table.
                database.writeToStorage(MijdasDB.Tables.USER ,formData[0],formData[5],
                                        formData[2],formData[3],formData[4]);

                /***************************USER INSERTS DEPENDING ON USER TYPE******************/
                if(formData[1].equals("Motorist")) //Motorist Inserts
                {
                    //SQL insert parameters - upon account creation, username and membership values are inserted.
                    fields = new  MijdasDB.Motorist[]{MijdasDB.Motorist.USERNAME,  MijdasDB.Motorist.HASMEMBERSHIP};

                    /*******************************************************************************
                     * writeToStorage(TableName, insert fields, varies)
                     * NOTE: ordering of fields and values (String...values) must be the same.
                     * formData[0] - username
                     * Boolean.false.toString() - assumes no membership for members upon user creation.
                    ********************************************************************************/
                    database.writeToStorage(MijdasDB.Tables.MOTORISTS, fields, //after fields, paramter list is variadic
                                           formData[0], Boolean.FALSE.toString());
                }
                else if(formData[1].equals("Mechanic")) //Mechanic Inserts
                {
                    fields = new MijdasDB.Mechanic[]{MijdasDB.Mechanic.USERNAME};
                    database.writeToStorage(MijdasDB.Tables.MECHANICS,fields, formData[0]);
                }
                //Close database session
                database.close();
                return true;
            }
        }
        catch(SQLException e){e.printStackTrace();}
        return false;
    }
    
    //args = (username, firstname, lastname, email, lnumber)
    public boolean writeProfileUpdate(String username, String fName, String lName, String email, String lnum)
    {   //Insertable interface to enable a polymorphic behaviour for the table enums
        String profileWhereClause = "username = '" + username + "';";
        try
        {
            if(!database.open()){return false;}
            else
            {
                //Update User information to User SQL table.
                database.updateData(MijdasDB.Tables.USER, profileWhereClause, MijdasDB.User.FNAME, fName);
                database.updateData(MijdasDB.Tables.USER, profileWhereClause, MijdasDB.User.LNAME, lName);
                database.updateData(MijdasDB.Tables.USER, profileWhereClause, MijdasDB.User.EMAIL, email);
                
                if(SessionController.getInstance().getUserType() == UserType.MECHANIC){
                    database.updateData(MijdasDB.Tables.MECHANICS, profileWhereClause, MijdasDB.Mechanic.LICENSE, lnum);
                }
                else if(SessionController.getInstance().getUserType() == UserType.MOTORIST){
                    database.updateData(MijdasDB.Tables.MOTORISTS, profileWhereClause, MijdasDB.Motorist.LICENSE, lnum);
                }

                //Close database session
                database.close();
                return true;
            }
        }
        catch(SQLException e){e.printStackTrace();}
        return false;
    }
    

    public boolean writeVehicleMembership(String vehicleType, String lNum, boolean member)
    {
        String profileWhereClause = "regPlate = '" + vehicleType + "';";
        try
        {
            if(!database.open()){return false;}
            else
            {
                if(member == true)
                {
                    database.updateData(MijdasDB.Tables.VEHICLE, profileWhereClause, MijdasDB.Vehicle.HASMEMBERSHIP, "1");
                }
                else if(member == false)
                {
                    database.updateData(MijdasDB.Tables.VEHICLE, profileWhereClause, MijdasDB.Vehicle.HASMEMBERSHIP, "0");
                }
                database.close();
                return true;
            }
         }
        catch(SQLException e){e.printStackTrace();}
        return false;   
    }
    
    public boolean writeVehicleUpdate(String licenseNumber, String registrationNumber, String manufacturer, String model, String color)
    {
        String profileWhereClause = "license = '" + licenseNumber + "';";
        try
        {
            if(!database.open()){return false;}
            else
            {
                //Update User information to User SQL table.
                database.updateData(MijdasDB.Tables.VEHICLE, profileWhereClause, MijdasDB.Vehicle.REGPLATE, registrationNumber);    
                database.updateData(MijdasDB.Tables.VEHICLE, profileWhereClause, MijdasDB.Vehicle.MANUFACTURER, manufacturer);
                database.updateData(MijdasDB.Tables.VEHICLE, profileWhereClause, MijdasDB.Vehicle.MODEL, model);
                database.updateData(MijdasDB.Tables.VEHICLE, profileWhereClause, MijdasDB.Vehicle.COLOR, color);
                database.updateData(MijdasDB.Tables.VEHICLE, profileWhereClause, MijdasDB.Vehicle.HASMEMBERSHIP, "false");
                //Close database session
                database.close();
                return true;
            }
        }
        catch(SQLException e){e.printStackTrace();}
        return false;
    }
    
    public boolean writeVehicleInsert(String licenseNumber, String registrationNumber, String manufacturer, String model, String color)
    {
        Insertable fields[];
        try
        {
            if(!database.open()){return false;}
            else
            {
                //Insert User information to User SQL table.
                fields = new MijdasDB.Vehicle[]{MijdasDB.Vehicle.LICENSE, MijdasDB.Vehicle.REGPLATE, MijdasDB.Vehicle.MANUFACTURER, MijdasDB.Vehicle.MODEL, MijdasDB.Vehicle.COLOR, MijdasDB.Vehicle.HASMEMBERSHIP};
                database.writeToStorage(MijdasDB.Tables.VEHICLE, fields, licenseNumber, registrationNumber, manufacturer, model, color, "false");
                //Close database session
                database.close();
                return true;
            }
        }
        catch(SQLException e){e.printStackTrace();}
        return false;
    }
    
    //args = (username, firstname, lastname, email, lnumber)

    public boolean writeMessage(String motorist, String mechanic, String messageTxt)
    {   //Insertable interface to enable a polymorphic behaviour for the table enums
//        String profileWhereClause = "username = '" + username + "';";
//        String storageFieldsClause = "motoristUsername, mechanicUsername, messageText, motoristSent";
        Insertable[] storageFieldsClause = new  MijdasDB.Message[]{MijdasDB.Message.MOTORISTUSERNAME, MijdasDB.Message.MECHANICUSERNAME, MijdasDB.Message.MESSAGETEXT, MijdasDB.Message.MOTORISTSENT};
        String[] storageValuesClause = {motorist, mechanic, messageTxt, "null"};
        try
        {
            if(!database.open()){return false;}
            else
            {
                //Update User information to User SQL table.
                //check current session if sender is a motorist or mechanic
                if(SessionController.getInstance().getUserType() == UserType.MECHANIC){
                    storageValuesClause[3] = "false";
                    database.writeToStorage(MijdasDB.Tables.MESSAGE, storageFieldsClause, storageValuesClause);
                }
                else if(SessionController.getInstance().getUserType() == UserType.MOTORIST){
                    storageValuesClause[3] = "true";
                    database.writeToStorage(MijdasDB.Tables.MESSAGE, storageFieldsClause, storageValuesClause);
                }

                //Close database session
                database.close();
                return true;
            }
        }
        catch(SQLException e){e.printStackTrace();}
        return false;
    }
    

    //method for writing review to sql database
    public boolean writeReview(String mechanic, String messageTxt, int starRating)
    {   //Insertable interface to enable a polymorphic behaviour for the table enums
        Insertable[] storageFieldsClause = new  MijdasDB.Reviews[]{MijdasDB.Reviews.MECHANICUSERNAME, MijdasDB.Reviews.MESSAGETEXT, MijdasDB.Reviews.STARRATING};
        String[] storageValuesClause = {mechanic, messageTxt, Integer.toString(starRating)};
        try
        {
            if(!database.open()){return false;}
            else
            {
                //Update User information to User SQL table.
//                System.out.println("Fields: "+storageFieldsClause);
//                System.out.println("Values: "+storageValuesClause);
                database.writeToStorage(MijdasDB.Tables.REVIEWS, storageFieldsClause, storageValuesClause);
                
                //Close database session
                database.close();
                return true;
            }
        }
        catch(SQLException e){e.printStackTrace();}
        return false;
    }
    
    public boolean updateMechanicStars(String mechanic)
    {   //Insertable interface to enable a polymorphic behaviour for the table enums
        try
        {
            //update mechanics new average stars
            int avgStars = getAvgRatings(mechanic);
            
            if(!database.open()){return false;}
            else
            {
                //Update User information to User SQL table.
                //if avg returned correctly
                if(avgStars != -1){
//                    System.out.println(mechanic+"= "+avgStars+"*");
                    database.updateData(MijdasDB.Tables.MECHANICS, ("username = '"+mechanic+"';"), MijdasDB.Mechanic.STARS, Integer.toString(avgStars));
                }
                
                //Close database session
                database.close();
                return true;
            }
        }
        catch(SQLException e){e.printStackTrace();}
        return false;
    }
    
    
    
    


    /*******************************************************************
     * @param username - the username entered into login screen
     * @return - whether the user has data entered into motorist table
     ******************************************************************/
    public boolean isMotorist(String username)
    {
        ResultSet rs;
        boolean userIsMotorist = false;
        try
        {
            if(!database.open()){return false;}
            else
            {
                rs = database.executeFunction(MijdasDB.Function.CHECK_MOTORIST, "'"+username+"'");
                //If there is there is data in the motorist table
                //corresponding to the username entered
                rs.next();

                if(rs.getString(1) != null)
                {
                    userIsMotorist = true;
                }
                database.close();
            }
        }
        catch (SQLException ex) {ex.printStackTrace(); }
        return userIsMotorist;
    }
    /*******************************************************************
     * @param username - the username entered into login screen
     * @return - whether the user has data entered into Mechanic table
     ******************************************************************/
    public boolean isMechanic(String username)
    {
        ResultSet rs;
        boolean userIsMechanic = false;
        try
        {
            if(!database.open()){return false;}
            else
            {
                rs = database.executeFunction(MijdasDB.Function.CHECK_MECHANIC, "'"+username+"'");
                //If there is there is data in the motorist table
                //corresponding to the username entered
                rs.next();

                if(rs.getString(1) != null)
                {
                    userIsMechanic = true;
                }
                database.close();
            }
        }
        catch (SQLException ex) {ex.printStackTrace(); }
        return userIsMechanic;
    }

    public Mechanic getMechanic(String user)
    {
        Mechanic mechanic = null;
        String username = "";
        String firstName = "";
        String lastName = "";
        String email = "";
        Integer quality = null;
        Integer lNum = null;
        ResultSet rs = null;
        try
        {
           if(!database.open()){return null;}
           else
           {
               rs = database.executeProcedure(MijdasDB.Procedure.GET_MECHANIC, "'"+user+"'");

               while(rs.next())
               {
                    username  = rs.getString(1);
                    firstName = rs.getString(3);
                    lastName  = rs.getString(4);
                    email     = rs.getString(5);
                    quality   = rs.getInt(7);
                    lNum      = rs.getInt(8);
               }
                database.close();
                mechanic = new Mechanic(username, firstName, lastName, email, quality, lNum);
           }
        }
        catch (SQLException e){e.printStackTrace();}
        return mechanic;
    }
    
    public String getVehicleRead(String regNum)
    {
        String profileWhereClause = "regPlate = '" + regNum + "';";
        String membership = "";
        Insertable[] storageFieldsClause = new  MijdasDB.Vehicle[]{MijdasDB.Vehicle.HASMEMBERSHIP};
        ResultSet rs = null;
        try
        {
            if(!database.open()){return null;}
            else
            {
                rs = database.readData(MijdasDB.Tables.VEHICLE, profileWhereClause, storageFieldsClause);
                while(rs.next())
                {
                    membership = rs.getString(1);
                }
                database.close();
            }
        }
        catch (SQLException e){e.printStackTrace();}
        return membership;
    }
    
    public boolean getVehicleMembership(String username) {
        ResultSet rs = null;
        int lNum = 0;
        boolean hasMembership = false;
        try {
            if(!database.open()) {
                throw new SQLException("Error fetching vehicle membership data");
            }
            System.out.println("Username: " + username);
            rs = database.executeProcedure(MijdasDB.Procedure.GET_MOTORIST, "'" + username + "'");
            while(rs.next()) {
                lNum = rs.getInt(9);
            }
            System.out.println("lNum: " + lNum);
            rs = database.executeProcedure(MijdasDB.Procedure.GET_VEHICLE, "'" + lNum + "'");
            while(rs.next()) {
                hasMembership = rs.getBoolean(6);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println("M: " + hasMembership);
        return hasMembership;
    }
    
    public Vehicle getVehicle(String lNum)
    {
        Vehicle vehicle = null;
        int license = 0;
        String registration = "";
        String manufacturer = "";
        String model = "";
        String color = "";
        String hasMembership = "false";
        
        ResultSet rs = null;
        try
        {
           if(!database.open()){return null;}
           else
           {
               rs = database.executeProcedure(MijdasDB.Procedure.GET_VEHICLE, lNum);
               
               while(rs.next())
               {
                    license = rs.getInt(1);
                    registration = rs.getString(2);
                    manufacturer = rs.getString(3);
                    model = rs.getString(4);
                    color = rs.getString(5);
                    hasMembership = rs.getString(6);
               }
                database.close();
                vehicle = new Vehicle(registration, manufacturer, model, color, hasMembership);
           }
        }
        catch (SQLException e){e.printStackTrace();}
        return vehicle;
    }

    public ArrayList<Vehicle> getVehicleList(String lNum)
    {
        Vehicle vehicle = null;
        int license = 0;
        String registration = "";
        String manufacturer = "";
        String model = "";
        String color = "";
        String hasMembership = "";
        
        ResultSet rs = null;
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        try
        {
            if(!database.open()){return null;}
            else
            {
                rs = database.executeProcedure(MijdasDB.Procedure.GET_VEHICLE, lNum);
                while(rs.next())
                {
                    license = rs.getInt(1);
                    registration = rs.getString(2);
                    manufacturer = rs.getString(3);
                    model = rs.getString(4);
                    color = rs.getString(5);
                    hasMembership = rs.getString(6);
                    vehicle = new Vehicle(registration, manufacturer, model, color, hasMembership);
                    vehicles.add(vehicle);
                }
                database.close();
            }
        }
        catch (SQLException e){e.printStackTrace();}
        return vehicles;
    }
    
    public Motorist getMotorist(String user)
    {
        Motorist mechanic = null;
        String username = "";
        String firstName = "";
        String lastName = "";
        String email = "";
        Integer paymentOption = null;
        Integer lNum = null;

        ResultSet rs = null;
        try
        {
           if(!database.open()){return null;}
           else
           {
               rs = database.executeProcedure(MijdasDB.Procedure.GET_MOTORIST, "'"+user+"'");

               while(rs.next())
               {
                    username  = rs.getString(1);
                    firstName = rs.getString(3);
                    lastName  = rs.getString(4);
                    email     = rs.getString(5);
                    lNum   = rs.getInt(9);
               }
                database.close();
                mechanic = new Motorist(username, firstName, lastName, email, lNum);
           }
        }
        catch (SQLException e){e.printStackTrace();}
        return mechanic;
    }
    
    public ArrayList<String> getMechanicNames(){
        String username = "";

        ResultSet rs = null;
        ArrayList<String> mechanics = new ArrayList<String>();
        try
        {
           if(!database.open()){return null;}
           else
           {
               rs = database.executeProcedure(MijdasDB.Procedure.GET_ALL_MECHANIC_NAMES, "");
               while(rs.next())
               {
                    username  = rs.getString(1);
                    mechanics.add(username);
               }
                database.close();
           }
        }
        catch (SQLException e){e.printStackTrace();}
        return mechanics;
    }
    
    public ArrayList<Requests> getRequest(){
        Requests request =null;
        int requestNum=0;
        String nearestAddress="",motoristUsername="",details="", mechanicUsername = "";
        boolean isComplete=false, isAccepted = false;

        ResultSet rs = null;
        ArrayList<Requests> requests = new ArrayList<Requests>();
        try
        {
           if(!database.open()){return null;}
           else
           {
               rs = database.executeProcedure(MijdasDB.Procedure.GET_ALL_REQUEST, "");

               while(rs.next())
               {
                    requestNum  = rs.getInt(1);
                    motoristUsername = rs.getString(2);
                    nearestAddress = rs.getString(3);
                    details  = rs.getString(4);
                    isComplete     = rs.getBoolean(5);
                    mechanicUsername = rs.getString(6);
                    isAccepted = rs.getBoolean(7);
                    request = new Requests(requestNum, nearestAddress,motoristUsername, details,isComplete, mechanicUsername, isAccepted);
                    requests.add(request);
               }
                database.close();
           }
        }
        catch (SQLException e){e.printStackTrace();}
        
        return requests;
    }
    
    public Requests getRequest(String rNum){
        Requests request =null;
        int requestNum=0;
        String nearestAddress="",motoristUsername="",details="", mechanicUsername = "";
        boolean isComplete=false, isAccepted = false;

        ResultSet rs = null;
        try
        {
           if(!database.open()){return null;}
           else
           {
               rs = database.executeProcedure(MijdasDB.Procedure.GET_REQUEST, rNum);

               while(rs.next())
               {
                    requestNum  = rs.getInt(1);
                    motoristUsername = rs.getString(2);
                    nearestAddress = rs.getString(3);
                    details  = rs.getString(4);
                    isComplete     = rs.getBoolean(5);
                    mechanicUsername = rs.getString(6);
                    isAccepted = rs.getBoolean(7);
               }
                database.close();
                request = new Requests(requestNum, nearestAddress,motoristUsername, details,isComplete, mechanicUsername, isAccepted);
           }
        }
        catch (SQLException e){e.printStackTrace();}
        
        return request;
    }
    
    public Message getMessage(String mNum)
    {
        Message message =null;
        int messageNum=0;
        String mechanicUsername="",motoristUsername="",userText="";
        boolean isComplete=false;

        ResultSet rs = null;
        try
        {
           if(!database.open()){return null;}
           else
           {
               rs = database.executeProcedure(MijdasDB.Procedure.GET_MESSAGE, mNum);

               while(rs.next())
               {
                    messageNum  = rs.getInt(1);
                    motoristUsername = rs.getString(2);
                    mechanicUsername = rs.getString(3);
                    userText  = rs.getString(4);
                    isComplete     = rs.getBoolean(5); 
               }
                database.close();
                message = new Message(messageNum, mechanicUsername,motoristUsername, userText,isComplete);
           }
        }
        catch (SQLException e){e.printStackTrace();}
        return message;
    }
    

    public MechanicReview getReview(String rNum){
        MechanicReview review = null;
        int reviewNum = 0, starRating = 0;
        String mechanicUsername = "", userText = "";

        ResultSet rs = null;
        try
        {
           if(!database.open()){return null;}
           else
           {
               rs = database.executeProcedure(MijdasDB.Procedure.GET_REVIEW, rNum);

               while(rs.next())
               {
                    reviewNum = rs.getInt(1);
                    mechanicUsername = rs.getString(2);
                    userText = rs.getString(3);
                    starRating = rs.getInt(4);
                    
               }
                database.close();
                review = new MechanicReview(reviewNum, mechanicUsername, userText, starRating);
           }
        }
        catch (SQLException e){e.printStackTrace();}
        
        return review;
    }
    public int getAvgRatings(String mName)
    {
        ResultSet rs;
        int rAvg = -1;
        try
        {
            if(!database.open()){return 0;}
            else
            {
                rs = database.executeProcedure(MijdasDB.Procedure.GET_AVGRATING, "'"+mName+"'");
                rs.next();
                rAvg = rs.getInt(1);
                
                database.close();
            }
        }
        catch (SQLException ex) {ex.printStackTrace(); }
        return rAvg;
    }

    public int countRequest()
    {
        ResultSet rs;
        int reqCount = 0;
        try
        {
            if(!database.open()){return 0;}
            else
            {
                rs = database.executeProcedure(MijdasDB.Procedure.COUNT_REQUESTS," ");
                rs.next();
                reqCount = rs.getInt(1);
                database.close();
            }
        }
        catch (SQLException ex) {ex.printStackTrace(); }
        return reqCount;
    }
    
    public int countMessage()
    {
        ResultSet rs;
        int mesCount = 0;
        try
        {
            if(!database.open()){return 0;}
            else
            {
                rs = database.executeProcedure(MijdasDB.Procedure.COUNT_MESSAGE," ");
                rs.next();
                mesCount = rs.getInt(1);
                database.close();
            }
        }
        catch (SQLException ex) {ex.printStackTrace(); }
        return mesCount;
    }
    
    public boolean writeService(String...formData) {
        Insertable[] fields;
        try {
            if( !database.open() ) {
                throw new SQLException("Error writing service request to database");
            } else {
                fields = new MijdasDB.Service_Requests[]{MijdasDB.Service_Requests.USERNAME, MijdasDB.Service_Requests.ADDRESS, MijdasDB.Service_Requests.DETAILS};
                database.writeToStorage(MijdasDB.Tables.SERVICE_REQUESTS, fields, formData[0], formData[1], formData[2]);
                return true;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void deleteService(String requestNum) {
        try {
            if( !database.open() ) {
                throw new SQLException("Error deleting service request from database");
            } else {
                //int req = Integer.parseInt(requestNum);
                String whereClause = "requestNum = " + requestNum;
                database.deleteData(MijdasDB.Tables.SERVICE_REQUESTS, whereClause);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateServiceMechanic(String requestNum, String mechanic) {
        try {
            if( !database.open() ) {
                throw new SQLException("Error updating mechanic in service_request table");
            } else {
                
                String whereClause = "requestNum = " + requestNum;
                database.updateData(MijdasDB.Tables.SERVICE_REQUESTS, whereClause, MijdasDB.Service_Requests.MECHANICUSERNAME, mechanic);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateServiceAccepted(String requestNum, String isAccepted) {
        try {
            if( !database.open() ) {
                throw new SQLException("Error updating isAccepted in service_request table");
            } else {
                
                String whereClause = "requestNum = " + requestNum;
                database.updateData(MijdasDB.Tables.SERVICE_REQUESTS, whereClause, MijdasDB.Service_Requests.ISACCEPTED, isAccepted);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateServiceComplete(String requestNum, String isComplete) {
        try {
            if( !database.open() ) {
                throw new SQLException("Error updating isCompleted in service_request table");
            } else {
                
                String whereClause = "requestNum = " + requestNum;
                database.updateData(MijdasDB.Tables.SERVICE_REQUESTS, whereClause, MijdasDB.Service_Requests.ISCOMPLETE, isComplete);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
