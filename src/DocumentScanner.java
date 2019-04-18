/* Name(s): Alyssa Gibson, Christy Nguyen
   Class: CSC210: Data Structures & Algorithms
   Assignment: Use a binary search tree to count and organize the amount of
			   instances of words in "Alice in Wonderland".
*/

/* -----REQUIRED METHODS:-----
   [X]  CONSTRUCTOR(S)  - Alyssa
			Initializes the class (3 pts)
   [X]  SCANDOCUMENT	- Alyssa+Christy
    		Scans and parses a given document (text file) and builds a binary
			  search tree based on each individual word (5 pts)
   [X]  SEARCH			- Alyssa
			Searches for a word (using the BST classes search) and prints the word
			  with how many times it appears in the text. (5 pts)
   [X]  PRINTMAXWORD	- Alyssa+Christy
			Searches for the word with the highest count and prints it along with its
			  count (5 pts)
   [X]  PRINTPREORDER  - Christy
			Uses the BST Print_Preorder method to print the tree (3 pts)
   [X]  PRINTINORDER   - Christy
			Uses the BST Print_Inorder method to print the tree (3 pts)
   [X]  PRINTPOSTORDER - Christy
			Uses the BST Print_Postorder method to print the tree (3 pts)
*/

import java.util.*;
import java.io.*;

public class DocumentScanner {
	
	private ArrayList<String> list;
	private BinarySearchTree tree;
	
	public DocumentScanner(File file) {
		tree = new BinarySearchTree();
		list = new ArrayList<String>();
		ScanDocument(file);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).length() != 0) {
				tree.insert(new BinarySearchTreeNode(list.get(i)));
			}
		}
	}
	
	public void ScanDocument(File file) {
		try {
			Scanner sc = new Scanner(file).useDelimiter("\\W");

			while (sc.hasNext()) {
				String temp = sc.next();
				if (temp.length() != 0) {
					list.add(temp);
				}
			}
			sc.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}

	}
	
	public void Search(String word) {
		BinarySearchTreeNode found = tree.Search(word);
		try {
			System.out.println("Word: " + found.getValue() 
			+ " Count: " + found.getCount());
		}
		catch (NullPointerException e) {
			System.out.println("No match found for that word.");
		}
		
	}
	
	public void PrintMaxWord() {
		BinarySearchTreeNode cursor = tree.getRoot();
		BinarySearchTreeNode max = tree.getRoot();
		max = PrintMaxWord(cursor, max);
		System.out.println("Max Word: " + max.getValue()
			+ " Count: " + max.getCount());
	}
	
	public BinarySearchTreeNode PrintMaxWord(BinarySearchTreeNode cursor, BinarySearchTreeNode max) {	
		if (tree.getRoot() == null) {
			return null;
		}
		
		if (cursor.getLeftChild() != null) {
			if (max.getCount() < cursor.getLeftChild().getCount()) {
				max = cursor.getLeftChild();
			}
			max = PrintMaxWord(cursor.getLeftChild(), max);
		}
		
		if (cursor.getRightChild() != null) {
			if (max.getCount() < cursor.getRightChild().getCount()) {
				max = cursor.getRightChild();
			}
			max = PrintMaxWord(cursor.getRightChild(), max);
		}
		return max;
		
	}
	
	public void Delete(String word) {
		tree.delete(word);
	}
	
	public void PrintPreorder() {
		tree.PrintPreorder(tree.getRoot());
	}
	
	public void PrintPostorder() {
		tree.PrintPostorder(tree.getRoot());
	}
	
	public void PrintInorder() {
		tree.PrintInorder(tree.getRoot());
	}

}
