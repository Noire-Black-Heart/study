package au.edu.sydney.brawndo.erp.todo;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.CoreMatchers.*;

import au.edu.sydney.brawndo.erp.todo.Task.Field;


public class ToDoListImplTest {
	
	static ToDoListImpl myList;
	static Task myTaskNull;
	static Task myTask1;
	static Task myTask2;
	static Task myTask3;
	static Task myTask4;
	static LocalDateTime time = LocalDateTime.of(2019, 3, 15, 7, 7, 7); // 2019/3/15, 7:07:07
	static LocalDateTime time2 = LocalDateTime.of(2019, 3, 15, 7, 7, 9); // 2019/3/15, 7:07:07
	static LocalDateTime time3 = LocalDateTime.of(2019, 3, 15, 7, 7, 11); // 2019/3/15, 7:07:07
	static LocalDateTime time4 = LocalDateTime.of(2019, 3, 15, 7, 7, 13); // 2019/3/15, 7:07:07
	static LocalDateTime time5 = LocalDateTime.of(2019, 3, 15, 7, 7, 15); // 2019/3/15, 7:07:07
	static LocalDateTime time6 = LocalDateTime.of(2019, 3, 15, 7, 7, 17); // 2019/3/15, 7:07:07
	static LocalDateTime time7 = LocalDateTime.of(2019, 3, 15, 7, 7, 19); // 2019/3/15, 7:07:07
	static Map<Field, String> param;

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Before
	public void reset() {
		LocalDateTime time = LocalDateTime.of(2019, 3, 15, 7, 7, 7); // 2019/3/15, 7:07:07
		myList = new ToDoListImpl();
		
		//myTask1
		myTask1 = new TaskImpl(1, time, "loc1", "desc1")
		{
			@Override
			public int getID() {
				return 1;
			}
			
			@Override
			public LocalDateTime getDateTime() {
				return LocalDateTime.of(2019, 3, 15, 7, 7, 7);
			}
			
			@Override
			public String getLocation() {
				return "loc1";
			}
			
			@Override
			public String getDescription() {
				return "desc1";
			}
		};
		
		param = new HashMap<Field, String>();
		
	}
	
	@Test
	public void testAddNullBehindFix() {
		
		myList.add(1, time, "loc1", "des1");
		exception.expect(IllegalStateException.class);
		myList.add(null, time, "loc1", "des1");
		
	}
	
	
	
	@Test
	public void testAddDupIDFix() {
		
		myList.add(1, time, "loc1", "des1");
		exception.expect(IllegalArgumentException.class);
		myList.add(1, time, "loc1", "des1");
	}

	@Test
	public void testAddDupIDNull() {
		
		myList.add(null, time, "loc1", "des1");
		exception.expect(IllegalArgumentException.class);
		myList.add(myList.findAll().get(0).getID(), time, "loc1", "des1");
	}

	
	
	@Test
	public void testAddTimeNull() {
		exception.expect(IllegalArgumentException.class);
		myList.add(1, null, "loc1", "des1");
	}
	@Test
	public void testAddLocationNull() {
		exception.expect(IllegalArgumentException.class);
		myList.add(1, time, null, "des1");
	}
	@Test
	public void testAddLocationEmpty() {
		exception.expect(IllegalArgumentException.class);
		myList.add(1, time, "", "des1");
	}
	@Test
	public void testAddLocationLong() {
		myList.add(1, time, "OvzbquvYhSRiY9Jd18zoqqQ7fADFruJ8YNkXaoV6JbUa7jP6Q5Sl3C67EAFByzB6A0nWNIi9YHNzmcDt9wYwg0xv5uJq5texfPHw9yEOrsrIhrV5PTUSQ6WAlmuuXKVzniiEGRXJE5w6ey9BpQtxJx5IHHnUf312dcu6RtrItIMxobAoFRLKrEiLxdkhqlwrGEIsxx5znLa4T58caft4GC42ho3YOcNRmpCrZEqBp2vPM7k3dfR6esirJAs2Uq5E", "des0");
		
		exception.expect(IllegalArgumentException.class);	
		myList.add(2, time, "OvzbquvYhSRiY9Jd18zoqqQ7fADFruJ8YNkXaoV6JbUa7jP6Q5Sl3C67EAFByzB6A0nWNIi9YHNzmcDt9wYwg0xv5uJq5texfPHw9yEOrsrIhrV5PTUSQ6WAlmuuXKVzniiEGRXJE5w6ey9BpQtxJx5IHHnUf312dcu6RtrItIMxobAoFRLKrEiLxdkhqlwrGEIsxx5znLa4T58caft4GC42ho3YOcNRmpCrZEqBp2vPM7k3dfR6esirJAs2Uq5E1", "des1");
	}
	
