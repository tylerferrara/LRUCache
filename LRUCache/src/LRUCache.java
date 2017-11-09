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
	
	public int getCapacity() {
		return this.capacity;
	}
	
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
	
	public static void main(String args[]) {
		HashMap<Integer, String> testProvider = new HashMap<Integer, String>();
        testProvider.put(new Integer(1), "1");
        testProvider.put(new Integer(2), "2");
        testProvider.put(new Integer(3), "3");
        testProvider.put(new Integer(4), "4");
        testProvider.put(new Integer(5), "5");
        testProvider.put(new Integer(6), "6");
        testProvider.put(new Integer(7), "7");
        testProvider.put(new Integer(8), "8");
        testProvider.put(new Integer(9), "9");
        testProvider.put(new Integer(10), "10");
        testProvider.put(new Integer(11), "11");
        testProvider.put(new Integer(12), "12");
        testProvider.put(new Integer(13), "13");
        testProvider.put(new Integer(14), "14");
        Provider tp = new Provider(testProvider);
        LRUCache test = new LRUCache(tp, 4);
        
        double start = System.currentTimeMillis();
        test.get(1);
        double end = System.currentTimeMillis();
        System.out.println(end - start);
        test.get(2);
        test.get(3);
        test.get(4);
        start = System.currentTimeMillis();
        test.get(1);
        end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(test.recentlyUsed.getLast());
        test.get(5);
        System.out.println(test.storage.containsKey(1));
        System.out.println(test.storage.containsKey(2));
        System.out.println(test.getNumMisses());
        System.out.println(test.getCapacity());
	}

}
