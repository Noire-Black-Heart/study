package pipez;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import pipez.core.Block;
import pipez.core.SimpleBlock;

public class EvenFieldsPipeTest {

	@Test
	public void test_three_column() throws Exception {
	
		EvenFieldsPipe evp = EvenFieldsPipe.create();
		SimpleBlock sb = new SimpleBlock();
		sb.add("C1", "ABC"); //first column
		sb.add("C2", "def"); //second column
		sb.add("C3", "GHI"); //third column
		
		
		Block tb = evp.transform(sb);
		assertThat(tb.fields().length, is(1));
		assertThat(tb.fields()[0], is("C2"));
	}
	
	@Test
	public void test_zero_column() throws Exception {
	
		EvenFieldsPipe evp = EvenFieldsPipe.create();
		SimpleBlock sb = new SimpleBlock();
		
		Block tb = evp.transform(sb);
		assertThat(tb.fields().length, is(0));
	}
	
	@Test
	public void test_getName() {
	
		EvenFieldsPipe evp = EvenFieldsPipe.create();
		
		assertThat(evp.getName(), is("Even Fields Only"));
	}
}
