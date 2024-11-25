package wk12;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class TestCorrectness {

	private static final String MIS1_TREE_PATH = "mis1.txt";
	private static final String MIS2_TREE_PATH = "mis2.txt";
	private static final String MIS3_TREE_PATH = "mis3.txt";
	private static final String MIS4_TREE_PATH = "mis4.txt";

	private static void testSubsetSumHelper(int elements[], int numElements, int targets[], int numTarget) {
		System.out.println("Elements are " + Arrays.toString(elements));
		ArrayList<Integer> formed = new ArrayList<Integer>();
		ArrayList<Integer> notFormed = new ArrayList<Integer>();
		for (int i = 0; i < targets.length; i++) {
			if (SubsetSum.isSumPossible(elements, numElements, targets[i]))
				formed.add(targets[i]);
			else
				notFormed.add(targets[i]);
		}
		System.out.println("Can be formed: " + formed);
		System.out.println("Cannot be formed: " + notFormed);
	}

	private static void testSubsetSum() {
		System.out.println("****************** Subset Sum ******************\n");
		int elements[] = { 3, 34, 4, 12, 5, 2 };
		int targets[] = { 3, 9, 12, 13, 20, 22, 26, 27, 38, 47, 50, 62 };
		testSubsetSumHelper(elements, elements.length, targets, targets.length);

		System.out.println("\n***\n");
		elements = new int[] { 15, 22, 14, 26, 32, 9, 16, 8 };
		targets = new int[] { 8, 12, 10, 40, 54, 80, 114, 118, 121, 125, 150 };
		testSubsetSumHelper(elements, elements.length, targets, targets.length);

		System.out.println("\n***\n");
		elements = new int[] { 41, 34, 21, 20, 8, 7, 7, 4, 3, 3 };
		targets = new int[] { 1, 4, 8, 9, 16, 22, 28, 50, 89, 122, 138, 139, 148, 150, 183 };
		testSubsetSumHelper(elements, elements.length, targets, targets.length);
	}

	private static void testKnapsackHelper(int weights[], int profits[], int numElements, int W[], int numW) {
		System.out.println("Weights are " + Arrays.toString(weights));
		System.out.println("Profits are " + Arrays.toString(profits));
		System.out.println();
		for (int w = 0; w < numW; w++)
			System.out.printf("Optimal profit for knapsack of capacity %2d is %3d%n", W[w],
					Knapsack01.findOptimalProfit(profits, weights, weights.length, W[w]));
	}

	private static void testKnapsack() {
		System.out.println("\n****************** 0-1 Knapsack ******************\n");
		int weights[] = { 5, 4, 6, 3, 2 };
		int profits[] = { 10, 40, 30, 50, 25 };
		int W[] = { 2, 3, 5, 10, 14, 15, 17, 20 };
		testKnapsackHelper(weights, profits, weights.length, W, W.length);

		System.out.println("\n***\n");
		weights = new int[] { 12, 7, 11, 8, 9 };
		profits = new int[] { 24, 13, 23, 15, 16 };
		W = new int[] { 26, 46, 60, 80 };
		testKnapsackHelper(weights, profits, weights.length, W, W.length);

		System.out.println("\n***\n");
		weights = new int[] { 23, 31, 29, 44, 53, 38, 63, 85, 89, 82 };
		profits = new int[] { 92, 57, 49, 68, 60, 43, 67, 84, 87, 72 };
		W = new int[] { 165, 310, 400, 410, 800 };
		testKnapsackHelper(weights, profits, weights.length, W, W.length);
	}

	private static void testMISHelper(String treePath) throws FileNotFoundException {
		MWIS mis = new MWIS(treePath);
		int value = mis.computeSum(0);
		mis.computeSet(0);
		System.out.println("Maximum Independent Set has value = " + value);
		ArrayList<Integer> includedNodes = new ArrayList<Integer>();
		for (int i = 0; i < mis.numNodes; i++) {
			if (mis.isInSet[i])
				includedNodes.add(i);
		}
		System.out.print("The included nodes are: ");
		System.out.println(includedNodes);
	}

	private static void testMIS() throws FileNotFoundException {
		System.out.println("\n****************** Maximum Weighted Independent Set ******************\n");
		System.out.println("*** Tree 1 ***\n");
		testMISHelper(MIS1_TREE_PATH);
		System.out.println("\n*** Tree 2 ***\n");
		testMISHelper(MIS2_TREE_PATH);
		System.out.println("\n*** Tree 3 ***\n");
		testMISHelper(MIS3_TREE_PATH);
		System.out.println("\n*** Tree 4 ***\n");
		testMISHelper(MIS4_TREE_PATH);
	}

	private static void testVCHelper(String treePath) throws FileNotFoundException {
		MWVC vc = new MWVC(treePath);
		int value = vc.computeSum(0);
		vc.computeSet(0);
		System.out.println("Minimum Vertex Cover has value = " + value);
		ArrayList<Integer> includedNodes = new ArrayList<Integer>();
		for (int i = 0; i < vc.numNodes; i++) {
			if (vc.isInSet[i])
				includedNodes.add(i);
		}
		System.out.print("The included nodes are: ");
		System.out.println(includedNodes);
	}

	private static void testVC() throws FileNotFoundException {
		System.out.println("\n****************** Minimum Weighted Vertex Cover ******************\n");
		System.out.println("*** Tree 1 ***\n");
		testVCHelper(MIS1_TREE_PATH);
		System.out.println("\n*** Tree 2 ***\n");
		testVCHelper(MIS2_TREE_PATH);
		System.out.println("\n*** Tree 3 ***\n");
		testVCHelper(MIS3_TREE_PATH);
		System.out.println("\n*** Tree 4 ***\n");
		testVCHelper(MIS4_TREE_PATH);
	}

	private static void testLCSHelper(String str1, String str2) {
		System.out.println("First String: " + str1);
		System.out.println("Second String: " + str2);
		String lcs = LCS.longestCommonSubsequence(str1, str2);
		System.out.printf("Longest Common Subsequence is %s having length %d\n", lcs, lcs.length());
	}

	private static void testLCSSHelper(String str1, String str2) {
		System.out.println("First String: " + str1);
		System.out.println("Second String: " + str2);
		String lcs = LCS.longestCommonSubstring(str1, str2);
		System.out.printf("Longest Common Substring is %s having length %d\n", lcs, lcs.length());
	}

	private static void testLCS() {
		System.out.println("\n****************** Longest Common Subsequence ******************\n");
		String str1 = "AGGTAB";
		String str2 = "GXTXAYB";
		testLCSHelper(str1, str2);

		System.out.println("\n***\n");
		str1 = "XMJYAUZ";
		str2 = "MZJAWXU";
		testLCSHelper(str1, str2);

		System.out.println("\n***\n");
		str1 = "AAACCGTGAGTTATTCGTTCTAGAA";
		str2 = "CACCCCTAAGGTACCTTTGGTTC";
		testLCSHelper(str1, str2);

		System.out.println("\n****************** Longest Common Substring ******************\n");
		str1 = "AGGTAB";
		str2 = "GXTXAYB";
		testLCSSHelper(str1, str2);

		System.out.println("\n***\n");
		str1 = "XMJYAUZ";
		str2 = "MZJAWXU";
		testLCSSHelper(str1, str2);

		System.out.println("\n***\n");
		str1 = "AAACCGTGAGTTATTCGTTCTAGAA";
		str2 = "CACCCCTAAGGTACCTTTGGTTC";
		testLCSSHelper(str1, str2);
	}

	private static void testLIS() {
		System.out.println("\n****************** Longest Increasing Subsequence ******************\n");
		int[] seq0 = {10, 22, 9, 33, 21, 50, 41, 60, 55, 57};
		int[] seq1 = {10, 22, 9, 33, 21, 50, 41, 60, 7};
		int[] seq2 = {-1, 2, 0, 4, 8, 5, 7, 10, 3};
		int[] seq3 = {-30, 10, -20, 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
		int[] seq4 = {0, 15, 25, 30, 35, 1, 2};
		int[] seq5 = {15, 20, 5, 30, 22, 25, 35, 24};
		int[][] sequences = {seq0, seq1, seq2, seq3, seq4, seq5};
		for (int i = 0; i < sequences.length; i++)
			System.out.printf("A Longest Increasing Subsequence of %s is %s%n", Arrays.toString(sequences[i]),
					LIS.longestIncreasingSubsequence(sequences[i], sequences[i].length));
	}

	private static void testVankinsMile() {
		System.out.println("\n****************** Test Vankin's Mile ******************\n");
		int board[][] = {
				{-1, 7, -2, 10, -5},
				{8, -4, 3, -6, 0},
				{5, 1, 5, 6, -5},
				{-7, -4, 1, -4, 8},
				{7, 1, -9, 4, 0}
		};

		System.out.println("*** Board ***\n");
		for (int i = 0; i < board.length; i++) {
			StringBuilder out = new StringBuilder("[" + board[i][0]);
			for (int j = 1; j < board[0].length; j++)
				out.append(", " + board[i][j]);
			out.append("]");
			System.out.println(out);
		}
		int[] startRow = {0, 1, 3};
		int[] startCol = {1, 0, 2};
		for (int i = 0; i < startRow.length; i++) {
			int r = startRow[i];
			int c= startCol[i];
			System.out.printf("\nStarting at [%d,%d]\n", r, c);
			VankinsMile.findBestPath(board, board.length, board[0].length, r, c);
		}

		board = new int[][]{
				{-1, 7, -2, 10, -5},
				{8, -4, 3, -6, 0},
				{5, 1, 5, 6, -5},
				{-7, -4, 1, -4, 8},
				{7, 1, -9, 14, -10},
				{4, 43, 13, -36, 4}
		};

		System.out.println("\n*** Board ***\n");
		for (int i = 0; i < board.length; i++) {
			StringBuilder out = new StringBuilder("[" + board[i][0]);
			for (int j = 1; j < board[0].length; j++)
				out.append(", " + board[i][j]);
			out.append("]");
			System.out.println(out);
		}
		startRow = new int[]{0, 1, 3};
		startCol = new int[]{1, 0, 2};
		for (int i = 0; i < startRow.length; i++) {
			int r = startRow[i];
			int c= startCol[i];
			System.out.printf("\nStarting at [%d,%d]\n", r, c);
			VankinsMile.findBestPath(board, board.length, board[0].length, r, c);
		}
	}

	public static void main(String[] args) throws Exception {
		testSubsetSum();
		testKnapsack();
		testLIS();
		testVankinsMile();
		testLCS();
		testMIS();
		testVC();
	}
}
