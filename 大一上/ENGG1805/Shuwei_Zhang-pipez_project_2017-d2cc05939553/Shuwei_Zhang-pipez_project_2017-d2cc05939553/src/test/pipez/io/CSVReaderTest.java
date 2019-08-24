package pipez.io;

import static org.hamcrest.CoreMatchers.is; //eclipse mistakenly marks this as deprecated
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.eq;

import java.io.PrintStream;

import org.junit.Test;

import pipez.core.Block;
import pipez.core.SpecialBlocks;

public class CSVReaderTest {

	@Test
	public void test_nonexisting_file() {
		Reader csv = CSVReader.from("test_data/this_does_not_exist");
		
		PrintStream err = mock(PrintStream.class);
		PrintStream orierr = System.err;
		System.setErr(err);
		
		csv.open(); //you should see an error message
		verify(err).println(eq("The file (test_data\\this_does_not_exist) could not be found."));
		System.setErr(orierr);
		
	}
	
	@Test
	public void test_empty_file() {
		Reader csv = CSVReader.from("test_data/0_line_0_col.csv");
		assertThat(csv.next(), is(SpecialBlocks.SKIP_BLOCK));
		assertThat(csv.hasNext(), is(false));
		csv.close();
	}
	
	@Test
	public void test_empty_lines() {
		Reader csv = CSVReader.from("test_data/5_line_0_col.csv");
		int numLines = 0;
		while(csv.hasNext()) {
			Block b = csv.next();
			assertThat(b.fields().length, is(0));
			numLines++;
		}
		assertThat(numLines, is(5));
		csv.close();
	}
	
	@Test
	public void test_one_line_one_col() {
		
		Reader csv = CSVReader.from("test_data/1_line_1_col.csv");
		int numLines = 0;
		assertThat(csv.hasNext(), is(true));
		while(csv.hasNext()) {
			Block b = csv.next();
			assertThat(b.fields().length, is(1));
			assertThat(b.value(b.fields()[0]), is("l1c1"));
			numLines++;
		}
		assertThat(numLines, is(1));
		csv.close();
	}

	@Test
	public void test_one_line_four_col() {
		
		Reader csv = CSVReader.from("test_data/1_line_4_col.csv");
		int numLines = 0;
		assertThat(csv.hasNext(), is(true));
		while(csv.hasNext()) {
			Block b = csv.next();
			assertThat(b.fields().length, is(4));
			for(int i=0; i<4; i++) {
				assertThat(b.value(b.fields()[i]), is("l1c"+(i+1)));
			}
			numLines++;
		}
		assertThat(numLines, is(1));
		csv.close();
	}
	
	@Test
	public void test_ten_line_ten_col() {
		
		Reader csv = CSVReader.from("test_data/10_line_10_col.csv");
		int numLines = 0;
		assertThat(csv.hasNext(), is(true));
		while(csv.hasNext()) {
			Block b = csv.next();
			assertThat(b.fields().length, is(10));
			for(int i=0; i<10; i++) {
				assertThat(b.value(b.fields()[i]), is("l"+ (numLines+1) + "c" +(i+1)));
			}
			numLines++;
		}
		assertThat(numLines, is(10));
		csv.close();
	}
	
	@Test
	//8hang 4ge
	public void test_incomplete_lines() throws Exception {
		
		Reader csv = CSVReader.from("test_data/incomplete.csv");
		int numLines = 0;
		assertThat(csv.hasNext(), is(true));
		while(csv.hasNext()){
			Block b = csv.next();
			//assertThat(b.fields().length, is(4));
			//for(int i = 0; i<8; i++){
				//Block b = csv.next();
				//assertThat(b.fields().length, is(4));
		//}
			numLines++;
		}
		assertThat(numLines, is(8));
		csv.close();
		/**
		 * Test scenarios: 
		 * 
		 * ,l1c2,l1c3,l1c4
		 * l2c1,,l2c3,l2c4
		 * l3c1,l3c2,,l3c4
		 * l4c1,l4c2,l4c3,
		 * ,l5c2,l5c3,
		 * l6c1,,l6c3,
		 * l7c1,l7c2,,
		 * ,,l8c3,l8c4
		 */
		
	}
}
