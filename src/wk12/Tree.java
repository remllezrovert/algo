package wk12;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tree {

	public ArrayList<ArrayList<Integer>> adjList;
	public int weights[];
	public int numNodes;

	public Tree(final String filePath) throws FileNotFoundException {

		Scanner fileReader = new Scanner(new FileInputStream(filePath));
		numNodes = fileReader.nextInt();
		weights = new int[numNodes];
		adjList = new ArrayList<ArrayList<Integer>>(numNodes);
		
		for (int i = 0; i < numNodes; i++) {
			weights[i] = fileReader.nextInt();
			adjList.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < numNodes - 1; i++) {
			int src = fileReader.nextInt();
			int dest = fileReader.nextInt();
			adjList.get(src).add(dest);
		}
		fileReader.close();
	}
}
