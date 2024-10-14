package wk7;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TestCorrectness {

	private static String intervalFolder = "";

	private static void testStringSorter() throws NumberFormatException, IOException {
		System.out.println("\n****************** Sorting Strings ******************\n");
		String[] strings = { "abc", "xyzw", "xyzab", "abcdx", "wxcdx", "abcxy", "aac", "wxcdx", "abcx", "abc" };
		System.out.println("Original order of strings: " + Arrays.toString(strings));
		StringRadixSort.radixSort(strings, strings.length);
		System.out.println("Radix-sorted order of strings: " + Arrays.toString(strings));
	}

	private static int log2(int n) {
		if (n <= 2)
			return 1;
		int x = 1;
		int count = 0;
		while (x < n) {
			x = x * 2;
			count++;
		}
		return count;
	}

	static void printStatistics(HuffmanEncoder huffObj, int sigma, int[] frequencies, char[] alphabet) {
		int msgLength = 0;
		for (int i = 0; i < sigma; i++)
			msgLength += frequencies[i];
		System.out.println("ASCII encoding needs " + msgLength * 8 + " bits.");
		System.out.println(
				"Fixed length encoding needs " + (msgLength * log2(sigma) + sigma * (8 + log2(sigma))) + " bits.");
		System.out
				.println("Huffman encoding needs " + (huffObj.getTableSize() + huffObj.getEncodingLength()) + " bits.");
		System.out.print("Huffman Encoding: ");
		char c;
		for (int i = 0; i < sigma - 1; i++) {
			c = alphabet[i];
			System.out.print(c + "(" + huffObj.getEncoding(c) + "); ");
		}
		c = alphabet[sigma - 1];
		System.out.print(c + "(" + huffObj.getEncoding(c) + ")");
	}

	private static HuffmanEncoder testHuffmanEncoderHelper(int n, int sigma, double[] probabilities) {
		int freq[] = new int[sigma];
		char alphabet[] = new char[sigma];
		HashMap<Character, Integer> freqMap = new HashMap<>();
		for (int i = 0; i < sigma; i++) {
			freq[i] = (int) (n * probabilities[i]);
			alphabet[i] = (char) (i + 65);
			freqMap.put(alphabet[i], freq[i]);
		}
		System.out.println("alphabet: " + Arrays.toString(alphabet));
		System.out.println("frequencies: " + Arrays.toString(freq));
		System.out.println();
		HuffmanEncoder huffObj = new HuffmanEncoder(freqMap);
		huffObj.encode();
		printStatistics(huffObj, sigma, freq, alphabet);
		System.out.println("\n");
		return huffObj;
	}

	private static void testHuffmanEncoder() {

		System.out.println("\n****************** Huffman Encoding ******************\n");
		int sigma = 8;
		int n = 3280;
		double[] probabilities = { 0.06, 0.2, 0.15, 0.3, 0.05, 0.02, 0.08, 0.14 };
		testHuffmanEncoderHelper(n, sigma, probabilities);

		sigma = 6;
		n = 2000;
		probabilities = new double[] { 0.04, 0.07, 0.14, 0.2, 0.24, 0.31 };
		testHuffmanEncoderHelper(n, sigma, probabilities);

		sigma = 8;
		n = 200000;
		probabilities = new double[] { 0.04, 0.07, 0.12, 0.2, 0.18, 0.26, 0.11, 0.02 };
		testHuffmanEncoderHelper(n, sigma, probabilities);

		sigma = 8;
		n = 200000;
		probabilities = new double[] { 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125 };
		testHuffmanEncoderHelper(n, sigma, probabilities);
	}

	private static void testHuffmanDecoder() {
		System.out.println("****************** Huffman Decoding ******************\n");
		String msg = "BCCABBDDAECCBBAEDDCC";
		char alphabet[] = { 'A', 'B', 'C', 'D', 'E' };
		System.out.println("Original Message: " + msg + "\n");
		double probabilities[] = { 0.15, 0.25, 0.3, 0.2, 0.1 };
		int sigma = alphabet.length;
		HuffmanEncoder huffObj = testHuffmanEncoderHelper(20, sigma, probabilities);
		String encodedMsg = "";
		for (int i = 0; i < msg.length(); i++)
			encodedMsg += huffObj.getEncoding(msg.charAt(i));
		System.out.println("Encoded Message: " + encodedMsg);
		Hashtable<String, Character> encodingToChar = new Hashtable<String, Character>();
		for (int i = 0; i < sigma; i++)
			encodingToChar.put(huffObj.getEncoding(alphabet[i]), alphabet[i]);
		String decodedMsg = HuffmanDecoder.decode(encodedMsg, encodingToChar);
		System.out.println("Decoded Message: " + decodedMsg);
	}

	private static ArrayList<Interval> readIntervals(String fileName) throws FileNotFoundException {
		Scanner scan = new Scanner(new FileReader(fileName));
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		while (scan.hasNext()) {
			char name = scan.next().charAt(0);
			int start = scan.nextInt();
			int end = scan.nextInt();
			intervals.add(new Interval(name, start, end));
		}
		scan.close();
		return intervals;
	}

	private static void testIntervalScheduling() throws FileNotFoundException {
		System.out.println("\n****************** Interval Scheduling ******************\n");
		ArrayList<Interval> intervals = readIntervals(intervalFolder + "intervalScheduling1.txt");
		System.out.println("The intervals are: " + intervals);
		System.out.println("Selected intervals are: " + GreedyIntervals.schedule(intervals));

		System.out.println();
		intervals = readIntervals(intervalFolder + "intervalScheduling2.txt");
		System.out.println("The intervals are: " + intervals);
		System.out.println("Selected intervals are: " + GreedyIntervals.schedule(intervals));

		System.out.println();
		intervals = readIntervals(intervalFolder + "intervalScheduling3.txt");
		System.out.println("The intervals are: " + intervals);
		System.out.println("Selected intervals are: " + GreedyIntervals.schedule(intervals));
	}

	private static void testIntervalColoring() throws FileNotFoundException {
		System.out.println("\n****************** Interval Coloring ******************\n");
		ArrayList<Interval> intervals = readIntervals(intervalFolder + "intervalColoring1.txt");
		System.out.println("The intervals are: " + intervals);
		System.out.println("Number of colors needed to paint the interval with no overlapping colors: "
				+ GreedyIntervals.color(intervals));

		System.out.println();
		intervals = readIntervals(intervalFolder + "intervalColoring2.txt");
		System.out.println("The intervals are: " + intervals);
		System.out.println("Number of colors needed to paint the interval with no overlapping colors: "
				+ GreedyIntervals.color(intervals));

		System.out.println();
		intervals = readIntervals(intervalFolder + "intervalColoring3.txt");
		System.out.println("The intervals are: " + intervals);
		System.out.println("Number of colors needed to paint the interval with no overlapping colors: "
				+ GreedyIntervals.color(intervals));
	}

	private static void testHeavyHitters() {
		System.out.println("\n****************** k-Heavy Hitters ******************");
		String msg = "5345135394923830939";
		for (int k = 2; k <= 10; k++) {
			ArrayList<Character> naive = HeavyHitters.naive(msg, k);
			Collections.sort(naive);
			ArrayList<Character> onePass = HeavyHitters.misraGriesOnePass(msg, k);
			Collections.sort(onePass);
			ArrayList<Character> twoPass = HeavyHitters.misraGriesTwoPass(msg, k);
			Collections.sort(twoPass);
			System.out.println("\n" + k + "-heavy hitters: " + naive);
			System.out.println("One-pass Misra Gries: " + onePass);
			System.out.println("Two-pass Misra Gries: " + twoPass);
		}
	}

	public static void main(String args[]) throws Exception {
		testStringSorter();
		testHuffmanEncoder();
		testHuffmanDecoder();
		testIntervalScheduling();
		testIntervalColoring();
		testHeavyHitters();
	}
}
