package aps2.binomialheap;

import java.util.Vector;

/**
 * This class is an implementation of the Binomial min-heap.
 */
public class BinomialHeap {
	Vector<BinomialNode> data; // list of root nodes
	int n;                     // number of elements
	BinomialNode MIN = new BinomialNode(Integer.MAX_VALUE);
	
	BinomialHeap(){
		data = new Vector<BinomialNode>();
	}
	
	/**
	 * Inserts a new key to the binomial heap and consolidates the heap.
	 * Duplicates are allowed.
	 * 
	 * @param key Key to be inserted
	 * @return True, if the insertion was successful; False otherwise.
	 */
	public boolean insert(int key) {
		BinomialNode node = new BinomialNode(key);
		this.data.add(node);
		n++;
		if(this.consolidate())
			return true;
		else
			return false;
	}
	
	/**
	 * Returns the minimum element in the binomial heap. If the heap is empty,
	 * return the maximum integer value.
	 * 
	 * @return The minimum element in the heap or the maximum integer value, if the heap is empty.
	 */
	public int getMin() {
		return this.MIN.getKey();
	}
	
	private void setMin() {
		for(BinomialNode n : this.data) {
			if(n.compare(this.MIN) > 0) {
				this.MIN = n;
			}
				
		}
	}
	
	/**
	 * Find and removes the minimum element in the binomial heap and
	 * consolidates the heap.
	 * 
	 * @return True, if the element was deleted; False otherwise.
	 */
	public boolean delMin() {
		if(this.data.isEmpty())
			return false;
		
		this.data.remove(this.MIN);
		Vector<BinomialNode> children = this.MIN.getChildren();
		n--;
		for(BinomialNode node : children) {
			this.data.add(node);
			this.MIN = new BinomialNode(Integer.MAX_VALUE);
			this.consolidate();
		}
		
		return true;
	}
	
	/**
	 * Merges two binomial trees.
	 * 
	 * @param t1 The first tree
	 * @param t2 The second tree
	 * @return Returns the new parent tree
	 */
	public static BinomialNode mergeTrees(BinomialNode t1, BinomialNode t2) {
		BinomialNode root, child;
		if(t1.compare(t2) >= 0) {
			root = t1;
			child = t2;
		}
		else {
			root = t2;
			child = t1;			
		}
		root.addChild(child);
		return root;
	}
	
	/**
	 * This function consolidates the binomial heap ie. merges the binomial
	 * trees with the same degree into a single one.
	 * 
	 * @return True, if changes were made to the list of root nodes; False otherwise.
	 */
	private boolean consolidate() {
		for(int i=0; i<this.data.size(); i++) {
			for(int z=0; z<this.data.size(); z++) {
				if(i != z && this.data.elementAt(i).getDegree() == this.data.elementAt(z).getDegree()) {
					BinomialNode tree = mergeTrees(this.data.elementAt(i), this.data.elementAt(z));
					this.data.remove(z);
					this.data.remove(i);
					this.data.addElement(tree);
					i = 0;
					z = 0;
				}
			}
		}
		setMin();
		return true;
	}
}

