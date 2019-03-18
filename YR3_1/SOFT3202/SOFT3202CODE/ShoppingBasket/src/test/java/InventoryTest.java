import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;
import org.junit.Test;

public class InventoryTest {

//    @Before
//    public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
//       Field instance = Inventory.class.getDeclaredField("instance");
//       instance.setAccessible(true);
//       instance.set(null, null);
//    }
	
	
	@Test
	public void getInstanceTest() {
		Inventory instance = Inventory.getInstance();
		assertNotNull("inventory is null!", instance);
	}
	
	
	@Test
	public void addProductTest() {
		Inventory instance = Inventory.getInstance();
		Product  apple = new Product("apple", 2.5);
		List<javafx.util.Pair<Product, Integer>> compareList = new ArrayList<>();
		compareList.add(new javafx.util.Pair<Product, Integer>(apple, 10));
		instance.addProduct(apple, 10);
		
		assertThat("Apples were not added correctly", compareList, is(instance.getProducts()));
		
		boolean thrown = false;
		try {
			instance.addProduct(null, 5);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		
		assertTrue("Did not throw exception when given null", thrown);
		
		//number lower than 0
				boolean zeroNum = false;
				try {
					instance.addProduct(apple, 0);
				}catch(IllegalArgumentException e) {
					zeroNum = true;
				}
				assertTrue("did not throw exception having zero number", zeroNum);
	}
	
	
	
	

}
