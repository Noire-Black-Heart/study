package au.edu.sydney.brawndo.erp.spfea;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import au.edu.sydney.brawndo.erp.auth.AuthModule;
import au.edu.sydney.brawndo.erp.todo.Task;
import au.edu.sydney.brawndo.erp.todo.ToDoListImpl;



public class SPFEASystemQA {
	static SPFEAFacadeImpl myFacade;
	static LocalDateTime future = LocalDateTime.of(2029, 3, 15, 7, 7, 19);
	static SPFEATestFactory factory = new SPFEATestFactory();
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Before
	public void reset() {
		myFacade = new SPFEAFacadeImpl();
		AuthModule cyka = factory.makeAuthModule();
		myFacade.setAuthProvider(cyka);
		myFacade.setEmailProvider(factory.makeEmailService());
		myFacade.setFaxProvider(factory.makeFaxService());
		myFacade.setOrderingProvider(factory.makeOrderingFacade(cyka));
		myFacade.setPrintProvider(factory.makePrintService());
		myFacade.setToDoProvider(new ToDoListImpl());
	}
	
	@Test
	public void test1() {
		//adding task
		Task task1 = myFacade.addNewTask(future, "this is the task", "HOME OFFICE");
		
		//completing the task
		task1.complete();
		
		//getting the list
		List<Task> taskList = myFacade.getAllTasks();
		
		//comparing the shit
		assertEquals(taskList.size(), 1);
		assertTrue(taskList.get(0).isCompleted());
		
	}
	
	@Test
	public void test2() {
		//log in
		myFacade.login("Beef Supreme", "hunter2");
		//create user
		myFacade.addCustomer("James", "Zhang", "123456789", "qwer@");
		//set user print fax
		myFacade.setCustomerPreferences(myFacade.getCustomerID("James", "Zhang"), false, true, true);
		//create order and assert
		int order = myFacade.placeOrder(myFacade.getCustomerID("James", "Zhang"), 5);
		int total = myFacade.getTotalLifetimeCosts(myFacade.getCustomerID("James","Zhang"));
		assertEquals(order, total);
		
		
	}
	
}
