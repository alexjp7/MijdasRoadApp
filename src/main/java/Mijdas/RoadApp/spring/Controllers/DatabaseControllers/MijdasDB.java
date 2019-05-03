
package Mijdas.RoadApp.spring.Controllers.DatabaseControllers;
/*

TODO:
    * Fill in rest of tables

*/

public class MijdasDB
{
    public enum Tables 
    {
        USER("USER"),
        MOTORISTS("MOTORIST"),
        MECHANICS("MECHANIC"),
        TRANSACTIONS("TRANSACTIONS"),
        VECHILE("VEHICLE"),
        TEST("TEST");

        private final String name;
        private Tables(String name)
        {
            this.name = name;
        }

        public String getTableName()
        {
            return name;
        }
    }
    
    public enum User implements Insertable
    {
        USERNAME("username"),
        PASSWORD("password"),
        FNAME("fName"),
        LNAME("lName"),
        EMAIL("email");

        private final String value;
        private User(String value)
        {
            this.value = value;
        }
        @Override
        public String getValue()
        {
            return value;
        }

    }

    public enum Motorist implements Insertable
    {
       USERNAME("username"),
       HASMEMBERSHIP("hasMembership"),
       PAYMENTOPTION("paymentOption"),
       LICENSE("license");

       private final String value;
       private Motorist(String value)
       {
           this.value = value;
       }
       @Override
       public String getValue()
       {
           return value;
       }
    }

    
    public enum Mechanic implements Insertable
    {
        USERNAME("username"),
        STARS("password"),
        LICENSE("fName");
  
        private final String value;
        private Mechanic(String value)
        {
            this.value = value;
        }
        @Override
        public String getValue()
        {
            return value;
        }

    }

}





