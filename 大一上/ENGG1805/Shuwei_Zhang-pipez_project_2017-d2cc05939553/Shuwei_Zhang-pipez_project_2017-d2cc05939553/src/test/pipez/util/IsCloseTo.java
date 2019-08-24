package pipez.util;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/** 
 * From Hamcrest library's org.hamcrest.number.IsCloseTo 
 * 
 */

public class IsCloseTo extends TypeSafeMatcher<Double>{

	double delta, value;
	
	public IsCloseTo(double value, double error) {
	        this.delta = error;
	        this.value = value;
	}
	
	@Override
	public void describeTo(Description description) {
		description.appendText("a numeric value within ")
		.appendValue(delta)
		.appendText(" of ")
		.appendValue(value);
	}

	@Override
	protected boolean matchesSafely(Double item) {
		return actualDelta(item) <= 0.0;
	}

	@Override
	public void describeMismatchSafely(Double item, Description mismatchDescription) {
		mismatchDescription.appendValue(item)
		.appendText(" differed by ")
		.appendValue(actualDelta(item))
		.appendText(" more than delta ")
		.appendValue(delta);
	}

	private double actualDelta(Double item) {
		return Math.abs(item - value) - delta;
	}
	

    /**
     * Creates a matcher of {@link Double}s that matches when an examined double is equal
     * to the specified <code>operand</code>, within a range of +/- <code>error</code>.
     * For example:
     * <pre>assertThat(1.03, is(closeTo(1.0, 0.03)))</pre>
     * 
     * @param operand
     *     the expected value of matching doubles
     * @param error
     *     the delta (+/-) within which matches will be allowed
     */
    public static Matcher<Double> closeTo(double operand, double error) {
        return new IsCloseTo(operand, error);
    }
    
}
