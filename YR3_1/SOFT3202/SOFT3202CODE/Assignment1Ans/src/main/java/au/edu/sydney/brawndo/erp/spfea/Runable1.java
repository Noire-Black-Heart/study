package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.database.TestDatabase;
import au.edu.sydney.brawndo.erp.ordering.Order;

public class Runable1 implements Runnable{
    private AuthToken token;
    private Order order;
    public Runable1(AuthToken token,Order order){
        this.token = token;
        this.order = order;
    }

    @Override
    public void run() {
        TestDatabase.getInstance().saveOrder(token, order);
    }
}
