package wk7;

import java.util.Arrays;
import java.util.Random;

public class TestTime {

	final static int[] LARGE_STRING_SIZES = { 1000000, 5000000, 10000000, 15000000, 20000000, 25000000 };

	private static void testStringSorter() throws Exception {
		System.out.println("\n****************** Time Test String Sorting ******************\n");
		long timeMerge = 0, timeRadix = 0, timeStd = 0;
		Random rand = new Random(0);
		int maxLength = 250;
		for (int size : LARGE_STRING_SIZES) {
			String[] radixSortStrings = new String[size];
			String[] mergeSortStrings = new String[size];
			String[] stdSortStrings = new String[size];
			for (int i = 0; i < size; i++) {
				int stringLength = 1 + rand.nextInt(maxLength);
				StringBuilder str = new StringBuilder();
				for (int j = 0; j < stringLength; j++)
					str.append((char) (97 + rand.nextInt(26)));

				radixSortStrings[i] = mergeSortStrings[i] = stdSortStrings[i] = str.toString();
			}

			long startTime = System.currentTimeMillis();
			new StringMergeSort(mergeSortStrings, size).mergesort();
			timeMerge = System.currentTimeMillis() - startTime;

			startTime = System.currentTimeMillis();
			StringRadixSort.radixSort(radixSortStrings, size);
			timeRadix = System.currentTimeMillis() - startTime;

			startTime = System.currentTimeMillis();
			Arrays.sort(stdSortStrings);
			timeStd = System.currentTimeMillis() - startTime;

			for (int i = 0; i < size - 1; i++) {
				if (mergeSortStrings[i].compareTo(mergeSortStrings[i + 1]) > 0)
					throw new Exception("Something wrong with merge sort");
				if (radixSortStrings[i].compareTo(radixSortStrings[i + 1]) > 0)
					throw new Exception("Something wrong with radix sort");
				if (stdSortStrings[i].compareTo(stdSortStrings[i + 1]) > 0)
					throw new Exception("Something wrong with Java sort");
			}

			System.out.println(
					"Merge-sort time for " + size + " strings = " + timeMerge + " (may vary with each execution)");
			System.out.println(
					"Radix-sort time for " + size + " strings = " + timeRadix + " (may vary with each execution)");
			System.out.println(
					"Java standard-sort time for " + size + " strings = " + timeStd + " (may vary with each execution)");
			System.out.println();
		}
	}

	public static void main(String[] args) throws Exception {
		testStringSorter();
	}
}
