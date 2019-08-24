package pipez.core;

public class Utils {

	
	public static String[] stringsOf(int... ints) {
		String[] ss = new String[ints.length];
		int i=0;
		for(int in :ints) ss[i++] = Integer.toString(in);
		return ss;
	}
	
	public static String[] stringsOf(double... dbls) {
		String[] ss = new String[dbls.length];
		int i=0;
		for(double d :dbls) ss[i++] = Double.toString(d);
		return ss;
	}
	
	/**
	 * Convenient method to convert an array of Strings into an array of doubles
	 * @param dbls an array of floating point strings
	 * @return array of doubles of converted strings
	 */
	public static double[] dblsOf(String[] dbls) {
		double[] ds = new double[dbls.length];
		int i=0;
		for(String d :dbls) {
			try {
				ds[i++] = Double.parseDouble(d);
			}catch(NumberFormatException nfe) {
				PipezExceptionHandler.handle( new Exception(d + " could not be converted to a double.", nfe)); }
		}
		return ds;
	}

	public static int[] intsOf(String[] ints) {
		int[] is = new int[ints.length];
		int i=0;
		for(String s :ints) {
			try {
				is[i++] = Integer.parseInt(s);
			}catch(NumberFormatException nfe) {
				PipezExceptionHandler.handle( new Exception(s + " could not be converted to an integer.", nfe)); }
		}
		return is;
	}
}
