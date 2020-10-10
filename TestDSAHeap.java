import java.util.*;

/**
** Author: Cassandra Jacklya
** Purpose: to test the DSAHeap class successfully
** Last modified on: 26th September 2020
**/

public class TestDSAHeap {
	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		FileIO file = new FileIO();
		
		int choice;
		String filename;
		int number;
		try {
			DSAHeap heap = new DSAHeap(0);
			DSAHeap heap2 = new DSAHeap(5);
			System.out.println("What do you want to do?");
			//keeps printing out the menu until the user wants to exit
			do {
				System.out.println("\nChoose a number: ");
				System.out.println("1: Add data (RandomNames7000.csv)");
				System.out.println("2: Remove data (from RandomNames7000.csv)");
				System.out.println("3: Do a heap sort to heap and heap two array");
				System.out.println("4: Display heap array");
				System.out.println("5: Exit");
				choice = sc.nextInt();
			
				switch(choice) {
					case 1:
						heap = file.readFile("RandomNames7000.csv");
						
						/*********************
						** 			10
						**		   /  \
						**		  6    5
						**		 / \
						**	    4   1
						**     Before removal
						*********************/
						
						heap2.add(5,"hello");
						heap2.add(4,"hi");
						heap2.add(6,"girl");
						heap2.add(10,"boy");
						heap2.add(1,"bye");
						System.out.println("Also tested out a small array. Press 3 to display heap two");
					break;
					case 2:
						/*********************
						** 			5
						**		   / \
						**		  4   1
						**   After removal
						*********************/
						
						heap2.remove();
						heap2.remove();
						heap.remove();
						heap.remove();
						heap.remove();
						heap.remove();
						System.out.println("Succes! Removed some values from the heap and heap two array");
					break;
					case 3:
						//checking for heap sort method
						heap.heapSort();
						heap2.heapSort();
						System.out.println("Heap sort operation done on both!");
					break;
					case 4:
						//prints out the smaller data to the terminal
						file.writeFile("Hello.csv",heap.toString());	//but writes the larger data set into a file
						System.out.println("\nSmall data set output (After removal):");
						System.out.println(heap2.toString());
					break;
					case 5:
						System.out.println("Goodbye!");
					break;
					default:
						throw new IllegalArgumentException("Invalid option");
				}
			} while (choice != 5);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
}