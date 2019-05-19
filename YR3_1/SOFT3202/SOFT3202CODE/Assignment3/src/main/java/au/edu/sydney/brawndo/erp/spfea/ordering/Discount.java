package au.edu.sydney.brawndo.erp.spfea.ordering;

import java.util.Map;

import au.edu.sydney.brawndo.erp.ordering.Product;

public interface Discount {
	public double getDiscountRate();
	public int getDiscountThreshold();
	public double getTotalCost(Map<Product, Integer> products);
}
