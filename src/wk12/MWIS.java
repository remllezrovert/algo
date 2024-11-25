package wk12;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MWIS extends Tree {

    public int computedSum[];
    boolean isIncludedSumLarger[];
    boolean isInSet[];

    public MWIS(final String filePath) throws FileNotFoundException {

        super(filePath);
        computedSum = new int[numNodes];
        isIncludedSumLarger = new boolean[numNodes];
        isInSet = new boolean[numNodes];
        for (int i = 0; i < numNodes; i++) {
            computedSum[i] = Integer.MIN_VALUE;
            isIncludedSumLarger[i] = false;
            isInSet[i] = false;
        }
    }

    public int computeSum(int node) {// complete this method

        if (computedSum[node] != Integer.MIN_VALUE) return computedSum[node];

        int excl = 0; 
        int incl = weights[node]; 
        ArrayList<Integer> children = adjList.get(node);

        for (int child : children) {
            excl += computeSum(child); 
            
            ArrayList<Integer> grandChildren = adjList.get(child);
            for (int grandChild : grandChildren) {
                incl += computeSum(grandChild); 
            }
        }

        if (incl > excl) {
            computedSum[node] = incl;
            isIncludedSumLarger[node] = true;
        } else {
            computedSum[node] = excl;
        }

        return computedSum[node];
    }

    public void computeSet(int root) {
        if (isIncludedSumLarger[root]) {
            isInSet[root] = true; 
        }
        
        adjList.get(root).forEach((child) ->computeSetHelper(child, root));
    }

    private void computeSetHelper(int node, int parent) {
        if (isIncludedSumLarger[node] && !isInSet[parent]) {
            isInSet[node] = true; 
        }
        
        adjList.get(node).forEach((child) -> computeSetHelper(child, node));
    }
}
