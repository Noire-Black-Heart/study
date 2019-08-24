package pipez;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import pipez.core.Block;
import pipez.core.SimpleBlock;
import static pipez.util.TestUtils.*;

public class FieldMatchPipeTest {

	@Test
	public void test_match() {
		SimpleBlock sb = new SimpleBlock("abc", "cde", "efg", "ghi" );
		
		FieldMatchPipe select = FieldMatchPipe.create("C3");
		Block b = select.transform(sb);

		assertThat(b.values().length, is(4));
		assertThat(b.values(), are("abc", "cde", "efg", "ghi" ));
		
	}
	
	@Test
	public void test_nomatch() {
		SimpleBlock sb = new SimpleBlock("abc", "cde", "efg", "ghi" );
		
		FieldMatchPipe select = FieldMatchPipe.create("C5");
		Block b = select.transform(sb);

		assertThat(b.values().length, is(0));
	}
	
	@Test public void test_nomatch_case() {
		
		SimpleBlock sb = new SimpleBlock("abc", "cde", "efg", "ghi" );
		
		FieldMatchPipe select = FieldMatchPipe.create("c3");
		Block b = select.transform(sb);

		assertThat(b.values().length, is(0));
	}
	
	@Test
	public void test_nomatch_inverse() {
		SimpleBlock sb = new SimpleBlock("abc", "cde", "efg", "ghi" );
		
		FieldMatchPipe select = FieldMatchPipe.createInverse("abc");
		Block b = select.transform(sb);

		assertThat(b.values().length, is(4));
		assertThat(b.values(), are("abc", "cde", "efg", "ghi" ));
	}

	@Test
	public void test_match_inverse() {
		SimpleBlock sb = new SimpleBlock("abc", "cde", "efg", "ghi" );
		
		FieldMatchPipe select = FieldMatchPipe.createInverse("C3");
		Block b = select.transform(sb);

		assertThat(b.values().length, is(0));
	}

	@Test
	public void test_match_ignorecase() {
		SimpleBlock sb = new SimpleBlock("abc", "cde", "efg", "ghi");
		
		FieldMatchPipe select = FieldMatchPipe.createIgnoreCase("c3");
		Block b = select.transform(sb);
		
		assertThat(b.values().length, is(4));
		assertThat(b.values(), are("abc", "cde", "efg", "ghi"));
	}
	
	@Test
	public void test_nomatch_ignorecase() {
		SimpleBlock sb = new SimpleBlock("abc", "cde", "efg", "ghi");
		
		FieldMatchPipe select = FieldMatchPipe.createIgnoreCase("abc");
		Block b = select.transform(sb);
		
		assertThat(b.values().length, is(0));
	}
	
	@Test
	public void test_match_inverse_ignorecase() {
		SimpleBlock sb = new SimpleBlock("abc", "cde", "efg", "ghi" );
		
		FieldMatchPipe select = FieldMatchPipe.createInverseIgnoreCase("c3");
		Block b = select.transform(sb);

		assertThat(b.values().length, is(0));
	}
	
	@Test
	public void test_nomatch_inverse_ignorecase() {
		SimpleBlock sb = new SimpleBlock("abc", "cde", "efg", "ghi" );
		
		FieldMatchPipe select = FieldMatchPipe.createInverseIgnoreCase("ABC");
		Block b = select.transform(sb);

		assertThat(b.values().length, is(4));
		assertThat(b.values(), are("abc", "cde", "efg", "ghi" ));
	}
	
	@Test
	public void test_getName() {
	
		FieldMatchPipe select = FieldMatchPipe.create("c1");
		
		assertThat(select.getName(), is("Field Match"));
	}
}
