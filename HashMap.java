import java.util.*;

public class HashMap<K extends Comparable<K>, V> implements Map<K,V> {

	private List<List<Entry<K,V>>> 	table;
	private int	count;
	private int tableSize;

	// For Part III
	private long getLoops;
	private long putLoops;
	

	public HashMap() {
		tableSize = 1000003; // prime number
		table = new ArrayList<List<Entry<K,V>>>(tableSize);

		// initializes table as a list of empty lists
		for (int i = 0; i < tableSize; i++) {
			table.add(new LinkedList<Entry<K,V>>());
		}

		count = 0;

		// For Part III:
		resetGetLoops();
		resetPutLoops();
	}

	// For Part III
	public long getGetLoopCount() {
		return getLoops;
	}

	// For Part III
	public long getPutLoopCount() {
		return putLoops;
	}

	// For Part III
	public void resetGetLoops() {
		getLoops = 0;
	}
	
	// For Part III
	public void resetPutLoops() {
		putLoops = 0;
	}

	public boolean containsKey(K key) {

		try {
			get(key);
			return true;
		} catch (KeyNotFoundException e) {
			return false; 
		}
	}

	public V get (K key) throws KeyNotFoundException {
		
		if (count == 0){
			throw new KeyNotFoundException();
		}

		int index = Math.abs(key.hashCode()) % tableSize;
		List<Entry<K,V>> list = table.get(index);
		Iterator<Entry<K,V>> iter = list.iterator();
		Entry<K,V> cur = null;

		while (iter.hasNext()) {
			cur = iter.next();
			if (cur.getKey().compareTo(key) == 0){
				return cur.getValue();
			}
		}

		throw new KeyNotFoundException(); 
	}


	public List<Entry<K,V>>	entryList() {
		List <Entry<K,V>> resultList = new LinkedList<Entry<K,V>>();
		Iterator<List<Entry<K,V>>> bigIter = table.iterator();

		while (bigIter.hasNext()) {
			List<Entry<K,V>> curList = bigIter.next();
			Iterator<Entry<K,V>> smallIter = curList.iterator();
			while (smallIter.hasNext()){
				Entry<K,V> curEntry = smallIter.next();
				resultList.add(curEntry);
			}
		}

		return resultList;		

		// Tip: you will need to iterate through each index in the table (and each index holds a list)
		//      you will THEN need to iterate through each element in the linked list chain at a 
		//      specific index and add each element to l

		
	}
	
	public void put (K key, V value){
		
		int index = Math.abs(key.hashCode())%tableSize;
		Entry<K,V> toInsert = new Entry<K,V>(key, value);

		List<Entry<K,V>> list = table.get(index);

		if (count == 0){
			list.add(toInsert);
			count++;
			return;
		}

		Iterator<Entry<K,V>> iter = list.iterator();
		
		
		while (iter.hasNext()) {
			Entry<K,V> cur = iter.next();
			if (cur.getKey().compareTo(key) == 0){
				cur.setValue(value);
				return;
			}
		}

		list.add(toInsert);
		count++;
	}

	public int size() {
		return count;
	}

    public void clear() {
		for(int i = 0; i < tableSize; i++) {
			table.get(i).clear();
		}
        count = 0;
    }

}