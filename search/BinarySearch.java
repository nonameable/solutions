import java.util.Arrays;

public class BinarySearch {
	

	// uses binary search to find an element in an array. If the element is found, it returns the position of the element. Otherwise, it returns -1;
	// asumes the array is already sorted
	public int binarySearchIterative(int[] array, int n) {
		int index = -1;
		// should consider empty list, list with just 1, 2 elements
		// should consider odd list, even list
		// should consider list with non-disctict integers
		int start = 0;
		int end = array.length - 1;
		boolean found = false;
		while( !(end < start) && !(start > end) && !found) {
			int mid = (start + end) / 2;
			if( n == array[mid]){
				index = mid;
				found = true;
			}
			else if (n < array[mid]) {
				end = mid - 1;
			}
			else {
				start = mid + 1;
			}
		}

		return index;
	}

	public int BinarySearchRecursive(int[] array, int n) {
		return -1;

	}


	public static void main(String[] args) {
		BinarySearch bs = new BinarySearch ();

		System.out.println("Testing normal array with disctint numbers");
		int[] array = {18, 2, 3, 16, 8, 1, 12, 21, 7, 13, 67};
		Arrays.sort(array);
		int index = bs.binarySearchIterative(array, 3);
		System.out.println("Index is: " + index);

		System.out.println("Testing normal array with non-disctict numbers");
		int[] array2 = {18, 2, 3, 16, 18, 1, 2, 21, 7, 3, 67};
		Arrays.sort(array2);
		int index2 = bs.binarySearchIterative(array2, 18);
		System.out.println("Index is: " + index2);

		System.out.println("Testing 1 element array");
		int[] array3 = {18};
		Arrays.sort(array3);
		int index3 = bs.binarySearchIterative(array3, 18);
		System.out.println("Index is: " + index3);

		System.out.println("Testing 1 element array for non-existent value");
		int[] array4 = {18};
		Arrays.sort(array4);
		int index4 = bs.binarySearchIterative(array4, 1);
		System.out.println("Index is: " + index4);
		
	}
}