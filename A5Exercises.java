//V00999227 Sofiia Khutorna

public class A5Exercises {

	/*
	 * Purpose: get the number of occurrences of toFind in theList
	 * Parameters: List<T> theList - the list to search through
	 *             T toFind - the target search value
	 * Returns: int - the number of occurrences of toFind found
	 */
	public static<T> int countMatches(List<T> theList, T toFind) {
		
		return countMatchesRec(theList, toFind, 0); 
	}

	public static<T> int countMatchesRec(List<T> theList, T toFind, int pos){
		if (pos == theList.size()){
			return 0;
		}else{
			if (theList.get(pos).equals(toFind)){
				return 1 + countMatchesRec(theList, toFind, pos+1);
			}else{
				return countMatchesRec(theList, toFind, pos+1);
			}
		}
	}
	
	/*
	 * Purpose: change all occurrences of x to y in the given list
	 * Parameters: List<T> theList - the list to search through
	 *			   T x - the value to change
	 *			   T y = the value to change all x's to
	 * Returns: void - nothing
	 */
	public static<T> void changeXToY(List<T> theList, T x, T y) {
		changeXToYRec(theList, x, y, 0);
	}

	public static<T> void changeXToYRec(List<T> theList, T x, T y, int pos){
		if (pos == theList.size()){
			return;
		}else{
			if (theList.get(pos).equals(x)){
				theList.change(pos,y);
				changeXToYRec(theList, x, y, pos+1);
			}else{
				changeXToYRec(theList, x, y, pos+1);
			}
		}
	}
		
	/* 
	 * Purpose: determines if all values in the list are equivalent
	 * Parameters: List<T> theList - the list
	 * Returns: boolean - true unless any values in the list are 
	 *                    different from one another
	 */
	public static<T> boolean allEqual(List<T> theList) {
		if (theList.size()<=1) {
			return true;
		} else {
			return allEqualRec(theList, 1); 
		}
	}

	public static<T> boolean allEqualRec(List<T> theList, int pos) {

		if (pos == theList.size()) {
			return true;
		} else {
			if (theList.get(pos).equals(theList.get(pos-1))) {
				return allEqualRec(theList, pos+1);
			} else {
				return false;
			}
		}
	}
	
	
	/*
	 * Purpose: get the range of values in the given list
	 * Parameters: List<Integer> theList - the list of Integers
	 * Returns: int - the range of values
	 */
	public static int rangeOfValues(List<Integer> theList) {
		if (theList.size() == 0) {
			return 0;
		} else {
			return rangeOfValuesRec(theList, 1, theList.get(0), theList.get(0));
		}
	}
	
	/*
	 * Complete the design of the recursive helper below
	 */
	
	/*
	 * Purpose: get the range of values in the given list from index i onward
	 * Parameters: List<Integer> theList - the list of Integers
	 *             int i - the current index
	 *             Integer min - the smallest value found so far
	 *             Integer max - the largest value found so far
	 * Returns: int - the range of values
	 */
	public static int rangeOfValuesRec(List<Integer> theList, int i, Integer min, Integer max) {
		if (theList.size() == i){
			return (max-min+1);
		}else{
			if (theList.get(i) > max){
				return rangeOfValuesRec(theList, i+1, min, theList.get(i));
			}else if (theList.get(i) < min){
				return rangeOfValuesRec(theList, i+1, theList.get(i), max);
			}else{
				return rangeOfValuesRec(theList, i+1, min, max);
			}
		}
		
	}
}