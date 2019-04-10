package au.edu.sydney.brawndo.erp.spfea;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.HashMap;
import java.util.Map;

import au.edu.sydney.brawndo.erp.todo.Task;
import au.edu.sydney.brawndo.erp.todo.TaskImpl;
import au.edu.sydney.brawndo.erp.todo.ToDoListImpl;
import au.edu.sydney.brawndo.erp.todo.Task.Field;

public class SPFEAFacadeImplTest {
		
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
//
//			//myTask1
//			myTask1 = new TaskImpl(1, time, "loc1", "desc1")
//			{
//				@Override
//				public int getID() {
//					return 1;
//				}
//
//				@Override
//				public LocalDateTime getDateTime() {
//					return LocalDateTime.of(2019, 3, 15, 7, 7, 7);
//				}
//
//				@Override
//				public String getLocation() {
//					return "loc1";
//				}
//
//				@Override
//				public String getDescription() {
//					return "desc1";
//				}
//			};
			
			param = new HashMap<Field, String>();
			
		}
	
	
	
	
	
	@Test
	public void testAdd() {
		
		
		
	}

	}

