package au.edu.sydney.brawndo.erp.spfea.ordering;

import au.edu.sydney.brawndo.erp.ordering.Order;
import au.edu.sydney.brawndo.erp.ordering.Product;

import java.util.HashMap;
import java.util.Map;

public class OrderDecorator extends OrderComponent{
    private Order order;
    private Map<Integer,Discount> map = new HashMap<>();
    public OrderDecorator(Order order,DiscountBean dBean,int discountType){
        this.order = order;
        initMap(dBean,order);
        setDiscountType(discountType);
    }

    private void initMap(DiscountBean dBean,Order order){
        map.put(1,new Discount1(dBean,order));
        map.put(2,new Discount2(dBean,order));
    }

    @Override
    public double getTotalCost(){
        this.map.get(getDiscountType()).setOrder(order);
       return this.map.get(getDiscountType()).getTotalCost();
    }

    @Override
    public String generateInvoiceData(){
        return this.map.get(getDiscountType()).generateInvoiceData();
    }
    @Override
    public String shortDesc(){

        return this.map.get(getDiscountType()).shortDesc();
    }
    @Override
    public String longDesc(){
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
