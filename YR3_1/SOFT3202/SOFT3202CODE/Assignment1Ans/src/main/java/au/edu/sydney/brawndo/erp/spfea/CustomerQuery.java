package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.database.TestDatabase;

public class CustomerQuery implements CustomerConnection{
    private String name;
    public CustomerQuery(String name){
        this.name = name;
    }
    @Override
    public String getCustomerField(AuthToken token, int id) {
        return TestDatabase.getInstance().getCustomerField(token, id, name);
    }
}
