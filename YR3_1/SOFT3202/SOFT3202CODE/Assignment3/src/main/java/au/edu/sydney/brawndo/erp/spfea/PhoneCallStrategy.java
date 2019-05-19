package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.contact.PhoneCall;
import au.edu.sydney.brawndo.erp.ordering.Customer;

public class PhoneCallStrategy implements ContactStrategy {

	@Override
	public boolean sendContact(AuthToken token, Customer customer, String data) {
		// TODO Auto-generated method stub
		String phone = customer.getPhoneNumber();
        if (null != phone) {
            PhoneCall.sendInvoice(token, customer.getfName(), customer.getlName(), data, phone);
            return true;
        }
		
		return false;
	}

}
