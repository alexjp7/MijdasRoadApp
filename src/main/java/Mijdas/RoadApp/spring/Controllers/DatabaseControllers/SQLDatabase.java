
package Mijdas.RoadApp.spring.Controllers.DatabaseControllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/***********************************************************************
 * 
    SQLDatabase is a wrapper for the java database-connector methods/
 * As such, the methods contained will interface with the selected MySQL DB.
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
        return connection.isClosed();
    }
    
    public void close() throws SQLException
    {
        connection.close();
    }
/********************************************************************
 * 
 * @param table - Enumerator values for the existing tables
 * @param whereClause - optional, to allow for additional where clauses
 *              should be set to null if not necessary.
 * @param values - the fields to be requested from SQL table.
 * @return - returns the appropriate result set
 * @throws SQLException 
 * 
 * TO-DO add JOIN/UNION capabilites (probably best overloaded)
 ********************************************************************/
    public ResultSet readData(MijdasTables table, String whereClause, String...values) throws SQLException 
    {
        
        ResultSet rs;
        String fields="";
        stmt = connection.createStatement();
        
        //Concat SQL fields 
        for (int i = 0; i < values.length; i++) 
        {
            fields  += values[i];
            //Only append , to intermediary fields.
            if(i < values.length - 1)
            {
                fields +=",";
            }
        }
       
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
    
    public void writeToStorage(MijdasTables table)
    {
        
        
    }


    
//         Statement smt = con.createStatement();
//            
//            ResultSet rs = smt.executeQuery("select*from test");
//            while(rs.next())
//            {
//            System.out.println(rs.getString(1)+rs.getString(2));
//            }
    public boolean hasData() 
    {
        return false;
    }
    
    
}