	@Test
	public void testAddNormal() {
		
		//normal
		myList.add(1, LocalDateTime.of(2019, 3, 15, 7, 7, 7), "loc1", "desc1");
		
		assertEquals(myList.findAll().get(0).getDateTime(), LocalDateTime.of(2019, 3, 15, 7, 7, 7));
		assertEquals(myList.findAll().get(0).getDescription(), "desc1");
		assertEquals(myList.findAll().get(0).getLocation(), "loc1");
		assertEquals(myList.findAll().get(0).getField(Field.DESCRIPTION), "desc1");
		assertEquals(myList.findAll().get(0).getField(Field.LOCATION), "loc1");
		
	}
	
	@Test
	public void testAddDupNullID() {
		
		//null
		myList.add(null, time2, "loc1", "desc1");
		myList.add(null, time2, "loc2", "desc2");
		myList.add(null, time2, "loc3", "desc3");
		
		param.put(Field.LOCATION, "loc2");
	//	param.put(Field.DESCRIPTION, "des6");
		assertThat(myList.findAll(param, null, null, null, true).get(0).getID(), not(myList.findAll(null, time, time, null, false).get(2).getID()));
		
	}

	@Test
	public void testRemoveNotPresent() {
		
		assertFalse(myList.remove(20));
		
	}

	@Test
	public void testRemovePresent() {
		myList.add(1, LocalDateTime.of(2019, 3, 15, 7, 7, 7), "loc1", "desc1");
		
		assertEquals(myList.findAll().size(), 1);
		
		assertTrue(myList.remove(1));
		
		assertEquals(myList.findAll().size(), 0);
		
	}

	@Test
	public void testFindOne() {
		
		myList.add(1, LocalDateTime.of(2019, 3, 15, 7, 7, 7), "loc1", "desc1");
		assertEquals(myList.findOne(1).getDateTime(), LocalDateTime.of(2019, 3, 15, 7, 7, 7));
		assertEquals(myList.findOne(1).getDescription(), "desc1");
		assertEquals(myList.findOne(1).getLocation(), "loc1");
		assertEquals(myList.findOne(1).getField(Field.DESCRIPTION), "desc1");
		assertEquals(myList.findOne(1).getField(Field.LOCATION), "loc1");
		
		assertEquals(myList.findOne(200), null);
		
	}

	@Test
	public void testFindAll() {
		
		assertEquals(myList.findAll().size(), 0);
		
		myList.add(null, time, "loc1", "des1");
		myList.add(null, time, "loc1", "des1");
		myList.add(null, time, "loc1", "des1");
		myList.add(null, time, "loc1", "des1");
		myList.add(null, time, "loc1", "des1");
		assertEquals(myList.findAll().size(), 5);
		
	}

	@Test
	public void testFindAllComplete() {
		myList.add(1, time, "loc1", "des1");
		myList.add(2, time, "loc2", "des1");
		myList.add(3, time, "loc3", "des1");
		myList.add(4, time, "loc4", "des1");
		myList.add(5, time, "loc5", "des1");
		
		myList.findOne(1).complete();
		myList.findOne(3).complete();
		myList.findOne(5).complete();
		
		assertEquals(myList.findAll(true).size(), 3);
		assertEquals(myList.findAll(false).size(), 2);
		
	}

	@Test
	public void testFindAllTime() {
		myList.add(1, time, "loc1", "des1");
		myList.add(2, time2, "loc2", "des1");
		myList.add(3, time3, "loc3", "des1");
		myList.add(4, time4, "loc4", "des1");
		myList.add(5, time5, "loc5", "des1");
		myList.add(6, time6, "loc6", "des1");
		myList.add(7, time7, "loc7", "des1");
		
		myList.findOne(3).complete();
		myList.findOne(4).complete();
		
		assertEquals(myList.findAll(time, time7, null).size(), 5);
		assertEquals(myList.findAll(time, time7, true).size(), 2);
		assertEquals(myList.findAll(time, time7, false).size(), 3);
		assertEquals(myList.findAll(null, time7, null).size(), 6);
		assertEquals(myList.findAll(null, time7, true).size(), 2);
		assertEquals(myList.findAll(null, time7, false).size(), 4);
		assertEquals(myList.findAll(time, null, null).size(), 6);
		assertEquals(myList.findAll(time, null, true).size(), 2);
		assertEquals(myList.findAll(time, null, false).size(), 4);
		assertEquals(myList.findAll(null, null, null).size(), 7);
		
	}

