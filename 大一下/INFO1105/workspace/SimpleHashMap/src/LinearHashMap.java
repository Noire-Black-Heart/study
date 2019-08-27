import java.util.ArrayList;

//import SimpleHashMap.HashMapEntry;

public class LinearHashMap<K, V> implements Map<K, V>  {

	private class HashMapEntry implements Entry<K, V> {
		
		private K key;
		private V value;
		
		public HashMapEntry(K key, V value) {
			// TODO
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return this.key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return this.value;
		}
	}
	
	private HashMapEntry[] items;
	private int numberOfItems;
	
	@SuppressWarnings("unchecked")	
	public LinearHashMap(int capacity) {
		items = (LinearHashMap<K, V>.HashMapEntry[]) new LinearHashMap.HashMapEntry[capacity];
		this.numberOfItems = 0;
	}
	
	private int hash(K key) {
		return key.hashCode();
	}
	
	private int compress(int hash) {
		return Math.abs(hash) % items.length;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.numberOfItems;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(this.size() == 0){
			return true;
		}else{
		return false;
		}
	}
	
	public boolean isDefunct(HashMapEntry entry){
		if(entry.getKey() == null && entry.getValue() == null){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		int compIndex = compress(key.hashCode());
		V value = getRecursion(this.items, key, compIndex, 0);
		return value;

	}
	
	public V getRecursion(HashMapEntry[] items, K key, int currentLoc, int numCount){
		//if the current location is null, or the probe return to initial position
		if(items[currentLoc] == null || numCount == items.length){
			return null;
		}
		//if the current location's key matches the wanted key
		if(items[currentLoc].getKey() == key){
			return items[currentLoc].getValue();
		}
		
		//if the current location is defunct or the key doesnt match
		if(isDefunct(items[currentLoc]) || items[currentLoc].getKey() != key){
			//if we arrived the last of table
			if(currentLoc == items.length -1){
				numCount++;
				return getRecursion(items, key, 0, numCount);
			}
			//progress as normal
			else{
			numCount++;
			return getRecursion(items, key, currentLoc+1, numCount);
			}
		}
		return null;
	}
	
	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		int compIndex = compress(key.hashCode());
		V oldValue = putRecursion(this.items, key, value, compIndex, 0);
		return oldValue;
	}

	public V putRecursion(HashMapEntry[] items, K key, V value, int currentLoc, int numCount){
			//if we find a defunct or empty cell
			if(items[currentLoc] == null || isDefunct(items[currentLoc])){
			items[currentLoc] = new HashMapEntry(key, value);
			this.numberOfItems++;
			return null;
		}
		//if current location match the key we need, then replace the value and return old value
		if(items[currentLoc].getKey() == key){
			V temp = items[currentLoc].getValue();
			items[currentLoc].value = value;
			return temp;
		}
		//if table is full
			if(numCount == items.length){
			throw new RuntimeException("Table is full");
		}
	
		//if current location is occupied
		else{
			//if we arrived the last of table
			if(currentLoc == items.length -1){
				numCount++;
				return putRecursion(items, key, value, 0, numCount);
			}
			//progress as normal
			else{
			numCount++;
			return putRecursion(items, key, value, currentLoc+1, numCount);
			}
		}
	//	return null;
	}
	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		int compIndex = compress(key.hashCode());
		V oldValue = removeRecursion(this.items, key, compIndex, 0);
		return oldValue;
	}

	public V removeRecursion(HashMapEntry[] items, K key, int currentLoc, int numCount){
		//if we traversed the table and didnt find anything
		if(numCount == items.length || items[currentLoc] == null){
			return null;
		}
		//if the current key matches the wanted key
		if(items[currentLoc].getKey() == key){
			V temp = items[currentLoc].getValue();
			items[currentLoc].key = null;
			items[currentLoc].value = null;
			this.numberOfItems--;
			return temp;
		}
		
		//if the current key doesnt match the wanted key
		else{
			//if we arrived last of table
			if(currentLoc == items.length-1){
				numCount++;
				return removeRecursion(items, key, 0, numCount);
			}else{
				numCount++;
				return removeRecursion(items, key, currentLoc+1, numCount);
			}
		}
		
	}
	@Override
	public Iterable<K> keySet() {
		// TODO Auto-generated method stub
		ArrayList<K> keySet = new ArrayList<K>();
		for(int i = 0; i < items.length; i++){
			if(this.items[i] != null && !isDefunct(this.items[i])){
				keySet.add(this.items[i].getKey());
			}
		}
		
		return keySet;
	}

	@Override
	public Iterable<V> values() {
		// TODO Auto-generated method stubArrayList<K> keySet = new ArrayList<K>();
		ArrayList<V> valueSet = new ArrayList<V>();
		for(int i = 0; i < items.length; i++){
			if(this.items[i] != null && !isDefunct(this.items[i])){
				valueSet.add(this.items[i].getValue());
			}
		}
		
		return valueSet;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		ArrayList<Entry<K, V>> valueSet = new ArrayList<Entry<K, V>>();
		for(int i = 0; i < items.length; i++){
			if(this.items[i] != null && !isDefunct(this.items[i])){
				valueSet.add(this.items[i]);
			}
		}
		
		return valueSet;
	}

}
