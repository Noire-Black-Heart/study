package au.edu.sydney.brawndo.erp.todo;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TaskImplTest {
	
	static Task myTask;
	
	@Before
	public void createTask() {
		LocalDateTime time = LocalDateTime.of(2019, 3, 15, 7, 7, 7); // 2019/3/15, 7:07:07
		myTask = new TaskImpl(1, time, "Sydney", "cyka blyat");
		//task id is 1, 
		//task datetime is 2019/3/15, 7:07:07
		//task location is Sydney
		//task description is cyka blyat
		
		System.out.println("this should take place");
		
		
	}
	
	@Test
	public void testGetID() {
		assertEquals(myTask.getID(), 1);
	}
	
	@Test
	public void testGetDateTime() {
		assertEquals(myTask.getDateTime(), LocalDateTime.of(2019, 3, 15, 7, 7, 7));
	}

	@Test
	public void testGetID() {
		assertEquals(myTask.getID(), 1);
	}
	
	@Test
	public void testGetID() {
		assertEquals(myTask.getID(), 1);
	}
	
	@Test
	public void testGetID() {
		assertEquals(myTask.getID(), 1);
	}
	
	@Test
	public void testGetID() {
		assertEquals(myTask.getID(), 1);
	}
	
	@Test
	public void testGetID() {
		assertEquals(myTask.getID(), 1);
	}
	
	@Test
	public void testGetID() {
		assertEquals(myTask.getID(), 1);
	}
	
	@Test
	public void testGetID() {
		assertEquals(myTask.getID(), 1);
	}
}


