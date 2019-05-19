package au.edu.sydney.brawndo.erp.spfea.products;

import au.edu.sydney.brawndo.erp.ordering.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Products singleton class, query products get from here
 * Using the singleton pattern, each Product can only produce one object, so when comparing two objects is equal,
 * just compare the addresses of the two Product objects is equal
 */
public class ProductsSingleton {
    private static volatile ArrayList<Product> instance=null;
    private ProductsSingleton(){}
    public static synchronized List<Product> getInstance()
    {
        //Determine whether the product instance exists. If it is, you do not need to recreate it. Otherwise, recreate the instance.
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
