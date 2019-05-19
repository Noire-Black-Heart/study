package au.edu.sydney.brawndo.erp.spfea.ordering;

import java.util.Map;

import au.edu.sydney.brawndo.erp.ordering.Product;


//this class stand for the flat discount
public class DiscountFlat implements Discount {

	private int discountThreshold;
	private double discountRate;
	
	public DiscountFlat(int discountThreshold, double discountRate) {
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
            cost +=  products.get(product) * product.getCost() * discountRate;
        }
        return cost;
	}

}
