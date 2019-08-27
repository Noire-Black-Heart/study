package pipez;

import java.util.Map;

import pipez.core.AdaptiveHashMap;
import pipez.core.Block;
import pipez.core.Pipe;
import pipez.core.PipezExceptionHandler;
import pipez.core.SimpleBlock;

/**
 * Rename the fields of a block. 
 * This pipe will pass through any fields that do not need renaming.
 * 
 * @author whwong
 *
 */
public class RenameFieldsPipe implements Pipe {

	@Override
	public String getName() {
		return "Rename Fields";
	}
	
	private Map<String, String> rename;
	private RenameFieldsPipe(Map<String, String> rename) {
		this.rename = rename;
	}

	public static RenameFieldsPipe create(String[] from, String[] to) {
		
		Map<String,String> map = new AdaptiveHashMap();
		if(from == null || to == null) {
			//do nothing
		}else if(from.length != to.length) {
			PipezExceptionHandler.handle(new Exception("Error in the String[] arguments of RenameFieldsPipe: the lengths of (from) and (to) do not match."));
		}else {
			for(int i=0; i<from.length; i++) {
				map.put(from[i], to[i]);
			}
		}
		return new RenameFieldsPipe(map);
	}

	@Override
	public Block transform(Block block) {
		SimpleBlock sb = new SimpleBlock();
		for(String f :block.fields()) {
			sb.add(rename.getOrDefault(f, f) /* retain field if not renamed */ , block.value(f));
		}
		return sb;
	}
	
}
