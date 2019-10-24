package contention.abstractions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import java.util.ArrayList;
import soft3410.LinkedListIntSet;

public abstract class AbstractCompositionalIntSet implements CompositionalIntSet, CompositionalMap<Integer, Integer> {

	public class IntEntry implements java.util.Map.Entry<Integer, Integer>{

		private Integer key;
		private Integer value;
		private IntEntry next;
		
		public IntEntry(Integer key, Integer value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}
		
		@Override
		public Integer getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public Integer getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public Integer setValue(Integer value) {
			// TODO Auto-generated method stub
			this.value = value;
			return this.value;
		}
		
		public void setNext(IntEntry next) {
			this.next = next;
		}
		
		public IntEntry getNext() {
			return this.next;
		}
	}
	
	protected ArrayList<ArrayList<IntEntry>> bucketList;
	protected int size;
	
	protected ArrayList<LinkedListIntSet> buckets;
	
	//helper method to get bucket number
	public int getBucketNum(Integer number) {
		int n = number % 10;
		return n;
	}
	
	
    @Override
    public boolean containsKey(Object key) {
        //throw new RuntimeException("unimplemented method");
        // TODO Auto-generated method stub
    	int hash = key.hashCode();
        int bucketNum = getBucketNum(hash);
        
        for(IntEntry entry : bucketList.get(bucketNum)) {
        	if(entry.getKey() == key) {
        		return true;
        	}
        }
		return false;
        
    }

    @Override
    public boolean containsValue(Object value) {
    	//throw new RuntimeException("unimplemented method");
        // TODO Auto-generated method stub
        int bucketNum = getBucketNum((Integer)value);
        
        for(IntEntry entry : bucketList.get(bucketNum)) {
        	if(entry.getKey() == value) {
        		return true;
        	}
        }
		return false;
        
    }

    @Override
    public Set<Entry<Integer, Integer>> entrySet() {
        //throw new RuntimeException("unimplemented method");
        // TODO Auto-generated method stub
        Set<Entry<Integer, Integer>> entrySet = new HashSet<Entry<Integer, Integer>>();
        for(ArrayList<IntEntry> bucket : bucketList) {
        	for(IntEntry entry : bucket) {
        		if(entry != null) {
        		entrySet.add(entry);
        		}
        	}
        	
        }
        return entrySet;
    }

    @Override
    public Integer get(Object key) {
        if (containsInt((Integer) key)) {
            return (Integer) key;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        //throw new RuntimeException("unimplemented method");
        for(ArrayList<IntEntry> bucket : bucketList) {
        	if(bucket.size() != 0) {
        		return false;
        	}
        }
		return true;
    }

    @Override
    public Set<Integer> keySet() {
        //throw new RuntimeException("unimplemented method");
        // TODO Auto-generated method stub
        Set<Integer> keySet = new HashSet<Integer>();
        for(ArrayList<IntEntry> bucket : bucketList) {
        	for(IntEntry entry : bucket) {
        		if(entry != null) {
        		keySet.add(entry.getKey());
        		}
        	}
        	
        }
        return keySet;
    }

    @Override
    public Integer put(Integer key, Integer value) {
        //throw new RuntimeException("unimplemented method");
        // TODO Auto-generated method stub
        //IntEntry entry = new IntEntry(key, value);
        int hash = key.hashCode();
        int bucketNum = getBucketNum(hash);
        ArrayList<IntEntry> bucket = bucketList.get(bucketNum);
        //if the bucket is empty
        if(bucket.get(0) == null) {
        	bucket.add(new IntEntry(key, value));
        	size ++;
        	return key;
        }
        
        //if not empty, then traverse through the bucket
        IntEntry current = bucket.get(0);
        while(current.next != null) {
        	//if the key already exist
        	if(current.getKey() == key) {
        		//update current value
        		current.setValue(value);
        		return value;
        	}
        	//if current is not the target, advance
        	current = current.next;
        }
        // check last node
        if(current.getKey() == key) {
        	//update current value
        	current.setValue(value);
        	return value;
        }
        //if no same key, add new node
        IntEntry entry = new IntEntry(key, value);
        current.setNext(entry);
        return value;
    }
    
  
    @Override
    public void putAll(Map<? extends Integer, ? extends Integer> m) {
        throw new RuntimeException("unimplemented method");
        // TODO Auto-generated method stub
    }

    @Override
    public Integer remove(Object key) {
        if (removeInt((Integer) key)) {
            return (Integer) key;
        }
        return null;
    }

    @Override
    public Collection<Integer> values() {
        throw new RuntimeException("unimplemented method");
        // TODO Auto-generated method stub
    }

    @Override
    public Integer putIfAbsent(Integer k, Integer v) {
        if (addInt(k)) {
            return null;
        }
        return k;
    }

    @Override
    public Object putIfAbsent(int x, int y) {
        if (addInt(x)) {
            return null;
        }
        return x;
    }

    @Override
    public Object getInt(int x) {
        throw new RuntimeException("unimplemented method");
        // TODO Auto-generated method stub
    }

    @Override
    public boolean addAll(Collection<Integer> c) {
        throw new RuntimeException("unimplemented method");
        // TODO Auto-generated method stub
    }

    @Override
    public boolean removeAll(Collection<Integer> c) {
        throw new RuntimeException("unimplemented method");
        // TODO Auto-generated method stub
    }

    @Override
    public void fill(int range, long size) {
        throw new RuntimeException("unimplemented method");
        // TODO Auto-generated method stub
    }
}
