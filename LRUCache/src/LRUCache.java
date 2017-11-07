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
	public U get (T key) {
		// do I already have key locally??
		// yes: return
		// no:  fetch from provider and add to local storage
		
		U attempt = storage.get(key);
		if(attempt.equals(null)) {
			//didn't find it
			this.numMisses += 1;
			// get from provider
			U data = provider.get(key);
			// add to local storage
			addToStorage(key, data);
			return data;
		} else {
			// got it!
			// wow that was quick...
			return attempt;
		}
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	private void addToStorage(T key, U data) {
		if(storage.size() == this.capacity) {
			// find oldest data entry (hard to implement)
			storage.remove(recentlyUsed.getFirst());
			recentlyUsed.removeFirst();
			// replace that data with new data
			storage.put(key, data);
			// record newest change
			recentlyUsed.add(key);
		} else {
			storage.put(key, data);
			recentlyUsed.add(key);
		}
	}

	/**
	 * Returns the number of cache misses since the object's instantiation.
	 * @return the number of cache misses since the object's instantiation.
	 */
	public int getNumMisses () {
		return this.numMisses;
	}
}
