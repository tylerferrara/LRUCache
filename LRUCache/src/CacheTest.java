import static org.junit.Assert.*;
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
	//Test order when calling get on a cache
	@Test
	public void testLRU()
	{
		DataProvider<Integer,String> provider = new Provider(); // Need to instantiate an actual DataProvider
		Cache<Integer,String> cache = new LRUCache<Integer,String>(provider, 5);
		
		cache.get(1);
		
	}
	//Test based on System.time
	@Test
	public void testTime()
	{
		final long startTime = System.currentTimeMillis();
		DataProvider<Integer,String> provider = new Provider(); // Need to instantiate an actual DataProvider
		Cache<Integer,String> cache = new LRUCache<Integer,String>(provider, 5);
		for(int i = 0; i < cache.getCapacity(); i++)
		{
			cache.get(i);
		}
		
		
			
		final long endTime = System.currentTimeMillis();
		
		final long totalTime = endTime-startTime;
		final long  tester= 100000000;
		assertEquals(totalTime < tester, true);
		
	}
	
	
	
}
