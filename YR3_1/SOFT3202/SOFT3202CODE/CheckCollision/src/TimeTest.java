import java.util.*;


public class TimeTest {
	
	public static void main(String args[]) {
		
		
		
		String str1 = "cyka";
		
		String str2 = "blyat"; 
		
		
		long start = System.nanoTime();
		
	    String str3 	= 	str1 + str2;
		
		long end = System.nanoTime();
		long duration = (end - start);
		
		System.out.println("string + = " + duration);
		//string builder
		start = System.nanoTime();
		
		StringBuilder sb = new StringBuilder();
		sb.append(str1).append(str2);
		
		end = System.nanoTime();
		duration = (end - start);
		System.out.println("stringbuilder = " + duration);
		
		//printf
		start = System.nanoTime();
		
		System.out.printf("%s%s", str1, str2);
		
		end = System.nanoTime();
		duration = (end - start);
		System.out.println("printf = " + duration);
		
		//arraylist
		start = System.nanoTime();
		List<Integer> scheisser = new ArrayList<Integer>();
		scheisser.add(1);
		scheisser.add(2);
		scheisser.add(3);
		end = System.nanoTime();
		duration = (end - start);
		System.out.println("arraylist = " + duration);
		
		//array.aslist
		start = System.nanoTime();
		int[] arr = new int[] {1, 2, 3};
		List<int[]> scheisser2 =  Arrays.asList(arr);
		end = System.nanoTime();
		duration = (end - start);
		System.out.println("array.aslist = " + duration);
		
		//for each
		List<String> baka = new ArrayList<String>();
		baka.add("1");
		baka.add("2");
		baka.add("3");
		start = System.nanoTime();
		for(String s : baka) {
			//do nothing
		}
		end = System.nanoTime();
		duration = (end - start);
		System.out.println("foreach = " + duration);
		
		//for loop
		start = System.nanoTime();
		for(int i = 0; i < baka.size() ; i ++) {
			//do nothing
		}
		end = System.nanoTime();
		duration = (end - start);
		System.out.println("forloop = " + duration);
		
		
	}
}
