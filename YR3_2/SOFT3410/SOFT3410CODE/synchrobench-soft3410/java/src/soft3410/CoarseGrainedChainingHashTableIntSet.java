package soft3410;

import java.util.ArrayList;

import soft3410.LinkedListIntSet;
//the code used LinkedListIntSet class as its bucket, which is not written by me, but provided by synchrobench instead. 

public class CoarseGrainedChainingHashTableIntSet extends contention.abstractions.AbstractCompositionalIntSet {
	

	private ArrayList<LinkedListIntSet> bucketList;

	
	public CoarseGrainedChainingHashTableIntSet() {
		this.bucketList = new ArrayList<LinkedListIntSet>();
		for(int i = 0; i <10; i++) {
			this.bucketList.add(new LinkedListIntSet());
		}
		
	}
	
	
	
	/**
	   * Add a new int to the set.
	   * 
	   * @param value  The new int to be added
	   * @return false if the int already exists in the set
	   */
	@Override
	public synchronized boolean addInt(int x) {
		// TODO Auto-generated method stub
		int hash = Integer.hashCode(x);
        int bucketNum = getBucketNum(hash);

        LinkedListIntSet bucket = bucketList.get(bucketNum);
        
        
        return bucket.addInt(x);

	}

	/**
	   * Remove an int from the set.
	   * 
	   * @param value  The int to be removed
	   * @return false if the int did not exist in the set
	   */
	@Override
	public synchronized boolean removeInt(int x) {
		// TODO Auto-generated method stub
		int hash = Integer.hashCode(x);
        int bucketNum = getBucketNum(hash);

        LinkedListIntSet bucket = bucketList.get(bucketNum);
        
        return bucket.removeInt(x);
        
        
	}

	/* (non-Javadoc)
	 * @see contention.abstractions.CompositionalIntSet#containsInt(int)
	 */
	@Override
	public synchronized boolean containsInt(int x) {
		// TODO Auto-generated method stub
		int hash = Integer.hashCode(x);
        int bucketNum = getBucketNum(hash);
        LinkedListIntSet bucket = bucketList.get(bucketNum);
        
        return bucket.containsInt(x);
        

	}

	@Override
	public synchronized int size() {
		// TODO Auto-generated method stub
		int s = 0;
		for(LinkedListIntSet bucket : bucketList) {

			s = s + bucket.size();
		}
		return s;
	}

	@Override
	public synchronized void clear() {
		// TODO Auto-generated method stub
		for(LinkedListIntSet bucket : bucketList) {
			bucket.clear();
		}
	}
	
	

}
