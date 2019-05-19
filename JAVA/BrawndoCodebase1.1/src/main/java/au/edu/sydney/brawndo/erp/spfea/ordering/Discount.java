package au.edu.sydney.brawndo.erp.spfea.ordering;

import au.edu.sydney.brawndo.erp.ordering.Order;

/**
 * Discount type abstract class
 * Each type of discount is implemented
 * getTotalCost
 * generateInvoiceData
 * shortDesc
 * longDesc
 * setOrder
 * Method for materializing each discount instance
 */
public interface Discount {
    double getTotalCost();
    String generateInvoiceData();
    String shortDesc();
    String longDesc();
    void setOrder(Order order);
}
