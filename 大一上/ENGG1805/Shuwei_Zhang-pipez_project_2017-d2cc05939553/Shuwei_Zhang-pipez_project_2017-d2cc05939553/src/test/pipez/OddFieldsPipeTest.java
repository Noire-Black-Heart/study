package pipez;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import pipez.core.Block;
import pipez.core.SimpleBlock;

public class OddFieldsPipeTest {

	@Test
	public void test_three_columns() throws Exception {
	
		OddFieldsPipe evp = OddFieldsPipe.create();
		SimpleBlock sb = new SimpleBlock();
		sb.add("C1", "ABC"); //first column
		sb.add("C2", "def"); //second column
		sb.add("C3", "GHI"); //third column
		
		
		Block tb = evp.transform(sb);
		assertThat(tb.fields().length, is(2));
		assertThat(tb.fields()[0], is("C1"));
		assertThat(tb.fields()[1], is("C3"));
	}
	
	@Test
	public void test_zero_column() throws Exception {
	
		OddFieldsPipe evp = OddFieldsPipe.create();
		SimpleBlock sb = new SimpleBlock();
		
		Block tb = evp.transform(sb);
		assertThat(tb.fields().length, is(0));
	}
	
	@Test
	public void test_getName() throws Exception {
	
		OddFieldsPipe evp = OddFieldsPipe.create();
		
		assertThat(evp.getName(), is("Odd Fields Only"));
	}
}
