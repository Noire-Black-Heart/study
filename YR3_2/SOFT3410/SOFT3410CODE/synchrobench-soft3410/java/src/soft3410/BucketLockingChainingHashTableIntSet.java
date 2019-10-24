package soft3410;

import java.util.ArrayList;

import soft3410.CoarseGrainedLinkedListIntSet;
//the code used CoarseGrainedLinkedListIntSet class as its bucket, which is not written by me, but provided by synchrobench instead. 

public class BucketLockingChainingHashTableIntSet extends contention.abstractions.AbstractCompositionalIntSet {
	
	
	private ArrayList<CoarseGrainedLinkedListIntSet> bucketList;
	
	public BucketLockingChainingHashTableIntSet() {
		this.bucketList = new ArrayList<CoarseGrainedLinkedListIntSet>();
		for(int i = 0; i <10; i++) {
			this.bucketList.add(new CoarseGrainedLinkedListIntSet());
		}
		
	}
	
	
	
	/**
	   * Add a new int to the set.
	   * 
	   * @param value  The new int to be added
	   * @return false if the int already exists in the set
	   */
	@Override
	public boolean addInt(int x) {
		// TODO Auto-generated method stub
		int hash = Integer.hashCode(x);
        int bucketNum = getBucketNum(hash);
        CoarseGrainedLinkedListIntSet bucket = bucketList.get(bucketNum);
        
        
        return bucket.addInt(x);
	}

	/**
	   * Remove an int from the set.
	   * 
	   * @param value  The int to be removed
	   * @return false if the int did not exist in the set
	   */
	@Override
	public boolean removeInt(int x) {
		// TODO Auto-generated method stub
		int hash = Integer.hashCode(x);
        int bucketNum = getBucketNum(hash);
        CoarseGrainedLinkedListIntSet bucket = bucketList.get(bucketNum);
        
        return bucket.removeInt(x);
        

	}

	/* (non-Javadoc)
	 * @see contention.abstractions.CompositionalIntSet#containsInt(int)
	 */
	@Override
	public boolean containsInt(int x) {
		// TODO Auto-generated method stub
		int hash = Integer.hashCode(x);
        int bucketNum = getBucketNum(hash);
        CoarseGrainedLinkedListIntSet bucket = bucketList.get(bucketNum);
        
        return bucket.containsInt(x);

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		int s = 0;
		for(CoarseGrainedLinkedListIntSet bucket : bucketList) {
			//System.out.println(bucket.size());
			s = s + bucket.size();
		}
		return s;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for(CoarseGrainedLinkedListIntSet bucket : bucketList) {
			bucket.clear();
		}
	}
	
	

}
