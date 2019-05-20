package au.edu.sydney.brawndo.erp.spfea.products;

import au.edu.sydney.brawndo.erp.ordering.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsSingleton {
    private static volatile ArrayList<Product> instance=null;
    private ProductsSingleton(){}
    public static synchronized List<Product> getInstance()
    {
        if(instance==null)
        {
            instance=new ArrayList<>();
        }
        instance.clear();
        for(Product p : ProductDatabase.getTestProducts()){
            instance.add(p);
        }
        return instance;
    }
}
