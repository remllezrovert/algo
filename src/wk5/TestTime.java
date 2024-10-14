package wk5;

import java.util.Arrays;
import java.util.Random;

public class TestTime {

	static Random rand;

	final static int[] LARGE_POINT_SIZES = { 10, 50, 100, 500, 1000, 2000, 5000, 10000, 20000, 50000, 100000 };

	private static void testIfSorted(int A[], int len, char s) throws Exception {
		for (int i = 0; i < len - 1; i++)
			if (A[i] > A[i + 1]) {
				if (s == 'M')
					throw new Exception("MergeSort code is incorrect");
				else if (s == '3')
					throw new Exception("QuickSort (median of 3) code is incorrect");
				else if (s == 'Q')
					throw new Exception("QuickSort (randomized) code is incorrect");
				else if (s == 'R')
					throw new Exception("RadixSort code is incorrect");
			}
	}
	
	private static void testIfSorted(Integer A[], int len, char s) throws Exception {
		for (int i = 0; i < len - 1; i++)
			if (A[i] > A[i + 1]) {
				if (s == 'M')
					throw new Exception("MergeSort code is incorrect");
				else if (s == '3')
					throw new Exception("QuickSort (median of 3) code is incorrect");
				else if (s == 'Q')
					throw new Exception("QuickSort (randomized) code is incorrect");
				else if (s == 'R')
					throw new Exception("RadixSort code is incorrect");
			}
	}

	private static int getRandom() {
		return rand.nextInt() / 3;
	}

	private static void compareSorting() throws Exception {
		System.out.println("Length, MergeSort, QuickSort (median of 3), QuickSort (randomized), RadixSort");
		double mergeAvg = 0, quickMedianOf3Avg = 0, quickRandomAvg = 0, radixRandomAvg = 0;
		int numExec = 0;
		for (int num = 500000; num <= 50000000; num *= 1.3) {
			int[] array = new int[num];
			int[] temp = new int[num];
			for (int i = 0; i < num; i++)
				array[i] = getRandom();

			double timeStart, timeEnd;

			for (int i = 0; i < num; i++)
				temp[i] = array[i];
			timeStart = System.currentTimeMillis();
			new MergeSort(array, num).mergesort();
			timeEnd = System.currentTimeMillis();
			testIfSorted(array, num, 'M');
			mergeAvg += (timeEnd - timeStart);
			System.out.print(num + ", " + (timeEnd - timeStart));

			for (int i = 0; i < num; i++)
				array[i] = temp[i];
			timeStart = System.currentTimeMillis();
			QuickSort.quicksortMedianOf3(temp, num);
			timeEnd = System.currentTimeMillis();
			testIfSorted(temp, num, '3');
			quickMedianOf3Avg += (timeEnd - timeStart);
			System.out.print(", " + (timeEnd - timeStart));

			for (int i = 0; i < num; i++)
				temp[i] = array[i];
			timeStart = System.currentTimeMillis();
			QuickSort.quicksortRandom(temp, num);
			timeEnd = System.currentTimeMillis();
			testIfSorted(temp, num, 'Q');
			quickRandomAvg += (timeEnd - timeStart);
			System.out.print(", " + (timeEnd - timeStart));

			for (int i = 0; i < num; i++)
				temp[i] = array[i];
			timeStart = System.currentTimeMillis();
			RadixSort.radixSort(temp, num);
			timeEnd = System.currentTimeMillis();
			testIfSorted(temp, num, 'R');
			radixRandomAvg += (timeEnd - timeStart);
			System.out.println(", " + (timeEnd - timeStart));

			numExec++;
		}
		System.out.printf("\nMergeSort average time is: %.2f millisecs%n", mergeAvg / numExec);
		System.out.printf("QuickSort (median of 3) average time is: %.2f millisecs%n", quickMedianOf3Avg / numExec);
		System.out.printf("QuickSort (randomized) average time is: %.2f millisecs%n", quickRandomAvg / numExec);
		System.out.printf("RadixSort average time is: %.2f millisecs%n", radixRandomAvg / numExec);
	}

	private static void compareSelection() throws Exception {
		double momAvg = 0, randomAvg = 0, radixSortedAvg = 0;
		int numExec = 0;
		System.out.println("Length, Selection via RadixSort, Randomized Selection, Median of Medians Selection");
		for (int num = 500000; num <= 50000000; num *= 1.3) {
			int[] array = new int[num];
			int[] radix = new int[num];
			for (int i = 0; i < num; i++)
				radix[i] = array[i] = getRandom();

			double timeStart, timeEnd;
			int K[] = new int[(int) Math.log(num)];
			int lenK = K.length;
			for (int i = 0; i < lenK; i++)
				K[i] = rand.nextInt(num);

			timeStart = System.currentTimeMillis();
			RadixSort.radixSort(radix, num);
			timeEnd = System.currentTimeMillis();
			radixSortedAvg += (timeEnd - timeStart) * lenK;
			System.out.print(num + ", " + (timeEnd - timeStart));

			double selTime = 0, momTime = 0;
			int selArray[] = new int[num];
			for (int i = 0; i < lenK; i++) {
				for (int j = 0; j < num; j++)
					selArray[j] = array[j];
				
				timeStart = System.currentTimeMillis();
				int answer = Selection.select(selArray, num, K[i]);
				timeEnd = System.currentTimeMillis();
				
				selTime += (timeEnd - timeStart);
				if (selArray[answer] != radix[K[i] - 1])
					throw new Exception("Selection code is incorrect");
			}

			for (int i = 0; i < lenK; i++) {
				for (int j = 0; j < num; j++)
					selArray[j] = array[j];

				timeStart = System.currentTimeMillis();
				int answer = MedianOfMedians.select(selArray, num, K[i]);
				timeEnd = System.currentTimeMillis();

				momTime += (timeEnd - timeStart);
				if (selArray[answer] != radix[K[i] - 1])
					throw new Exception("Median of Medians code is incorrect");
			}

			randomAvg += selTime;
			momAvg += momTime;
			numExec += lenK;
			System.out.printf(", %.2f", selTime / lenK);
			System.out.printf(", %.2f%n", momTime / lenK);
		}
		System.out.printf("\nSelection using RadixSort average time is: %.2f millisecs%n", radixSortedAvg / numExec);
		System.out.printf("Selection using random pivot average time is: %.2f millisecs%n", randomAvg / numExec);
		System.out.printf("Selection using Median of Medians average time is: %.2f millisecs%n", momAvg / numExec);
	}

