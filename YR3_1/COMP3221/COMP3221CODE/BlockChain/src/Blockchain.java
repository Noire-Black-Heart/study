import java.util.ArrayList;

public class Blockchain {
    private Block head;
    private ArrayList<Transaction> pool;
    private int length;

    private final int poolLimit = 3;

    public Blockchain() {
        pool = new ArrayList<>();
        length = 0;
    }

    // getters and setters
    public Block getHead() { return head; }
    public ArrayList<Transaction> getPool() { return pool; }
    public int getLength() { return length; }
    public void setHead(Block head) { this.head = head; }
    public void setPool(ArrayList<Transaction> pool) { this.pool = pool; }
    public void setLength(int length) { this.length = length; }

    // add a transaction
    public int addTransaction(String txString) {
        // TODO: implement you code here.
    	Transaction trans = new Transaction();
    	//split the txString
    	String[] parts = txString.split("\\|");
    	
    	//check if content have |, cant be checked in transaction function
    	if(parts.length != 3 || !parts[0].equals("tx")) {
    		return 0;
    	}
    	
    	//add it to transaction
    	
    	trans.setSender(parts[1]);
    	trans.setContent(parts[2]);
    	
    	//if not valid
    	if(trans.isValidTransaction() == false) {
    		return 0;
    	}
    	
    	//if valid
    	pool.add(trans);
    	
    				//if pool reach limit
			    	if(pool.size() == poolLimit) {
			    		//create new block
			    		Block newHead = new Block();
			    		newHead.setTransactions(pool);
			    		
			    		newHead.setPreviousBlock(head);
			    		//if this is the genesis block
			    		if(this.getHead() == null) {
			    			//init the newHead as genesis
			    			byte[] array = new byte[32];
			    			newHead.setPreviousHash(array);
			    		}
			    		//if this is not genesis
			    		else {
			    			newHead.setPreviousHash(this.getHead().calculateHash());
			    		}
			    		
			    		//set new stuff
			    		this.setHead(newHead);
			    		length = length+1;
			    		pool = new ArrayList<>();
			    		
			    		return 2;
			    	}
    	
    	//normal add
    	return 1;
    }

    public String toString() {
        String cutOffRule = new String(new char[81]).replace("\0", "-") + "\n";
        String poolString = "";
        for (Transaction tx : pool) {
            poolString += tx.toString();
        }

        String blockString = "";
        Block bl = head;
        while (bl != null) {
            blockString += bl.toString();
            bl = bl.getPreviousBlock();
        }

        return "Pool:\n"
                + cutOffRule
                + poolString
                + cutOffRule
                + blockString;
    }

    // implement helper functions here if you need any.
}