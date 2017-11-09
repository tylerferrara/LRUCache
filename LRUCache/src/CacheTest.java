import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

/**
 * Code to test an <tt>LRUCache</tt> implementation.
 */
public class CacheTest {
	
		
	
	/*Tests the LRU function of the cache
	 * Removes the least recently used
	 * Tests the numMisses of the data provider 
	 * */
	@Test
	public void lestRecentlyUsedIsCorrect()
	{
		HashMap<Integer,String> testStorage = new HashMap<Integer, String>(5);
		Provider<Integer,String> provider = new Provider(testStorage); // Need to instantiate an actual DataProvider
		Cache<Integer,String> cache = new LRUCache<Integer,String>(provider, 5);
		int misses = provider.getNumMisses();
		
		for(int i =0; i < testStorage.size();i++)
		{
			testStorage.put(new Integer(i), Integer.toString(i));
		}
		
		assertEquals(provider.getNumMisses(), 0);
		cache.get(1); //1 miss
		assertEquals(provider.getNumMisses(), misses +1);
		
		cache.get(2); //2 miss
		cache.get(3); //3 miss
		cache.get(4); //4 miss
		cache.get(5); //5 miss
		misses = provider.getNumMisses();
		cache.get(6); //6 miss, kicks key + value at 1 out
		cache.get(1); //7 miss b/c 1 was already removed so it missed again
		misses = provider.getNumMisses();
		assertEquals(provider.getNumMisses(),misses);
	}
	//===================================================================================================
	/*
	 * Test numMisses from both the LRUCache as well as DataProvider
	 */
	@Test
	public void testNumMisses()
	{
		HashMap<Integer,String> testStorage = new HashMap<Integer, String>(5);
		Provider<Integer,String> provider = new Provider(testStorage);
		Cache<Integer,String> cache = new LRUCache<Integer,String>(provider, 5);
		int misses = provider.getNumMisses();
		
		for(int i =0; i < testStorage.size();i++)
		{
			testStorage.put(i, Integer.toString(i));
		}
		cache.get(1);
		assertEquals(cache.getNumMisses() == provider.getNumMisses(), true);
		
		
	}
	//===================================================================================================
	/*Test a cache of a type string
	 * 
	 */
	@Test
	public void testOther()
	{
		HashMap<String,String> testStorage = new HashMap<String, String>(5);
		Provider<String,String> provider = new Provider(testStorage); // Need to instantiate an actual DataProvider
		Cache<String,String> cache = new LRUCache<String,String>(provider, 5);
		int misses = provider.getNumMisses();
		for(int i =0; i < testStorage.size();i++)
		{
			testStorage.put("" + i, "" + i);
		}
		
		assertEquals(provider.getNumMisses(), 0);
		cache.get("1");
		assertEquals(provider.getNumMisses(), misses+1);
		cache.get("2");
		cache.get("3");
		cache.get("4");
		cache.get("5");
		cache.get("1");
		misses = provider.getNumMisses();
		assertEquals(provider.getNumMisses(),misses);	
	}
	//===================================================================================================
	/*Test a cache size of 10 for time
	 * 
	 */
	@Test
	public void testSize10()
	{
		
		HashMap<Integer,Integer> testStorage = new HashMap<Integer, Integer>(100);
		for(int i = 0; i <testStorage.size();i++)
		{
			testStorage.put(i, i);
		}

		Provider<Integer,Integer> provider = new Provider(testStorage); // Need to instantiate an actual DataProvider
		Cache<Integer,Integer> cache = new LRUCache<Integer,Integer>(provider, 10);
		final long startTime = System.currentTimeMillis();
		for(int i =0; i< 10; i++)
		{
			cache.get(i);
		}
		final long endTime = System.currentTimeMillis();
		//System.out.println("Execution time: " + (endTime - startTime));
		assertEquals(endTime - startTime < 1000, true);
	}
	//===================================================================================================
		/*Test a cache size of 1000 for time
		 * 
		 */
	@Test
	public void testSize1k()
	{
		
		HashMap<Integer,Integer> testStorage = new HashMap<Integer, Integer>(10000);
		for(int i = 0; i <testStorage.size();i++)
		{
			testStorage.put(i, i);
		}

		Provider<Integer,Integer> provider = new Provider(testStorage); // Need to instantiate an actual DataProvider
		Cache<Integer,Integer> cache = new LRUCache<Integer,Integer>(provider, 1000);
		final long startTime = System.currentTimeMillis();
		for(int i =0; i< 1000; i++)
		{
			cache.get(i);
		}
		final long endTime = System.currentTimeMillis();
		
		//System.out.println("Execution time: " + (endTime - startTime));
		assertEquals(endTime - startTime < 1000, true);
		}
	//===================================================================================================
		/*Test a cache size of 10000 for time
		 * 
		 */
	
	@Test
	public void testSize10k()
	{
		
		HashMap<Integer,Integer> testStorage = new HashMap<Integer, Integer>(10001);
		for(int i = 0; i <testStorage.size();i++)
		{
			testStorage.put(i, i);
		}

		Provider<Integer,Integer> provider = new Provider(testStorage); // Need to instantiate an actual DataProvider
		Cache<Integer,Integer> cache = new LRUCache<Integer,Integer>(provider, 10000);
		final long startTime = System.currentTimeMillis();
		for(int i =0; i< 10000; i++)
		{
			cache.get(i);
		}
		final long endTime = System.currentTimeMillis();
		//System.out.println("Execution time: " + (endTime - startTime));
		assertEquals(endTime - startTime < 1000, true);
	}
	
	
	
}
