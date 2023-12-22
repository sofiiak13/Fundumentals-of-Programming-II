
public class A4Exercises {

	/*
	 * Purpose: gets the number of buildings in the given list
	 * Parameters: List bList - the list of buildings
	 * Returns: int - the number of buildings in the given list
	 */
	public static int buildingsCount(List bList) {
		
		return bList.size(); 
	}
	
	/*
	 * Purpose: gets the total number of inhabitants living 
	 *          in all the buildings in the given list
	 * Parameters: List bList - the list of buildings
	 * Returns: int - the number of inhabitants across all buildings
	 */
	public static int inhabitantsCount(List bList) {
		int total = 0;
		for (int i = 0; i < bList.size(); i++){
			total += bList.get(i).getNumberOfInhabitants();
		}
		return total; 
	}
	
	/*
	 * Purpose: get the number of buildings into the list b is
	 * Parameters: List bList - the list of buildings
	 *             Building b - the building to find
	 * Returns: int - the distance the first occurrence of 
	 *                b is from the start of the list, or 
	 *                -1 if b is not found in bList
	 */
	public static int distanceAway(List bList, Building b) {

		
		for(int i = 0; i < bList.size(); i++) {
			if(bList.get(i).equals(b)) {
				return i;
			}
		}

		return -1;
	}

	
	
	/*
	 * Purpose: get the distance the tallest building is 
	 *          from the start of the list
	 * Parameters: List bList - the list of buildings
	 * Returns: int - the distance the tallest building
	 *                is from the start of the list
	 */
	public static int distanceToTallest(List bList) {

		if (bList.size() == 0){
			return -1;
		}

		Building tallest = bList.get(0);
		int mostStores = 0;

		for (int i = 0; i < bList.size(); i++) {
			if (bList.get(i).getNumberOfStories() > mostStores) {
				mostStores = bList.get(i).getNumberOfStories();
				tallest = bList.get(i);
			}
		}
	
		int distance = distanceAway(bList, tallest);

		return distance; 
	}

	/*
	 * Purpose: get the number of buildings visible 
	 *          from the beginning of the list 
	 * Parameters: List bList - the list of buildings
	 * Returns: int - the number of buildings visible
	 * 
	 * Note: Read through the assignment PDF for more information
	 */
	public static int numberVisible(List bList) {
		int curBiggestStores = 0;
		int total = 0;
		
		for (int i = 0; i < bList.size(); i++) {
			Building curB = bList.get(i);
			if (curB.getNumberOfStories() > curBiggestStores) {
				total++;
				curBiggestStores = curB.getNumberOfStories();
			}
		}
		
		return total; 
	}
		


}