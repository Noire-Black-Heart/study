package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.auth.AuthToken;

public interface CustomerConnection {
    String getCustomerField(AuthToken token, int id);
}
