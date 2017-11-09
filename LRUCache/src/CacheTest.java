import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

/**
 * Code to test an <tt>LRUCache</tt> implementation.
 */
public class CacheTest {
	
	@Test
	public void leastRecentlyUsedIsCorrect () {
		DataProvider<Integer,String> provider = null; // Need to instantiate an actual DataProvider
		Cache<Integer,String> cache = new LRUCache<Integer,String>(provider, 5);
		
	
		
	
	
	}
	//Test order when calling get on a cache, use data provider  method getNumMisses()
	@Test
	public void testLRU()
	{
		HashMap<Integer,String> testStorage = new HashMap<Integer, String>(5);
		Provider<Integer,String> provider = new Provider(testStorage); // Need to instantiate an actual DataProvider
		Cache<Integer,String> cache = new LRUCache<Integer,String>(provider, 5);
		int misses = provider.getNumMisses();
		System.out.println(misses);
		for(int i =0; i < testStorage.size();i++)
		{
			testStorage.put(i, Integer.toString(i));
		}
		
		assertEquals(provider.getNumMisses(), 0);
		//assertEquals(, provider.getNumMisses()==misses+1);
		//System.out.println(misses);
		cache.get(1);
		assertEquals(provider.getNumMisses(), misses+1);
		cache.get(2);
		cache.get(3);
		cache.get(4);
		cache.get(5);
		cache.get(1);
		assertEquals(provider.getNumMisses(),misses+1);
	}
	
	
	
	@Test
	public void testOther()
	{
		HashMap<String,String> testStorage = new HashMap<String, String>(5);
		Provider<String,String> provider = new Provider(testStorage); // Need to instantiate an actual DataProvider
		Cache<String,String> cache = new LRUCache<String,String>(provider, 5);
		int misses = provider.getNumMisses();
		System.out.println(misses);
		for(int i =0; i < testStorage.size();i++)
		{
			testStorage.put("" + i, "" + i);
		}
		
		assertEquals(provider.getNumMisses(), 0);
		//assertEquals(, provider.getNumMisses()==misses+1);
		//System.out.println(misses);
		cache.get("1");
		assertEquals(provider.getNumMisses(), misses+1);
		cache.get("2");
		cache.get("3");
		cache.get("4");
		cache.get("5");
		cache.get("1");
		assertEquals(provider.getNumMisses(),misses+1);	
	}
	   
	//Test based on System.time
	@Test
	public void testTime()
	{
		HashMap<Integer,Integer> testStorage = new HashMap<Integer, Integer>(10000000);
		for(int i = 0; i <testStorage.size();i++)
		{
			testStorage.put(i, i);
		}
		
		Provider<Integer,Integer> provider = new Provider(testStorage); // Need to instantiate an actual DataProvider
		Cache<Integer,Integer> cache = new LRUCache<Integer,Integer>(provider, 5);
		
		//Fill cache with stuff
		ArrayList<LRUCache<Integer,Integer>> cacheArray = new ArrayList();
		for(int i=0; i < 100;i++)
		{
			cacheArray.add(new LRUCache<Integer,Integer>(provider, i));
		}
		
		final long[] cacheStartTimes = new long[cacheArray.size()];
		final long[] cacheEndTimes = new long[cacheArray.size()];
		for(int i=0; i< cacheArray.size();i++)
		{
			cacheStartTimes[i] = System.currentTimeMillis();
			//for(int j =0; )
			cacheEndTimes[i] = System.currentTimeMillis();
		}
		final long endTime = System.currentTimeMillis();
		
		
		
		
	}
	
	
	
}
