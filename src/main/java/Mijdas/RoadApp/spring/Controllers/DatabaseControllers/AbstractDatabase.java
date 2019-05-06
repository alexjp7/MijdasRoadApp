
package Mijdas.RoadApp.spring.Controllers.DatabaseControllers;

/*
    Might not actually use this, having generic methods is annoying for specifc databse types
*/
public interface AbstractDatabase
{
    public void writeToStorage(String query);
    public String readData();
    public boolean hasData();
}
