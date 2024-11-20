
package wk9;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestCorrectness {
	
	static final String DAG1_PATH = "dag1.txt";
	static final String DAG2_PATH = "dag2.txt";

	static final String BELLMANFORD1_GRAPH_PATH = "bellmanford1.txt";
	static final String BELLMANFORD2_GRAPH_PATH = "bellmanford2.txt";
	static final String BELLMANFORD3_GRAPH_PATH = "bellmanford3.txt";

	static final String DIJKSTRA1_GRAPH_PATH = "dijkstra1.txt";
	static final String DIJKSTRA2_GRAPH_PATH = "dijkstra2.txt";

	static final String APSP1_GRAPH_PATH = "apsp1.txt";
	static final String APSP2_GRAPH_PATH = "apsp2.txt";
	static final String APSP3_GRAPH_PATH = "apsp3.txt";
	
	static final String MST_GRAPH_PATH = "mst_graph.txt";

	private static void testDAG() throws Exception {
		String filePaths[] = { DAG1_PATH, DAG2_PATH };
		for (int i = 0; i < filePaths.length; i++) {
			System.out.printf("*** Test Longest Path on DAG %d ***\n\n", i + 1);
			DAG ag = new DAG(new Graph(filePaths[i], GraphType.WEIGHTED));
			ArrayList<Integer> topo =  ag.topoSort();
			System.out.println("Topological Order: " + topo);
			for (int j = 0; j < ag.numVertices; j++) {
				int[] distance = ag.longestPaths(j);
				System.out.printf("Longest Path Array (from v%d): %s\n", j,
						Arrays.toString(distance).replaceAll("" + Integer.MIN_VALUE, "-inf"));
			}
			System.out.println();
		}
		for (int i = 0; i < filePaths.length; i++) {
			System.out.printf("*** Count Number of Odd & Even Edges Path on DAG %d ***\n\n", i + 1);
			DAG ag = new DAG(new Graph(filePaths[i], GraphType.WEIGHTED));
			for (int j = 0; j < ag.numVertices; j++) {
				int[][] count = ag.countOddEvenHops(j);
				System.out.printf("Number of even length paths (from v%d): %s\n", j,
						Arrays.toString(count[0]));
				System.out.printf("Number of odd length paths (from v%d):  %s\n\n", j,
						Arrays.toString(count[1]));
			}
		}
	}

	private static void testBellmanFord() throws FileNotFoundException {
		System.out.println("*** Graph 1 ***\n");
		Graph graph = new Graph(BELLMANFORD1_GRAPH_PATH, GraphType.WEIGHTED);
		BellmanFord bf = new BellmanFord(graph);
		for (int i = 0; i < graph.numVertices; i++) {
			String distArray = Arrays.toString(bf.execute(i)).replaceAll("" + Integer.MAX_VALUE, "inf");
			System.out.printf("Distances from v%d: %s%n", i, distArray);
		}

		System.out.println("\n*** Graph 2 ***");
		graph = new Graph(BELLMANFORD2_GRAPH_PATH, GraphType.WEIGHTED);
		bf = new BellmanFord(graph);
		if (bf.execute(0) == null)
			System.out.println("\nHas a negative cycle.");
		else
			System.out.println("\nSomething is wrong.");

		System.out.println("\n*** Graph 3 ***\n");
		graph = new Graph(BELLMANFORD3_GRAPH_PATH, GraphType.WEIGHTED);
		bf = new BellmanFord(graph);
		for (int i = 0; i < graph.numVertices; i++) {
			String distArray = Arrays.toString(bf.execute(i)).replaceAll("" + Integer.MAX_VALUE, "inf");
			System.out.printf("Distances from v%d: %s%n", i, distArray);
		}
	}

	private static void testDijkstra() throws Exception {
		String filePaths[] = { DIJKSTRA1_GRAPH_PATH, DIJKSTRA2_GRAPH_PATH };
		for (int j = 0; j < filePaths.length; j++) {
			System.out.println("*** Graph " + (j + 1) + " ***\n");
			Graph graph = new Graph(filePaths[j], GraphType.WEIGHTED);
			Dijkstra dijk = new Dijkstra(graph);
			for (int i = 0; i < dijk.numVertices; i++) {
				int[] distance = dijk.execute(i);
				System.out.println("Distance array (from v" + i + "): "
						+ Arrays.toString(distance).replaceAll("" + Integer.MAX_VALUE, "inf"));
			}
			System.out.println();
		}
	}

	private static void testAPSP() throws Exception {
		Graph graph = new Graph(APSP1_GRAPH_PATH, GraphType.WEIGHTED);
		Johnson johnson = new Johnson(graph);

		int distArrayJohnson[][] = johnson.execute();
		System.out.println("*** Graph 1 Distance Matrix (using Johnson) ***\n");
		for (int i = 0; i < graph.numVertices; i++)
			System.out.printf("%s%n", Arrays.toString(distArrayJohnson[i]).replaceAll("" + Integer.MAX_VALUE, "infty"));

		System.out.println();
		graph = new Graph(APSP2_GRAPH_PATH, GraphType.WEIGHTED);
		johnson = new Johnson(graph);
		distArrayJohnson = johnson.execute();
		System.out.println("*** Graph 2 Distance Matrix (using Johnson) ***\n");
		for (int i = 0; i < graph.numVertices; i++)
			System.out.printf("%s%n", Arrays.toString(distArrayJohnson[i]).replaceAll("" + Integer.MAX_VALUE, "infty"));

		System.out.println();
		graph = new Graph(APSP3_GRAPH_PATH, GraphType.WEIGHTED);
		johnson = new Johnson(graph);
		distArrayJohnson = johnson.execute();
		System.out.println("*** Graph 3 ***\n");
		if (distArrayJohnson != null)
			System.out.println("Something wrong with Johnson's method.");
		else
			System.out.println("Has a negative cycle.");
	}

	private static void testDJP() throws FileNotFoundException {
		DJP djp = new DJP(MST_GRAPH_PATH, GraphType.WEIGHTED);
		ArrayList<Edge> mst = djp.execute();
		int mstWeight = 0;
		for (Edge e : mst)
			mstWeight += e.weight;
		System.out.printf("MST has weight %d%nThe edges are: %s%n", mstWeight, mst);
	}

	public static void main(String args[]) throws Exception {
		System.out.println("****************** Acyclic Graphs ******************\n");
		testDAG();
		System.out.println("****************** Bellman-Ford ******************\n");
		testBellmanFord();
		System.out.println("\n****************** Dijkstra ******************\n");
		testDijkstra();
		System.out.println("****************** APSP algorithms ******************\n");
		testAPSP();
		System.out.println("\n****************** DJP ******************\n");
		testDJP();
	}
}
