import java.util.HashMap;

public class Provider<T, U> implements DataProvider<T, U> {
	
	private HashMap<T, U> storage;
	private int numMisses;
	
	public Provider(HashMap<T, U> data) {
		this.storage = data;
	}
	
	public U get(T key) {
		return storage.get(key);
	}
	
	public int getNumMisses() {
		return numMisses;
	}
}
