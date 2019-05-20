package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.contact.Email;

public class EmailHandler implements ContactStrategy {
    @Override
    public boolean doSend(ContactBean bean) {
        String email = bean.getCustomer().getEmailAddress();
        if (null != email) {
            Email.sendInvoice(bean.getToken(), bean.getCustomer().getfName(), bean.getCustomer().getlName(), bean.getData(), email);
            return true;
        }
        return false;
    }
}
