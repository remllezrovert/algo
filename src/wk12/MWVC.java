package wk12;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MWVC extends Tree {

    public int computedSum[];
    boolean isIncludedSumLarger[];
    boolean isInSet[];

    public MWVC(final String filePath) throws FileNotFoundException {
        super(filePath);
        computedSum = new int[numNodes];
        isIncludedSumLarger = new boolean[numNodes];
        isInSet = new boolean[numNodes];
        for (int i = 0; i < numNodes; i++) {
            computedSum[i] = Integer.MAX_VALUE;
            isIncludedSumLarger[i] = false;
            isInSet[i] = false;
        }
    }

    public int computeSum(int node) { // complete this method
	if (computedSum[node] != Integer.MAX_VALUE) return computedSum[node];
		int excl = 0;
		int incl = weights[node];
		ArrayList<Integer> children = adjList.get(node);
		for (int child: children){
			excl += weights[child];
			incl += computeSum(child);

			ArrayList<Integer> grandChildren = adjList.get(child);
			for (int grandChild: grandChildren){
				excl = excl + computeSum(grandChild);
			}
		};

		if (incl <= excl){
			computedSum[node] = incl;
		} else {
            computedSum[node] = excl;
			isIncludedSumLarger[node] = true;
        }
		return computedSum[node];


    }

    public void computeSet(int root) { // complete this method
		if (!isIncludedSumLarger[root])		{
			isInSet[root] = true;
		}
		adjList.get(root).forEach((child)->computeSetHelper(child,root));

    }

    private void computeSetHelper(int node, int parent) { // complete this method
	if (!isIncludedSumLarger[node] || !isInSet[parent]){
			isInSet[node] = true;
		}
		adjList.get(node).forEach((child)->computeSetHelper(child,node));

	}


}