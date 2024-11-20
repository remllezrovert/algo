
package wk9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph {

	public int numVertices;
	public int numEdges;
	public ArrayList<ArrayList<Edge>> adjList;

	public Graph(final Graph graph) {
		this.numVertices = graph.numVertices;
		this.numEdges = graph.numEdges;
		this.adjList = graph.adjList;
	}

	public Graph(final String filePath, final GraphType type) throws FileNotFoundException {
		createGraphFromFile(filePath, type);
	}

	private void createGraphFromFile(final String filePath, final GraphType type) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(filePath));

		numVertices = fileReader.nextInt();
		numEdges = fileReader.nextInt();

		adjList = new ArrayList<ArrayList<Edge>>(numVertices);
		
		for (int i = 0; i < numVertices; i++)
			adjList.add(new ArrayList<Edge>());

		for (int i = 0; i < numEdges; i++) {
			int src = fileReader.nextInt();
			int dest = fileReader.nextInt();
			Edge edge;
			if (type.equals(GraphType.WEIGHTED)) {
				int weight = fileReader.nextInt();
				edge = new Edge(src, dest, weight);
			} else
				edge = new Edge(src, dest);
			adjList.get(src).add(edge);
		}

		fileReader.close();
	}
}
