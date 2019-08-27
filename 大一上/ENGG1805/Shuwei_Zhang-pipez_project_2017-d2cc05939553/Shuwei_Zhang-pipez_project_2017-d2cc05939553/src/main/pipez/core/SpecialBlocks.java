package pipez.core;

public class SpecialBlocks {
	
	
	/**
	 * A special block that represents an empty line 
	 * i.e. it will be passed through a pipeline and will be written out.
	 */
	public static final Block EMPTY_BLOCK = new NullBlock();
	
	/**
	 * A special block that will be passed through a pipeline but will not be written out.
	 */
	public static final Block SKIP_BLOCK = new NullBlock();
	
	
	/* Basic class for special blocks */

    private static class NullBlock implements Block{

		@Override
		public String value(String field) {
			return null;
		}

		String[] fields = {}, values = {};
		Block[] blocks = new Block[0];
		
		@Override
		public String[] fields() {
			return fields;
		}

		@Override
		public Block[] blocks() {
			return blocks;
		}

		@Override
		public String[] values() {
			return values;
		}
	}

}
