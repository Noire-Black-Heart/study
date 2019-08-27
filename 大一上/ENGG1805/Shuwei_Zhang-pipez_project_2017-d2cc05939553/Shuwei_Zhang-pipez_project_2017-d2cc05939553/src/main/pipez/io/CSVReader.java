package pipez.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import pipez.core.Block;
import pipez.core.PipezExceptionHandler;
import pipez.core.SimpleBlock;
import pipez.core.SpecialBlocks;

public class CSVReader implements Reader {

	private File in;
	private Scanner sc;
	
	private CSVReader(File in) {
		this.in = in;
	}
	
	public static CSVReader from(String in) {
		return new CSVReader(new File(in));
	}
	
	@Override
	public boolean hasNext() {
		if(sc == null) open();
		if(sc == null) return false;
		return sc.hasNextLine();
	}

	@Override
	public Block next() {
	
		if(! hasNext()) return SpecialBlocks.SKIP_BLOCK;
		
		String line = sc.nextLine();
		return parse(line);
	}

	/**
	 * A simple parser that tokenizes on the simple comma.
	 * 
	 * @param line
	 * @return
	 */
	private Block parse(String line) {
		
		String[] fields = line.split(","); // be careful of empty lines here - split() will return the original 
										   // line in fields[0] if nothing matched the split pattern
		
		int i=1;
		SimpleBlock block = new SimpleBlock();
		if(line.length() > 0) 
			for(String f: fields) {
				block.add("V"+i++, f);
			}
		return block;
	}

	@Override
	public void open() {
		try {
			sc = new Scanner(in);
		} catch (FileNotFoundException e) {
			PipezExceptionHandler.handle(new Exception("The file (" + in + ") could not be found.", e));
		}
	}

	@Override
	public void close() {
		if (sc!= null) sc.close();
	}
}
