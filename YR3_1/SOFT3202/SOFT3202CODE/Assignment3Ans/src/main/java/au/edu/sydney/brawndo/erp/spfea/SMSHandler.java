package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.contact.SMS;

public class SMSHandler implements ContactStrategy {
    public SMSHandler(){}
    @Override
    public boolean doSend(ContactBean bean) {
        String smsPhone = bean.getCustomer().getPhoneNumber();
        if (null != smsPhone) {
            SMS.sendInvoice(bean.getToken(), bean.getCustomer().getfName(), bean.getCustomer().getlName(), bean.getData(), smsPhone);
            return true;
        }
        return false;
    }
}
