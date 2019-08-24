package pipez;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import pipez.core.Block;
import pipez.core.SimpleBlock;
import static pipez.ArithmeticPipe.*;
import static pipez.util.TestUtils.*;
import static pipez.core.Utils.*;

public class ArithmeticPipeTest {

	@Test
	public void test_add() throws Exception {
		SimpleBlock block = new SimpleBlock(); 
		block.add(1,2,3,4,5);
		
		ArithmeticPipe ap = ArithmeticPipe.create(
				Add("C1", 1.0), Add("C2", 2.0), Add("C3", 3.0), Add("C4", 4.0), Add("C5", 5.0));
		
		Block b = ap.transform(block);
		
		assertThat( dblsOf(b.values()), areCloseTo(2.0,4.0,6.0,8.0,10.0));
	}
	
	@Test
	public void test_minus_tozero() throws Exception {
		SimpleBlock block = new SimpleBlock(); 
		block.add(1,2,3,4,5);
		
		ArithmeticPipe ap = ArithmeticPipe.create(
				Minus("C1", 1.0), Minus("C2", 2.0), Minus("C3", 3.0), Minus("C4", 4.0), Minus("C5", 5.0));
		
		Block b = ap.transform(block);
		
		assertThat( dblsOf(b.values()), areCloseTo(0.0, 0.0, 0.0, 0.0, 0.0));
	}

	@Test
	public void test_minus_one() throws Exception {
		SimpleBlock block = new SimpleBlock(); 
		block.add(1,2,3,4,5);
		
		ArithmeticPipe ap = ArithmeticPipe.create(
				Minus("C1", 1.0), Minus("C2", 1.0), Minus("C3", 1.0), Minus("C4", 1.0), Minus("C5", 1.0));
		
		Block b = ap.transform(block);
		
		assertThat( dblsOf(b.values()), areCloseTo(0.0, 1.0, 2.0, 3.0, 4.0));
	}
	
	@Test
	public void test_mult() throws Exception {
		SimpleBlock block = new SimpleBlock(); 
		block.add(1,2,3,4,5);
		
		ArithmeticPipe ap = ArithmeticPipe.create(
				Mult("C1", 1.0), Mult("C2", 2.0), Mult("C3", 3.0), Mult("C4", 4.0), Mult("C5", 5.0));
		
		Block b = ap.transform(block);
		assertThat( dblsOf(b.values()), areCloseTo(1.0, 4.0, 9.0, 16.0, 25.0));
	}
	
	
	@Test
	public void test_div_two() throws Exception {
		SimpleBlock block = new SimpleBlock(); 
		block.add(1,2,3,4,5);
		
		ArithmeticPipe ap = ArithmeticPipe.create(
				Div("C1", 2.0), Div("C2", 2.0), Div("C3", 2.0), Div("C4", 2.0), Div("C5", 2.0));
		
		Block b = ap.transform(block);
		assertThat( dblsOf(b.values()), areCloseTo(0.5, 1.0, 1.5, 2.0, 2.5));
	}
	
	@Test
	public void test_getName() {
	
		ArithmeticPipe ap = ArithmeticPipe.create();
		
		assertThat(ap.getName(), is("Arithmetic Ops"));
	}
}