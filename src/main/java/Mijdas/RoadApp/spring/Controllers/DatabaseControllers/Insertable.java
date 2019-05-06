
package Mijdas.RoadApp.spring.Controllers.DatabaseControllers;

/**********************************************************************
 * designed to be implemented on enum types in MijdasTable class field
 ***********************************************************************/
public interface Insertable
{
    /**********************************************************
     * @return - the string name for a column name in SQL table
    **********************************************************/
    public String getValue();

    /**********************************************************
     * @return - whether the table field is intended to
     * be inserted as a string literal
    **********************************************************/
    public boolean isLiteral();

}
