package pipez.core;

public interface Block {
	/**
	 * Returns fields in this block. The ordering is preserved when 
	 * the block is derived from data sources where ordering is defined
	 *  e.g. column ordering is preserved for Block derived from a CSV file.
	 * 
	 * @return fields in this block.
	 */
	public String[] fields();
	
	/**
	 * Return the values in this block. The ordering follows the corresponding 
	 * ordering of the fields if it is defined. 
	 * e.g. values follow their column ordering in the CSV file used to derive this Block
	 * @return 
	 */
	public String[] values();
	
	/**
	 * Returns the value associated with a field
	 * 
	 * @param field
	 * @return value associated with field parameter
	 */
	public String value(String field);
	
	/**
	 * Return the embedded blocks of this block.
	 * @return
	 */
	public Block[] blocks();
	

}
