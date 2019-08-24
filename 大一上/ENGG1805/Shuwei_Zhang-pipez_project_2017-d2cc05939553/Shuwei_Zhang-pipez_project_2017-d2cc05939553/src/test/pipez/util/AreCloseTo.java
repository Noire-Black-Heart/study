package pipez.util;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class AreCloseTo extends TypeSafeMatcher<double[]>{

	private double[] vals;
	double delta;

	public AreCloseTo(double[] values, double delta) {
		vals = values;
		this.delta = delta;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("numeric values within ")
		.appendValue(delta)
		.appendText(" of ")
		.appendValue(vals);
	}

	@Override
	protected boolean matchesSafely(double[] items) {
		if(items == null) {
			return vals == null;
		}
		if (items.length == vals.length) {
			int i = 0;
			for(double v: vals) {;
			if(Math.abs(v - items[i++]) > delta) return false;
			}
			return true;
		}
		return false;
	}

	public static Matcher<double[]> closeTo(double error, double[] operands) {
		return new AreCloseTo(operands, error);
	}

}
