package au.edu.sydney.brawndo.erp.spfea.ordering;

import au.edu.sydney.brawndo.erp.ordering.Order;
import au.edu.sydney.brawndo.erp.ordering.Product;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * New order component
 */
public class OrderComponent implements Order {
    private int discountType;

    public int getDiscountType() {
        return discountType;
    }

    public OrderComponent setDiscountType(int discountType) {
        this.discountType = discountType;
        return this;
    }

    @Override
    public int getOrderID() {
        return 0;
    }

    @Override
    public double getTotalCost() {
        return 0;
    }

    @Override
    public LocalDateTime getOrderDate() {
        return null;
    }

    @Override
    public void setProduct(Product product, int qty) {

    }

    @Override
    public Set<Product> getAllProducts() {
        return null;
    }

    @Override
    public int getProductQty(Product product) {
        return 0;
    }

    @Override
    public String generateInvoiceData() {
        return null;
    }

    @Override
    public int getCustomer() {
        return 0;
    }

    @Override
    public void finalise() {

    }

    @Override
    public Order copy() {
        return null;
    }

    @Override
    public String shortDesc() {
        return null;
    }

    @Override
    public String longDesc() {
        return null;
    }
}
