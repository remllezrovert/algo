package wk12;
import java.util.ArrayList;
import java.util.Collections;

public class LIS {

	public static ArrayList<Integer> longestIncreasingSubsequence(int[] arr, int len) { // complete this method
		int[] length = new int[len];
		int[] pred = new int[len];
		for (int i = 0; i < len; i++){
		length[i] = 1;
		pred[i] = -1;
		}
		for (int i = 0; i < len; i++){
			for (int j = 0; j < i; j++){
				if (arr[j] < arr[i] && length[j] + 1 > length[i]){
					length[i] = length[j] + 1;
					pred[i] = j;
				}
		}

		}
		
		int lisIndex  = 0;
		//this line gets the index of the max
		for (int i = 1; i < len; i++) if (length[i] > length[lisIndex]) lisIndex = i;
		ArrayList<Integer> list = new ArrayList<Integer>();
		while (lisIndex >= 0){
			list.add(arr[lisIndex]);
			lisIndex = pred[lisIndex];
		}
		reverse(list);
		return list;

	}

	private static void reverse(ArrayList<Integer> list) {
		for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
			int temp = list.get(j);
			list.set(j, list.get(i));
			list.set(i, temp);
		}
	}
}
