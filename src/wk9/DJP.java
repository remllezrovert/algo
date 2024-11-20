
package wk9;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DJP extends Graph {

	public DJP(String filePath, GraphType type) throws FileNotFoundException {
		super(filePath, type);
	}

	public ArrayList<Edge> execute() throws IndexOutOfBoundsException {
		int[] label = new int[numVertices];
		boolean[] closed = new boolean[numVertices];
		ArrayList<Edge> parent = new ArrayList<>(numVertices);
		for (int i = 0; i < numVertices; i++) {
			label[i] = Integer.MAX_VALUE;
			closed[i] = false;
			parent.add(null);
		}

		label[numVertices - 1] = 0;
		PriorityQueue<PriorityQueueElement> open = new PriorityQueue<>(Comparator.comparingInt(arg -> arg.priority));
		open.add(new PriorityQueueElement(numVertices - 1, 0));

		while (open.size() > 0) {
			int minVertex = open.poll().item;
			for (Edge adjEdge : adjList.get(minVertex)) {
				int adjVertex = adjEdge.dest;
				if (!closed[adjVertex]) {
					if (adjEdge.weight < label[adjVertex]) {
						label[adjVertex] = adjEdge.weight;
						parent.set(adjVertex, adjEdge);
						open.add(new PriorityQueueElement(adjVertex, adjEdge.weight));
					}
				}
			}
			closed[minVertex] = true;
		}
		parent.remove(parent.size() - 1);
		return parent;
	}
}
