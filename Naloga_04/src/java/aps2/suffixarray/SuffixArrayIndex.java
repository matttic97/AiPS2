package aps2.suffixarray;

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays; 

public class SuffixArrayIndex {
	private String text; // input string
	private int[] SA;    // suffix array
	
	public int[] getSuffixArray() { 
		return SA; 
		}
	
	SuffixArrayIndex(String text){
		this.text = text;
		this.SA = new int[text.length()];
		construct();
	}
	
	/**
	 * Constructs the suffix array corresponding to the text in expected
	 * O(n log n) suffix comparisons.
	 */
	private void construct() {
		int d = this.text.length();
		String[] tmp = new String[d];
		for(int i=0; i<d; i++) {
			tmp[i] = this.text.substring(i);
		}
		Arrays.sort(tmp);
		for(int i=0; i<d; i++) {
			SA[i] = (d - tmp[i].length());
		}
	}
	
	/**
	 * Returns True, if the suffix at pos1 is lexicographically before
	 * the suffix at pos2.
	 * 
	 * @param int pos1
	 * @param int pos2
	 * @return boolean
	 */
	public boolean suffixSuffixCompare(int pos1, int pos2) {
		if(this.text.charAt(pos1) < this.text.charAt(pos2))
			return true;
		return false;
	}
	
	/**
	 * Return True, if the query string is lexicographically lesser or
	 * equal to the suffix string at pos1.
	 * 
	 * @param String query The query string
	 * @param int pos2 Position of the suffix
	 * @return boolean
	 */
	public boolean stringSuffixCompare(String query, int pos2) {
		if(query.charAt(0) <= this.text.charAt(pos2))
			return true;
		return false;
	}
	
	private boolean compare(String query, int pos) {
		for(int i=0; i<query.length(); i++) {
			if(query.charAt(i) != this.text.charAt(pos+i))
				return false;
		}
		return true;
	}
	
	/**
	 * Returns the positions of the given substring in the text using binary
	 * search. The empty query is located at all positions in the text.
	 * 
	 * @param String query The query substring
	 * @return A set of positions where the query is located in the text
	 */
	public Set<Integer> locate(String query) {
		int d = this.SA.length;
		Set<Integer> set = new HashSet<Integer>();
		
		if(query.isEmpty()){
			for(int i=0; i<d; i++)
				set.add(i);
			return set;
		}
		else
			return search(set, query);
	}
	
	private Set<Integer> search(Set<Integer> set, String query){
		int n = this.text.length();
		int m = query.length();
		 for (int i = 0; i <= n - m; i++) {
	            int j; 
	  
	            for (j = 0; j < m; j++) 
	                if (this.text.charAt(i + j) != query.charAt(j)) 
	                    break; 
	  
	            if (j == m)
	                set.add(i); 
	        } 
		return set;
	}
	
	private Set<Integer> binarySearch(Set<Integer> set, int l, int r, String query){
		if(r >= l) {
			int m = l+(r-l)/2;
			
			if(this.stringSuffixCompare(query, m)) {
				
				for(int i=m; i>=l; i--)
					if(compare(query, m))
						set.add(m);
				return binarySearch(set, m+1, r, query);
			}
			else
				return binarySearch(set, l, m-1, query);
		}
		return set;
	}
	
	/**
	 * Returns the longest substring in the text which repeats at least 2 times
	 * by examining the suffix array.
	 * 
	 * @return The longest repeated substring in the text
	 */
	public String longestRepeatedSubstring() {
		throw new UnsupportedOperationException("You need to implement this function!");
	}
	
	/**
	 * Calculates the length of the longest common prefix of two suffixes.
	 * 
	 * @param int pos1 Position of the first suffix in the text
	 * @param int pos2 Position of the second suffix in the text
	 * @return The number of characters in the common prefix of the first and the second suffix
	 */
	public int longestCommonPrefixLen(int pos1, int pos2) {
		throw new UnsupportedOperationException("You need to implement this function!");
	}
}

