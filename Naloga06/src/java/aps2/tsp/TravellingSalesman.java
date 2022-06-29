package aps2.tsp;

import java.util.Vector;

public class TravellingSalesman {
	
	private Graph graph;
	
	public TravellingSalesman() {
		this.graph = new Graph();
	}
	
	/**
	 * To solve the travelling salesman problem (TSP) you need to find a shortest
	 * tour over all nodes in the graph where each node must be visited exactly
	 * once. You need to finish at the origin node.
	 *
	 * In this task we will consider complete undirected graphs only with
	 * Euclidean distances between the nodes.
	 */
	
	/**
	 * Adds a node to a graph with coordinates x and y. Assume the nodes are
	 * named by the order of insertion.
	 * 
	 * @param x X-coordinate
	 * @param y Y-coordinate
	 */
	public void addNode(int x, int y){
		this.graph.addNode(x, y); 
	}
	
	/**
	 * Returns the distance between nodes v1 and v2.
	 * 
	 * @param v1 Identifier of the first node
	 * @param v2 Identifier of the second node
	 * @return Euclidean distance between the nodes
	 */
	public double getDistance(int v1, int v2) {
		Node n1 = this.graph.getNode(v1);
		Node n2 = this.graph.getNode(v2);
		int x = n1.getX() - n2.getX(); 
		int y = n1.getY() - n2.getY();
		
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	/**
	 * Finds and returns an optimal shortest tour from the origin node and
	 * returns the order of nodes to visit.
	 * 
	 * Implementation note: Avoid exploring paths which are obviously longer
	 * than the existing shortest tour.
	 * 
	 * @param start Index of the origin node
	 * @return List of nodes to visit in specific order
	 */
	public int[] calculateExactShortestTour(int start){
		throw new UnsupportedOperationException("You need to implement this function!"); 
	}
	
	/**
	 * Returns an optimal shortest tour and returns its distance given the
	 * origin node.
	 * 
	 * @param start Index of the origin node
	 * @return Distance of the optimal shortest TSP tour
	 */
	public double calculateExactShortestTourDistance(int start){
		throw new UnsupportedOperationException("You need to implement this function!"); 
	}	
	
	/**
	 * Returns an approximate shortest tour and returns the order of nodes to
	 * visit given the origin node.
	 * 
	 * Implementation note: Use a greedy nearest neighbor apporach to construct
	 * an initial tour. Then use iterative 2-opt method to improve the
	 * solution.
	 * 
	 * @param start Index of the origin node
	 * @return List of nodes to visit in specific order
	 */
	public int[] calculateApproximateShortestTour(int start){
		throw new UnsupportedOperationException("You need to implement this function!"); 
	}
	
	/**
	 * Returns an approximate shortest tour and returns its distance given the
	 * origin node.
	 * 
	 * @param start Index of the origin node
	 * @return Distance of the approximate shortest TSP tour
	 */
	public double calculateApproximateShortestTourDistance(int start){
		throw new UnsupportedOperationException("You need to implement this function!"); 
	}
	
	/**
	 * Constructs a Hamiltonian cycle by starting at the origin node and each
	 * time adding the closest neighbor to the path.
	 * 
	 * Implementation note: If multiple neighbors share the same distance,
	 * select the one with the smallest id.
	 * 
	 * @param start Origin node
	 * @return List of nodes to visit in specific order
	 */
	public int[] nearestNeighborGreedy(int start){
		Vector<Node> nodes = this.graph.nodes;
		int res[] = new int[nodes.size()];
		boolean[] visited = new boolean[nodes.size()];
		
		res[0] = start;
		visited[start] = true;
		int minI = 0;
		double min, d;
		
		for(int j=1; j<nodes.size(); j++) {
			
			min = Double.MAX_VALUE;
			
			for(int i=0; i<nodes.size(); i++) {
				d = getDistance(start, i);
				if(d == min && !visited[i] && j!=i) {
					if(nodes.elementAt(i).getID() < nodes.elementAt(minI).getID()) {
						min = d;
						minI = i;
					}
				}
				else if(d < min && !visited[i]) {
					min = d;
					minI = i;
				}
			}
			
			res[j] = minI;
			start = minI;
			visited[start] = true;
		}
		return res;
	}
	
	/**
	 * Improves the given route using 2-opt exchange.
	 * 
	 * Implementation note: Repeat the procedure until the route is not
	 * improved in the next iteration anymore.
	 * 
	 * @param route Original route
	 * @return Improved route using 2-opt exchange
	 */
	private int[] twoOptExchange(int[] route) {
		throw new UnsupportedOperationException("You need to implement this function!");
	}
	
	/**
	 * Swaps the nodes i and k of the tour and adjusts the tour accordingly.
	 * 
	 * Implementation note: Consider the new order of some nodes due to the
	 * swap!
	 * 
	 * @param route Original tour
	 * @param i Name of the first node
	 * @param k Name of the second node
	 * @return The newly adjusted tour
	 */
	public int[] twoOptSwap(int[] route, int i, int k) {
		throw new UnsupportedOperationException("You need to implement this function!");
	}

	/**
	 * Returns the total distance of the given tour i.e. the sum of distances
	 * of the given path add the distance between the final and initial node.
	 * 
	 * @param tour List of nodes in the given order
	 * @return Travelled distance of the tour
	 */
	public double calculateDistanceTravelled(int[] tour){
		double res = 0.0;
		for(int i=1; i<tour.length; i++) {
			res += getDistance(tour[i], tour[i-1]);
		}
		res += getDistance(tour[0], tour[tour.length-1]);
		return res;
	}
	
}