	@Test
	public void testFindAllCyka() {
		myList.add(1, time, "loc1", "des1");
		myList.add(2, time2, "loc2", "des2");
		myList.add(3, time3, "loc3", "des3");
		myList.add(4, time4, "loc4", "des4");
		myList.add(5, time5, "loc5", "des5");
		myList.add(6, time6, "loc6", "des6");
		myList.add(7, time7, "loc7", "des7");
		//dup in location
		myList.add(11, time, "loc5", "des4");
		myList.add(12, time2, "loc5", "des5");
		myList.add(13, time3, "loc5", "des6");
		myList.add(14, time4, "loc5", "des7");
		
		myList.findOne(3).complete();
		myList.findOne(4).complete();
		
		
		param.put(Field.LOCATION, "loc5");

		
//		for(Task t : myList.findAll(param, time4, null, null, false)) {
//			System.out.println(t.getID());
//		}
		//original and check
		assertEquals(myList.findAll(null, null, null, null, true).size(), 11);
		assertEquals(myList.findAll(null, null, null, true, true).size(), 2);
		assertEquals(myList.findAll(param, null, null, null, true).size(), 5);
		assertEquals(myList.findAll(param, time4, null, null, true).size(), 1);
		assertEquals(myList.findAll(param, time, time4, null, true).size(), 2);
		
		//original or check
	//	assertEquals(myList.findAll(null, null, null, null, false).size(), 11);
		
		assertEquals(myList.findAll(param, null, null, null, false).size(), 5);
	//	assertEquals(myList.findAll(null, time, time, null, false).size(), 9);
	//	assertEquals(myList.findAll(null, time4, null, true, false).size(), 5);
		
		myList.findOne(12).complete();
		
		assertEquals(myList.findAll(param, time, time4, true, true).size(), 1);
		
		param.put(Field.DESCRIPTION, "des6");
		assertEquals(myList.findAll(param, null, null, null, true).size(), 1);
		assertEquals(myList.findAll(param, null, null, null, false).size(), 6);
//		for(Task t : myList.findAll(param, time7, time, false, false)) {
//			System.out.println(t.getID());
//		}
//		assertEquals(myList.findAll(param, time7, time, false, false).size(), 5);
		
		
	}

	
	@Test
	public void testClear() {
		myList.add(1, time, "loc1", "des1");
		myList.add(2, time2, "loc2", "des2");
		myList.add(3, time3, "loc3", "des3");
		myList.add(4, time4, "loc4", "des4");
		myList.add(5, time5, "loc5", "des5");
		myList.add(6, time6, "loc6", "des6");
		myList.add(7, time7, "loc7", "des7");
		
		myList.clear();
		assertEquals(myList.findAll().size(), 0);
	}
	
	@Test
	public void testRemove1() {
		myList.add(1, time, "loc1", "des1");
		myList.add(2, time2, "loc2", "des2");
		myList.add(3, time3, "loc3", "des3");
		myList.add(4, time4, "loc4", "des4");
		myList.add(5, time5, "loc5", "des5");
		myList.add(6, time6, "loc6", "des6");
		myList.add(7, time7, "loc7", "des7");
		
		myList.remove(2);
		assertEquals(myList.findAll().size(), 6);
	}

//	@Test
//	public void testRemove1() {
//		myList.add(1, time, "loc1", "des1");
//		myList.add(2, time2, "loc2", "des2");
//		myList.add(3, time3, "loc3", "des3");
//		myList.add(4, time4, "loc4", "des4");
//		myList.add(5, time5, "loc5", "des5");
//		myList.add(6, time6, "loc6", "des6");
//		myList.add(7, time7, "loc7", "des7");
//		
//		myList.remove(2);
//		assertEquals(myList.findAll().size(), 6);
//	}
}
