package aps2.bstmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the (unbalanced) Binary Search Tree set node.
 */
public class BSTMapNode {
	private static int counter;
	private BSTMapNode left, right, parent;
	private int key;
	private String value;

	public BSTMapNode(BSTMapNode l, BSTMapNode r, BSTMapNode p,
			int key, String value) {
		super();
		this.left = l;
		this.right = r;
		this.parent = p;
		this.key = key;
		this.value = value;
	}
	
	public BSTMapNode getLeft() {
		return left;
	}

	public void setLeft(BSTMapNode left) {
		this.left = left;
	}

	public BSTMapNode getRight() {
		return right;
	}

	public void setRight(BSTMapNode right) {
		this.right = right;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public int compare(BSTMapNode node) {
		counter++;
		return node.key-this.key;
	}

	public int getCounter() {
		return counter;
	}

	public void resetCounter() {
		counter = 0;
	}

	/**
	 * If the element doesn't exist yet, adds the given element to the subtree.
	 * 
	 * @param element Given key/value wrapped inside an empty BSTNode instance
	 * @return true, if the element was added; false otherwise.
	 */
	public boolean add(BSTMapNode element) {
		BSTMapNode r = this.findLast(element);
		if(r != null) {
			if(r.compare(element) < 0)
				r.left = element;
			else 
				r.right = element;
			element.parent = r;
			return true;
		}
		else
			return false;
	}
	
	private BSTMapNode findLast(BSTMapNode x) {
		int c = this.compare(x);
		if(c == 0)
			return null;
		else if(c < 0 && this.left != null)
			return this.left.findLast(x);
		else if(c > 0 && this.right != null)
			return this.right.findLast(x);
		else
			return this;
	}
	
	/**
	 * Finds and removes the element with the given key from the subtree.
	 * 
	 * @param element Given key wrapped inside an empty BSTNode instance
	 * @return true, if the element was found and removed; false otherwise.
	 */
	public boolean remove(BSTMapNode element) {
		int c = this.compare(element);
		if(c == 0) {
			delete(this);
			return true;
		}
		else if(c < 0 && this.left != null)
			return this.left.remove(element);
		else if(c > 0 && this.right != null)
			return this.right.remove(element);
		else
			return false;
	}
	
	private void delete(BSTMapNode x) {
		BSTMapNode p, l, r;
		p = x.parent;
		l = x.left;
		r = x.right;
		
		if(r != null) {
			BSTMapNode n = r;
			while(n.left != null) {
				n = n.left;
			}
			//n.parent.left = null;
			n.left = x.left;
			n.right = x.right;
		}
		else if(l != null) {
			
		}
		
		if(p.compare(x) < 0) {
			p.left = r;
			r.parent = p;
			l.parent = r.findMin();
			l.parent.left = l;
		}
		else {
			p.right = l;
			l.parent = p;
			r.parent = l.findMax();
			r.parent.right = r;
		}
	}

	/**
	 * Checks whether the element with the given key exists in the subtree.
	 * 
	 * @param element Query key wrapped inside an empty BSTNode instance
	 * @return true, if an element with the given key is contained in the subtree; false otherwise.
	 */
	public boolean contains(BSTMapNode element) {
		BSTMapNode r = this;
		while(r != null) {
			int c = r.compare(element);
			if(c == 0)
				return true;
			else if(c < 0)
				r = r.left;
			else
				r = r.right;
		}
		return false;
	}
	
	/**
	 * Maps the given key to its value.
	 * 
	 * @param element Query key wrapped inside an empty BSTNode instance
	 * @return String value of the given key; null, if an element with the given key does not exist in the subtree.
	 */
	public String get(BSTMapNode element) {
		BSTMapNode r = this;
		while(r != null) {
			int c = r.compare(element);
			if(c == 0)
				return r.value;
			else if(c < 0)
				r = r.left;
			else
				r = r.right;
		}
		return null;
	}

	/**
	 * Finds the smallest element in the subtree.
	 * 
	 * @return Smallest element in the subtree
	 */
	public BSTMapNode findMin() {
		if(this.left == null)
			return this;
		else
			return this.left.findMin();
	}
	
	private BSTMapNode findMax() {
		if(this.right == null)
			return this;
		else
			return this.right.findMax();
	}
	
	/**
	 * Depth-first pre-order traversal of the BST.
	 * 
	 * @return List of keys stored in BST obtained by pre-order traversing the tree.
	 */
	List<Integer> traversePreOrder() {
		List<Integer> res = new ArrayList<Integer>();
		if(this != null) {
			res.add(this.key);
			if(this.left != null )
				res.addAll(this.left.traversePreOrder());
			if(this.right != null )
				res.addAll(this.right.traversePreOrder());
		}
		return res;
	}

	/**
	 * Depth-first in-order traversal of the BST.
	 * 
	 * @return List of keys stored in BST obtained by in-order traversing the tree.
	 */
	List<Integer> traverseInOrder() {
		List<Integer> res = new ArrayList<Integer>();
		if(this != null) {
			if(this.left != null )
				res.addAll(this.left.traversePreOrder());
			res.add(this.key);
			if(this.right != null )
				res.addAll(this.right.traversePreOrder());
		}
		return res;
	}

	/**
	 * Depth-first post-order traversal of the BST.
	 * 
	 * @return List of keys stored in BST obtained by post-order traversing the tree.
	 */
	List<Integer> traversePostOrder() {
		List<Integer> res = new ArrayList<Integer>();
		if(this != null) {
			if(this.left != null )
				res.addAll(this.left.traversePreOrder());
			if(this.right != null )
				res.addAll(this.right.traversePreOrder());
			res.add(this.key);
		}
		return res;
	}

	/**
	 * Breadth-first (or level-order) traversal of the BST.
	 * 
	 * @return List of keys stored in BST obtained by breadth-first traversal of the tree.
	 */
	List<Integer> traverseLevelOrder() {
		throw new UnsupportedOperationException("You need to implement this function!");
	}
}
