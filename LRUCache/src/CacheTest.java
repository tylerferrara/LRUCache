import static org.junit.Assert.*;

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
		assertEquals(provider.getNumMisses(), provider.getNumMisses()==0);
		assertEquals(cache.get(1), provider.getNumMisses()==misses+1);
		System.out.println(misses);
		cache.get(2);
		cache.get(3);
		cache.get(4);
		cache.get(5);
	}
	
	@Test
	public void testInt()
	{
		
	}
	@Test
	public void testString()
	{
		
	}
	@Test
	public void testObject()
	{
		
	}
	   
	//Test based on System.time
	@Test
	public void testTime()
	{
		HashMap<Integer,String> testStorage = new HashMap<Integer, String>(5);
		Provider<Integer,String> provider = new Provider(testStorage); // Need to instantiate an actual DataProvider
		Cache<Integer,String> cache = new LRUCache<Integer,String>(provider, 5);
		final long startTime = System.currentTimeMillis();
		//Fill cache with stuff
		
		
			
		final long endTime = System.currentTimeMillis();
		
		final long totalTime = endTime-startTime;
		final long tester= 100000000;
		assertEquals(totalTime < tester, true);
		
	}
	
	
	
}
