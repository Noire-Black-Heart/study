package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.database.TestDatabase;

/**
 * Analog database connection pool
 */
public class ConnectionPool {
    private static TestDatabase tb ;
    static {
        tb = TestDatabase.getInstance();
    }

    public static TestDatabase getConnection(){
        tb = TestDatabase.getInstance();
        /*if(tb == null){
            tb =  TestDatabase.getInstance();
        }*/
        return tb;
    }
}
