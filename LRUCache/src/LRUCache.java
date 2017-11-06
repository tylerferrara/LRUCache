import java.util.HashMap;

/**
 * An implementation of <tt>Cache</tt> that uses a least-recently-used (LRU)
 * eviction policy.
 */
public class LRUCache<T, U> implements Cache<T, U> {
	
	private int capacity;
	private DataProvider<T, U> provider;
	private HashMap<T, String> usage;
	private int numMisses;
	private HashMap<T, U> storage;
	
	/**
	 * @param provider the data provider to consult for a cache miss
	 * @param capacity the exact number of (key,value) pairs to store in the cache
	 */

	public LRUCache (DataProvider<T, U> provider, int capacity) {
		this.provider = provider;
		this.storage = new HashMap<T, U>(capacity);
		this.usage = new HashMap<T, String>(capacity);
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
	
	private void addToStorage(T key, U data) {
		if(storage.size() == this.capacity) {
			// replace new data and key with oldest used one
			
			// find oldest data entry (hard to implement)
			// replace that data with new data
			// record change in usage
		} else {
			storage.put(key, data);
			usage.put(key, "Some sort of time-stamp...");
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
