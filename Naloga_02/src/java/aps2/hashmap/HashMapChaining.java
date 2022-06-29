package aps2.hashmap;

import java.util.LinkedList;

import aps2.hashmap.HashMapOpenAddressing.CollisionProbeSequence;

/**
 * Hash map employing chaining on collisions.
 */
public class HashMapChaining {
	private LinkedList<Element> table[];
	private HashFunction.HashingMethod h;
	
	public HashMapChaining(int m, HashFunction.HashingMethod h) {
		this.h = h;
		this.table = new LinkedList[m];
		for (int i=0; i<table.length; i++) {
			table[i] = new LinkedList<Element>();
		}
	}
	
	public LinkedList<Element>[] getTable() {
		return this.table;
	}
	
	/**
	 * If the element doesn't exist yet, inserts it into the set.
	 * 
	 * @param k Element key
	 * @param v Element value
	 * @return true, if element was added; false otherwise.
	 */
	public boolean add(int k, String v) {
		int m = table.length;
		int h = getHash(k, m);
		Element el = new Element(k, v);
		
		int hash = h;
		if(!table[hash].contains(el)) {
			table[hash].add(el);
			return true;
		}
		return false;
	}
	
	private int getHash(int k, int m) {
		if(this.h == HashFunction.HashingMethod.DivisionMethod)
			return HashFunction.DivisionMethod(k, m);
		else
			return HashFunction.KnuthMethod(k, m);
	}

	/**
	 * Removes the element from the set.
	 * 
	 * @param k Element key
	 * @return true, if the element was removed; otherwise false
	 */
	public boolean remove(int k) {
		int m = table.length;
		int h = getHash(k, m);
		for(Element e : table[h]) {
			if(e.key == k) {
				table[h].remove(e);
				return true;
			}
		}
		return false;
	}

	/**
	 * Finds the element.
	 * 
	 * @param k Element key
	 * @return true, if the element was found; false otherwise.
	 */
	public boolean contains(int k) {
		int m = table.length;
		int h = getHash(k, m);
		for(Element e : table[h]) {
			if(e.key == k)
				return true;
		}
		return false;
	}
	
	/**
	 * Maps the given key to its value, if the key exists in the hash map.
	 * 
	 * @param k Element key
	 * @return The value for the given key or null, if such a key does not exist.
	 */
	public String get(int k) {
		int m = table.length;
		int h = getHash(k, m);
		for(Element e : table[h]) {
			if(e.key == k)
				return e.value;
		}
		return null;
	}
}

