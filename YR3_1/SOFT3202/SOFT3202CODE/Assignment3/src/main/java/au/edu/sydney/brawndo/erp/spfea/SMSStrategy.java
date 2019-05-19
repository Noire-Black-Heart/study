package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.contact.SMS;
import au.edu.sydney.brawndo.erp.ordering.Customer;

public class SMSStrategy implements ContactStrategy {

	@Override
	public boolean sendContact(AuthToken token, Customer customer, String data) {
		// TODO Auto-generated method stub
		String smsPhone = customer.getPhoneNumber();
        if (null != smsPhone) {
            SMS.sendInvoice(token, customer.getfName(), customer.getlName(), data, smsPhone);
            return true;
        }
		
		return false;
	}
	



}
