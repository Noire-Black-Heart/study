package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.contact.Email;
import au.edu.sydney.brawndo.erp.ordering.Customer;

public class EmailStrategy implements ContactStrategy {

	@Override
	public boolean sendContact(AuthToken token, Customer customer, String data) {
		// TODO Auto-generated method stub
		String email = customer.getEmailAddress();
        if (null != email) {
            Email.sendInvoice(token, customer.getfName(), customer.getlName(), data, email);
            return true;
        }
		
		return false;
	}

}
