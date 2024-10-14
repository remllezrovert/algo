package wk5;

import java.util.Arrays;
import java.util.Collections;

public class RadixSort {

	private static void countSortOnDigits(int A[], int n, int digits[]) { 
		int[] C = new int[n];
		Arrays.fill(C,0);

		int[] T = new int[n];
		Arrays.fill(T,0);

		for (int i = 0; i < n; i++){
			C[digits[i]]++;
		}

		for (int i = 1; i < n; i++){
			C[i] = C[i - 1] + C[i];
		}

		for (int i = n - 1; i >= 0; i--){
			C[digits[i]]--;
			T[C[digits[i]]] = A[i];
		}

		//A = Arrays.copyOf(T,T.length);

		for (int i = 0; i < n; i++){
			A[i] = T[i];
		}


	

	}

	private static void radixSortNonNeg(int A[], int n) { 
		int M = Arrays.stream(A).max().orElseThrow();
		int[] digits = new int[n];
		int e = 1;
		while (M / e > 0){
			for (int i = 0; i < n; i++){
				digits[i] = (A[i] / e) % n;
			}
			countSortOnDigits(A, n, digits);
			e = e * n;
		}



	}

	public static void radixSort(int[] array, int n) { // complete this function

      	int posCount = 0;
        int negCount = 0;
		//count negatives 
        for (int num : array) {
            if (num > 0) {
                posCount++;
            } else if (num < 0) {
                negCount++;
            }
        }

        int[] pos= new int[posCount];
        int[] neg= new int[negCount];

        posCount = 0; 
        negCount = 0; 

//seperate negatives from positives
        for (int num : array) {
            if (num > 0) {
                pos[posCount++] = num;
            } else if (num < 0) {
                neg[negCount++] = num*-1;
            }
        }
			radixSortNonNeg(pos,posCount);
			radixSortNonNeg(neg,negCount);

        for (int i = negCount - 1; i >= 0; i--) {
            array[negCount - 1 - i] = neg[i]*-1;
        }

        for (int j = 0; j < posCount; j++) {
            array[negCount + j] = pos[j];
        }

	}

}







