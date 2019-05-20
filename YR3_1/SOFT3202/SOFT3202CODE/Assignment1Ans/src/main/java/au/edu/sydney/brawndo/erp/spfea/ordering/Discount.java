package au.edu.sydney.brawndo.erp.spfea.ordering;

import au.edu.sydney.brawndo.erp.ordering.Order;

public interface Discount {
    double getTotalCost();
    String generateInvoiceData();
    String shortDesc();
    String longDesc();
    void setOrder(Order order);
}
