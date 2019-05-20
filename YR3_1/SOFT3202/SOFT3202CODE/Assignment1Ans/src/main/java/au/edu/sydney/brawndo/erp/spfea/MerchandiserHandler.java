package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.contact.Merchandiser;

public class MerchandiserHandler implements ContactStrategy {
    @Override
    public boolean doSend(ContactBean bean) {
        String merchandiser = bean.getCustomer().getMerchandiser();
        String businessName = bean.getCustomer().getBusinessName();
        if (null != merchandiser && null != businessName) {
            Merchandiser.sendInvoice(bean.getToken(), bean.getCustomer().getfName(), bean.getCustomer().getlName(), bean.getData(), merchandiser,businessName);
            return true;
        }
        return false;
    }
}
