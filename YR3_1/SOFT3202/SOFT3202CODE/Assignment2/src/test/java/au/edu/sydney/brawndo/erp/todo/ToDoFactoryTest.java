package au.edu.sydney.brawndo.erp.todo;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ToDoFactoryTest {

//	@Test
//	public void testConstructor() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void testMakeList() {
		ToDoFactory fact = new ToDoFactory();
		ToDoList list = fact.makeToDoList();
		
		assertThat(list, instanceOf(ToDoList.class));
		
	}

}
