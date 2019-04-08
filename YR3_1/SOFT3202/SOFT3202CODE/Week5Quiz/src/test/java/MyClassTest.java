import static org.mockito.verification.VerificationEvent.*;

import java.util.Collection;

import static org.mockito.ArgumentCaptor.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class MyClassTest {

	MyClass myClass;
	MyDependency myDep;
	int count;
	@Before
    public void setUp() throws Exception {
        myDep = mock(MyDependency.class);
        myClass = new MyClass(myDep);
        count = 0;
     //   mController.setStudentDAO(mStuDao);
    }

	
	@Test
	public void test() {
		//fail("Not yet implemented");
		
		
//		when(myDep.getSomeResult(anyString(), anyInt())).then(new Answer<Result>() {
//		    @Override
//		    public Result answer(InvocationOnMock invocation) throws Throwable {
//		        count++;
//		        return null;
//		    }
//
//		}); 
		
		when(myDep.getSomeResult(anyString(), anyInt())).thenReturn("123");
		
	//	Collection<Invocation> invocations = Mockito.mockingDetails(myDep).getInvocations();
		myClass.doSomething();
		
		Collection<Invocation> invocations = Mockito.mockingDetails(myDep).getInvocations();
		
		System.out.println("count is " + invocations.size());
		
	}
	
	@Test
	public void argTest() {
		
		
		ArgumentCaptor<String> argstr = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Integer> argint = ArgumentCaptor.forClass(Integer.class);
		
//		when(myDep.getSomeResult(anyString(), anyInt())).then(new Answer<Result>() {
//		    @Override
//		    public Result answer(InvocationOnMock invocation) throws Throwable {
//		        count++;
//		        verify(myDep, times(1)).getSomeResult(argstr.capture(), argint.capture());
////			verify(myDep, times(1)).getSomeResult(argstr.capture(), argint.capture());
//		        return null;
//		    }
//		}); 
		
		when(myDep.getSomeResult(anyString(), anyInt())).thenReturn("cyka");
		//verify(myDep, atLeast(1)).getSomeResult(argstr.capture(), argint.capture());
		
		myClass.doSomething();
		
		verify(myDep, atLeast(1)).getSomeResult(argstr.capture(), argint.capture());
		
		System.out.println("last Strign is " +argstr.getValue());
		System.out.println("Strign is " +argstr.getAllValues());
		System.out.println("last int is " +argint.getValue());
		System.out.println("int is " +argint.getAllValues());
		
		
	}

}
