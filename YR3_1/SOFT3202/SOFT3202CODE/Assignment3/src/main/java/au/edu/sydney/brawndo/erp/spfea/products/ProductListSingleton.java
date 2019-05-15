package au.edu.sydney.brawndo.erp.spfea.products;

import au.edu.sydney.brawndo.erp.ordering.Product;

import java.util.ArrayList;

public class ProductListSingleton {
	
	//this singleton class avoids creating a new list object each time getting products from database, since we only need one list for all access
	
    private static ArrayList<Product> instance;
    
    private ProductListSingleton(){}
    
    public static ArrayList<Product> getInstance(){
        if(instance == null){
            instance = new ArrayList<Product>();
            //fill in the products
            for(Product p : ProductDatabase.getTestProducts()){
                instance.add(p);
            }
        }
        return instance;
    }
}
