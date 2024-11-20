package wk9;

import java.util.Arrays;

public class BellmanFord extends Graph {

    public BellmanFord(final Graph graph) {
        super(graph);
    }

    public int[] execute(int source) {
        int[] dist = new int[numVertices];
        Arrays.fill(dist, Integer.MAX_VALUE); 
        dist[source] = 0; 

        for (int i = 1; i < numVertices; i++) { 
            boolean didDistChange = false; 

            for (int v = 0; v < numVertices; v++) { 
                for (Edge e : adjList.get(v)) { 
                    if (dist[e.src] == Integer.MAX_VALUE) {
                        continue;  
                    }
                    int newDist = dist[e.src] + e.weight;
                    if (newDist < dist[e.dest]) {
                        dist[e.dest] = newDist;
                        didDistChange = true; 
                    }
                }
            }

            if (!didDistChange) {
                break; 
            }
        }

        for (int v = 0; v < numVertices; v++) {
            for (Edge e : adjList.get(v)) {
                if (dist[e.src] == Integer.MAX_VALUE) {
                    continue; 
                }
                if (dist[e.src] + e.weight < dist[e.dest]) {
                    return null;
                }
            }
        }

        return dist; 
    }
}
