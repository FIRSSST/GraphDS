package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import misc.Pair;

public class GraphAdjacencyList<T> {
	
	private HashMap<T, ArrayList<Pair<T, Double>>> adjacencyList;
	
	public GraphAdjacencyList() {
		adjacencyList = new HashMap<>();
	}
	
	// Directed Graph
	public Boolean addEdge(T v1, T v2, Double weight) { // addEdge(vertex1, vertex2, weight)
		
		if (adjacencyList.containsKey(v1)) {
			
			ArrayList<Pair<T, Double>> edges = adjacencyList.get(v1);
			for (Pair<T, Double> edge: edges) {
				if (edge.getFirst() == v2) {
					return false;
				}
			}
			
			Pair<T, Double> edge = new Pair<T, Double>(v2, weight);
			((ArrayList<Pair<T,Double>>) adjacencyList.get(v1)).add(edge);
			
		} else {
			
			ArrayList<Pair<T, Double>> edges = new ArrayList<Pair<T, Double>> ();
			Pair<T, Double> edge = new Pair<T, Double>(v2, weight);
			edges.add(edge);
			adjacencyList.put(v1, edges);
			
		}
		if (adjacencyList.containsKey(v2) == false)  {
			ArrayList<Pair<T, Double>> edges = new ArrayList();
			adjacencyList.put(v2, edges);
		}
		return true;
	}
	
	// UnDirected Graph
	public Boolean addEdge(T v1, T v2, Double weight, Boolean undirected) { // addEdge(vertex1, vertex2, weight)
		if (undirected == true) {
			if (adjacencyList.containsKey(v1)) {
				
				ArrayList<Pair<T, Double>> edges = adjacencyList.get(v1);
				for (Pair<T, Double> edge: edges) {
					if (edge.getFirst() == v2) {
						return false;
					}
				}
				
				Pair<T, Double> edge = new Pair<T, Double>(v2, weight);
				((ArrayList<Pair<T,Double>>) adjacencyList.get(v1)).add(edge);
				
			} else {
				
				ArrayList<Pair<T, Double>> edges = new ArrayList<Pair<T, Double>> ();
				Pair<T, Double> edge = new Pair<T, Double>(v2, weight);
				edges.add(edge);
				adjacencyList.put(v1, edges);
				
			}
			if (adjacencyList.containsKey(v2)) {
				
				ArrayList<Pair<T, Double>> edges = adjacencyList.get(v2);
				for (Pair<T, Double> edge: edges) {
					if (edge.getFirst() == v1) {
						return false;
					}
				}
				
				Pair<T, Double> edge = new Pair<T, Double>(v1, weight);
				((ArrayList<Pair<T,Double>>) adjacencyList.get(v2)).add(edge);
				
			} else {
				
				ArrayList<Pair<T, Double>> edges = new ArrayList<Pair<T, Double>> ();
				Pair<T, Double> edge = new Pair<T, Double>(v1, weight);
				edges.add(edge);
				adjacencyList.put(v2, edges);
				
			}
			
			return true;
		} 
		
		return addEdge(v1, v2, weight);
		
		
	}
	
	public ArrayList<Pair<T, Double>> getNeighbors(T vertex) {
		return adjacencyList.get(vertex);
	}

	private T getMinimumFromUnvisited(HashMap<T, Pair<Double, T>> unvisited) {
		Pair<Double, T> current_min = null;
		T current_minNode = null;
		
		for (T key : unvisited.keySet()) {
			  if (current_min == null) {
				  current_min = unvisited.get(key);
			  }
			  if (current_minNode == null) {
				  current_minNode = key;
			  }
			  if (unvisited.get(key).getFirst() < current_min.getFirst()) {
				  current_min = unvisited.get(key);
				  current_minNode = key;
			  }
		}
		return current_minNode;
	}
	
	public HashMap<T, Pair<Double, T>> dijkstra(T source) {
		
		
		HashMap<T, Pair<Double, T>> visited = new HashMap<>(); // whether vertex T has been visited
		HashMap<T, Pair<Double, T>> unvisited = new HashMap<>(); // vertex and distance of previous vertex
		
		for (T vertex: adjacencyList.keySet()) {
			unvisited.put(vertex, new Pair<Double, T>(Double.POSITIVE_INFINITY, null));
		}

		unvisited.get(source).setFirst(0.0);
		
		Boolean finished = false;
		while (finished == false) {
			if (unvisited.size() == 0) {
				finished = true;
			} else {
				T currentNode = getMinimumFromUnvisited(unvisited);
		
				for (Pair<T, Double> neighbor: getNeighbors(currentNode)) {
					if (visited.containsKey(neighbor.getFirst()) == false) {
						
						Double unvisitedDistance = unvisited.get(currentNode).getFirst();
						Double graphDistance = neighbor.getSecond();
						
						Double cost = unvisitedDistance + graphDistance;
						
						if (cost < unvisited.get(neighbor.getFirst()).getFirst()) {
							unvisited.get(neighbor.getFirst()).setFirst(cost);
							unvisited.get(neighbor.getFirst()).setSecond(currentNode);
						}
						
						
					}
				}
				visited.put(currentNode, unvisited.get(currentNode));
				unvisited.remove(currentNode);
			}
		}
		return visited;
	}
	
	public Pair<ArrayList<T>, Double> shortestPath(T source, T des) {
		HashMap<T, Pair<Double, T>> visited = dijkstra(source);
		ArrayList<T> path = new ArrayList<T>();
		if (source == des) {
			return new Pair<ArrayList<T>, Double>(path, visited.get(source).getFirst());
		}
		if (visited.get(des).getSecond() == null) {
			return new Pair<ArrayList<T>, Double>(path, -1.0);	
		}
		
		Double totalCost = 0.0;
		T current = visited.get(des).getSecond();
		totalCost = visited.get(des).getFirst();
		
		path.add(des);
		path.add(current);
		
		while (current != source) {
			current = visited.get(current).getSecond();
			path.add(current);
		}
		Collections.reverse(path);
		return new Pair<ArrayList<T>, Double>(path, totalCost);
	}

	public String toString() {
		String s = "";
		for (T key: adjacencyList.keySet()) {
			s += key + " = " + adjacencyList.get(key) + "\n";
		}
		return (s);
		
	}
	

}