	private static void compareSortingWithDuplicates() throws Exception {
		double mergeAvg = 0, quickRandomAvg = 0;
		int numExec = 0;
		String[] duplicateText = { "Very Few Duplicates", "Few Duplicates", "More Duplicates", "Even More Duplicates","Plenty of Duplicates" };
		for (int duplicate = 10, k = 0; duplicate <= 100000; duplicate *= 10, k++) {
			System.out.println("\n**** " + duplicateText[k] + " ****\n");
			System.out.println("Length,         MergeSort,         Randomized Quick-Sort");
			for (int num = 500000; num <= 50000000; num *= 2) {
				int[] array = new int[num];
				int[] temp = new int[num];
				for (int i = 0; i < num; i++)
					array[i] = rand.nextInt(num / duplicate);

				double timeStart, timeEnd;

				for (int i = 0; i < num; i++)
					temp[i] = array[i];

				timeStart = System.currentTimeMillis();
				new MergeSort(array, num).mergesort();
				timeEnd = System.currentTimeMillis();

				testIfSorted(array, num, 'M');
				mergeAvg += (timeEnd - timeStart);
				System.out.printf("%8d,        %7.2f", num, (timeEnd - timeStart));

				for (int i = 0; i < num; i++)
					temp[i] = array[i];

				timeStart = System.currentTimeMillis();
				QuickSort.quicksortRandom(temp, temp.length);
				timeEnd = System.currentTimeMillis();

				testIfSorted(temp, num, 'Q');
				quickRandomAvg += (timeEnd - timeStart);
				System.out.printf(",             %7.2f\n", (timeEnd - timeStart));

				numExec++;
			}
			System.out.printf("\nMergeSort average time is: %.2f millisecs%n", mergeAvg / numExec);
			System.out.printf("QuickSort (randomized) average time is: %.2f millisecs%n", quickRandomAvg / numExec);
		}
	}

	private static void compareInversion() throws Exception {
		double mergeSortAvg = 0, bruteForceAvg = 0;
		int numExec = 0;
		System.out.println("Length, BruteForce Inversion, MergeSort Inversion");
		for (int num = 10000; num <= 300000; num *= 1.3) {
			int array[] = new int[num];
			for (int i = 0; i < num; i++)
				array[i] = getRandom();

			double timeStart, timeEnd;
			InversionCounting invCount = new InversionCounting(array, num);

			timeStart = System.currentTimeMillis();
			int count = invCount.bruteForce();
			timeEnd = System.currentTimeMillis();
			bruteForceAvg += (timeEnd - timeStart);
			System.out.print(num + ", " + (timeEnd - timeStart));

			timeStart = System.currentTimeMillis();
			if (count != invCount.countInversions())
				throw new Exception("Inversion Counting code is incorrect");
			timeEnd = System.currentTimeMillis();
			mergeSortAvg += (timeEnd - timeStart);
			System.out.println(", " + (timeEnd - timeStart));
			numExec++;
		}
		System.out.printf("\nBruteForce average time is: %.2f millisecs%n", bruteForceAvg / numExec);
		System.out.printf("MergeSort Inversion average time is: %.2f millisecs%n", mergeSortAvg / numExec);
	}

	private static void testClosestPoints() throws Exception {
		Random rand = new Random(System.currentTimeMillis());
		for (int numPoints : LARGE_POINT_SIZES) {

			Point[] points = new Point[numPoints];
			for (int j = 0; j < numPoints; j++)
				points[j] = new Point(rand.nextInt(), rand.nextInt());

			long startTime = System.currentTimeMillis();
			Point[] bruteForce = ClosestPairOfPoints.bruteForce(points);
			long timeBruteForce = System.currentTimeMillis() - startTime;

			startTime = System.currentTimeMillis();
			Point[] nlogn = ClosestPairOfPoints.findClosestPair(points);
			long timenlogn = System.currentTimeMillis() - startTime;

			double distBruteForce = bruteForce[0].distance(bruteForce[1]);
			double distnlogn = nlogn[0].distance(nlogn[1]);
			if (distBruteForce == distnlogn) {
				System.out.println("Time to find closest pair among " + numPoints
						+ " points using brute-force strategy = " + timeBruteForce + " (may vary with each execution)");
				System.out.println("Time to find closest pair among " + numPoints
						+ " points using divide & conquer strategy = " + timenlogn + " (may vary with each execution)");
				System.out.println();
			} else
				throw new Exception("Something is wrong!");
		}
	}

	public static void main(String args[]) throws Exception {
		rand = new Random(System.currentTimeMillis());
		System.out.println("*** Time Test Sorting ***\n");
		compareSorting();
		compareSortingWithDuplicates();
		System.out.println("\n*** Time Test Selection ***\n");
		compareSelection();
		System.out.println("\n*** Time Test Inversion ***\n");
		compareInversion();
		System.out.println("\n****************** Time Test Closest Pair of Points ******************\n");
		testClosestPoints();
	}
}
