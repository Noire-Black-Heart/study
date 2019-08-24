package pipez.util;

import org.hamcrest.Matcher;
import static pipez.core.Utils.*;

public class TestUtils {

	@SafeVarargs
	public static <T> Matcher<T[]> are(T... values){
		return org.hamcrest.CoreMatchers.equalTo(values);
	}

	public static Matcher<double[]> areCloseTo(double... operands) {
		return areCloseToErr(0.00001, operands);
	}
	
	public static Matcher<double[]> areCloseToErr(double error, double... operands) {
		return AreCloseTo.closeTo(error, operands);
	}

}
