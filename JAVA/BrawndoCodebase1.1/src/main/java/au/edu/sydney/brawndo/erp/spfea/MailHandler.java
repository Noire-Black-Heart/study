package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.contact.Mail;

/**
 * Mail contact class, implementing the ContactStrategy interface
 */
public class MailHandler implements ContactStrategy {
    @Override
    public boolean doSend(ContactBean bean) {
        String address = bean.getCustomer().getAddress();
        String suburb = bean.getCustomer().getSuburb();
        String state = bean.getCustomer().getState();
        String postcode = bean.getCustomer().getPostCode();
        if (null != address && null != suburb &&
                null != state && null != postcode) {
            Mail.sendInvoice(bean.getToken(), bean.getCustomer().getfName(), bean.getCustomer().getlName(), bean.getData(), address, suburb, state, postcode);
            return true;
        }
        return false;
    }
}
