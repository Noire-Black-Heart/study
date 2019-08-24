package pipez;

import java.io.PrintStream;

import pipez.core.Block;
import pipez.core.Pipe;

/**
 * A convenient Pipe that prints out Blocks that pass thru it.
 * This is usefulf for debugging pipelines.
 * 
 * @author whwong
 *
 */
public class TailPipe implements Pipe{

	PrintStream ps;
	String prefix = "";
	
	private TailPipe(PrintStream ps) {
		this.ps = ps;
	}
	
	private TailPipe(PrintStream ps, String prefix) {
		this.ps = ps;
		this.prefix = prefix;
	}
	
	/**
	 * Creates a new TailPipe that writes out to the given PrintStream
	 * with a helpful prefix string.
	 * 
	 * @param ps
	 * @param prefix
	 * @return
	 */
	public static TailPipe create(PrintStream ps, String prefix) {
		return new TailPipe(ps, prefix);
	}
	
	/**
	 * Create a new TailPipe that writes out to the console 
	 * without a prefix.
	 * 
	 * @return
	 */
	public static TailPipe create() {
		return new TailPipe(System.out);
	}
	
	/**
	 * Create a new TailPipe that writes out to the console 
	 * with a helpful prefix string.
	 * 
	 * @param prefix
	 * @return
	 */
	public static TailPipe create(String prefix) {
		return new TailPipe(System.out, prefix);
	}
	
	@Override
	public String getName() {
		return "Tail debugger";
	}

	@Override
	public Block transform(Block block) {
		StringBuffer buff = new StringBuffer();
		if(prefix.length() > 0) {
			buff.append('[');
			buff.append(prefix);
			buff.append("] ");
		}
		
		for(String f: block.fields()) {
			
			buff.append(f);
			buff.append(':');
			buff.append(block.value(f));
			buff.append("  ");
		}
		ps.println(buff.toString());
		return block;
	}

}
