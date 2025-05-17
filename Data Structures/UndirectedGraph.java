package p5;

import graph.Graph;
import graph.Edge;
import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;

public class UndirectedGraph {
	private final Graph G;

	// Creates a graph from the given input file
	private Graph createInputFileGraph(String inputFile) {
		Graph graph = null;

		try {
			Scanner scanner = new Scanner(new File(inputFile));

			int numVertices = scanner.nextInt();

			int numEdges = scanner.nextInt();

			//Creates the undirected graph, initializes weight and adds edges
			graph = new Graph(numVertices, false);
			for (int i = 0; i < numEdges; i++) {
				int u = scanner.nextInt();
				int v = scanner.nextInt();
				int weight = scanner.nextInt();
				graph.addEdge(new Edge(u, v, weight));
			}

			scanner.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return graph;
	}

	// Initializes the graph from the input file
	public UndirectedGraph(String inputFile) {
		G = createInputFileGraph(inputFile);
	}

	// Converts graph to a string
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < G.getVertexCount(); i++) {
			sb.append(i).append(": ");
			List<String> edges = new ArrayList<>();
			for (int j = 0; j < G.getVertexCount(); j++) {
				if (G.isEdge(i, j)) {
					edges.add("<" + j + "," + (int) G.getEdge(i, j).getWeight() + ">");
				}
			}

			sb.append(String.join(" ", edges));

			if (!edges.isEmpty()) {
				sb.append(" ");
			}

			sb.append("\n");
		}

		return sb.toString();
	}

	// Counts total number of triplets in the graph
	public int countTriplets() {
		int count = 0;

		// Looks over graph and if triplet is found, increment the count variable
		for (int u = 0; u < G.getVertexCount(); u++) {
			for (int v = u + 1; v < G.getVertexCount(); v++) {
				if (G.isEdge(u, v)) {
					for (int w = v + 1; w < G.getVertexCount(); w++) {
						if (G.isEdge(v, w) && G.isEdge(w, u)) {
							count++;
						}
					}
				}
			}
		}

		return count;
	}

	// Performs bst starting from 0
	public String ifConnectedThenBreadthFirstTraversal() {
		StringBuilder bft = new StringBuilder();
		boolean[] visited = new boolean[G.getVertexCount()];
		Queue<Integer> queue = new LinkedList<>();
		//Starts the bft from the vertex 0
		queue.offer(0);
		visited[0] = true;

		while (!queue.isEmpty()) {
			int vertex = queue.poll();
			bft.append(vertex).append(" ");
			for (int i = 0; i < G.getVertexCount(); i++) {
				if (G.isEdge(vertex, i) && !visited[i]) {
					queue.offer(i);
					visited[i] = true;
				}
			}
		}

		// Checks if all the vertices have been visited, if null then not connected
		for (boolean v : visited) {
			if (!v) {
				return null;
			}
		}

		return bft.toString().trim();
	}

	// Checks to see if graph is connected using bfs
	private boolean isGraphConnected() {
		boolean[] visited = new boolean[G.getVertexCount()];
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(0);
		visited[0] = true;

		while (!queue.isEmpty()) {
			int vertex = queue.poll();
			for (int i = 0; i < G.getVertexCount(); i++) {
				if (G.isEdge(vertex, i) && !visited[i]) {
					queue.offer(i);
					visited[i] = true;
				}
			}
		}

		// Same as before, if false then not connected, otherwise true
		for (boolean v : visited) {
			if (!v) {
				return true;
			}
		}

		return false;
	}

	// Represents a node in Dijkstra's
	private static class Node {
		int vertex;
		int distance;

		Node(int vertex, int distance) {
			this.vertex = vertex;
			this.distance = distance;
		}
	}

	// Finds shortest path length between two vertices, uses Dijkstra's
	public int findShortestPathLengthBetween(int u, int v) {
		// Returns infinity if the graph isn't connected
		if (isGraphConnected()) {
			return Integer.MAX_VALUE;
		}

		// Stores the shortest distances, then initializes them
		int[] dist = new int[G.getVertexCount()];
		Arrays.fill(dist, Integer.MAX_VALUE);

		//Distance to u from u is 0
		dist[u] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
		pq.offer(new Node(u, 0));

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int vertex = node.vertex;
			if (vertex == v) {
				return dist[vertex];
			}
			for (int i = 0; i < G.getVertexCount(); i++) {
				if (G.isEdge(vertex, i)) {
					int newDistance = (int) (dist[vertex] + G.getEdge(vertex, i).getWeight());
					if (newDistance < dist[i]) {
						dist[i] = newDistance;
						pq.offer(new Node(i, newDistance));
					}
				}
			}
		}

		return Integer.MAX_VALUE;
	}

	//Checks if graph is a tree by seeing if connected and has n - 1 vertices
	public boolean isTree() {
		// Check if the graph is connected, false if not
		if (isGraphConnected()) {
			return false;
		}

		// Gets amount of vertices and edges
		int numVertices = G.getVertexCount();
		int numEdges = G.getEdgeCount();

		// If number of edges is not exactly num vertices - 1, then not a tree
        return numEdges == numVertices - 1;
    }
}