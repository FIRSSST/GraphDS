package graph;

import java.util.ArrayList;

public class GraphAdjacencyMatrix<T> {
	ArrayList<T> vertexList;
	Double[][] matrix;
	
	
	public GraphAdjacencyMatrix() {
		
	}
	
	// Directed Graph
	public void addEdge(T v1, T v2, Double weight) {
		if (vertexList.contains(v1) && vertexList.contains(v2)) {
			matrix[vertexList.indexOf(v1)][vertexList.indexOf(v2)] = weight;
		} else if (vertexList.contains(v1) && vertexList.contains(v2) == false) {
			vertexList.add(v2);
			matrix[vertexList.indexOf(v1)][vertexList.indexOf(v2)] = weight;
		} else if (vertexList.contains(v1) == false && vertexList.contains(v2)) {
			vertexList.add(v1);
			matrix[vertexList.indexOf(v1)][vertexList.indexOf(v2)] = weight;
		} else {
			vertexList.add(v1);
			vertexList.add(v2);
			matrix[vertexList.indexOf(v1)][vertexList.indexOf(v2)] = weight;
		}
	}
 }
