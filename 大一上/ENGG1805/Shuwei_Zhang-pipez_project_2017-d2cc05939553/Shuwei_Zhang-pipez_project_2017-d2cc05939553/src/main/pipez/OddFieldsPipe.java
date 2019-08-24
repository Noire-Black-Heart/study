package pipez;

import pipez.core.Block;
import pipez.core.Pipe;
import pipez.core.SimpleBlock;

/**
 * A pipe that lets through only the odd-numbered fields of each block
 * e.g. for a CSV of three columns, the second column is dropped and the 
 * first and third column pass through.
 * 
 * @author TBD
 *
 */
public class OddFieldsPipe implements Pipe {

	@Override
	public String getName() {
		return "Odd Fields Only";
	}
	
	private OddFieldsPipe() {}
	
	public static OddFieldsPipe create() {
		return new OddFieldsPipe();
	}

	@Override
	public Block transform(Block block) {
		SimpleBlock nb = new SimpleBlock();
		int i=0;
		for(String f :block.fields()) {
			if(i++%2 == 0) nb.add(f, block.value(f));
		}
		return nb;
	}

}
