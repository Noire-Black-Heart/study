package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.ordering.Customer;

public class ContactContext {
	
	// the context class for the STRATEGY pattern on contact handling
		private ContactStrategy strategy = null;
		
		public ContactContext(ContactStrategy strategy) {
			this.strategy = strategy;
		}
		
		
		public boolean sendInvoice(AuthToken token, Customer customer, String data) {
			
			//return false for null(unchosen) strategy
			if(this.strategy == null) {
				return false;
			}
			//execution corresponding strategy
			return strategy.sendContact(token, customer, data);
			
		}
		
}
