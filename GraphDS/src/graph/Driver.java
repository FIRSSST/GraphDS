package graph;

public class Driver {

	public static void main(String[] args) {
		GraphAdjacencyList<String> graph = new GraphAdjacencyList<String>();
		/*
		graph.addEdge("A", "D", 60.0);
		graph.addEdge("A", "C", 12.0);
		graph.addEdge("B", "A", 10.0);
		graph.addEdge("C", "B", 20.0);
		graph.addEdge("C", "D", 32.0);
		graph.addEdge("E", "A", 7.0);
		*/
		graph.addEdge("A", "C", 5.0, true);
		graph.addEdge("A", "B", 8.0, true);
		graph.addEdge("B", "C", 3.0, true);
		graph.addEdge("B", "D", 1.0, true);
		graph.addEdge("C", "D", 2.0, true);
		graph.addEdge("C", "E", 9.0, true);
		graph.addEdge("D", "E", 2.0, true);
		System.out.println(graph);
		graph.removeEdge("A", "B");
		System.out.println(graph);
		System.out.println(graph.shortestPath("B", "C"));
	}

}
