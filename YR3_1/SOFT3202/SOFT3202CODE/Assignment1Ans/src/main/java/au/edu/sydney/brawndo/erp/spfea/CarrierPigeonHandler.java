package au.edu.sydney.brawndo.erp.spfea;

import au.edu.sydney.brawndo.erp.contact.CarrierPigeon;

public class CarrierPigeonHandler implements ContactStrategy {
    @Override
    public boolean doSend(ContactBean bean) {
        String pigeonCoopID = bean.getCustomer().getPigeonCoopID();
        if (null != pigeonCoopID) {
            CarrierPigeon.sendInvoice(bean.getToken(), bean.getCustomer().getfName(), bean.getCustomer().getlName(), bean.getData(), pigeonCoopID);
            return true;
        }
        return false;
    }
}
