package pipez;

import pipez.core.Block;
import pipez.core.Pipe;
import pipez.core.SimpleBlock;

/**
 * A pipe that lets through only the even-numbered fields of each block
 * e.g. for a CSV of three columns, the second column is passed through and the 
 * first and third column are dropped.
 * 
 * @author TBD
 *
 */
public class EvenFieldsPipe implements Pipe {

	@Override
	public String getName() {
		return "Even Fields Only";
	}

	private EvenFieldsPipe() {}
	
	public static EvenFieldsPipe create() {
		return new EvenFieldsPipe();
	}
	
	@Override
	public Block transform(Block block) {
		SimpleBlock nb = new SimpleBlock();
		int i=0;
		for(String f :block.fields()) {
			if(i++%2 != 0) nb.add(f, block.value(f));
		}
		return nb;
	}
	


}
