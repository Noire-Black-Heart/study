package au.edu.sydney.brawndo.erp.spfea;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;
import org.mockito.Mockito;


import au.edu.sydney.brawndo.erp.auth.AuthModule;
import au.edu.sydney.brawndo.erp.auth.AuthToken;
import au.edu.sydney.brawndo.erp.email.EmailService;
import au.edu.sydney.brawndo.erp.fax.FaxService;
import au.edu.sydney.brawndo.erp.ordering.OrderingFacade;
import au.edu.sydney.brawndo.erp.print.PrintService;
import au.edu.sydney.brawndo.erp.todo.Task;
import au.edu.sydney.brawndo.erp.todo.ToDoList;


public class SPFEAFacadeImplTest {
	
		static SPFEAFacadeImpl myFacade;
		
		static ToDoList todo;
		//static ToDoList todo2;
		
		
		static AuthModule auth;
		//static AuthModule auth2;
		static EmailService email;
		//static EmailService email2;
		static FaxService fax;
		static OrderingFacade ordering;
		static PrintService print;
		static AuthToken token;
		
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
		static LocalDateTime future = LocalDateTime.of(2029, 3, 15, 7, 7, 19);
		
		public static void add2Customers() {
		//log in first
		myFacade.login("Beef Supreme", "hunter2");
		//auth all
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		myFacade.addCustomer("123", "456", "123", "jsif@");
		myFacade.addCustomer("qwe", "asd", "1234", "jsif@2");
		}
		
		public static void addCustomer() {
		//log in first
		myFacade.login("Beef Supreme", "hunter2");
		//auth all
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		myFacade.addCustomer("123", "456", "123", "jsif@");
		}
		
		public static void setProvider() {
			myFacade.setOrderingProvider(ordering);
			when(ordering.placeOrder(any(AuthToken.class), any(String.class), any(String.class), any(int.class))).thenReturn(new BigDecimal(1));
			myFacade.setEmailProvider(email);
			myFacade.setFaxProvider(fax);
			myFacade.setPrintProvider(print);
		}
	

		@Rule
		public final ExpectedException exception = ExpectedException.none();
		
		@Before
		public void reset() {
			todo = mock(ToDoList.class);
			email = mock(EmailService.class);
			fax = mock(FaxService.class);
			ordering = mock(OrderingFacade.class);
			print = mock(PrintService.class);
			auth = mock(AuthModule.class);
			token = mock(AuthToken.class);
			//when for auth.log
			when(auth.login(eq("Beef Supreme"), eq("hunter2"))).thenReturn(token);
			
			myTask1 = mock(Task.class);
			myTask2 = mock(Task.class);

			myFacade = new SPFEAFacadeImpl();
	//		taskList = new ArrayList<Task>();
	//		taskList.add(myTask1);
	//		taskList.add(myTask2);
			
			
			//auth.authenticate(token);
			
			
			//when for todolist.add
			when(todo.add(nullable(Integer.class), any(LocalDateTime.class), any(String.class), any(String.class))).thenReturn(myTask1);
			
			
			//set auth provider
			myFacade.setAuthProvider(auth);
			
			
		}
	
