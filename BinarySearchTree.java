import java.util.*;

/*
 * An implementation of a binary search tree. This tree stores 
 * both keys and values associated with those keys.
 *
 * More information about binary search trees can be found here:
 * http://en.wikipedia.org/wiki/Binary_search_tree
 */
class BinarySearchTree <K extends Comparable<K>, V>  {

	public static final int BST_PREORDER  = 1;
	public static final int BST_POSTORDER = 2;
	public static final int BST_INORDER   = 3;

	// These are package friendly for the TreeView class
	BSTNode<K,V> root;
	int	count;

	public BinarySearchTree () {
		root = null;
		count = 0;
	}

	
	/* Purpose: Insert a new key-value element into the tree.  
	 *          If the key already exists in the tree, update 
	 *          the value stored at that node with the new value.
	 * Parameters: K key - the key for which the BST is ordered
     *	 		   V value - the value to associate with the key
	 * Returns: nothing
	 * Pre-Conditions: the tree is a valid binary search tree
	 */
	public void insert (K key, V value) {
		BSTNode<K,V> toInsert = new BSTNode<K,V>(key, value);
		if (root == null){
			root = toInsert;
		}else{
			BSTNode<K,V> cur = root;
			boolean foundSpot = false;
		
			while(cur != null && !foundSpot){
				if (cur.key.compareTo(key) == 0){
					cur.value = toInsert.value;
					return;
				}else if (cur.key.compareTo(key) > 0){
					if (cur.left == null){
						cur.left = toInsert;
						foundSpot = true;
					}else{
						cur = cur.left;
					}	
				}else if (cur.key.compareTo(key) < 0){
					if (cur.right == null){
						cur.right = toInsert;
						foundSpot = true;
					}else{
						cur = cur.right;
					}
					
				}
			
			}
			
		}
		count++;
	}

	/* 	
	 * Purpose: Get the value of the given key. 
	 * Parameters: K key - the key to search for
	 * Returns: V - the value associated with the key
	 * Pre-conditions: the tree is a valid binary search tree
	 * Throws: KeyNotFoundException if key isn't in the tree
	 */
	public V find (K key) throws KeyNotFoundException {
		if (root == null){
			throw new KeyNotFoundException();
		}

		BSTNode<K,V> cur = root;
		boolean found = false;
		V toReturn = null;

		while (cur != null && !found){
			if (cur.key.compareTo(key) == 0){
				toReturn = cur.value;
				found = true;
			} 

			if (cur.key.compareTo(key) < 0) {
				if (cur.right == null){
					throw new KeyNotFoundException();
				}else{
					cur = cur.right;
				}
			} else if (cur.key.compareTo(key) > 0){
				if (cur.left == null){
					throw new KeyNotFoundException();
				} else{
					cur = cur.left;
				}
			}
		}

		return toReturn;
		
	}


	/* 	
	 * Purpose: Get the number of nodes in the tree.
	 * Parameters: none
	 * Returns: int - the number of nodes in the tree. 
	 */
	public int size() {
		
		return count; // so it compiles
	}

	/*
	 * Purpose: Remove all nodes from the tree.
	 * Parameters: none
	 * Returns: nothing
	 */
	public void clear() {
		root = null;
		count = 0;
	}

	/* 
	 * Purpose: Get the height of the tree. 
	 * Parameters: none
	 * Returns: int - the height of the tree
	 * Example: We define height as being the number of 
	 * arrows that need to be followed on the path from 
	 * the root to the deepest leaf node. This means that 
	 * a tree with one node (just the root) has height 0,
	 * and an empty tree (root is null) has height -1.
	 *
	 * See the assignment PDF and the test program for examples.
	 */
	public int height() {
		if (root == null){
			return -1;
		}else{
			return heightRec(root);
		}

		
	}

	private int heightRec(BSTNode<K,V> cur){
		int leftHeight = -1;
		int rightHeight = -1;
		
		if (cur.left != null){
			leftHeight = heightRec(cur.left);
		}
		if (cur.right != null){
			rightHeight = heightRec(cur.right);
		}
		if (rightHeight > leftHeight){
			return rightHeight+1;
		}else{
			return leftHeight+1;
		}
	}

