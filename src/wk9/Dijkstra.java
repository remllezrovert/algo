
package wk9;
import java.util.Comparator;
import java.util.PriorityQueue;
public class Dijkstra extends Graph {

	public Dijkstra(Graph graph) {
		super(graph);
	}

	public int[] execute(int source) {
		int distance[] = new int[numVertices];
		boolean closed[] = new boolean[numVertices];
		for (int i = 0; i < numVertices; i++) {
			distance[i] = Integer.MAX_VALUE;
			closed[i] = false;
		}

		distance[source] = 0;
		PriorityQueue<PriorityQueueElement> open = new PriorityQueue<>(Comparator.comparingInt(arg -> arg.priority));
		open.add(new PriorityQueueElement(source, 0));

		while (open.size() > 0) {
			int minVertex = open.poll().item;
			for (int i = 0; i < adjList.get(minVertex).size(); i++) {
				Edge adjEdge = adjList.get(minVertex).get(i);
				int adjVertex = adjEdge.dest;
				if (!closed[adjVertex]) {
					int dist = distance[minVertex] + adjEdge.weight;
					if (dist < distance[adjVertex]) {
						distance[adjVertex] = dist;
						open.add(new PriorityQueueElement(adjVertex, dist));
					}
				}
			}
			closed[minVertex] = true;
		}
		return distance;
	}
}
