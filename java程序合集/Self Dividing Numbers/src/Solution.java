import java.util.*;
class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
    	List<Integer> ans = new ArrayList<Integer>();
    	int n = left;
    	while(n <= right){
    		int current = n;
    		while(true){
    			if(current > 0 && current < 10 ){
    				if(n % current == 0){
    					ans.add(n);
    				}
    				break;
    			}
    			
    			int digit = current % 10;
    			if(digit == 0){
    				break;
    			}
    			if(n % digit != 0 ){
    				break;
    			}
    			
    			current = current / 10;
    		}
    		n++;
    	}
        return ans;
    }
}



//1002 ---- 1002 %10 = 2----first
//1002 % 2 = 0 ---- continue
//1002 / 10 = 100------------first inner loop end
//100 % 10 = 0----second
//0 found, break the loop//

//103 ---- 103 % 10 = 3 -- first
//103 % 3 != 0 ---break the loop

//122 -- 122 % 10 = 2 --- first
//122 % 2 = 0 ---- continue
//122 / 10 = 12--------------first inner loop end
//12 % 10 = 2 ---second
//12 % 2 = 0 --- continue
//12 / 10 = 1 --------------second inner loop end
//last digit reached (0<current number<10)
//122 % current number(1) = 0
//list add number
//continue big loop