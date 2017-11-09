import java.util.HashMap;
import java.util.LinkedList;

/**
 * An implementation of <tt>Cache</tt> that uses a least-recently-used (LRU)
 * eviction policy.
 */
public class LRUCache<T, U> implements Cache<T, U> {
	
	private int capacity;
	private DataProvider<T, U> provider;
	private LinkedList<T> recentlyUsed;
	private int numMisses;
	private HashMap<T, U> storage;
	
	/**
	 * @param provider the data provider to consult for a cache miss
	 * @param capacity the exact number of (key,value) pairs to store in the cache
	 */

	public LRUCache (DataProvider<T, U> provider, int capacity) {
		this.provider = provider;
		this.storage = new HashMap<T, U>(capacity);
		this.recentlyUsed = new LinkedList<T>();
		this.capacity = capacity;
		this.numMisses = 0;
	}

	/**
	 * Returns the value associated with the specified key.
	 * @param key the key
	 * @return the value associated with the key
	 */
	/*Get method for LRUCache
	 * Attempts to retrieve value from cache, if not found will look into storage
	 * also updates recently used element
	 * (non-Javadoc)
	 * @see DataProvider#get(java.lang.Object)
	 */
	public U get (T key) {
		
		if(storage.containsKey(key)) {
			updateRecentlyUsed(key);
			return storage.get(key);
		} else {
			this.numMisses += 1;
			U data = provider.get(key);
			addToStorage(key, data);
			return data;
		}
	}
	
	/*Add data to cache
	 * if element is already there, make it most recently used
	 */
	private void addToStorage(T key, U data) {
		if(storage.size() == this.capacity) {
			storage.remove(recentlyUsed.getFirst());
			storage.put(key, data);
			updateRecentlyUsed(key);
		} else {
			storage.put(key, data);
			updateRecentlyUsed(key);
		}
	}

	/**
	 * Returns the number of cache misses since the object's instantiation.
	 * @return the number of cache misses since the object's instantiation.
	 */
	public int getNumMisses () {
		return this.numMisses;
	}
	/*
	 * Tracks what the most recently used element is 
	 */
	private void updateRecentlyUsed(T key) {
		if(this.recentlyUsed != null && this.recentlyUsed.size() > 0) {
			if(key == this.recentlyUsed.getLast()) {
				return;
			} else if(this.recentlyUsed.contains(key)) {
				this.recentlyUsed.remove(key);
				this.recentlyUsed.addLast(key);
			} else if(this.recentlyUsed.size() < this.capacity) {
				this.recentlyUsed.addLast(key);
			} else {
				this.recentlyUsed.remove(key);
				this.recentlyUsed.addLast(key);
			}
		} else {
			this.recentlyUsed.addLast(key);
		}
	}
	
	

}
