/* Name(s): Alyssa Gibson, Christy Nguyen
   Class: CSC210: Data Structures & Algorithms
   Assignment: Use a binary search tree to count and organize the amount of
			   instances of words in "Alice in Wonderland".
*/

/* -----REQUIRED METHODS:-----
   [X]  CONSTRUCTOR(S)  - Alyssa
			Initializes the class (3 pts)
   [X]  INSERT			- Christy (Debugged by Alyssa)
    		Inserts a node into the binary search tree (7 pts)
   [X]  SEARCH			- Alyssa
			Searches for and returns a node in the binary search tree with the
    		  given key if found. Returns null if not found. (6 pts)
   [X]  DELETE			- Christy
			Deletes a node from the binary search tree given a search key (5 pts)
   [X]  PRINT_PREORDER  - Christy
			Prints the tree nodes in preorder format (5 pts)
   [X]  PRINT_INORDER   - Christy
			Prints the tree nodes in inorder format (5 pts)
   [X]  PRINT_POSTORDER - Christy
			Prints the tree nodes in postorder format (5 pts)
*/

public class BinarySearchTree {

	private BinarySearchTreeNode root;

	public BinarySearchTree() {
		root = null;
	}

	public void insert(BinarySearchTreeNode node) {
		node.setHash(HashGenerator(node.getValue()));
		// CASE 1: If the root is null
		// set root as node
		if (root == null) {
			root = node;
		} else {
			insert(node, root);
		}

	}

	public void insert(BinarySearchTreeNode node, BinarySearchTreeNode cursor) {
		if (node.getHash() < cursor.getHash()) {
			if (cursor.getLeftChild() == null) {
				cursor.setLeftChild(node);
			} else {
				insert(node, cursor.getLeftChild());
			}
		}

		else if (node.getHash() > cursor.getHash()) {
			if (cursor.getRightChild() == null) {
				cursor.setRightChild(node);
			} else {
				insert(node, cursor.getRightChild());
			}
		}

		else {
			cursor.increment();
		}

	}

	public void delete(String word) {
		BinarySearchTreeNode temp = Search(word);
		
		if (temp == null) {
			System.out.println("No word found.");
			return;
		}
		
		else {
			delete(temp, root);
			System.out.println(temp.getValue() + " deleted.");
		}
		
	}
	
	public void delete(BinarySearchTreeNode to_delete, BinarySearchTreeNode cursor) {
		// BASE CASE: CURSOR IS TO_DELETE'S PARENT
		if (to_delete == root) {
			root = null;
		}
		
		if (cursor.getLeftChild() == to_delete) {
			cursor.setLeftChild(null);
		}
		
		else if (cursor.getRightChild() == to_delete) {
			cursor.setRightChild(null);
		}
		
		// RECURSIVE CASE: FIND TO_DELETE'S PARENT
		else {
			if (cursor.getLeftChild() != to_delete && cursor.getLeftChild() != null) {
				delete(to_delete, cursor.getLeftChild());
			}
			
			if (cursor.getRightChild() != to_delete && cursor.getRightChild() != null) {
				delete(to_delete, cursor.getRightChild());
			}
		}
		

	}
	
	

	public BinarySearchTreeNode Search(String word) {
		BinarySearchTreeNode found = new BinarySearchTreeNode(word);
		found.setHash(HashGenerator(word));
		// CASE 1: If the search item == root
		if (found.getHash() == root.getHash()) {
			return root;
		}

		else {
			return Search(found, root);
		}
	}

	public BinarySearchTreeNode Search(BinarySearchTreeNode found, BinarySearchTreeNode cursor) {
		// CASE 2: If the search item's hash is LESS than cursor's
		if (found.getHash() < cursor.getHash()) {
			return Search(found, cursor.getLeftChild());
		}

		// CASE 3: If the search item's hash is GREATER than the cursor's
		else if (found.getHash() > cursor.getHash()) {
			return Search(found, cursor.getRightChild());
		}
		// CASE 4: If the search item's hash == cursor's hash
		else if (found.getHash() == cursor.getHash()) {
			return cursor;
		}

		else {
			return null;
		}

	}

	public void PrintPreorder(BinarySearchTreeNode cursor) {
		if (root == null) {
			System.out.println("Tree is empty.");
			return;
		}
		
		System.out.println(cursor.getValue() + " Count: " + cursor.getCount());
		if (cursor.getLeftChild() != null) {
			PrintPreorder(cursor.getLeftChild());
		}
		if (cursor.getRightChild() != null) {
			PrintPreorder(cursor.getRightChild());
		}

		return;

	}

	public void PrintPostorder(BinarySearchTreeNode cursor) {
		if (root == null) {
			System.out.println("Tree is empty.");
			return;
		}
		
		if (cursor.getLeftChild() != null) {
			PrintPostorder(cursor.getLeftChild());
		}
		if (cursor.getRightChild() != null) {
			PrintPostorder(cursor.getRightChild());
		}
		System.out.println(cursor.getValue() + " Count: " + cursor.getCount());

	}

	public void PrintInorder(BinarySearchTreeNode cursor) {
		if (root == null) {
			System.out.println("Tree is empty.");
			return;
		}
		
		if (cursor.getLeftChild() != null) {
			PrintInorder(cursor.getLeftChild());
		}
		System.out.println(cursor.getValue() + " Count: " + cursor.getCount());
		if (cursor.getRightChild() != null) {
			PrintInorder(cursor.getRightChild());
		}
	}

	public BinarySearchTreeNode getRoot() {
		return root;
	}

	public int HashGenerator(String key) {
		double hash = 0.0;
		int base;
		int power;
		for (int i = 0; i < key.length(); i++) {
			base = (int) key.charAt(i) - 96;
			power = (i == 0) ? 3 : 2;
			// if i == 0, then power = 3. else, power = 2.
			// called ternary statements
			hash += Math.pow(base, power) * Math.PI / (i + 1);
		}

		return (int) (hash * Math.E * 5 / key.length());
	}

}
