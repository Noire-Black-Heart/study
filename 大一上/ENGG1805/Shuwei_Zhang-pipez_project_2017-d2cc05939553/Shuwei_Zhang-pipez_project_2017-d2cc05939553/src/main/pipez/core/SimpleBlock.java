package pipez.core;

import static pipez.core.Utils.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class SimpleBlock implements Block{

	protected LinkedHashMap<String,String> block; //needed to preserve ordering
	protected LinkedList<Block> blocks;
	
	public SimpleBlock() {
		block = new LinkedHashMap<>();
		blocks = new LinkedList<>();
	}
	
	/** 
	 * Convenient constructor to add values using default column names starting
	 * from C1, C2, ....
	 * 
	 * @param vals
	 */
	public SimpleBlock(String...vals) {
		this();
		add(vals);
	}
	
	public SimpleBlock(int...is) {
		this();
		add(is);
	}
	
	public SimpleBlock(double...ds) {
		this();
		add(ds);
	}
	
	public void addEmbeddedBlock(Block block){
		blocks.add(block);
	}
	
	/**
	 * Convenience method to add values to the block using default column names of "C<nth_field"
	 * e.g. if there are three existing fields, the field of the next val would be C4
	 * 
	 * @param vals
	 */
	public void add(String... values) {
		
		int numFields = block.size();
		for(String v: values) {
			block.put("C" + (++numFields), v);
		}
	}
	
	public void add(int... values) {
		this.add(stringsOf(values));
	}
	
	public void add(double... values) {
		this.add(stringsOf(values));
	}
	/**
	 * 
	 * @param field
	 * @param value
	 * @return Previous value associated with 'field'; null if no previous value or if null
	 * was the previous value.
	 * 
	 */
	public String add(String field, String value) {
		return block.put(field, value);	
	}
	
	public String delete(String field) {
		return block.remove(field);
	}
	
	@Override
	public String value(String field) {
		return block.get(field);
	}

	@Override
	public String[] fields() {
		return block.keySet().toArray(new String[block.size()]);
	}

	@Override
	public Block[] blocks() {
		return blocks.toArray(new Block[blocks.size()]);
	}

	@Override
	public String[] values() {
		return block.values().toArray(new String[block.size()]);
	}

}
