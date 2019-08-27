package pipez;

import static org.hamcrest.CoreMatchers.is; //IGNORE - 	eclipse mistakenly flags this as deprecated
import static org.junit.Assert.*;

import org.junit.Test;

import pipez.core.Block;
import pipez.core.SimpleBlock;
import pipez.core.SpecialBlocks;

public class IdentityPipeTest {

	@Test
	public void test_empty_block() {
		IdentityPipe id = IdentityPipe.create();
		Block b = id.transform(SpecialBlocks.EMPTY_BLOCK);
		assertThat(b, is(SpecialBlocks.EMPTY_BLOCK));
	}
	
	@Test
	public void test_single_field() throws Exception {
		IdentityPipe id = IdentityPipe.create();
		SimpleBlock sb = new SimpleBlock();
		sb.add("C1", "123");
		
		Block tb = id.transform(sb);
		assertThat(tb.fields().length, is(1));
		assertThat(tb.fields()[0], is("C1"));
		assertThat(tb.value("C1"), is("123"));
	}
	
	@Test
	public void test_getName() {
	
		IdentityPipe evp = IdentityPipe.create();
		
		assertThat(evp.getName(), is("Identity"));
	}
}
