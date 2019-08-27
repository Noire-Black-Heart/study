package pipez;

import pipez.core.Block;
import pipez.core.Pipe;
import pipez.core.SimpleBlock;
import pipez.core.SpecialBlocks;

/**
 * A demo pipe that does nothing but write out its input blocks.
 * 
 * @author whwong
 *
 */
public class IdentityPipe implements Pipe{

	public static IdentityPipe create() {
		return new IdentityPipe();
	}
	
	private IdentityPipe() {}
	
	@Override
	public String getName() {
		return "Identity";
	}

	@Override
	public Block transform(Block block) {
		return block;
	}
	
}
