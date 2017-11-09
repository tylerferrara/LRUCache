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
	//@Test
	public void testLRU()
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
		System.out.println(provider.getNumMisses() + " misses");
		assertEquals(provider.getNumMisses(), misses +1);
		
		cache.get(2); //2 miss
		cache.get(3); //3 miss
		cache.get(4); //4 miss
		cache.get(5); //5 miss
		misses = provider.getNumMisses();
		System.out.println(misses);
		cache.get(6); //6 miss, kicks key + value at 1 out
		cache.get(1); //7 miss b/c 1 was already removed so it missed again
		misses = provider.getNumMisses();
		System.out.println(misses);
		assertEquals(provider.getNumMisses(),misses);
	}
	
	
	
	//@Test
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
	//@Test
	public void testSizeTen()
	{
		
		HashMap<Integer,Integer> testStorage = new HashMap<Integer, Integer>(100);
		for(int i = 0; i <testStorage.size();i++)
		{
			testStorage.put(i, i);
		}

		Provider<Integer,Integer> provider = new Provider(testStorage); // Need to instantiate an actual DataProvider
		Cache<Integer,Integer> cache = new LRUCache<Integer,Integer>(provider, 10);
		final long startTime = System.currentTimeMillis();
		System.out.println(startTime);
		for(int i =0; i< 10; i++)
		{
			cache.get(i);
		}
		final long endTime = System.currentTimeMillis();
		System.out.println(endTime);
		System.out.println(endTime - startTime);
	}
	@Test
	public void testSizeThousand()
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
		System.out.println(endTime);
		System.out.println(endTime - startTime);
	}
	
	@Test
	public void testSizeMillion()
	{
		
		HashMap<Integer,Integer> testStorage = new HashMap<Integer, Integer>(10000000);
		for(int i = 0; i <testStorage.size();i++)
		{
			testStorage.put(i, i);
		}

		Provider<Integer,Integer> provider = new Provider(testStorage); // Need to instantiate an actual DataProvider
		Cache<Integer,Integer> cache = new LRUCache<Integer,Integer>(provider, 1000000);
		final long startTime = System.currentTimeMillis();
		for(int i =0; i< 1000000; i++)
		{
			cache.get(i);
			System.out.println(System.currentTimeMillis());
		}
		final long endTime = System.currentTimeMillis();
		System.out.println("Execution time: " + (endTime - startTime));
	}
	
	
	
}
