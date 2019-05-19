package au.edu.sydney.brawndo.erp.spfea.ordering;

import au.edu.sydney.brawndo.erp.ordering.Order;
import au.edu.sydney.brawndo.erp.ordering.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Decorator order creation class
 * Inherit the OrderComponent order parent class
 */
public class OrderDecorator extends OrderComponent{
    private Order order;
    private Map<Integer,Discount> map = new HashMap<>();

    /**
     * Initialize order parameters
     * @param order
     * @param dBean
     * @param discountType
     */
    public OrderDecorator(Order order,DiscountBean dBean,int discountType){
        this.order = order;
        initMap(dBean,order);
        setDiscountType(discountType);
    }

    /**
     * Register a discount type via initMap
     * @param dBean  Discount class instance
     * @param order  Order instance
     */
    private void initMap(DiscountBean dBean,Order order){
        map.put(1,new Discount1(dBean,order));
        map.put(2,new Discount2(dBean,order));
    }

    @Override
    public double getTotalCost(){
        //Returns the total price of the corresponding instance order when getting the total price
        this.map.get(getDiscountType()).setOrder(order);
       return this.map.get(getDiscountType()).getTotalCost();
    }

    @Override
    public String generateInvoiceData(){
        //Return the data of the instance order
        return this.map.get(getDiscountType()).generateInvoiceData();
    }
    @Override
    public String shortDesc(){
        //Return a brief description of the instance order
        return this.map.get(getDiscountType()).shortDesc();
    }
    @Override
    public String longDesc(){
        //Return a detailed description of the instance order
        return this.map.get(getDiscountType()).longDesc();
    }

    @Override
    public void setProduct(Product product, int qty) {
        order.setProduct(product,qty);
    }

    public Discount getRegistMap(Integer discountType){
        return map.get(discountType);
    }
}
