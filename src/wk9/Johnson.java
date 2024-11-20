
package wk9;

import java.util.ArrayList;

import wk9.BellmanFord;

public class Johnson extends Graph {

	public Johnson(final Graph graph) {
		super(graph);
	}

	public int[][] execute() throws Exception { // complete this method
		adjList.add(new ArrayList<Edge>());

		for (int i = 0; i < numVertices; i++){
			Edge e = new Edge(numVertices,i,0);
			ArrayList<Edge> a = adjList.get(numVertices);;
			a.add(e);
			adjList.set(numVertices,a);

		}
		numEdges += numVertices;
		numVertices += 1;

		BellmanFord fordGraph = new BellmanFord(this);
		int[] phi = fordGraph.execute(numVertices - 1);
		numVertices--;
		numEdges -= numVertices;
		adjList.remove(numVertices); //remove the last row
		if (phi == null) return null;
		for (ArrayList<Edge> arr : adjList) for (Edge e : arr){
			e.weight = e.weight + phi[e.src] - phi[e.dest];
		}
		int[][] allPairMatrix = new int[numVertices][];
		Dijkstra d = new Dijkstra(this);

		for (int i = 0; i < numVertices; i++){
			allPairMatrix[i] = d.execute(i);
		}
		for (int i = 0; i < numVertices; i++) for (int j = 0; j < numVertices; j++){
			if (i != j && allPairMatrix[i][j] != Integer.MAX_VALUE){
				allPairMatrix[i][j] = allPairMatrix[i][j] - phi[i] + phi[j];
			}
		}
		for (ArrayList<Edge> arr : adjList) for (Edge e : arr){
			e.weight = phi[e.src]; //don't know what to do here
		}
		return allPairMatrix;
	}
}
