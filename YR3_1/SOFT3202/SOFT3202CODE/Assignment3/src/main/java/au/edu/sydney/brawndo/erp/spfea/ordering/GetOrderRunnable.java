package au.edu.sydney.brawndo.erp.spfea.ordering;

import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.database.TestDatabase;
import au.edu.sydney.brawndo.erp.ordering.Order;

public class GetOrderRunnable implements Runnable {
	private AuthToken token;
	private int orderID;
	private volatile Order order;
	
	public GetOrderRunnable(AuthToken token,int orderID){
        this.token = token;
        this.orderID = orderID;
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		order = TestDatabase.getInstance().getOrder(token, orderID);
	}
	
	public Order getOrder() {
		return order;
	}

}
