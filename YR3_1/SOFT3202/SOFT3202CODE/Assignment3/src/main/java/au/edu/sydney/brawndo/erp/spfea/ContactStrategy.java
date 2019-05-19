package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.auth.*;
import au.edu.sydney.brawndo.erp.ordering.*;

// the strategy interface
public interface ContactStrategy {
		public boolean sendContact(AuthToken token, Customer customer, String data);
}
