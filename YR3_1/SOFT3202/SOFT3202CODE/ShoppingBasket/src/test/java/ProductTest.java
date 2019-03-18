import static org.junit.Assert.*;

import org.junit.Test;

public class ProductTest {

	@Test
	public void productTestIllegalArgument() {
		//empty name
		boolean emptyName = false;
		try {
			Product myProduct = new Product("", 20);
		}catch(IllegalArgumentException e) {
			emptyName = true;
		}
		assertTrue("did not throw exception having empty name", emptyName);
		
		//null name
		boolean nullName = false;
		try {
			Product myProduct = new Product(null, 20);
		}catch(IllegalArgumentException e) {
			nullName = true;
		}
		assertTrue("did not throw exception having null name", nullName);
		
		//price lower than 0
		boolean zeroPrice = false;
		try {
			Product myProduct = new Product("haha", 0);
		}catch(IllegalArgumentException e) {
			zeroPrice = true;
		}
		assertTrue("did not throw exception having zero price", zeroPrice);
	}
	
	@Test
	public void getTest() {
		Product myProduct = new Product("cyka", 2.5);
		Double dd = myProduct.getPrice();
		
		assertEquals(myProduct.getName(), "cyka");
		assertEquals(dd.toString() + "cyka blyat" , myProduct.getPrice(), 2.5, 0.002);
		
		
	}

}
