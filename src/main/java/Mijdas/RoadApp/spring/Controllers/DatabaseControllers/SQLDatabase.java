
package Mijdas.RoadApp.spring.Controllers.DatabaseControllers;

import java.sql.CallableStatement;
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
     * @param table - Enumerated values for the existing tables
     * @param whereClause - optional, to allow for additional where clauses
     *              should be set to null if not necessary.
     * @param values - the fields to be requested from SQL table.
     * @return - returns the appropriate result set
     * @throws SQLException
     *
     * TO-DO add JOIN/UNION capabilites (probably best overloaded)
     ********************************************************************/
    public ResultSet readData(MijdasDB.Tables table, String whereClause, Insertable...values) throws SQLException
    {
        ResultSet rs;
        String fields = SQLValuesToString(values);
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
    /*****************************************************************
     * To be used when ALL fields of a table are going to be inserted
     * @param table - Enumerated values for the existing tables
     * @param values - the values to be inserted into the table
    *******************************************************************/
    public void writeToStorage(MijdasDB.Tables table, String...values) throws SQLException
    {
        String sqlValues = SQLValuesToString(values, table);
        stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO "+ table.getTableName() + " VALUES ("+sqlValues+")");
    }

    /******************************************************************************
     * Overload for writeToStorage
     * @param fields - additional SQL inert parameters
     * @param values - the values to be inserted (in the same order as fields)
     ****************************************************************************/
    public void writeToStorage(MijdasDB.Tables table, Insertable[] fields, String...values) throws SQLException
    {
        String sqlString = null;
        String sqlValues = SQLValuesToString(fields,values);
        String sqlFields = SQLFieldsToString(fields);

        //Build String
        sqlString = "INSERT INTO ";
        sqlString += table.getTableName();
        sqlString +="("+sqlFields+") ";
        sqlString +="VALUES (" + sqlValues +")";
        System.out.println("SQLSTRING: "+sqlString);
        stmt = connection.createStatement();
        stmt.executeUpdate(sqlString);
    }

    public ResultSet executeFunction(MijdasDB.Function function, String value) throws SQLException
    {
        ResultSet rs = null;

        String sqlString;
        sqlString = "SELECT ";
        sqlString += function.getFunctionName() + "("+value+")";
        stmt = connection.createStatement();
        rs = stmt.executeQuery(sqlString);
        return rs;
    }
    
    public ResultSet executeProcedure(MijdasDB.Procedure procedure, String value) throws SQLException
    {
        ResultSet rs = null;
        String sqlString;
        sqlString = "CALL " + procedure.getProcedureName() + "(" + value + ")";
        stmt = connection.createStatement();
        rs = stmt.executeQuery(sqlString);
      
        return rs;
    }

    /***************************************************
     * Used to update a row in the SQL Database
     * @param table - table to be updated
     * @param whereClause - SQL "WHERE" clause
     * @param param - the field to be updated
     * @param value - the value of the new field
     ***************************************************/
    public void updateData(MijdasDB.Tables table,String whereClause, Insertable field, String value) throws SQLException
    {
        String sqlString = null;
        //Check if value is to be inserted as a string literal/
        value = field.isLiteral() ? "'" + value + "'" : value;
        //Build update query
        sqlString  ="UPDATE ";
        sqlString  += table.getTableName();
        sqlString  += " SET "+field.getValue();
        sqlString  += "=" + value+" WHERE "+ whereClause;

        System.out.println(sqlString);
        
        stmt = connection.createStatement();
        stmt.executeUpdate(sqlString);
    }

    /******************************************************
     * @param fields - SQL table fields
     * @return  - concatenated String value for fields
     * e.g. field1, field2, field3
     ***********************************************************/
    private String SQLValuesToString(Insertable...fields)
    {
        String s="";

        //Concat SQL fields
        for (int i = 0; i < fields.length; i++)
        {
            s += fields[i].getValue();
            //Only append , to intermediary fields.
            if(i < fields.length - 1)
            {
                s +=",";
            }
        }
        return s;
    }

    /***********************************
     *
     * @param values - values to be concatenated
     * @param table - the table of which
     * @return
     */
    private String SQLValuesToString(String[] values, MijdasDB.Tables table)
    {
        String s ="";
        boolean [] isLiteral = table.isLiteralField();

        for(int i = 0; i < values.length ; i++)
        {
             s += isLiteral[i] ? "'" + values[i] + "'": values[i];
             if(i != values.length -1)
             { //Only append , to intermediary fields
                 s += ",";
             }
        }
        return s;
    }

    /*****************************************************************************
     * @param fields - SQL table fields
     * @param values - the value that will insert/update into SQL table
     * @return  - concatenated String value for fields
     ******************************************************************************/
    private String SQLValuesToString(Insertable[] fields, String[] values)
    {
        String s="";

        //Concat SQL fields
        for (int i = 0; i < values.length; i++)
        {
            //Add quotes to String values  if there data type requires it
            s +=  fields[i].isLiteral() ? "'" + values[i] + "'" : values[i];
            //Only append , to intermediary fields.
            if(i < values.length - 1)
            {
                s +=",";
            }
        }
        return s;
    }

    private String SQLFieldsToString(Insertable[] params)
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
    /*
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
    */
}
