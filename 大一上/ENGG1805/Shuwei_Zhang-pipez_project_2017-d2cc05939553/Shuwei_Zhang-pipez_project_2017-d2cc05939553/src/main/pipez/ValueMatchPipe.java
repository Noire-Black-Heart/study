package pipez;

import pipez.core.Block;
import pipez.core.Pipe;

import static pipez.core.SpecialBlocks.*;

/**
 * Returns only Blocks which have at least one value which has the given string.
 * e.g. for a given CSV file, only lines which contain the given string are passed through.
 * 
 * Also includes an inverse-mode, which does not pass through Blocks that have at least
 * one value which has the given string.
 * 
 * @author whwong
 *
 */
public class ValueMatchPipe implements Pipe {

	@Override
	public String getName() {
		return "Value Match";
	}

	private String toMatch = null;
	private boolean inverse = false, ignoreCase = false;
	private boolean inverseMatch = false;
	
	private ValueMatchPipe(String toMatch, boolean inverse, boolean ignoreCase) {
		this.toMatch = toMatch;
		this.inverse = inverse;
		this.ignoreCase = ignoreCase;
	}
	
	public static ValueMatchPipe create(String toMatch) {
		return new ValueMatchPipe(toMatch, false, false);
	}
	
	public static ValueMatchPipe createIgnoreCase(String toMatch) {
		return new ValueMatchPipe(toMatch, false, true);
	}
	
	public static ValueMatchPipe createInverse(String toMatch) {
		return new ValueMatchPipe(toMatch, true, false);
	}
	
	public static ValueMatchPipe createInverseIgnoreCase(String toMatch) {
		return new ValueMatchPipe(toMatch, true, true);
	}
	
	@Override
	public Block transform(Block block) {
		//System.out.println(toMatch + this.inverse + this.ignoreCase);
		//for the only ignore case one
		if(this.inverse == false && this.ignoreCase == true){
			for(String v :block.values()) {
				if(v.equalsIgnoreCase(toMatch)) {
				return block;
			}
			}
		}
		
		//for the only inverse one 
		if(this.inverse == true && this.ignoreCase == false){
		for(String v :block.values()) {
					if(v.equals(toMatch)){
						inverseMatch = true;
					}
			}
		//System.out.println(inverseMatch);
		if(inverseMatch == false){
				return block;
			}
		}
		
		//for both inverse and ignore one
		if(this.inverse == true && this.ignoreCase == true){
			for(String v :block.values()) {
						if(v.equalsIgnoreCase(toMatch)){
							inverseMatch = true;
						}
				}if(inverseMatch == false){
					return block;
				}
			}
		
				//for the normal one
			if(this.inverse == false && this.ignoreCase == false) {
				for(String v :block.values()) {
					//System.out.println("both false" + v + toMatch);
				if(v.equals(toMatch)) {
				return block;
			}
			}
		}
		
		return SKIP_BLOCK;
	}

}
