import java.util.*;

/**
** Author: Cassandra Jacklya
** Purpose: to implement a heap class similar to the built in java (priority queue)
** Last modified on: 26th September 2020
**/

public class DSAHeap{
	
	//private fields
	private DSAHeapEntry[] heap;
	private int count;
	
	//---------------private inner class DSAHeapEntry------------------------
	private class DSAHeapEntry {
		private Object value;
		private int priority;
		
		//default constructor
		public DSAHeapEntry() {}
		
		//alternate constructor
		public DSAHeapEntry(int inPriority, Object inValue) {
			value = inValue;
			priority = inPriority;
		}
		
		//gets the value of the element
		public Object getValue() {
			return this.value;
		}
		
		//gets the priority value of the element
		public int getPriority() {
			return this.priority;
		}
	}
		
	//----------DSAHeap methods start here--------------------------
	
	//default constructor
	public DSAHeap(int maxSize) {
		heap = new DSAHeapEntry[maxSize];
		for (int i = 0; i < heap.length; i++) {
			heap[i] = new DSAHeapEntry();	//initialize to prevent null pointer exception
		}
		count = 0;
	}
	
	//adds a new element (with its priority) into the heap array
	public void add(int priority, Object value) {
		heap[count] = new DSAHeapEntry(priority, value);	//added to the next unused portion in the array
		trickleUp(count);	//newly added value will trickleUp the heap tree if priority is higher
		count++;	//increases the count
	}
	
	//removes the highest priority element from the heap
	public Object remove() {
		Object temp = heap[0];	
		heap[0] = heap[count-1];	//remove the first element and replace it with the most recently added element
		count = count - 1;	//decreases the count
		heap[count] = new DSAHeapEntry();	//initialize the removed element to clear its value
		trickleDown(0,count);	//trickleDown if the priority is lower
		return temp;
	}
	
	//prints out the value and its priority to the main
	public String toString() {
		String word = "";
		for (int i = 0; i < heap.length; i++) {
			if (i == heap.length - 1) {	//if it is the last value in the heap, ignore adding a new line
				word = word + heap[i].getPriority() + "," + heap[i].getValue();
			}
			else {	//else a new line is added
				word = word + heap[i].getPriority() + "," + heap[i].getValue() + "\n";
			}
		}
		return word;
	}
	
	//wrapper method as user does not keep track of the number of values in the heap
	public void heapSort() {
		heapSortwrap(count);
	}
	
	private  void heapSortwrap(int numItems) {
		heapify(numItems);	//to ensure the rules of a max heap still stands
		for (int i = (numItems -1); i > 0; i--) {
			swap(0,i);	//swaps the last element in the unsorted section with the root
			trickleDown(0,i);	//trickleDown if priority is lower
		}
	}
	
	private void heapify(int numItems) {
		for (int i = (numItems/2) - 1; i > 0; i--) {
			trickleDown(i,numItems);	
		}
	}
	
	private void trickleUp(int curIdx) {
		int parentIdx = (curIdx-1)/2;
		if (curIdx > 0) {
			if (heap[curIdx].getPriority() > heap[parentIdx].getPriority()) {
				swap(parentIdx,curIdx);
				trickleUp(parentIdx);
			}
		}
	}
	
	private void trickleDown(int curIdx, int numItems) {
		int leftChildIdx = (curIdx * 2) + 1;	//left child is always the current node * 2 added by 1
		int rightChildIdx = (leftChildIdx) + 1;	//right child is always the leftChildIdx node + 1
		int largeIdx;	//check which node has higher priority
		if (leftChildIdx < numItems) {
			largeIdx = leftChildIdx;
			if (rightChildIdx < numItems) {
				if (heap[leftChildIdx].getPriority() < heap[rightChildIdx].getPriority()) {
					largeIdx = rightChildIdx;
				}
			}
			if (heap[largeIdx].getPriority() > heap[curIdx].getPriority()) {
				swap(largeIdx,curIdx);
				trickleDown(largeIdx,numItems);
			}
		}
	}
	
	//swapping operation for modularity
	private void swap(int pos1, int pos2) {
		DSAHeapEntry temp = heap[pos1];
		heap[pos1] = heap[pos2];
		heap[pos2] = temp;
	}
}