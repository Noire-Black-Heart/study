package pipez;

import org.junit.Test;

import pipez.core.Block;
import pipez.core.SimpleBlock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static pipez.util.TestUtils.*;

public class RenameFieldsPipeTest {
	
	@Test
	public void test_col_rename() {
		SimpleBlock sb = new SimpleBlock();
		sb.add("C1", "1");
		sb.add("C2", "2");
		sb.add("C3", "3");
		
		RenameFieldsPipe rn = RenameFieldsPipe.create(
								new String[]{"C1","C2","C3"}, 
								new String[]{"col1", "col2", "col3"} );
		Block b = rn.transform(sb);
		assertThat(b.fields(), are("col1","col2","col3"));
		assertThat(b.value("col1"), is("1"));
		assertThat(b.value("col2"), is("2"));
		assertThat(b.value("col3"), is("3"));
	}

	@Test
	public void test_some_col_rename() {
		SimpleBlock sb = new SimpleBlock();
		sb.add("C1", "1");
		sb.add("C2", "2");
		sb.add("C3", "3");
		
		RenameFieldsPipe rn = RenameFieldsPipe.create(
								new String[]{"C1"}, 
								new String[]{"col1"} );
		Block b = rn.transform(sb);
		assertThat(b.fields(), are("col1","C2","C3"));
		assertThat(b.value("col1"), is("1"));
		assertThat(b.value("C2"), is("2"));
		assertThat(b.value("C3"), is("3"));
	}
	
	@Test
	public void test_no_col_rename() {
		SimpleBlock sb = new SimpleBlock();
		sb.add("C1", "1");
		sb.add("C2", "2");
		sb.add("C3", "3");
		
		RenameFieldsPipe rn = RenameFieldsPipe.create(
								new String[]{}, 
								new String[]{} );
		Block b = rn.transform(sb);
		assertThat(b.fields(), are("C1","C2","C3"));
		assertThat(b.value("C1"), is("1"));
		assertThat(b.value("C2"), is("2"));
		assertThat(b.value("C3"), is("3"));
	}
	
	@Test
	public void test_getName() {
	
		RenameFieldsPipe evp = RenameFieldsPipe.create(new String[]{}, 
													   new String[]{} );
		
		assertThat(evp.getName(), is("Rename Fields"));
	}
}
