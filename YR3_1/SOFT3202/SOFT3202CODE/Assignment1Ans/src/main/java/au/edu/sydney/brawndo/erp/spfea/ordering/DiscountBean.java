package au.edu.sydney.brawndo.erp.spfea.ordering;

import au.edu.sydney.brawndo.erp.ordering.Product;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DiscountBean {
    private Set<Product> products;
    private int id;
    private LocalDateTime date;
    private int customerID;
    private double discountRate;
    private int numShipments;
    private int discountThreshold;
    private boolean business;
    private boolean subscription;

    public boolean isBusiness() {
        return business;
    }

    public DiscountBean setBusiness(boolean business) {
        this.business = business;
        return this;
    }

    public boolean isSubscription() {
        return subscription;
    }

    public DiscountBean setSubscription(boolean subscription) {
        this.subscription = subscription;
        return this;
    }

    public int getDiscountThreshold() {
        return discountThreshold;
    }

    public DiscountBean setDiscountThreshold(int discountThreshold) {
        this.discountThreshold = discountThreshold;
        return this;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public DiscountBean setProducts(Set<Product> products) {
        this.products = products;
        return this;
    }

    public int getId() {
        return id;
    }

    public DiscountBean setId(int id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public DiscountBean setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public int getCustomerID() {
        return customerID;
    }

    public DiscountBean setCustomerID(int customerID) {
        this.customerID = customerID;
        return this;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public DiscountBean setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
        return this;
    }

    public int getNumShipments() {
        return numShipments;
    }

    public DiscountBean setNumShipments(int numShipments) {
        this.numShipments = numShipments;
        return this;
    }
}
