package wk12;
public class SubsetSum {

    public static boolean isSumPossible(int[] elements, int numElements, int target) {
        boolean[] currentRow = new boolean[target + 1];
        boolean[] prevRow = new boolean[target + 1];
        for (int i = 0; i <= target; i++)
            prevRow[i] = false;
        prevRow[0] = true;
        for (int i = 0; i < numElements; i++) {
            if (elements[i] > target)
                continue;
            for (int j = 0; j < elements[i]; j++)
                currentRow[j] = prevRow[j];
            for (int j = elements[i]; j <= target; j++)
                currentRow[j] = prevRow[j] || prevRow[j - elements[i]];
            for (int j = 0; j <= target; j++)
                prevRow[j] = currentRow[j];
        }
        boolean answer = currentRow[target];
        return answer;
    }
}
