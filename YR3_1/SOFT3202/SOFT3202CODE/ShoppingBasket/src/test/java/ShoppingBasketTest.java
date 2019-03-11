import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;
import org.junit.Test;

// class
public class ShoppingBasketTest{

@Test
public void addItemTest() {
	ShoppingBasket myBasket = new ShoppingBasket();
	List<javafx.util.Pair<String, Integer>> compareList = 
		new ArrayList<>();
	compareList.add(new javafx.util.Pair<String, Integer>("apple", 10));
	myBasket.addItem("apple", 10);
	
	assertThat("Apples were not added correctly", compareList, is(myBasket.getItems()));
	
	boolean thrown = false;
	try {
		myBasket.addItem("grapefruit", 5);
	} catch (IllegalArgumentException e) {
		thrown = true;
	}
	
	assertTrue("Did not throw exception when given grapefruit", thrown);
}

@Test
public void RemoveItemTest() {
	ShoppingBasket myBasket = new ShoppingBasket();
	
	myBasket.addItem("apple", 10);
	
	List<javafx.util.Pair<String, Integer>> compareList = 
			new ArrayList<>();
		compareList.add(new javafx.util.Pair<String, Integer>("apple", 9));
	
	boolean thrownArg = false;
	try {
		
		assertTrue ( myBasket.removeItem("apple", 4));
		
		assertFalse(myBasket.removeItem("apple", 59));
		myBasket.removeItem(null, 1);
	} 
		catch (IllegalArgumentException e) {
		thrownArg = true;
	}
//	assertTrue("Did not throw exception when given grapefruit", thrownArg);
//	try {
//		myBasket.removeItem("apple", 0);
//	} catch ()	
}

@Test
public void GetValueTest() {
	ShoppingBasket myBasket = new ShoppingBasket();
	myBasket.addItem("apple", 10);
	assertTrue(myBasket.getValue() == 25);
}

@Test
public void ClearTest() {
	ShoppingBasket myBasket = new ShoppingBasket();
	myBasket.addItem("apple", 10);
	myBasket.clear();
	List<javafx.util.Pair<String, Integer>> compareList = 
			new ArrayList<>();
	assertThat(compareList, is(myBasket.getItems()));
}
}