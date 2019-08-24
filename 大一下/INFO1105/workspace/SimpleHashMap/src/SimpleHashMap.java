import java.util.*;
public class SimpleHashMap<K, V> implements Map<K, V> {

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
	public SimpleHashMap(int capacity) {
		items = (SimpleHashMap<K, V>.HashMapEntry[]) new SimpleHashMap.HashMapEntry[capacity];
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

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		int hashedKey = hash(key);
		int compIndex = compress(hashedKey);
		if(this.items[compIndex] == null){
			return null;//no key found
		}else{
			if(this.items[compIndex].getKey() == key){
				return this.items[compIndex].getValue();
			}
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		int hashedKey = hash(key);
		int compIndex = compress(hashedKey);
		if(this.items[compIndex] != null){//already exist stuff
			if(this.items[compIndex].getKey() != key){//stuff dont have same key
				throw new IllegalArgumentException("Key conflict");
			}else{
				V beforeValue = this.items[compIndex].getValue();//store the value before overwrite
				this.items[compIndex] = new HashMapEntry(key, value);
				return beforeValue;
			}
		}else{
			this.items[compIndex] = new HashMapEntry(key, value);
			this.numberOfItems++;
		}
		return null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		int hashedKey = hash(key);
		int compIndex = compress(hashedKey);
		if(this.items[compIndex] == null){
			return null;//no key found
		}else{
			V beforeValue = this.items[compIndex].getValue();
			this.items[compIndex] = null;
			this.numberOfItems--;
			return beforeValue;
		}
	//	return null;
	}

	@Override
	public Iterable<K> keySet() {
		// TODO Auto-generated method stub
		ArrayList<K> keySet = new ArrayList<K>();
		for(int i = 0; i < items.length; i++){
			if(this.items[i] != null){
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
			if(this.items[i] != null){
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
			if(this.items[i] != null){
				valueSet.add(this.items[i]);
			}
		}
		
		return valueSet;
	}

}
