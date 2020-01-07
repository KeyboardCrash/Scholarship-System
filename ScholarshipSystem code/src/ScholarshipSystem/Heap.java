package ScholarshipSystem;

import java.util.ArrayList;

/**
 * An implementation of a Heap data structure
 * 
 * @author Colin Au Yeung
 * @version final (4/12/19)
 */

public class Heap {

	private ArrayList<WeighedTitle> arr = new ArrayList<>();
	private int lastleaf = -1;

	/**
	 * Swap the places of two node objects
	 * 
	 * @param node1
	 * @param node2
	 */
	private void swap(int node1, int node2) {
		if (node1 <= lastleaf && node2 <= lastleaf) {
			WeighedTitle temp = arr.get(node1);
			arr.set(node1, arr.get(node2));
			arr.set(node2, temp);
		}
	}

	/**
	 * Bubble a node down using swap based on the weight of each node
	 * 
	 * @param index
	 */
	private void percolatedown(int index) {
		int child1 = 2 * index + 1;
		int child2 = 2 * index + 2;
		if (child1 <= lastleaf) {
			if (child2 <= lastleaf) {
				arr.get(child2);
				if (arr.get(child2).getWeight() <= arr.get(child1).getWeight()) {
					if (arr.get(child2).getWeight() < arr.get(index).getWeight()) {
						swap(child2, index);
						if (child2 * 2 + 1 <= lastleaf) {
							percolatedown(child2);
						}
					}
				} else {
					if (arr.get(child1).getWeight() < arr.get(index).getWeight()) {
						swap(child1, index);
						if (child1 * 2 + 1 <= lastleaf) {
							percolatedown(child1);
						}
					}
				}
			} else {
				if (arr.get(child1).getWeight() < arr.get(index).getWeight()) {
					swap(child1, index);
					if (child1 * 2 + 1 <= lastleaf) {
						percolatedown(child1);
					}
				}
			}
		}
	}

	/**
	 * Run a bubble down from the root of the tree to re-affirm properties of data
	 * structure
	 */
	private void heapify() {
		for (int i = (lastleaf - 1) / 2; i >= 0; i--) {
			percolatedown(i);
		}
	}

	/**
	 * Form a priority queue based on the weight of the nodes
	 * 
	 * @param n
	 */
	public void queue(WeighedTitle n) {
		lastleaf++;
		arr.add(lastleaf, n);
		int i = lastleaf;
		while (i != 0 && arr.get((i - 1) / 2).getWeight() > arr.get(i).getWeight()) {
			swap((i - 1) / 2, i);
			i = (i - 1) / 2;
		}
	}

	/**
	 * Pop a node off the queue and address the tree by bubbling the value down and
	 * out
	 * 
	 * @return WeightedTitle
	 */
	public WeighedTitle dequeue() {
		if (!isEmpty()) {
			swap(0, lastleaf);
			WeighedTitle temp = arr.get(lastleaf);
			arr.remove(lastleaf);
			lastleaf--;
			percolatedown(0);
			return temp;
		}
		return null;
	}

	/**
	 * check if the tree has any nodes
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return lastleaf == -1;
	}

	/**
	 * Get a node at a given index
	 * 
	 * @param index
	 * @return WeightedTitle
	 */
	public WeighedTitle getTitle(int index) {
		return arr.get(index);
	}

	/**
	 * Get the length of an array
	 * 
	 * @return int
	 */
	public int getLength() {
		return arr.size();
	}
}
