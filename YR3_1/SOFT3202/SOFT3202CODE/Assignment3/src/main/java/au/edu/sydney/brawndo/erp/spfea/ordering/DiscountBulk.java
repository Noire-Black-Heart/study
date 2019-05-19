package au.edu.sydney.brawndo.erp.spfea.ordering;

import java.util.Map;

import au.edu.sydney.brawndo.erp.ordering.Product;

//this class stand for the bulk discount
public class DiscountBulk implements Discount {
	
	private int discountThreshold;
	private double discountRate;
	
	public DiscountBulk(int discountThreshold, double discountRate) {
		this.discountRate = discountRate;
		this.discountThreshold = discountThreshold;
	}
	
	@Override
	public double getDiscountRate() {
		// TODO Auto-generated method stub
		return discountRate;
	}

	@Override
	public int getDiscountThreshold() {
		// TODO Auto-generated method stub
		return discountThreshold;
	}

	@Override
	public double getTotalCost(Map<Product, Integer> products) {
		// TODO Auto-generated method stub
		double cost = 0.0;
        for (Product product: products.keySet()) {
            int count = products.get(product);
            if (count >= discountThreshold) {
                cost +=  count * product.getCost() * discountRate;
            } else {
                cost +=  count * product.getCost();
            }
        }
        return cost;
	}

}
