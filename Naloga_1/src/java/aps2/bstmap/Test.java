package aps2.bstmap;

public class Test {
	
	public static void main(String[] args) {
		BSTMap bst = new BSTMap();
		System.out.println(bst.add(10, "Matic"));
		System.out.println(bst.add(15, "Diana"));
		System.out.println(bst.contains(10));
		System.out.println(bst.get(10));
	}

}
