
package Mijdas.RoadApp.spring.Controllers.DatabaseControllers;
/*

TODO:
    * Fill in rest of tables

*/

public enum MijdasTables 
{
    USERS("USER"),
    MOTORISTS("MOTORIST"),
    MECHANICS("MECHANIC"),
    TRANSACTIONS("TRANSACTIONS"),
    VECHILE("VEHICLE"),
    TEST("TEST");
    
    private final String name;
    
    private MijdasTables(String name)
    {
        this.name = name;
    }
    
    public String getTableName()
    {
        return name;
    }
}

