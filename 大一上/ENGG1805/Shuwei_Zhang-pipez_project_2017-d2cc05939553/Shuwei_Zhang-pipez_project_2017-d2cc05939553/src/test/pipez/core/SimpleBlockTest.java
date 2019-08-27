package pipez.core;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static pipez.util.TestUtils.*;

import org.junit.Test;

public class SimpleBlockTest {

	@Test
	public void test_empty_block() throws Exception {
		SimpleBlock block = new SimpleBlock();
		assertThat(block.fields().length, is(0));
		assertThat(block.values().length, is(0));
		assertThat(block.blocks().length, is(0));
	}
	
	@Test
	public void test_vals_constructor() {
		SimpleBlock block = new SimpleBlock("A", "B", "C");
		assertThat(block.fields().length, is(3));
		assertThat(block.fields()[0], is("C1"));
		assertThat(block.fields()[1], is("C2"));
		assertThat(block.fields()[2], is("C3"));
		assertThat(block.value("C1"), is("A"));
		assertThat(block.value("C2"), is("B"));
		assertThat(block.value("C3"), is("C"));
		
		assertThat(block.values(), are("A","B","C"));
	}
	
	@Test
	public void test_one_field() {
		
		SimpleBlock block = new SimpleBlock();
		block.add("A", "1");
		assertThat(block.fields().length, is(1));
		assertThat(block.values().length, is(1));
		assertThat(block.fields(), are("A"));
		assertThat(block.values(), are("1"));
		assertThat(block.value("A"), is("1"));
		
		assertThat(block.add("A", "2"), is("1"));
	}
	
	@Test
	public void test_three_fields() throws Exception {
		
		SimpleBlock block = new SimpleBlock();
		block.add("A", "1");
		block.add("B", "1");
		block.add("c", "3");
		assertThat(block.fields().length, is(3));
		assertThat(block.values().length, is(3));
		assertThat(block.fields(), are("A","B","c"));
		assertThat(block.values(), are("1","1","3"));
		assertThat(block.value("A"), is("1"));
		assertThat(block.value("B"), is("1"));
		assertThat(block.value("c"), is("3"));
	}
	
	@Test
	public void test_three_values() throws Exception {
		
		SimpleBlock block = new SimpleBlock();
		block.add("A", "1");
		block.add("B", "1");
		block.add("c", "3");
		block.add("4","5","6");
		assertThat(block.fields().length, is(6));
		assertThat(block.values().length, is(6));
		assertThat(block.fields(), are("A","B","c","C4","C5","C6"));
		assertThat(block.values(), are("1","1","3","4","5","6"));
		assertThat(block.value("A"), is("1"));
		assertThat(block.value("B"), is("1"));
		assertThat(block.value("c"), is("3"));
		assertThat(block.value("C4"), is("4"));
		assertThat(block.value("C5"), is("5"));
		assertThat(block.value("C6"), is("6"));
	}
	@Test
	public void test_one_embedded_block() {
		SimpleBlock block = new SimpleBlock();
		SimpleBlock embBlock = new SimpleBlock();
		block.addEmbeddedBlock(embBlock);
		assertThat(block.blocks().length, is(1));
		assertThat(block.blocks(), are(embBlock));
	}

	@Test
	public void test_three_embedded_block() {
		SimpleBlock block = new SimpleBlock();
		SimpleBlock embBlock1 = new SimpleBlock();
		SimpleBlock embBlock2 = new SimpleBlock();
		SimpleBlock embBlock3 = new SimpleBlock();
		block.addEmbeddedBlock(embBlock1);
		block.addEmbeddedBlock(embBlock2);
		block.addEmbeddedBlock(embBlock3);
		assertThat(block.blocks().length, is(3));
		assertThat(block.blocks(), are(embBlock1, embBlock2, embBlock3));
	}
}