		//test setters
	@Test
	public void testSetTodoProvider() {
		//test
		myFacade.setToDoProvider(todo);		
		myFacade.setToDoProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade.addNewTask(future, "desc", "HOME OFFICE");
	}
	@Test
	public void testSetAuthProvider() {
		myFacade.setAuthProvider(auth);
		myFacade.login("Beef Supreme", "hunter2");
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		myFacade.setAuthProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade.login("Beef Supreme", "hunter2");
	}
	@Test
	public void testOrderProvider() {
		SPFEAFacade myFacade2 = Mockito.spy(myFacade);
		//doThrow(new IllegalStateException()).when(myFacade2).placeOrder(0, 0);
		//test
		myFacade2.setOrderingProvider(ordering);;		
		myFacade2.setOrderingProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade2.placeOrder(124, 1);
	}
	@Test
	public void testEmailProvider() {
		SPFEAFacade myFacade2 = Mockito.spy(myFacade);
		//doThrow(new IllegalStateException()).when(myFacade2).placeOrder(0, 0);
		myFacade2.setOrderingProvider(ordering);
		when(ordering.placeOrder(any(AuthToken.class), any(String.class), any(String.class), any(int.class))).thenReturn(new BigDecimal(1));
		//login
		myFacade2.setAuthProvider(auth);
		myFacade2.login("Beef Supreme", "hunter2");
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		//add customer
		myFacade2.addCustomer("123", "123", "123", "jsif@");
		//test
		myFacade2.setEmailProvider(email);		
		myFacade2.setEmailProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade2.placeOrder(1, 1);
	}
	@Test
	public void testFaxProvider() {
		SPFEAFacade myFacade2 = Mockito.spy(myFacade);
		//doThrow(new IllegalStateException()).when(myFacade2).placeOrder(0, 0);
		myFacade2.setOrderingProvider(ordering);
		when(ordering.placeOrder(any(AuthToken.class), any(String.class), any(String.class), any(int.class))).thenReturn(new BigDecimal(1));
		//login
		myFacade2.setAuthProvider(auth);
		myFacade2.login("Beef Supreme", "hunter2");
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		myFacade2.setEmailProvider(email);
		//add customer
		myFacade2.addCustomer("123", "123", "123", "jsif@");
		//test
		myFacade2.setFaxProvider(fax);		
		myFacade2.setFaxProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade2.placeOrder(1, 1);
	}
	@Test
	public void testPrintProvider() {
		SPFEAFacade myFacade2 = Mockito.spy(myFacade);
		//doThrow(new IllegalStateException()).when(myFacade2).placeOrder(0, 0);
		myFacade2.setOrderingProvider(ordering);
		when(ordering.placeOrder(any(AuthToken.class), any(String.class), any(String.class), any(int.class))).thenReturn(new BigDecimal(1));
		//login
		myFacade2.setAuthProvider(auth);
		myFacade2.login("Beef Supreme", "hunter2");
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		myFacade2.setEmailProvider(email);
		//add customer
		myFacade2.addCustomer("123", "123", "123", "jsif@");
		//test
		myFacade2.setFaxProvider(fax);
		myFacade2.setCustomerPreferences(1, false, true, false);
		myFacade2.setPrintProvider(print);
		myFacade2.setPrintProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade2.placeOrder(1, 1);
	}
	//test AddNewTask
	@Test
	public void testAddTaskTimeNull() {
		myFacade.setToDoProvider(todo);
		exception.expect(IllegalArgumentException.class);
		myFacade.addNewTask(null, "desc", "HOME OFFICE");
	}
	@Test
	public void testAddTaskTimeBefore() {
		myFacade.setToDoProvider(todo);
		exception.expect(IllegalArgumentException.class);
		myFacade.addNewTask(time, "desc", "HOME OFFICE");
	}
	@Test
	public void testAddTaskLocationGood() {
		//exception.expect(IllegalArgumentException.class);
		myFacade.setToDoProvider(todo);
		myFacade.addNewTask(future, "desc", "HOME OFFICE");
		myFacade.addNewTask(future, "desc", "CUSTOMER SITE");
		myFacade.addNewTask(future, "desc", "MOBILE");
	}
	@Test
	public void testAddTaskLocationBad() {
		myFacade.setToDoProvider(todo);
		exception.expect(IllegalArgumentException.class);
		myFacade.addNewTask(future, "desc", "cyka");
	}
	@Test
	public void testAddTaskNullProvider() {
		myFacade.setToDoProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade.addNewTask(future, "desc", "MOBILE");
	}
	@Test
	public void testAddTaskCompleted() {
		myFacade.setToDoProvider(todo);

		Task task1 = myFacade.addNewTask(future, "desc", "HOME OFFICE");
		
		assertFalse(task1.isCompleted());
	}
	
	//test completeTask
	@Test
	public void testCompleteNullProvider() {
		myFacade.setToDoProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade.completeTask(1);
	}
	@Test
	public void testCompleteAlreadyComplete() {
		myFacade.setToDoProvider(todo);
		Task task1 = myFacade.addNewTask(future, "desc", "HOME OFFICE");
		when(todo.findOne(task1.getID())).thenReturn(task1);
		
		//call twice, so first should false, second should true since first one changed it.
		when(task1.isCompleted()).thenReturn(false).thenReturn(true);

		myFacade.completeTask(task1.getID());
		exception.expect(IllegalStateException.class);
		myFacade.completeTask(task1.getID());
	}
	@Test
	public void testCompleteNoMatching() {
		myFacade.setToDoProvider(todo);
		myFacade.addNewTask(future, "desc", "HOME OFFICE");
		exception.expect(IllegalArgumentException.class);
		myFacade.completeTask(10086);
	}
	
