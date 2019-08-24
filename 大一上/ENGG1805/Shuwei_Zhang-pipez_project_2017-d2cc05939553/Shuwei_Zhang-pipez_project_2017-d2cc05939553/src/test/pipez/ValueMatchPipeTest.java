package pipez;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import pipez.core.Block;
import pipez.core.SimpleBlock;
import static pipez.util.TestUtils.*;

public class ValueMatchPipeTest {

	@Test
	public void test_match() {
		SimpleBlock sb = new SimpleBlock("abc", "cde", "efg", "ghi" );
		
		ValueMatchPipe select = ValueMatchPipe.create("efg");
		Block b = select.transform(sb);

		assertThat(b.values().length, is(4));
		assertThat(b.values(), are("abc", "cde", "efg", "ghi" ));
		
	}
	
	@Test
	public void test_nomatch() {
		SimpleBlock sb = new SimpleBlock("abc", "cde", "efg", "ghi" );
		
		ValueMatchPipe select = ValueMatchPipe.create("ijk");
		Block b = select.transform(sb);

		assertThat(b.values().length, is(0));
	}
	
	@Test public void test_nomatch_case() {
		
		SimpleBlock sb = new SimpleBlock("abc", "cde", "efg", "ghi" );
		
		ValueMatchPipe select = ValueMatchPipe.create("EFG");
		Block b = select.transform(sb);

		assertThat(b.values().length, is(0));
	}
	
	@Test
	public void test_match_ignorecase() {
		SimpleBlock sb = new SimpleBlock("abc", "cde", "efg", "ghi" );
		
		ValueMatchPipe select = ValueMatchPipe.createIgnoreCase("EFG");
		Block b = select.transform(sb);

		assertThat(b.values().length, is(4));
		assertThat(b.values(), are("abc", "cde", "efg", "ghi" ));
		
	}
	
	@Test
	public void test_nomatch_ignorecase() {
		SimpleBlock sb = new SimpleBlock("abc", "cde", "efg", "ghi");
		
		ValueMatchPipe select = ValueMatchPipe.createIgnoreCase("JKL");
		Block b = select.transform(sb);
		
		assertThat(b.values().length, is(0));
	}
	
	@Test
	public void test_match_inverse() {
		SimpleBlock sb = new SimpleBlock("abc", "cde", "efg", "ghi" );
		
		ValueMatchPipe select = ValueMatchPipe.createInverse("abc");
		Block b = select.transform(sb);

		assertThat(b.values().length, is(0));
	}

	@Test
	public void test_nomatch_inverse() {
		SimpleBlock sb = new SimpleBlock("abc", "cde", "efg", "ghi" );
		
		ValueMatchPipe select = ValueMatchPipe.createInverse("jkl");
		Block b = select.transform(sb);

		assertThat(b.values().length, is(4));
		assertThat(b.values(), are("abc", "cde", "efg", "ghi" ));
	}
	
	@Test
	public void test_match_inverse_ignorecase() {
		SimpleBlock sb = new SimpleBlock("abc", "cde", "efg", "ghi" );
		
		ValueMatchPipe select = ValueMatchPipe.createInverseIgnoreCase("ABC");
		Block b = select.transform(sb);

		assertThat(b.values().length, is(0));
	}
	
	@Test
	public void test_nomatch_inverse_ignorecase() {
		SimpleBlock sb = new SimpleBlock("abc", "cde", "efg", "ghi" );
		
		ValueMatchPipe select = ValueMatchPipe.createInverseIgnoreCase("qwe");
		Block b = select.transform(sb);

		assertThat(b.values().length, is(4));
		assertThat(b.values(), are("abc", "cde", "efg", "ghi" ));
	}
	
	@Test
	public void test_getName() {
	
		ValueMatchPipe evp = ValueMatchPipe.create("");
		
		assertThat(evp.getName(), is("Value Match"));
	}
}
