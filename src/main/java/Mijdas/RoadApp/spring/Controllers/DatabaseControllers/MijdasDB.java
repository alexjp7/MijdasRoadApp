
package Mijdas.RoadApp.spring.Controllers.DatabaseControllers;
/*

* Enumerated values to represent the database being used
* these are used to reduce errors in typing sql strings.
TODO:
    * Fill in rest of tables

*/

public class MijdasDB
{
    //Table strucutre of fields (in order)- whether or not they are required to be literal or not
    private static final boolean[] userFields         = new boolean[]{true,true,true,true,true};
    private static final boolean[] motoristFields     = new boolean[]{true,false,true,false};
    private static final boolean[] mechanicFields     = new boolean[]{true,false,false};
    //Not yet implemented.
    private static  boolean[] transactionFields;
    private static  boolean[] vehicleFields;      
    
    public enum Tables 
    {
        USER("USER",userFields),
        MOTORISTS("MOTORIST", motoristFields),
        MECHANICS("MECHANIC",mechanicFields),
        TRANSACTIONS("TRANSACTIONS",transactionFields),
        VECHILE("VEHICLE",vehicleFields);

        private final String name;
        private boolean[] isFieldLiteral;
        private Tables(String name, boolean[] isFieldLiteral)
        {
            this.name = name;
            this.isFieldLiteral = isFieldLiteral;
        }

        public String getTableName()
        {
            return name;
        }
        
        public boolean[] isLiteralField()
        {
            return isFieldLiteral;
        }
    }
    
    public enum User implements Insertable
    {
        USERNAME("username",true),
        PASSWORD("password",true),
        FNAME("fName",true),
        LNAME("lName",true),
        EMAIL("email",true);

        private final String value;
        private final boolean isLiteral;
        private User(String value, boolean isLiteral)
        {
            this.value = value;
            this.isLiteral = isLiteral;
        }
        @Override
        public String getValue()
        {
            return value;
        }

        @Override
        public boolean isLiteral()
        {
           return isLiteral;
        }

    }

    public enum Motorist implements Insertable
    {
       USERNAME("username",true),
       HASMEMBERSHIP("hasMembership",false),
       PAYMENTOPTION("paymentOption",true),
       LICENSE("license",false);

       private final String value;
       private final boolean isLiteral;
       private Motorist(String value, boolean isLiteral)
       {
           this.value = value;
           this.isLiteral = isLiteral;
       }
       @Override
       public String getValue()
       {
           return value;
       }

        @Override
        public boolean isLiteral()
        {
           return isLiteral;
        }
    }

    
    public enum Mechanic implements Insertable
    {
        USERNAME("username",true),
        STARS("qualityInStars",false),
        LICENSE("license",false);
  
        private final String value;
        private final boolean isLiteral;
        private Mechanic(String value, boolean isLiteral)
        {
            this.value = value;
            this.isLiteral = isLiteral;
        }
        @Override
        public String getValue()
        {
            return value;
        }

        @Override
        public boolean isLiteral()
        {
           return isLiteral;
        }
    }

}





