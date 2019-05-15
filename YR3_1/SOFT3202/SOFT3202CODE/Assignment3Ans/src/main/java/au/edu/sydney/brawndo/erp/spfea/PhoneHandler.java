package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.contact.PhoneCall;

public class PhoneHandler implements ContactStrategy {
    @Override
    public boolean doSend(ContactBean bean) {
        String phone = bean.getCustomer().getPhoneNumber();
        if (null != phone) {
            PhoneCall.sendInvoice(bean.getToken(), bean.getCustomer().getfName(), bean.getCustomer().getlName(), bean.getData(), phone);
            return true;
        }
        return false;
    }
}
