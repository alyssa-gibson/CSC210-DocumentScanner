/* Name(s): Alyssa Gibson, Christy Nguyen
   Class: CSC210: Data Structures & Algorithms
   Assignment: Use a binary search tree to count and organize the amount of
			   instances of words in "Alice in Wonderland".
*/

import java.util.*;
import java.io.*;

public class DocumentScannerDemo {
	
	static Scanner kb = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("---DOCUMENT SCANNER DEMO---");
		File file = new File("src\\alice.txt");
		System.out.println("Current file: " + file.getName());
		int userin = -1;
		DocumentScanner sc = new DocumentScanner(file);
		do { System.out.println( "\n---MENU---\n"
				+ "\t 1) Print Unique Words"
				+ "\n\t 2) Search for a Word"
				+ "\n\t 3) Find Most Common Word"
				+ "\n\t 4) Delete a Word"
				+ "\n\t 5) Exit");
		
			try {
				userin = kb.nextInt();
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input. Please try again:");
				kb = new Scanner(System.in);
				userin = kb.nextInt();
			}
			
			switch (userin) {
			
				case 1:
					// Print
					Print(sc);
					break;
					
				case 2:
					// Search
					Search(sc);
					break;
					
				case 3:
					// MaxWord
					long startTime = System.nanoTime();
					FindMaxWord(sc);
					long endTime = System.nanoTime();
					long duration = (endTime - startTime);
					System.out.println("Duration: " + duration + "ns");
					break;
				
				case 4:
					// Delete
					Delete(sc);
					break;
					
				case 5:
					// Exit
					break;
					
				default:
					System.out.println("Invalid input. Please try again:");
					userin = kb.nextInt();
				
			}
			
		}
		while (userin != 5);
		
		System.exit(0);
		
	}
	
	public static void Print(DocumentScanner sc) {
		int userin = -1;
		System.out.println("Which traversal?"
				+ "\n\t1) Preorder Traversal"
				+ "\n\t2) Inorder Traversal"
				+ "\n\t3) Postorder Traversal"); 
		
		try {
			userin = kb.nextInt();
		}
		catch (InputMismatchException e) {
			System.out.println("Invalid input. Please try again:");
			kb = new Scanner(System.in);
			userin = kb.nextInt();
		}
		
		switch (userin) {
			
			case 1:
				sc.PrintPreorder();
				break;
				
			case 2:
				sc.PrintInorder();
				break;
				
			case 3:
				sc.PrintPostorder();
				break;
				
		}
		return;

	}
	
	public static void Search(DocumentScanner sc) {
		String userin = "";
		System.out.println("Input word to search for:");
		try {
			userin = kb.next();
		}
		catch (InputMismatchException e) {
			System.out.println("Invalid input. Please try again:");
			kb = new Scanner(System.in);
			userin = kb.next();
		}
		
		try {
			sc.Search(userin);
		}
		catch (NullPointerException e) {
			System.out.println("Word not found.");
		}
		
	}
	
	public static void FindMaxWord(DocumentScanner sc) {
		try {
			sc.PrintMaxWord();
		}
		catch (NullPointerException e) {
			System.out.println("Tree is empty.");
		}
	}
	
	public static void Delete(DocumentScanner sc) {
		String userin = "";
		System.out.println("Input word to search for:");
		try {
			userin = kb.next();
		}
		catch (InputMismatchException e) {
			System.out.println("Invalid input. Please try again:");
			kb = new Scanner(System.in);
			userin = kb.next();
		}
		
		sc.Delete(userin);
		
	}

}
