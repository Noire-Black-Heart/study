import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MemberTest {
	
	public static Inventory instance = Inventory.getInstance();
	
	public Product initProd() {
		Product apple = new Product("apple", 2.5);
		return apple;
	}
	
	@Test
	public void testSelect() {
		Member mem = new Member();
		Product apple = initProd();
		instance.addProduct(apple, 10);
		
		//null name
				boolean nullName = false;
				try {
					mem.selectProduct(null, 10);
				}catch(IllegalArgumentException e) {
					nullName = true;
				}
				assertTrue("did not throw exception having null name", nullName);
		
		//thing lower than 0
				boolean zeroPrice = false;
				try {
					mem.selectProduct(apple, 0);
				}catch(IllegalArgumentException e) {
					zeroPrice = true;
				}
				assertTrue("did not throw exception having zero things", zeroPrice);
		
		//no thing
				boolean nothing = false;
				try {
					mem.selectProduct(apple, 999);
				}catch(IllegalStateException e) {
					nothing = true;
				}
				assertTrue("dont throw when have no product", nothing);
		
	}
	
	@Test
	public void testReturn() {
		Member mem = new Member();
		Product apple = initProd();
		List<javafx.util.Pair<Product, Integer>> compareList = new ArrayList<>();
		compareList.add(new javafx.util.Pair<Product, Integer>(apple, 10));
		
		
		//null name
		boolean nullName = false;
		try {
			mem.returnProduct(null, 10);
		}catch(IllegalArgumentException e) {
			nullName = true;
		}
		assertTrue("did not throw exception having null name", nullName);

//thing lower than 0
		boolean zeroPrice = false;
		try {
			mem.returnProduct(apple, 0);
		}catch(IllegalArgumentException e) {
			zeroPrice = true;
		}
		assertTrue("did not throw exception having zero things", zeroPrice);

//no thing
		boolean nothing = false;
		try {
			mem.returnProduct(apple, 999);
		}catch(IllegalStateException e) {
			nothing = true;
		}
		assertTrue("dont throw when have no product", nothing);
		
	}
	

	

}
