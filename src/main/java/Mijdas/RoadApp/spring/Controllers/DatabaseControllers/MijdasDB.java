
package Mijdas.RoadApp.spring.Controllers.DatabaseControllers;
/*

* Enumerated values to represent the database being used
* these are used to reduce errors in typing sql strings.
TODO:
    * Fill in rest of tables
*/

public class MijdasDB
{
    public enum Tables
    {
        USER("USER",userFields),
        MOTORISTS("MOTORIST", motoristFields),
        MECHANICS("MECHANIC",mechanicFields),
        SERVICE_REQUESTS("SERVICE_REQUEST",serviceRequestFields),
        TRANSACTIONS("TRANSACTIONS",transactionFields),
        MESSAGE("MESSAGE",messageFields),
        VEHICLE("VEHICLE",vehicleFields);


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
    public enum Message implements Insertable
    {
        ID("id",false),
	MOTORISTUSERNAME("motoristUsername",true),
	MECHANICUSERNAME("mechanicUsername",true),
	MESSAGETEXT("messageText",true),
	MOTORISTSENT("motoristSent",false);

        private final String value;
        private final boolean isLiteral;
        private Message(String value, boolean isLiteral)
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
    public enum Service_Requests implements Insertable
    {
        REQUESTNUM("requestNum",false),
	USERNAME("motoristUsername",true),
	ADDRESS("nearestAddress",true),
	DETAILS("details",true),
	LATITUDE("latitude",false),
	LONGITUDE("longitude",false);

        private final String value;
        private final boolean isLiteral;
        private Service_Requests(String value, boolean isLiteral)
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
    
    public enum Vehicle implements Insertable
    {
        LICENSE("license",false),
	REGPLATE("regPlate",true),
	MANUFACTURER("manufacturer",true),
	MODEL("model",true),
	COLOR("color",true);

        private final String value;
        private final boolean isLiteral;
        private Vehicle(String value, boolean isLiteral)
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

    public enum Function
    {
        CHECK_MOTORIST("checkMotorist"),
        CHECK_MECHANIC("checkMechanic");

        private String value;
        private Function(String value)
        {
            this.value = value;
        }

        public String getFunctionName()
        {
            return value;
        }
    }

    public enum Procedure
    {
        GET_MOTORIST("getMotorist"),
        GET_MECHANIC("getMechanic"),
        GET_VEHICLE("getVehicle"),
        GET_REQUEST("getRequest"),
        GET_ALL_REQUEST("getAllRequest"),
        GET_MESSAGE("getMessage"),
        COUNT_REQUESTS("countRequests"),
        COUNT_MESSAGE("countMessage");
            
        private String value;
        private Procedure(String value)
        {
            this.value = value;
        }

        public String getProcedureName()
        {
            return value;
        }
    }

    //Table strucutre of fields (in order)- whether or not they are required to be literal or not
    private static final boolean[] userFields         = new boolean[]{true,true,true,true,true};
    private static final boolean[] motoristFields     = new boolean[]{true,false,true,false};
    private static final boolean[] mechanicFields     = new boolean[]{true,false,false};
    private static final boolean[] serviceRequestFields  = new boolean[]{false,true,true,true,false};
    private static final boolean[] messageFields      = new boolean[]{false,true,true,true,false};
    private static final boolean[] vehicleFields     = new boolean[]{false,true,true,true,true};
    //Not yet implemented.
    private static  boolean[] transactionFields;
}
