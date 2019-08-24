package pipez;

import java.util.*;

import pipez.core.Block;
import pipez.core.Pipe;
import pipez.core.PipezExceptionHandler;
import pipez.core.SimpleBlock;

/**
 * Selects the n-th field of each block
 * e.g. select the n-th column of a CSV file.
 * 
 *  n=1 corresponds to the first column.
 *  n=-1 corresponds to the last column.
 *  
 * Also allows multiple fields to be selected.
 * 
 * @author whwong
 *
 */
public class NFieldPipe implements Pipe {

	public static NFieldPipe create(int n) {
		return new NFieldPipe(n);
	}

	public static NFieldPipe create(int... ns) {
		return new NFieldPipe(ns);
	}
	
	int[] ns = new int[10];
	//int[] arr;
	private NFieldPipe(int... ns) {
		//this.ns = ns;
		System.arraycopy(ns, 0, this.ns, 0, ns.length);
		int i=0;
		for(int n :ns) ns[i++] = n - 1;
		Arrays.sort(ns);
	}
	
	
	@Override
	public String getName() {
		return "n-th field pipe";
	}

	@Override
	public Block transform(Block block) {
		SimpleBlock newBlock = new SimpleBlock();
	
		//String[] fields = block.fields();
		String[] fields = new String[100];
		int i = 0;
		for(String f : block.fields()){
			fields[i] = f;
			i++;
		} 
		int k = 0; 
		k = i;
		System.out.println(k);
		
		i = 0;
		for(int n: this.ns) {
			if(n < 0){
				if(k+n >= 0){
					newBlock.add(fields[(k+n)], block.value(fields[(k+n)]));
				}
			}
			else if(n > 0){
				newBlock.add(fields[(n-1)], block.value(fields[(n-1)]));
			}
			else{
				
			}
		}
		return newBlock;
	}

}
