package aps2.shortestpath;

import java.util.Vector;

public class Graph{
	private Vector<String> nodes;
	private Vector<Edge> edges;
	
	public Graph() {
		this.nodes = new Vector<>();
		this.edges = new Vector<>();
	}
	
	public boolean addNode(String key) {
		if(this.nodes.contains(key))
			return false;
		nodes.add(key);
		return true;
	}
	
	public void addEdge(String start, String end, int weight) {
		this.edges.addElement(new Edge(start, end, weight));
	}
	
	public Vector<String> getKeys(){
		return this.nodes;
	}
	
	public int indexOf(String key) {
		return this.nodes.indexOf(key);
	}
	
	public Vector<Edge> getEdges(){
		return this.edges;
	}
}

class Edge{
	String start;
	String end;
	int weight;
	
	public Edge(String s, String e, int w) {
		this.start = s;
		this.end = e;
		this.weight = w;
	}
}
