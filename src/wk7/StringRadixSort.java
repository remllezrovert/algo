package wk7;

public class StringRadixSort {
	
	public static void radixSort(String[] strings, int n) {
		int max = 0;
		for (int i = 0; i < n; i++)
			if (strings[i].length() > max)
				max = strings[i].length();
		int[] digits = new int[n];
		for (int e = max - 1; e>= 0; e--) {
			for (int i = 0; i < n; i++)
				digits[i] = strings[i].length() <= e ? 0 : strings[i].charAt(e) - 96;
			countSortOnLowerCaseCharacters(strings, n, digits);
		}
	}

	private static void countSortOnLowerCaseCharacters(String[] strings, int n, int[] digits) {
		String T[] = new String[n];
		int C[] = new int[27];
		for (int i = 0; i < n; i++)
			C[digits[i]]++;
		for (int i = 1; i < 27; i++)
			C[i] += C[i - 1];
		for (int i = n - 1; i >= 0; i--) {
			C[digits[i]]--;
			T[C[digits[i]]] = strings[i];
		}
		for (int i = 0; i < n; i++)
			strings[i] = T[i];
	}
}
