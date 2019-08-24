package pipez;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import pipez.core.Block;
import pipez.core.SimpleBlock;
import static pipez.util.TestUtils.*
;
public class ReversePipeTest {

	@Test
	public void test_reverse_pipe() {
		ReversePipe reverse = ReversePipe.create();
		SimpleBlock sb = new SimpleBlock();
		sb.add("C1", "banana"); //first column
		sb.add("C2", "apple");  //second column
		sb.add("C3", "pear"); //third column

		Block output = reverse.transform(sb);
		assertThat(output.fields().length, is(3));
		assertThat(output.fields()[0], is("C3"));
		assertThat(output.fields()[1], is("C2"));
		assertThat(output.fields()[2], is("C1"));
	}

	
	@Test
	public void test_reverse_pipe_simplez() {
		ReversePipe reverse = ReversePipe.create();
		SimpleBlock sb = new SimpleBlock("banana", "apple", "pear");

		Block output = reverse.transform(sb);
		assertThat(output.values(), are("pear", "apple", "banana"));
	}

	@Test
	public void test_reverse_pipe_numbers(){
		ReversePipe reverse = ReversePipe.create();
		SimpleBlock sb = new SimpleBlock();
		sb.add("C1", "123"); //first column
		sb.add("C2", "456");  //second column
		sb.add("C3", "789"); //third column
		
		Block output = reverse.transform(sb);
		assertThat(output.fields().length, is(3));
		assertThat(output.fields()[0], is("C3"));
		assertThat(output.fields()[1], is("C2"));
		assertThat(output.fields()[2], is("C1"));
		assertThat(output.values(), are("789", "456", "123"));
		
	}
	
	@Test
	public void test_getName(){
	
		ReversePipe evp = ReversePipe.create();
		
		assertThat(evp.getName(), is("Reverse Order"));
	}

	
	
}