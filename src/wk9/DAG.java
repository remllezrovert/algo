
package wk9;

import java.util.*;

public class DAG extends Graph {

	public DAG(Graph graph) {
		super(graph);
	}

	public ArrayList<Integer> topoSort() {
		ArrayList<Integer> topoOrder = new ArrayList<>();
		int[] inDegree = new int[numVertices];
		for (int i = 0; i < numVertices; i++)
			inDegree[i] = 0;

		for (int i = 0; i < numVertices; i++)
			for (Edge e : adjList.get(i))
				inDegree[e.dest]++;

		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < numVertices; i++)
			if (inDegree[i] == 0)
				q.add(i);

		while (q.size() > 0) {
			int u = q.poll();
			topoOrder.add(u);
			for (Edge e : adjList.get(u)) {
				inDegree[e.dest]--;
				if (inDegree[e.dest] == 0)
					q.add(e.dest);
			}
		}
		if (topoOrder.size() != numVertices)
			return null;
		return topoOrder;
	}


public int[] longestPaths(int s) throws Exception {
    ArrayList<Integer> topoOrder = topoSort();
    int[] distance = new int[numVertices];
    Arrays.fill(distance, Integer.MIN_VALUE);
    distance[s] = 0;

    for (int v : topoOrder) {
        for (Edge adjEdge : adjList.get(v)) {
            int adjVertex = adjEdge.dest;

            // Check if distance[v] is valid before calculating len
            if (distance[v] != Integer.MIN_VALUE) {
                int len = distance[v] + adjEdge.weight;
                if (len > distance[adjVertex]) {
                    distance[adjVertex] = len;
                }
            }
        }
    }
    return distance;
}




	public int[][] countOddEvenHops(int s) { // complete this function
		ArrayList<Integer> topoOrder = topoSort(); 
		int[][] evenOddArr = new int[2][numVertices]; 
		Arrays.fill(evenOddArr[0], 0);
		Arrays.fill(evenOddArr[1], 0);

		evenOddArr[0][s] = 1; 

		for (int v : topoOrder) {
			for (Edge adjEdge : adjList.get(v)) {
				int adjVertex = adjEdge.dest;
				if (evenOddArr[0][v] > 0) {
					evenOddArr[1][adjVertex] += evenOddArr[0][v];
				}
				if (evenOddArr[1][v] > 0) {
					evenOddArr[0][adjVertex] += evenOddArr[1][v];
				}
			}
		}

    return evenOddArr; 
	}
}
	
