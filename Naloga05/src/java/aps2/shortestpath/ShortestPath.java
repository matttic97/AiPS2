package aps2.shortestpath;

import java.util.Stack;
import java.util.Vector;

public class ShortestPath {
	
	private Graph graph = new Graph();
	private int dest[];
	private String[] path;
			
	/**
	 * Adds a new node named s to the graph.
	 * 
	 * @param s Name of the node
	 * @return True, if a node is unique and successfully added; False otherwise.
	 */
	public boolean addNode(String s) {
		return this.graph.addNode(s);
	}
	
	/**
	 * Returns names of all nodes in the graph.
	 * 
	 * @return Names of all nodes in the graph.
	 */
	public Vector<String> getNodes() {
		return this.graph.getKeys();
	}
	
	/**
	 * Adds an edge from node a to node b.
	 * 
	 * @param a Source node.
	 * @param b Target node.
	 * @param w Edge weight. Warning: The weight can also be negative!
	 */
	public void addEdge(String a, String b, int w) {
		this.graph.addEdge(a, b, w);
	}

	/**
	 * Computes and locally stores shortest paths from the given origin node
	 * to all other nodes in the graph.
	 * 
	 * @param start Origin node.
	 */
	public void computeShortestPaths(String start) {
		int src = this.graph.indexOf(start);
		Vector<String> nodes = this.graph.getKeys();
		Vector<Edge> edges = this.graph.getEdges();
		int V = nodes.size();
		int E = edges.size();
		int[] d = new int[V];
		
		for(int i=0; i<V; i++) {
			d[i] = Integer.MAX_VALUE;
		}
		d[src] = 0;
		
		String[] p = new String[V];
		
		// naredimo tabelo z najkrajsimi potmi
		for(int i=1; i<V-1; i++) {
			for(int j=0; j<E; j++) {
				int u = nodes.indexOf(edges.elementAt(j).start);
				int v = nodes.indexOf(edges.elementAt(j).end);
				int w = edges.elementAt(j).weight;
				
				if (d[u]!=Integer.MAX_VALUE && d[u] + w < d[v]) {
					d[v]= d[u] + w;
					p[v] = nodes.elementAt(u);
				}
			}
		}
		this.path = p;
		
		// preverimo ce je kak negativen cikel
		boolean tmp = false;
		for (int j=0; j<E; ++j) 
        { 
			int u = nodes.indexOf(edges.elementAt(j).start);
			int v = nodes.indexOf(edges.elementAt(j).end);
			int w = edges.elementAt(j).weight;
			if (d[u]!=Integer.MAX_VALUE && d[u] + w < d[v]) { 
				tmp = true;
				break;
			}
        } 
		if(edges == null || tmp)
			this.dest = null;
		else
			this.dest = d;
	}
	
	/**
	 * Returns a list of nodes on the shortest path from the given origin to
	 * destination node. Returns null, if a path does not exist or there is a
	 * negative cycle in the graph.
	 * 
	 * @param start Origin node
	 * @param dest Destination node
	 * @return List of nodes on the shortest path from start to dest or null, if the nodes are not connected or there is a negative cycle in the graph.
	 */
	public Vector<String> getShortestPath(String start, String dest) {
		if(this.dest == null)
			return null;
		else {
			Vector<String> nodes = this.graph.getKeys();
			int dst = nodes.indexOf(dest);
			
			Vector<String> res = new Vector<>();
			Stack<String> tmp = new Stack<>();
			while(dst != 0) {
				tmp.push(this.path[dst]);
				dst = nodes.indexOf(tmp.peek());
			}
			tmp.push(start);
			for(int i=0; i<tmp.size(); i++) {
				res.add(tmp.pop());
			}
			res.add(dest);
			
			for(int i=0; i<res.size(); i++) {
				System.out.print(res.elementAt(i) + ", ");
			}
			
			return res;
		}
	}
	
	/**
	 * Returns the distance of the shortest path from the given origin to
	 * destination node. Returns Integer.MIN_VALUE, if a path does not exist
	 * or there is a negative cycle in the graph.
	 * 
	 * @param start Origin node
	 * @param dest Destination node
	 * @return Distance of the shortest path from start to dest or Integer.MIN_VALUE, if the nodes are not connected or there is a negative cycle in the graph.
	 */
	public int getShortestDist(String start, String dest) {
		Vector<String> nodes = this.graph.getKeys();
		if(this.dest == null)
			return Integer.MIN_VALUE;
		else
			return this.dest[nodes.indexOf(dest)];
	}
}
