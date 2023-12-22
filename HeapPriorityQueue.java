/*

Name: Soffia Khutorna
V00999227
* HeapPriorityQueue.java
*
* An implementation of a minimum PriorityQueue using a heap.
* based on the implementation in "Data Structures and Algorithms
* in Java", by Goodrich and Tamassia
*
* This implementation will throw a Runtime, HeapEmptyException
*	if the heap is empty and removeMin is called.
*
* This implementation will throw a Runtime, HeapFullException
*	if the heap is full and insert is called.
*
*/

public class HeapPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T> {

	protected final static int DEFAULT_SIZE = 10000;
	
	protected T[] storage;
	protected int currentSize;
	
	// Feel free to change rootIndex to 0 if you want to 
	// use 0-based indexing (either option is fine)
	private static final int rootIndex = 1;

	/*
	 * Constructor that initializes the array to hold DEFAULT_SIZE elements
	 */
	@SuppressWarnings({"unchecked"})
	public HeapPriorityQueue(Class<T> dataType) {
		// Creating generics arrays in Java is not very clean. The following 
		// two lines allocate the generic array for you based on whether you
		// have selected to store the root at index 0 or 1 above.
		if (rootIndex == 0) {
			storage = (T[]) java.lang.reflect.Array.newInstance(dataType, DEFAULT_SIZE);
		} else {
			storage = (T[]) java.lang.reflect.Array.newInstance(dataType, DEFAULT_SIZE+1);
		}
		currentSize = 0;
	}
	
	/*
	 * Constructor that initializes the array to hold size elements
	 */
	@SuppressWarnings({"unchecked"})
	public HeapPriorityQueue(Class<T> clazz, int size) {
		// Creating generics arrays in Java is not very clean. The following 
		// two lines allocate the generic array for you based on whether you
		// have selected to store the root at index 0 or 1 above.
		if (rootIndex == 0) {
			storage = (T[]) java.lang.reflect.Array.newInstance(clazz, size);
		} else {
			storage = (T[]) java.lang.reflect.Array.newInstance(clazz, size+1);
		}
		currentSize = 0;
	}

	public void insert (T element) throws HeapFullException {
		
		if (isFull()){
			throw new HeapFullException();
		}

		currentSize++;
		storage[currentSize] = element;
		
		bubbleUp(currentSize);
		
    }
	
	private void bubbleUp(int index) {
		if (index > 1 && storage[index].compareTo(storage[index/2]) < 0){
			swap(index, index/2);
			bubbleUp(index/2);	
		}
	}

	private void swap(int i1, int i2) {
		T val = storage[i1];
		storage[i1] = storage[i2];
		storage[i2] = val;
	}
			
	public T removeMin() throws HeapEmptyException {
		if (isEmpty()){
			throw new HeapEmptyException();
		}
		T removed = storage[rootIndex];
		storage[rootIndex] = storage[currentSize];
		currentSize--;
		bubbleDown(rootIndex);
		
		return removed; 
	}
	
	private void bubbleDown(int index) {
		
			if (left(index) > currentSize){
				return;
			}

			int minIndex = left(index);

			if (right(index) <= currentSize){
				if (storage[left(index)].compareTo(storage[right(index)]) > 0){
					minIndex = right(index);
				}
			}
	
			if (storage[index].compareTo(storage[minIndex]) > 0){
				swap(index, minIndex);
				bubbleDown(minIndex);
			}
			
		}
		
	

	private int left(int index){
		return 2*index;
	}

	private int right(int index){
		return 2*index+1;
	}

	
	public boolean isEmpty(){
		
		return currentSize == 0; 
	}
	
	public boolean isFull() {
		return storage.length <= currentSize+1;
	}
	
	public int size () {
		return currentSize; 
	}

	public String toString() {
		String s = "";
		String sep = "";
		if (rootIndex == 0) {
			for (int i = 0; i < currentSize; i++) {
				s += sep + storage[i];
				sep = " ";				
			}
		} else if (rootIndex == 1) {
			for(int i=1; i<=currentSize; i++) {
				s += sep + storage[i];
				sep = " ";
			}
		}
		return s;
	}
}
