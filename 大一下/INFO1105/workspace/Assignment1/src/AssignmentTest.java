import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AssignmentTest {

	

	// Set up JUnit to be able to check for expected exceptions
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// This will make it a bit easier for us to make Date objects
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	// This will make it a bit easier for us to make Date objects
	private static Date getDate(String s) {
		try {
			return df.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
			fail("The test case is broken, invalid SimpleDateFormat parse");
		}
		// unreachable
		return null;
	}

	// helper method to compare two Submissions using assertions
	private static void testHelperEquals(Submission expected, Submission actual) {
		assertEquals(expected.getUnikey(), actual.getUnikey());
		assertEquals(expected.getTime(), actual.getTime());
		assertEquals(expected.getGrade(), actual.getGrade());
	}

	// helper method to compare two Submissions using assertions
	private static void testHelperEquals(String unikey, Date timestamp, Integer grade, Submission actual) {
		assertEquals(unikey, actual.getUnikey());
		assertEquals(timestamp, actual.getTime());
		assertEquals(grade, actual.getGrade());
	}
	
	// helper method that adds a new appointment AND checks the return value is correct
	private static Submission testHelperAdd(SubmissionHistory history, String unikey, Date timestamp, Integer grade) {
		Submission s = history.add(unikey, timestamp, grade);
		testHelperEquals(unikey, timestamp, grade, s);
		return s;
	}

	// Helper method to build the trivial example submission history
	private SubmissionHistory buildTinyExample() {
		SubmissionHistory history = new Assignment();
		// submission A:
		history.add("aaaa1234", getDate("2016/09/03 09:00:00"), 66);
		// submission B:
		history.add("aaaa1234", getDate("2016/09/03 16:00:00"), 86);
		// submission C:
		history.add("cccc1234", getDate("2016/09/03 16:00:00"), 73);
		// submission D:
		history.add("aaaa1234", getDate("2016/09/03 18:00:00"), 40);
		return history;
	}

	/* ****************************************************************
	 * self made test
	 * **************************************************************** */
	@Test
	public void testRemoveRoot() {
		SubmissionHistory history = new Assignment();
		Submission s = testHelperAdd(history, "aaaa1234", getDate("2016/09/03 09:00:00"), 66);
		Submission b = testHelperAdd(history, "aaaa1234", getDate("2016/09/03 16:00:00"), 86);
		Submission q = testHelperAdd(history, "cccc1234", getDate("2016/09/03 16:00:00"), 73);
		Submission w = testHelperAdd(history, "aaaa1234", getDate("2016/09/03 18:00:00"), 40);
		history.remove(s);
		testHelperEquals(b, history.getSubmissionBefore("aaaa1234", getDate("2016/09/03 17:00:00")));
	}
	
	@Test
	public void testRemoveAdd(){
		SubmissionHistory history = new Assignment();
		Submission s = testHelperAdd(history, "aaaa1234", getDate("2016/09/03 09:00:00"), 66);
		Submission b = testHelperAdd(history, "aaaa1234", getDate("2016/09/03 16:00:00"), 86);
		Submission q = testHelperAdd(history, "cccc1234", getDate("2016/09/03 16:00:00"), 73);
		Submission w = testHelperAdd(history, "aaaa1234", getDate("2016/09/03 18:00:00"), 40);
		history.remove(w);
		history.add("aaaa1234", getDate("2016/09/03 18:00:00"), 40);
		testHelperEquals(w, testHelperAdd(history, "aaaa1234", getDate("2016/09/03 18:00:00"), 40));
	}
	
	@Test
	public void aLot(){
		SubmissionHistory history = new Assignment();
		Submission s = testHelperAdd(history, "aaaa1234", getDate("2016/09/03 09:00:00"), 66);
		Submission b = testHelperAdd(history, "aaaa1234", getDate("2016/09/03 16:00:00"), 86);
		Submission q = testHelperAdd(history, "cccc1234", getDate("2016/09/03 16:00:00"), 73);
		Submission w = testHelperAdd(history, "aaaa1234", getDate("2016/09/03 18:00:00"), 40);
		Submission a1 = testHelperAdd(history, "a", new Date(100000), 10);
		Submission b1 = testHelperAdd(history, "b", new Date(100000), 10);
		Submission c1 = testHelperAdd(history, "c", new Date(100000), 10);
		Submission d1 = testHelperAdd(history, "d", new Date(100000), 10);
		Submission e1 = testHelperAdd(history, "e", new Date(100000), 10);
		Submission f1 = testHelperAdd(history, "f", new Date(100000), 15); //best

		Submission a2 = testHelperAdd(history, "a", new Date(200000), 10);
		Submission b2 = testHelperAdd(history, "b", new Date(200000), 5);
		Submission c2 = testHelperAdd(history, "c", new Date(200000), 5);
		Submission d2 = testHelperAdd(history, "d", new Date(200000), 15); //best
		Submission e2 = testHelperAdd(history, "e", new Date(200000), 15); //best
		Submission f2 = testHelperAdd(history, "f", new Date(200000), 5);
		Submission r = testHelperAdd(history, "aaaa1111", new Date(400000), 10);
		Submission j = testHelperAdd(history, "aaaa1111", new Date(2000000), 68);
		Submission c = testHelperAdd(history, "bbbb1111", new Date(600000), 68);
		Submission e = testHelperAdd(history, "aaaa1111", new Date(1000000), 68);
		Submission a = testHelperAdd(history, "aaaa1111", new Date(200000), 56);
		Submission h = testHelperAdd(history, "bbbb1111", new Date(1600000), 23);
		Submission f = testHelperAdd(history, "aaaa1111", new Date(1200000), 80);
		Submission d = testHelperAdd(history, "aaaa1111", new Date(800000), 23);
		Submission g = testHelperAdd(history, "bbbb1111", new Date(1400000), 40);
		Submission i = testHelperAdd(history, "aaaa1111", new Date(1800000), 50);
		
		assertEquals(new Integer(80), history.getBestGrade("aaaa1111"));
		assertEquals(new Integer(68), history.getBestGrade("bbbb1111"));

		history.remove(i);
		assertEquals(new Integer(80), history.getBestGrade("aaaa1111"));
		assertEquals(new Integer(68), history.getBestGrade("bbbb1111"));

		history.remove(f);
		assertEquals(new Integer(68), history.getBestGrade("aaaa1111"));
		assertEquals(new Integer(68), history.getBestGrade("bbbb1111"));

		history.remove(j);
		assertEquals(new Integer(68), history.getBestGrade("aaaa1111"));
		assertEquals(new Integer(68), history.getBestGrade("bbbb1111"));

		history.remove(e);
		assertEquals(new Integer(56), history.getBestGrade("aaaa1111"));
		assertEquals(new Integer(68), history.getBestGrade("bbbb1111"));
		
		List<String> studentsExpected = Arrays.asList("b","c","f", "bbbb1111", "aaaa1234", "aaaa1111");
		List<String> studentsActual = history.listRegressions();
		
		//sort both lists, to make it easier to compare them
		Collections.sort(studentsExpected);
		Collections.sort(studentsActual);

		assertEquals(studentsExpected, studentsActual);
		
		List<String> studentsExpected2 = Arrays.asList("aaaa1234");
		List<String> studentsActual2 = history.listTopStudents();
		
		//sort both lists, to make it easier to compare them
		Collections.sort(studentsExpected2);
		Collections.sort(studentsActual2);

		assertEquals(studentsExpected2, studentsActual2);
		
	}
	
}
