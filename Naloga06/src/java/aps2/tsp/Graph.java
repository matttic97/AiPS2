package aps2.tsp;

import java.util.Vector;

public class Graph {
	Vector<Node> nodes;
	int size;
	
	public Graph() {
		this.nodes = new Vector<Node>();
		size = 0;
	}
	
	public void addNode(int x, int y){
		this.nodes.add(new Node(x, y, size++));
	}
	
	public Node getNode(int key) {
		return this.nodes.get(key);
	}
	
	public Vector<Node> getSet(){
		return this.nodes;
	}
	
	public double[][] emptyMatrix() {
		return new double[size][size];
	}
}

class Node{
	private int X, Y, ID;
	
	Node(int x, int y, int id) {
		this.X = x;
		this.Y = y;
		this.ID = id;
	}
	
	public int getX() {
		return this.X;
	}
	
	public int getY() {
		return this.Y;
	}
	
	public int getID() {
		return this.ID;
	}
}