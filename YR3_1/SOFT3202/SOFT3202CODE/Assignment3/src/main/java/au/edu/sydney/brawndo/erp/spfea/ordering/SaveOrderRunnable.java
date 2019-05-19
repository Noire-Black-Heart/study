package au.edu.sydney.brawndo.erp.spfea.ordering;

import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.database.TestDatabase;
import au.edu.sydney.brawndo.erp.ordering.Order;

public class SaveOrderRunnable implements Runnable {
	private AuthToken token;
    private Order order;
    public SaveOrderRunnable(AuthToken token,Order order){
        this.token = token;
        this.order = order;
    }

    @Override
    public void run() {
        TestDatabase.getInstance().saveOrder(token, order);
    }
}
