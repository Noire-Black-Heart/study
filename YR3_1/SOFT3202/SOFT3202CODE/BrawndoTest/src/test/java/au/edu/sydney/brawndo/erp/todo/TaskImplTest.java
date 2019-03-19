package au.edu.sydney.brawndo.erp.todo;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TaskImplTest {
	
	static Task myTask;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Before
	public void createTask() {
		LocalDateTime time = LocalDateTime.of(2019, 3, 15, 7, 7, 7); // 2019/3/15, 7:07:07
		myTask = new TaskImpl(1, time, "Sydney", "cyka blyat");
		//task id is 1, 
		//task datetime is 2019/3/15, 7:07:07
		//task location is Sydney
		//task description is cyka blyat
		
		//System.out.println("this should take place");
		
		
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
	public void testGetLocation() {
		assertEquals(myTask.getLocation(), "Sydney");
	}
	
	@Test
	public void testGetDescription() {
		assertEquals(myTask.getDescription(), "cyka blyat");
	}
	
	@Test
	public void testIsCompleted() {
		assertFalse(myTask.isCompleted());
	}
	
	@Test
	public void testSetDateTime() {
		//normal stuff
		myTask.setDateTime(LocalDateTime.of(2020, 4, 16, 8, 8, 8));//2020/4/16, 8:08:08
		assertEquals(myTask.getDateTime(), LocalDateTime.of(2020, 4, 16, 8, 8, 8));
		
		//invalid input
		exception.expect(IllegalArgumentException.class);
		myTask.setDateTime(null);
	}
	
	@Test
	public void testSetLocation() {
		//normal stuff
		myTask.setLocation("Melbourne");
		assertEquals(myTask.getLocation(), "Melbourne");
		
		//null input
		exception.expect(IllegalArgumentException.class);
		myTask.setLocation(null);
	}
	
	@Test
	public void testSetLocationEmpty() {
		exception.expect(IllegalArgumentException.class);
		myTask.setLocation("");
	}
	
	@Test
	public void testSetLocationLong() {
		//256 char
		myTask.setLocation("BAfVP7JKN8s98JoAIw8rAFHesELHxPtwX563El2KiYDx7ilLfDwXmauWi6yrISngsY5PcBq5ZyRZBsm6Fmxi0VILAfcw81dGUQap7BO0fCDvwyBeSQ5YUW7w6I4pVDUqrmrGVSAaS0YCMAgeD8Ywd9QT2SVSAHmytuUIL4ITBH3ro2oUTc5lGgivyPVVJ7tBSZJWV0oWEqJzAI0feaW7uWePqqyW4uWf2A7ZzvDG30jHniVzFE5DjtLWuavK1q8N");
		
		//257 char
		exception.expect(IllegalArgumentException.class);
		myTask.setLocation("m8LRC6xBDVrzBZ0j0g4aLqkUcXa2h2julcgSuf7SWYTNH2KYnr0HHYgE3nKWIDZUd751TBQ52Yk3dyjIJCloWD84ufgm2q1GpBW2r16vKuR1QYtlmgjoQn9CPNkeQjTll0ZALFEZyDTT405LrmyU5l6yfXqgDvMWSm2avDc44jJ0CUCfkJFlRZgM7UH18GIFTVy8QDprMkYjswS0mivb86lRX0aAgqzKX8IzoyAOOdsvUU6i1gKUkVQCtZBV7BzwP");
		
	}
	
	@Test
	public void testSetDescription() {
		myTask.setDescription("aaaaa");
		assertEquals(myTask.getDescription(), "aaaaa");
		
		//set null
		myTask.setDescription(null);
		assertNull(myTask.getDescription());
	}
	
	@Test
	public void testComplete() {
		//normal stuff
		myTask.complete();
		assertTrue(myTask.isCompleted());
		
		//invalid input
		exception.expect(IllegalStateException.class);
		myTask.complete();
		
	}
	
//	@Test
//	public void testGetID() {
//		assertEquals(myTask.getID(), 1);
//	}
}


