
package Mijdas.RoadApp.spring.Controllers.DatabaseControllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/***********************************************************************
 * SQLDatabase is a wrapper for the java database-connector methods.
 * As such, the methods contained will interface with the selected MySQL MijdasDB.
 *************************************************************************/
public class SQLDatabase 
{   
    
    private Connection connection;
    private String host,username,password;
    private Statement stmt;
    private ResultSet result;
    
    public SQLDatabase(String host, int port,String databaseName, String username, String password) 
    {   
         //Concatenated host string in relation to location of project dependancy 
        this.host     = "jdbc:mysql://"+host+":"+port+"/"+databaseName;
        this.username = username; 
        this.password = password;
    }
    
    public boolean open() throws SQLException
    {
        connection = DriverManager.getConnection(host,username,password);
        return !connection.isClosed();
    }
    
    public void close() throws SQLException
    {
        connection.close();
    }
    /********************************************************************
     * 
     * @param table - Enumerated values for the existing tables
     * @param whereClause - optional, to allow for additional where clauses
     *              should be set to null if not necessary.            
     * @param values - the fields to be requested from SQL table.
     * @return - returns the appropriate result set
     * @throws SQLException 
     * 
     * TO-DO add JOIN/UNION capabilites (probably best overloaded)
     ********************************************************************/
    public ResultSet readData(MijdasDB.Tables table, String whereClause, String...values) throws SQLException 
    {
        
        ResultSet rs;
        String fields = SQLFieldsToString(values);
        stmt = connection.createStatement();

        //if no where clause is present
        if(whereClause == null)
        {
            rs = stmt.executeQuery("SELECT "+fields+" FROM "+ table.getTableName());
        }
        else
        {
            rs = stmt.executeQuery("SELECT "+fields+" FROM "+ table.getTableName()+" WHERE " + whereClause);
        }
  
        
        return rs;  
    }
    /*********************************************************
     * TO-DO: Implement writing to database (INSERT/UPDATE)
     * @param table - Enumerated values for the existing tables
     * @param values - the values to be inserted into the table
    **********************************************************/
    
    public void writeToStorage(MijdasDB.Tables table, String...values) throws SQLException
    {
        String fields = SQLFieldsToString(values);
        stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO "+ table.getTableName() + " VALUES ("+fields+")");
    }  
    
    /**********************************************************
     * Overload for writeToStorage 
     * @param params - additional SQL inert parameters
     * must be split by comma (,) AND ordered the same as values.
     **********************************************************/
    public void writeToStorage(MijdasDB.Tables table, Insertable[] params, String...values) throws SQLException
    {
        String sqlString = null;
        String sqlValues = SQLFieldsToString(values);
        String sqlParams = SQLParamToString(params);
        
        //Build String
        sqlString = "INSERT INTO ";
        sqlString += table.getTableName();
        sqlString +="("+sqlParams+") ";
        sqlString +="VALUES (" + sqlValues +")";
        
        stmt = connection.createStatement();
        stmt.executeUpdate(sqlString);
    }  


    private String SQLFieldsToString(String...fields)
    {
        String s="";

        //Concat SQL fields 
        for (int i = 0; i < fields.length; i++) 
        {
            s += fields[i];
            //Only append , to intermediary fields.
            if(i < fields.length - 1)
            {
                s +=",";
            }
        }
        return s;
    }
    
    private String SQLParamToString(Insertable[] params)
    {
        String s ="";
        
       for(int i = 0; i < params.length ; i++)
       {
           s += params[i].getValue();
            //Only append , to intermediary fields.
           if(i != params.length -1)
           {
               s += ",";
           }
       }
        return s;
    }

    /************************************************
     * 
     * @param table - table to be checked to have data
     * @return - represents if table has data
     *************************************************/
    public boolean hasData(MijdasDB.Tables table)
    { 
        try
        {
            open();
            ResultSet rs = readData(table,null,"*");
            
            if(!rs.next())
            {
                return true;
            }    
            close();
        }
        catch(SQLException e){e.printStackTrace();}
   
        return false;
    }
    
    
}


