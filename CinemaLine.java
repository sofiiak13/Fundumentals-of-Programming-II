/*
 * CinemaLine
 * 
 * A class to simulate a lineup of people waiting to enter
 * the cinema. Some people come alone and go to the back of 
 * the line, others bribe their way to a certain position. 
 * Sometimes someone buys a bunch of tickets at once so 
 * multiple people can be removed from the line and enter
 * the cinema at the same time.
 */
public class CinemaLine {
	List<Person> list;
	//Do not add any other fields

	public CinemaLine() {
		//Do not change the constructor in any way
		list = new LinkedList<Person>();
	}
	
	/*
	 * Purpose: add a person to line to enter the cinema
	 * Parameter: Person p - the person to add
	 * Returns void - nothing
	 */
	public void enterLine(Person p) {
		list.addBack(p);
	}
	
	/*
	 * Purpose: handle the first person in line
	            (allow them to purchase their ticket
				 and enter into the cinema)
	 * Parameter: none
	 * Returns: Person - the person who gets their ticket
	 *                   and is no longer waiting in line
	 *                   or null if no one can be removed
	 *                   from the line
	 */
	public Person handleOne(){
		
		try{
			Person whoLeft = list.removeFront();
			return whoLeft; 
		} catch (ListEmptyException e){
			return null;
		}
		
	}
	
	/*
	 * Purpose: handle a whole group of people waiting in line
	 *          (allow a group to buy a number of tickets 
	 *           and all enter the cinema)
	 * Parameters: int num - the number of people
	 * Returns int - the number of people who were successfully
	 *               able to be removed from the line
	 */
	public int handleMultiple(int num) {
		int numRemoved = 0;
		try{
			for (int i = 0; i < num; i++){
				list.removeFront();
				numRemoved++;
			}
			return numRemoved;
		} catch (ListEmptyException e){
			return numRemoved;
		}
		
	}
	
	/*
	 * Purpose: accept a bribe to put someone into a specific
	 *          position in the line to get into the cinema
	 * Parameters: Person p - the person entering the line
	 *             int pos - the position they are trying to get to
	 * Returns boolean - true if person added to line, false otherwise
	 */
	public boolean premiumEntry(Person p, int pos) {
		try {
			list.insertAt(pos, p);
		} catch (InvalidPositionException e) {
			return false;
		}
		return true; 
	}
	
	/*
	 * Purpose: gets the number of people in line
	 * Parameters: none
	 * Returns: int - number of people in line
	 */
	public int peopleInLine() {
		
		return list.size(); // so it compiles
	}

	public String toString() {
		return list.toString();
	}
}
	
	