	//test getAllTasks
	@Test
	public void testgetAllTasksNullProvider() {
		myFacade.setToDoProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade.getAllTasks();
	}
	@Test
	public void testgetAllTasksMatch() {
		ArrayList<Task> taskList = new ArrayList<Task>();
		taskList.add(myTask1);
		taskList.add(myTask2);
		when(todo.findAll()).thenReturn(taskList);
		myFacade.setToDoProvider(todo);
		myFacade.addNewTask(future, "desc", "HOME OFFICE");
		when(todo.add(nullable(Integer.class), any(LocalDateTime.class), any(String.class), any(String.class))).thenReturn(myTask2);
		myFacade.addNewTask(future, "desc", "HOME OFFICE");
		List<Task> compList = myFacade.getAllTasks();
	//	compList.add(task1);
		assertEquals(compList, taskList);
	}
	//test login
	@Test
	public void testLoginNullName() {
		exception.expect(IllegalArgumentException.class);
		myFacade.login(null, "123");
	}
	@Test
	public void testLoginEmptyName() {
		exception.expect(IllegalArgumentException.class);
		myFacade.login("", "123");
	}
	@Test
	public void testLoginNullPassword() {
		exception.expect(IllegalArgumentException.class);
		myFacade.login("123", null);
	}
	@Test
	public void testLoginEmptyPassword() {
		exception.expect(IllegalArgumentException.class);
		myFacade.login("123", "");
	}
	@Test
	public void testLoginNullAuth() {
		myFacade.setAuthProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade.login("123", "123");
	}
	@Test
	public void testLoginAlready() {
		myFacade.login("Beef Supreme", "hunter2");
		exception.expect(IllegalStateException.class);
		myFacade.login("Beef Supreme", "hunter2");
	}
	@Test
	public void testLoginTrue() {
		assertTrue(myFacade.login("Beef Supreme", "hunter2"));
	}
	@Test
	public void testLoginFalse() {
		assertFalse(myFacade.login("Beef Supreme", "hu123213213213nter2"));
	}
	
	//test log out
	@Test
	public void testLogoutAlready() {
		myFacade.login("Beef Supreme", "hunter2");
		myFacade.logout();
		myFacade.login("Beef Supreme", "hunter2");
	}
	
	//test addCustomer
	@Test
	public void testaddCustomerNullfName() {
		//log in first
		myFacade.login("Beef Supreme", "hunter2");
		//auth all
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		exception.expect(IllegalArgumentException.class);
		myFacade.addCustomer(null, "qwer", "123", "jsif@");
	}
	@Test
	public void testaddCustomerEmptyfName() {
		//log in first
		myFacade.login("Beef Supreme", "hunter2");
		//auth all
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		exception.expect(IllegalArgumentException.class);
		myFacade.addCustomer("", "qwer", "123", "jsif@");
	}
	@Test
	public void testaddCustomerNulllName() {
		//log in first
		myFacade.login("Beef Supreme", "hunter2");
		//auth all
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		exception.expect(IllegalArgumentException.class);
		myFacade.addCustomer("123", null, "123", "jsif@");
	}
	@Test
	public void testaddCustomerEmptylName() {
		//log in first
		myFacade.login("Beef Supreme", "hunter2");
		//auth all
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		exception.expect(IllegalArgumentException.class);
		myFacade.addCustomer("123", "", "123", "jsif@");
	}
	@Test
	public void testaddCustomerNoLog() {
		//log in first
		//myFacade.login("Beef Supreme", "hunter2");
		//auth all
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		exception.expect(SecurityException.class);
		myFacade.addCustomer("123", "123", "123", "jsif@");
	}
	@Test
	public void testaddCustomerNoAuth() {
		//log in first
		myFacade.login("Beef Supreme", "hunter2");
		//auth all
		//when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		exception.expect(SecurityException.class);
		myFacade.addCustomer("123", "123", "123", "jsif@");
	}
	@Test
	public void testaddCustomerNoProvider() {
		//log in first
		myFacade.login("Beef Supreme", "hunter2");
		//auth all
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		myFacade.setAuthProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade.addCustomer("123", "123", "123", "jsif@");
	}
	@Test
	public void testaddCustomerDup() {
		//log in first
		myFacade.login("Beef Supreme", "hunter2");
		//auth all
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		myFacade.addCustomer("123", "123", "123", "jsif@");
		exception.expect(IllegalArgumentException.class);
		myFacade.addCustomer("123", "123", "123", "jsif@");
	}
	@Test
	public void testaddCustomerNormal() {
		//log in first
		myFacade.login("Beef Supreme", "hunter2");
		//auth all
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		assertFalse(myFacade.addCustomer("123", "123", "123", "jsif@") == myFacade.addCustomer("1243", "123", "123", "jsif@"));
	}
	
