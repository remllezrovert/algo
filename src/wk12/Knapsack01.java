package wk12;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Arrays;
import java.lang.Math;
import java.util.Collections;

public class Knapsack01 {

	public static int findOptimalProfit(final int profits[], final int weights[], int numElements, int capacity) { // complete this function
        int[] currentRow = new int[capacity + 1];
        int[] prevRow = new int[capacity + 1];

        for (int i = 0; i < numElements; ++i) {
            for (int j = 0; j <= capacity; ++j) {
                if (j < weights[i]) {
                    currentRow[j] = prevRow[j]; 
                } else {
                    currentRow[j] = Math.max(prevRow[j], prevRow[j - weights[i]] + profits[i]);
                }
            }
            System.arraycopy(currentRow, 0, prevRow, 0, currentRow.length);
        }

        return currentRow[capacity];
    }
	
}
