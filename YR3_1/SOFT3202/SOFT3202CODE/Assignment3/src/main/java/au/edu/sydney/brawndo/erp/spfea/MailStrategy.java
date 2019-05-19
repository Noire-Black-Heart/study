package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.contact.Mail;
import au.edu.sydney.brawndo.erp.ordering.Customer;

public class MailStrategy implements ContactStrategy {

	@Override
	public boolean sendContact(AuthToken token, Customer customer, String data) {
		// TODO Auto-generated method stub
        String address = customer.getAddress();
        String suburb = customer.getSuburb();
        String state = customer.getState();
        String postcode = customer.getPostCode();
        if (null != address && null != suburb &&
                null != state && null != postcode) {
            Mail.sendInvoice(token, customer.getfName(), customer.getlName(), data, address, suburb, state, postcode);
            return true;
        }

		
		return false;
	}

}