	/* 
	 * Purpose: Return a list of all the key-value Entry elements 
	 *          stored in the tree using a level-order traversal.
	 * Parameters: None
	 * Returns: List<Entry<K,V>> - a list of key-value entries
	 *
	 * Example: A level order traversal of a tree cannot be done 
	 *          without the help of a secondary data structure.
	 *
	 *          It is commonly implemented using a queue of nodes 
	 *          as the secondary data structure. You will still be 
	 *          adding the "visited" elements to l as you do in the 
	 *          other traversal methods but you will create an 
	 *          additional q to hold nodes still to visit. This is
	 *          similar to what we did in the worksheet on tree traversals.
	 *
	 * From wikipedia (they call it breadth-first), the algorithm 
	 * for level order is:
	 *
	 *  levelorder()
	 *      q = empty queue
	 *      q.enqueue(root)
	 *      while not q.empty do
	 *          node := q.dequeue()
	 *          visit(node)
	 *          if node.left != null then
	 *                q.enqueue(node.left)
	 *          if node.right != null then
	 *                q.enqueue(node.right)
	 *
	 * Note that you can use the Java LinkedList as a Queue
	 * and then use only the removeFirst() and addLast() methods on q
	 */
	public List<Entry<K,V>>	entryList() {
		// list to add all the nodes to
		List<Entry<K,V> > l = new LinkedList<Entry<K,V>>();

		// queue of nodes that need to be added
		LinkedList<BSTNode<K,V>> q = new LinkedList<BSTNode<K,V> >();
		if (root == null){
			return l;
		}

		q.addFirst(root);

		while (!q.isEmpty()){
			BSTNode<K,V> cur = q.removeFirst();
			K curKey = cur.key;
			V curValue = cur.value;

			Entry<K,V> toAdd = new Entry<K,V>(curKey, curValue);
			l.add(toAdd);
			//System.out.println(toAdd);

			if (cur.left != null){
				q.addLast(cur.left);
			}
			if (cur.right != null){
				q.addLast(cur.right);
			}
		}
		
		return l;
	}

	/* 	
	 * Purpose: Get a list of all the key-value entries stored in the tree
	 * Parameters: int whichTraversal - the type of traversal to perform:
	 * Returns: List<Entry<K,V>> - a list of key-value entries
	 * Example: The list will be constructed by performing a traversal
	 * specified by the parameter whichTraversal.
	 * 
	 * If whichTraversal is:
	 *	 BST_PREORDER	perform a pre-order traversal
	 *	 BST_POSTORDER	perform a post-order traversal
	 *	 BST_INORDER	perform an in-order traversal
	 */
	public List<Entry<K,V> > entryList (int which) {
		List<Entry<K,V> > entries = new LinkedList<Entry<K,V> >();

		if (which == BST_PREORDER) {
			preOrderRec(root, entries);
		}
		else if (which == BST_INORDER) {
			inOrderRec(root, entries);
		}
		else if (which == BST_POSTORDER) {
			postOrderRec(root, entries);
		}
		return entries;
	}

	private void inOrderRec (BSTNode<K,V> n, List <Entry<K,V>> entries) {

		if (n == null){
			return;
		}

		inOrderRec(n.left, entries);

		K theKey = n.key;
		V theVal = n.value;
		Entry<K,V> toAdd = new Entry<K,V>(theKey, theVal);
		entries.add(toAdd);

		inOrderRec(n.right, entries);
	}

	private void preOrderRec (BSTNode<K,V> n, List <Entry<K,V>> entries) {
		if (n == null){
			return;
		}

		K theKey = n.key;
		V theVal = n.value;
		Entry<K,V> toAdd = new Entry<K,V>(theKey, theVal);
		entries.add(toAdd);

		preOrderRec(n.left, entries);
		preOrderRec(n.right, entries);
		
	}

	private void postOrderRec (BSTNode<K,V> n, List <Entry<K,V>> entries) {

		if (n == null){
			return;
		}
		
		postOrderRec(n.left, entries);
		postOrderRec(n.right, entries);

		K theKey = n.key;
		V theVal = n.value;
		Entry<K,V> toAdd = new Entry<K,V>(theKey, theVal);
		entries.add(toAdd);
		
	}
}