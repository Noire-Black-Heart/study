package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.contact.CarrierPigeon;
import au.edu.sydney.brawndo.erp.ordering.Customer;

public class CarrierPigeonStrategy implements ContactStrategy {

	@Override
	public boolean sendContact(AuthToken token, Customer customer, String data) {
		// TODO Auto-generated method stub
		String pigeonCoopID = customer.getPigeonCoopID();
        if (null != pigeonCoopID) {
            CarrierPigeon.sendInvoice(token, customer.getfName(), customer.getlName(), data, pigeonCoopID);
            return true;
        }
		
		return false;
	}

}
