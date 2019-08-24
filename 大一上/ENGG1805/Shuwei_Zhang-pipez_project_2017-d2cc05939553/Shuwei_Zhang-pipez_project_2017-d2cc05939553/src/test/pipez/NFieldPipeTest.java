package pipez;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static pipez.util.TestUtils.*;

import pipez.core.Block;
import pipez.core.SimpleBlock;

import static pipez.core.Utils.*;

public class NFieldPipeTest {
	
	@Test
	public void test_order() {
		// test that NField(1,2,3)
		// is not the same as NField(3,2,1)
		SimpleBlock sb = new SimpleBlock(1,2,3,4,5,6,7,8,9,10);

		assertThat(sb.values(), are(stringsOf(1,2,3,4,5,6,7,8,9,10)));
		
		NFieldPipe np = NFieldPipe.create(1,2,3);
		Block b = np.transform(sb);
		
		assertThat(b.fields(), are("C1","C2","C3"));
		assertThat(b.value("C1"), is("1"));
		assertThat(b.value("C2"), is("2"));
		assertThat(b.value("C3"), is("3"));
		
		NFieldPipe np2 = NFieldPipe.create(3,2,1);
		Block b2 = np2.transform(sb);
		
		assertThat(b2.fields(), are("C3","C2","C1")); //order per selection
		assertThat(b2.value("C1"), is("1"));
		assertThat(b2.value("C2"), is("2"));
		assertThat(b2.value("C3"), is("3"));
		
	}
	
	@Test
	public void test_zero() {
		// test that NField(0) has no effect i.e. there is no selection for the 0-th field 
		SimpleBlock sb = new SimpleBlock(1,2,3,4,5,6,7,8,9,10);
		
		NFieldPipe np = NFieldPipe.create(0);
		Block b = np.transform(sb);
		
		assertThat(b.values().length, is(0));
	}
	
	@Test
	public void test_negative_fields() {
		// test that NField(-1) returns the last field
		// also test for block with only a single field
		SimpleBlock sb = new SimpleBlock(1,2,3,4,5,6,7,8,9,10);
		
		NFieldPipe np = NFieldPipe.create(-1, -3, -5);
		Block b = np.transform(sb);
		
		assertThat(b.value("C10"), is("10"));
		assertThat(b.value("C8"), is("8"));
		assertThat(b.value("C6"), is("6"));
	}
	
	@Test
	public void test_negative_one() {
		SimpleBlock sb = new SimpleBlock(1);
		NFieldPipe np = NFieldPipe.create(-1, -3, -5, 1);
		Block b = np.transform(sb);
		assertThat(b.values(), is(not(stringsOf(1,1)))); // test what "values" is not: 
														 // a selection of the first field twice
		assertThat(b.value("C1"), is("1"));
	}
	
	@Test
	public void test_negative_one_empty_fields() {
		// test that NField(-1) returns the last available field
		// but this does not necessarily mean 
		// that values will always come from the last column
		// e.g. lines of a CSV file missing values in the last column
		// will return the value of the last available field
		
		SimpleBlock line1 = new SimpleBlock(1,2,3); //line1 missing C4
		assertThat(line1.value("C4"), is(nullValue()));
		SimpleBlock line2 = new SimpleBlock(1,2);
		line2.add("C4", "4"); //line2 missing C3
		assertThat(line2.value("C3"), is(nullValue()));
		
		NFieldPipe np = NFieldPipe.create(-1);
		
		Block b = np.transform(line1);
		assertThat(b.fields(), is(not("C4")));
		assertThat(b.fields(), are("C3"));
		assertThat(b.value("C3"), is("3"));
		
		b = np.transform(line2);
		assertThat(b.fields(), is(not("C3")));
		assertThat(b.fields(), are("C4"));
		assertThat(b.value("C4"), is("4"));
	}

	
	@Test
	public void test_getName() {
	
		NFieldPipe evp = NFieldPipe.create();
		
		assertThat(evp.getName(), is("n-th field pipe"));
	}
}