	//test get customer id
	@Test
	public void testgetCustomerNullfName() {
		//log in first
		myFacade.login("Beef Supreme", "hunter2");
		//auth all
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		myFacade.addCustomer("123", "123", "123", "jsif@");
		exception.expect(IllegalArgumentException.class);
		myFacade.getCustomerID(null, "123");
	}
	@Test
	public void testgetCustomerEmptyfName() {
		//log in first
		myFacade.login("Beef Supreme", "hunter2");
		//auth all
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		myFacade.addCustomer("123", "123", "123", "jsif@");
		exception.expect(IllegalArgumentException.class);
		myFacade.getCustomerID("", "123");
	}
	@Test
	public void testgetCustomerNullLName() {
		//log in first
		myFacade.login("Beef Supreme", "hunter2");
		//auth all
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		myFacade.addCustomer("123", "123", "123", "jsif@");
		exception.expect(IllegalArgumentException.class);
		myFacade.getCustomerID("123", null);
	}
	@Test
	public void testgetCustomerEmptyLName() {
		//log in first
		myFacade.login("Beef Supreme", "hunter2");
		//auth all
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		myFacade.addCustomer("123", "123", "123", "jsif@");
		exception.expect(IllegalArgumentException.class);
		myFacade.getCustomerID("123", "");
	}
	@Test
	public void testgetCustomerNoName() {
		//log in first
		myFacade.login("Beef Supreme", "hunter2");
		//auth all
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		myFacade.addCustomer("123", "123", "123", "jsif@");
		//exception.expect(IllegalArgumentException.class);
		assertNull(myFacade.getCustomerID("123", "4"));
	}
	@Test
	public void testgetCustomerNoLog() {
		//log in first
		myFacade.login("Beef Supreme", "hunter2");
		//auth all
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		myFacade.addCustomer("123", "123", "123", "jsif@");
		myFacade.logout();
		exception.expect(SecurityException.class);
		myFacade.getCustomerID("123", "123");
	}
	@Test
	public void testgetCustomerNoAuth() {
		//log in first
		myFacade.login("Beef Supreme", "hunter2");
		//auth all
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		myFacade.addCustomer("123", "123", "123", "jsif@");
		when(auth.authenticate(any(AuthToken.class))).thenReturn(false);
		exception.expect(SecurityException.class);
		myFacade.getCustomerID("123", "123");
	}
	@Test
	public void testgetCustomerNoProvider() {
		//log in first
		myFacade.login("Beef Supreme", "hunter2");
		//auth all
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		myFacade.addCustomer("123", "123", "123", "jsif@");
		when(auth.authenticate(any(AuthToken.class))).thenReturn(false);
		myFacade.setAuthProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade.getCustomerID("123", "123");
	}
	@Test
	public void testgetCustomerNormal() {
		//log in first
		myFacade.login("Beef Supreme", "hunter2");
		//auth all
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		myFacade.addCustomer("123", "123", "123", "jsif@");
		myFacade.addCustomer("1234", "123", "123", "jsif@");
		//exception.expect(IllegalArgumentException.class);
		myFacade.getCustomerID("123", "123");
		assertFalse(myFacade.getCustomerID("123", "123") == myFacade.getCustomerID("1234", "123"));
	}
	
	//get all customers test
	@Test
	public void testgetAllCustomerNormal() {
		//log in first
		myFacade.login("Beef Supreme", "hunter2");
		//auth all
		when(auth.authenticate(any(AuthToken.class))).thenReturn(true);
		myFacade.addCustomer("123", "456", "123", "jsif@");
		myFacade.addCustomer("qwe", "asd", "123", "jsif@");
		List<String> customerList = myFacade.getAllCustomers();
		ArrayList<String> compList = new ArrayList<String>();
		compList.add("456, 123");
		compList.add("asd, qwe");//FUCK THIS FORMAT
		assertEquals(compList, customerList);
	}
	@Test
	public void testgetAllCustomerNoLog() {
		add2Customers();
				myFacade.logout();
				exception.expect(SecurityException.class);
				List<String> customerList = myFacade.getAllCustomers();
				ArrayList<String> compList = new ArrayList<String>();
				compList.add("456, 123");
				compList.add("asd, qwe");//FUCK THIS FORMAT
				assertEquals(compList, customerList);
	}
	@Test
	public void testgetAllCustomerNoAuth() {
		add2Customers();
		when(auth.authenticate(any(AuthToken.class))).thenReturn(false);
		exception.expect(SecurityException.class);
		List<String> customerList = myFacade.getAllCustomers();
		ArrayList<String> compList = new ArrayList<String>();
		compList.add("456, 123");
		compList.add("asd, qwe");//FUCK THIS FORMAT
		assertEquals(compList, customerList);
	}
	@Test
	public void testgetAllCustomerNoProvider() {
		add2Customers();
				myFacade.setAuthProvider(null);
				exception.expect(IllegalStateException.class);
				List<String> customerList = myFacade.getAllCustomers();
				ArrayList<String> compList = new ArrayList<String>();
				compList.add("456, 123");
				compList.add("asd, qwe");//FUCK THIS FORMAT
				assertEquals(compList, customerList);
	}
	
