package au.edu.sydney.brawndo.erp.spfea;

import java.util.HashMap;
import java.util.Map;

public class CustomerConnFactory {
    private static Map<String,CustomerConnection> flyweights = new HashMap<>();
    public  static CustomerConnection getConn(String key){
        if(!flyweights.containsKey(key)){
            flyweights.put(key,new CustomerQuery(key));
        }
        return flyweights.get(key);
    }
}