	//get customer phone test
	@Test
	public void testgetCustomerPhoneNoID() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		exception.expect(IllegalArgumentException.class);
		myFacade.getCustomerPhone(i + 1);
	}
	@Test
	public void testgetCustomerPhoneNormal() {
		add2Customers();
		int i = myFacade.getCustomerID("123", "456");
	//	exception.expect(IllegalArgumentException.class);
		assertEquals(myFacade.getCustomerPhone(i), "123");
		int j = myFacade.getCustomerID("qwe", "asd");
		assertFalse(myFacade.getCustomerPhone(i)== myFacade.getCustomerPhone(j));
	}
	@Test
	public void testgetCustomerPhoneNoLog() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		myFacade.logout();
		exception.expect(SecurityException.class);
		assertEquals(myFacade.getCustomerPhone(i), "123");
	}
	@Test
	public void testgetCustomerPhoneNoAuth() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		when(auth.authenticate(any(AuthToken.class))).thenReturn(false);
		exception.expect(SecurityException.class);
		assertEquals(myFacade.getCustomerPhone(i), "123");
	}
	@Test
	public void testgetCustomerPhoneNoProvider() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		myFacade.setAuthProvider(null);
		exception.expect(IllegalStateException.class);
		assertEquals(myFacade.getCustomerPhone(i), "123");
	}
	
	//test set phone
	@Test
	public void testsetCustomerPhoneNoID() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		exception.expect(IllegalArgumentException.class);
		myFacade.setCustomerPhone(i + 1, "10086");
	}
	@Test
	public void testsetCustomerPhonedoubleNull() {
		addCustomer();
		myFacade.addCustomer("cyka", "blyat", "123", null);
		int i = myFacade.getCustomerID("cyka", "blyat");
		exception.expect(IllegalArgumentException.class);
		myFacade.setCustomerPhone(i, null);
	}
	@Test
	public void testsetCustomerPhoneNotNumber() {
		addCustomer();
		myFacade.addCustomer("cyka", "blyat", "123", null);
		int i = myFacade.getCustomerID("cyka", "blyat");
		exception.expect(IllegalArgumentException.class);
		myFacade.setCustomerPhone(i, "idinahui");
	}
	@Test
	public void testSetCustomerPhoneNormal() {
		add2Customers();
		int i = myFacade.getCustomerID("123", "456");
		int j = myFacade.getCustomerID("qwe", "asd");
		myFacade.setCustomerPhone(i, null);
	//	exception.expect(IllegalArgumentException.class);
		assertNull(myFacade.getCustomerPhone(i));
		myFacade.setCustomerPhone(i, "456");
		assertEquals(myFacade.getCustomerPhone(i), "456");
		assertEquals(myFacade.getCustomerPhone(j), "1234");
	}
	@Test
	public void testSetCustomerPhoneNoLog() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		myFacade.logout();
		exception.expect(SecurityException.class);
		myFacade.setCustomerPhone(i, "346");
	}
	@Test
	public void testsetCustomerPhoneNoAuth() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		when(auth.authenticate(any(AuthToken.class))).thenReturn(false);
		exception.expect(SecurityException.class);
		myFacade.setCustomerPhone(i, "346");
	}
	@Test
	public void testsetCustomerPhoneNoProvider() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		myFacade.setAuthProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade.setCustomerPhone(i, "346");
	}
	
	
	//test get email
	@Test
	public void testgetCustomerEmailNoID() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		exception.expect(IllegalArgumentException.class);
		myFacade.getCustomerEmail(i + 1);
	}
	@Test
	public void testgetCustomerEmailNormal() {
		add2Customers();
		int i = myFacade.getCustomerID("123", "456");
	//	exception.expect(IllegalArgumentException.class);
		assertEquals(myFacade.getCustomerEmail(i), "jsif@");
		int j = myFacade.getCustomerID("qwe", "asd");
		assertFalse(myFacade.getCustomerEmail(i)== myFacade.getCustomerEmail(j));
	}
	@Test
	public void testgetCustomerEmailNoLog() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		myFacade.logout();
		exception.expect(SecurityException.class);
		assertEquals(myFacade.getCustomerEmail(i), "jsif@");
	}
	@Test
	public void testgetCustomerEmailNoAuth() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		when(auth.authenticate(any(AuthToken.class))).thenReturn(false);
		exception.expect(SecurityException.class);
		assertEquals(myFacade.getCustomerEmail(i), "jsif@");
	}
	@Test
	public void testgetCustomerEmailNoProvider() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		myFacade.setAuthProvider(null);
		exception.expect(IllegalStateException.class);
		assertEquals(myFacade.getCustomerEmail(i), "jsif@");
	}
	
	//test set email
	@Test
	public void testsetCustomerEmailNoID() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		exception.expect(IllegalArgumentException.class);
		myFacade.setCustomerEmail(i + 1, "ashfio@");
	}
	@Test
	public void testsetCustomerEmaildoubleNull() {
		addCustomer();
		myFacade.addCustomer("cyka", "blyat", null, "idi@");
		int i = myFacade.getCustomerID("cyka", "blyat");
		exception.expect(IllegalArgumentException.class);
		myFacade.setCustomerEmail(i, null);
	}
	@Test
	public void testsetCustomerEmailNotAT() {
		addCustomer();
		myFacade.addCustomer("cyka", "blyat", null, "idi@");
		int i = myFacade.getCustomerID("cyka", "blyat");
		exception.expect(IllegalArgumentException.class);
		myFacade.setCustomerEmail(i, "idinahui");
	}
	@Test
	public void testSetCustomerEmailNormal() {
		add2Customers();
		int i = myFacade.getCustomerID("123", "456");
		int j = myFacade.getCustomerID("qwe", "asd");
		myFacade.setCustomerEmail(i, null);
	//	exception.expect(IllegalArgumentException.class);
		assertNull(myFacade.getCustomerEmail(i));
		myFacade.setCustomerEmail(i, "adsg@");
		assertEquals(myFacade.getCustomerEmail(i), "adsg@");
		assertEquals(myFacade.getCustomerEmail(j), "jsif@2");
	}
	@Test
	public void testSetCustomerEmailNoLog() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		myFacade.logout();
		exception.expect(SecurityException.class);
		myFacade.setCustomerEmail(i, "adsg@");
	}
	@Test
	public void testsetCustomerEmailNoAuth() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		when(auth.authenticate(any(AuthToken.class))).thenReturn(false);
		exception.expect(SecurityException.class);
		myFacade.setCustomerEmail(i, "adsg@");
	}
	@Test
	public void testsetCustomerEmailNoProvider() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		myFacade.setAuthProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade.setCustomerEmail(i, "adsg@");
	}
	
	
	//remove customer test
	@Test
	public void testRemoveCustomerNoLog() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		myFacade.logout();
		exception.expect(SecurityException.class);
		myFacade.removeCustomer(i);
	}
	@Test
	public void testRemoveCustomerNoAuth() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		when(auth.authenticate(any(AuthToken.class))).thenReturn(false);
		exception.expect(SecurityException.class);
		myFacade.removeCustomer(i);
	}
	@Test
	public void testRemoveCustomerNoProvider() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		myFacade.setAuthProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade.removeCustomer(i);
	}
	@Test
	public void testRemoveCustomerNoID() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		exception.expect(IllegalArgumentException.class);
		myFacade.removeCustomer(i + 1);
	}
	@Test
	public void testRemoveCustomerNormal() {
		add2Customers();
		int i = myFacade.getCustomerID("123", "456");
		int j = myFacade.getCustomerID("qwe", "asd");
		myFacade.removeCustomer(i);
		assertNull(myFacade.getCustomerID("123", "456"));
		assertEquals(myFacade.getCustomerEmail(j), "jsif@2");
	}
	
	//test set preference
	@Test
	public void testPrefNoLog() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		myFacade.logout();
		exception.expect(SecurityException.class);
		myFacade.setCustomerPreferences(i, true, true, true);
	}
	@Test
	public void testPrefNoAuth() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		when(auth.authenticate(any(AuthToken.class))).thenReturn(false);
		exception.expect(SecurityException.class);
		myFacade.setCustomerPreferences(i, true, true, true);
	}
	@Test
	public void testPrefNoProvider() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		myFacade.setAuthProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade.setCustomerPreferences(i, true, true, true);
	}
	@Test
	public void testPrefAllNull() {
		addCustomer();
		int i = myFacade.getCustomerID("123", "456");
		exception.expect(IllegalArgumentException.class);
		myFacade.setCustomerPreferences(i, false, false, false);
	}
	@Test
	public void testPrefNormal1() {
		add2Customers();
		int i = myFacade.getCustomerID("123", "456");
		setProvider();
		myFacade.setCustomerPreferences(i, true, true, true);
		myFacade.placeOrder(i, 5);
		verify(email, times(1)).printInvoice(any(AuthToken.class), any(String.class), any(String.class));
		verify(print, times(1)).printInvoice(any(AuthToken.class), any(String.class));
		verify(fax, times(1)).faxInvoice(any(AuthToken.class), any(String.class), any(String.class));
	}
	@Test
	public void testPrefNormal2() {
		add2Customers();
		int i = myFacade.getCustomerID("123", "456");
		setProvider();
		myFacade.setCustomerPreferences(i, false, true, true);
		myFacade.placeOrder(i, 5);
		verify(email, times(0)).printInvoice(any(AuthToken.class), any(String.class), any(String.class));
		verify(print, times(1)).printInvoice(any(AuthToken.class), any(String.class));
		verify(fax, times(1)).faxInvoice(any(AuthToken.class), any(String.class), any(String.class));
	}
	@Test
	public void testPrefNormal3() {
		add2Customers();
		int i = myFacade.getCustomerID("123", "456");
		setProvider();
		myFacade.setCustomerPreferences(i, false, false, true);
		myFacade.placeOrder(i, 5);
		verify(email, times(0)).printInvoice(any(AuthToken.class), any(String.class), any(String.class));
		verify(print, times(0)).printInvoice(any(AuthToken.class), any(String.class));
		verify(fax, times(1)).faxInvoice(any(AuthToken.class), any(String.class), any(String.class));
	}
	@Test
	public void testPrefNormal4() {
		add2Customers();
		int i = myFacade.getCustomerID("123", "456");
		setProvider();
		myFacade.setCustomerPreferences(i, false, true, false);
		myFacade.placeOrder(i, 5);
		verify(email, times(0)).printInvoice(any(AuthToken.class), any(String.class), any(String.class));
		verify(print, times(1)).printInvoice(any(AuthToken.class), any(String.class));
		verify(fax, times(0)).faxInvoice(any(AuthToken.class), any(String.class), any(String.class));
	}
	@Test
	public void testPrefNormal5() {
		add2Customers();
		int i = myFacade.getCustomerID("123", "456");
		setProvider();
		myFacade.setCustomerPreferences(i, true, true, false);
		myFacade.placeOrder(i, 5);
		verify(email, times(1)).printInvoice(any(AuthToken.class), any(String.class), any(String.class));
		verify(print, times(1)).printInvoice(any(AuthToken.class), any(String.class));
		verify(fax, times(0)).faxInvoice(any(AuthToken.class), any(String.class), any(String.class));
	}
	@Test
	public void testPrefNormal6() {
		add2Customers();
		int i = myFacade.getCustomerID("123", "456");
		setProvider();
		myFacade.setCustomerPreferences(i, true, false, true);
		myFacade.placeOrder(i, 5);
		verify(email, times(1)).printInvoice(any(AuthToken.class), any(String.class), any(String.class));
		verify(print, times(0)).printInvoice(any(AuthToken.class), any(String.class));
		verify(fax, times(1)).faxInvoice(any(AuthToken.class), any(String.class), any(String.class));
	}
	@Test
	public void testPrefNormal7() {
		add2Customers();
		int i = myFacade.getCustomerID("123", "456");
		setProvider();
		myFacade.setCustomerPreferences(i, true, false, false);
		myFacade.placeOrder(i, 5);
		verify(email, times(1)).printInvoice(any(AuthToken.class), any(String.class), any(String.class));
		verify(print, times(0)).printInvoice(any(AuthToken.class), any(String.class));
		verify(fax, times(0)).faxInvoice(any(AuthToken.class), any(String.class), any(String.class));
	}
	
	//place order test
	@Test
	public void testOrderNoLog() {
		addCustomer();
		setProvider();
		int i = myFacade.getCustomerID("123", "456");
		myFacade.logout();
		exception.expect(SecurityException.class);
		myFacade.placeOrder(i, 5);
	}
	@Test
	public void testOrderNoAuth() {
		addCustomer();
		setProvider();
		int i = myFacade.getCustomerID("123", "456");
		when(auth.authenticate(any(AuthToken.class))).thenReturn(false);
		exception.expect(SecurityException.class);
		myFacade.placeOrder(i, 5);
	}
	@Test
	public void testOrderNoAuthProvider() {
		addCustomer();
		setProvider();
		int i = myFacade.getCustomerID("123", "456");
		myFacade.setAuthProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade.placeOrder(i, 5);
	}
	@Test
	public void testOrderNoEmailProvider() {
		add2Customers();
		int i = myFacade.getCustomerID("123", "456");
		setProvider();
		myFacade.setCustomerPreferences(i, true, true, true);
		myFacade.setEmailProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade.placeOrder(i, 5);
	}
	@Test
	public void testOrderNoFaxProvider() {
		add2Customers();
		int i = myFacade.getCustomerID("123", "456");
		setProvider();
		myFacade.setCustomerPreferences(i, true, true, true);
		myFacade.setFaxProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade.placeOrder(i, 5);
	}
	@Test
	public void testOrderNoPrintProvider() {
		add2Customers();
		int i = myFacade.getCustomerID("123", "456");
		setProvider();
		myFacade.setCustomerPreferences(i, true, true, true);
		myFacade.setPrintProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade.placeOrder(i, 5);
	}
	@Test
	public void testOrderNoID() {
		add2Customers();
		int i = myFacade.getCustomerID("123", "456");
		int j = myFacade.getCustomerID("qwe", "asd");
		setProvider();
		myFacade.setCustomerPreferences(i, true, true, true);
		myFacade.setPrintProvider(null);
		exception.expect(IllegalArgumentException.class);
		myFacade.placeOrder(j+1, 5);
	}
	@Test
	public void testOrderQty0() {
		add2Customers();
		int i = myFacade.getCustomerID("123", "456");
		setProvider();
		myFacade.setCustomerPreferences(i, true, true, true);
		myFacade.setPrintProvider(null);
		exception.expect(IllegalArgumentException.class);
		myFacade.placeOrder(i, 0);
	}
	@Test
	public void testOrderQtylessTHan0() {
		add2Customers();
		int i = myFacade.getCustomerID("123", "456");
		setProvider();
		myFacade.setCustomerPreferences(i, true, true, true);
		myFacade.setPrintProvider(null);
		exception.expect(IllegalArgumentException.class);
		myFacade.placeOrder(i, -1);
	}
	@Test
	public void testOrderNormal() {
		addCustomer();
		setProvider();
		int i = myFacade.getCustomerID("123", "456");
		assertEquals(myFacade.placeOrder(i, 5), 1);
		when(ordering.placeOrder(any(AuthToken.class), any(String.class), any(String.class), any(int.class) )).thenReturn(new BigDecimal(1));
		assertEquals(myFacade.placeOrder(i, 5), 1);
		assertEquals(myFacade.placeOrder(i, 4), 1);
		when(ordering.placeOrder(any(AuthToken.class), any(String.class), any(String.class), any(int.class) )).thenReturn(new BigDecimal(0.4));
		assertEquals(myFacade.placeOrder(i, 24), 0);
		when(ordering.placeOrder(any(AuthToken.class), any(String.class), any(String.class), any(int.class) )).thenReturn(new BigDecimal(0.5));
		assertEquals(myFacade.placeOrder(i, 24), 1);
		when(ordering.placeOrder(any(AuthToken.class), any(String.class), any(String.class), any(int.class) )).thenReturn(new BigDecimal(1.4));
		assertEquals(myFacade.placeOrder(i, 24), 1);
		when(ordering.placeOrder(any(AuthToken.class), any(String.class), any(String.class), any(int.class) )).thenReturn(new BigDecimal(1.5));
		assertEquals(myFacade.placeOrder(i, 24), 2);
	}
	
	
	//life time cost test
	@Test
	public void testLifeTimeNoLog() {
		addCustomer();
		setProvider();
		int i = myFacade.getCustomerID("123", "456");
		myFacade.logout();
		exception.expect(SecurityException.class);
		myFacade.getTotalLifetimeCosts(i);
	}
	@Test
	public void testLifeTimeNoAuth() {
		addCustomer();
		setProvider();
		int i = myFacade.getCustomerID("123", "456");
		when(auth.authenticate(any(AuthToken.class))).thenReturn(false);
		exception.expect(SecurityException.class);
		myFacade.getTotalLifetimeCosts(i);
	}
	@Test
	public void testLifeTimeNoAuthProvider() {
		addCustomer();
		setProvider();
		int i = myFacade.getCustomerID("123", "456");
		myFacade.setAuthProvider(null);
		exception.expect(IllegalStateException.class);
		myFacade.getTotalLifetimeCosts(i);
	}
	@Test
	public void testLifeTime0() {
		addCustomer();
		setProvider();
		int i = myFacade.getCustomerID("123", "456");
		when(ordering.getLifetimeCost(any(AuthToken.class), any(String.class), any(String.class) )).thenReturn(new BigDecimal(0));
		assertEquals(myFacade.getTotalLifetimeCosts(i), 0);
	}
	@Test
	public void testLifeTime1() {
		addCustomer();
		setProvider();
		int i = myFacade.getCustomerID("123", "456");
		when(ordering.getLifetimeCost(any(AuthToken.class), any(String.class), any(String.class) )).thenReturn(new BigDecimal(1));
		assertEquals(myFacade.getTotalLifetimeCosts(i), 1);
	}
	@Test
	public void testLifeTimemult() {
		addCustomer();
		setProvider();
		int i = myFacade.getCustomerID("123", "456");
		when(ordering.getLifetimeCost(any(AuthToken.class), any(String.class), any(String.class) )).thenReturn(new BigDecimal(1.4));
		assertEquals(myFacade.getTotalLifetimeCosts(i), 1);
		when(ordering.getLifetimeCost(any(AuthToken.class), any(String.class), any(String.class) )).thenReturn(new BigDecimal(1.5));
		assertEquals(myFacade.getTotalLifetimeCosts(i), 2);
	}
	
	
}

