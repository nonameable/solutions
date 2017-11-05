import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class KDifferencePairs {

	public ArrayList<Pair> getKDifferencePairs (int[] list, int k) {

		ArrayList<Pair> pairs = new ArrayList<Pair>();
		Arrays.sort(list);
		int c = 0;

		for (int i = 0; i < list.length ; i++ ) {
			boolean termination = false;
			for(int j = i + 1; j < list.length && !termination; j++){
				int delta = Math.abs(list[j] - list[i]);
				c++;
				if(k == delta) {
					Pair newPair = new Pair(list[i], list[j]);
					pairs.add(newPair);
					termination = true;
				}
				else if (k < delta){
					termination = true;
				}
			}
		}
		System.out.println("cuenta: " + c);

		return pairs;

	}

	// keep track of the last element of the array to stop traversing even more times
	public ArrayList<Pair> getKDifferencePairsOptimizedv1 (int[] list, int k) {

		ArrayList<Pair> pairs = new ArrayList<Pair>();
		Arrays.sort(list);
		int c = 0;
		int last = list[list.length - 1];

		boolean impossibleDelta = false;
		for (int i = 0; i < list.length && !impossibleDelta; i++ ) {
			boolean termination = false;
			if( k > Math.abs(last - list[i])) {
				impossibleDelta = true;
				continue;
			}
			for(int j = i + 1; j < list.length && !termination; j++){
				int delta = Math.abs(list[j] - list[i]);
				c++;
				if(k == delta) {
					Pair newPair = new Pair(list[i], list[j]);
					pairs.add(newPair);
					termination = true;
				}
				else if (k < delta){
					termination = true;
				}
			}
		}
		System.out.println("cuenta optimized v1: " + c);

		return pairs;

	}


	// uses binary search to find the pairs. IT STILL DOES NOT CHECK FOR IDENTIC PAIRS.
	public ArrayList<Pair> getKDifferencePairsOptimizedv2 (int[] list, int k) {
		ArrayList<Pair> pairs = new ArrayList<Pair>();
		Arrays.sort(list);

		for(int i = 0; i < list.length; i++){
			int indexLess = binarySearchIterative(list, list[i] - k);
			if(indexLess >= 0){
				Pair newPair = new Pair(list[i], list[indexLess]);
					pairs.add(newPair);
			}

			int indexMore = binarySearchIterative(list, list[i] + k);
			if(indexMore >= 0){
				Pair newPair = new Pair(list[i], list[indexMore]);
					pairs.add(newPair);
			}
		}

		return pairs;
	}

	// uses a hast table to search for pairs. AGAIN, IT DOES NOT CHECK FOR DUPLICATES
	public ArrayList<Pair> getKDifferencePairsOptimizedv3 (int[] list, int k) {
		ArrayList<Pair> pairs = new ArrayList<Pair>();
		Hashtable<Integer, Integer> ht = new Hashtable<Integer, Integer>();


		for(int i = 0; i < list.length; i++){
			ht.put(list[i], list[i]);
		}

		for(int i = 0; i < list.length; i++){
			Integer less = ht.get(list[i] - k);
			if(less != null) {
				Pair newPair = new Pair(list[i], less);
					pairs.add(newPair);
			}

			Integer more = ht.get(list[i] + k);
			if(more != null) {
				Pair newPair = new Pair(list[i], more);
					pairs.add(newPair);
			}
		}



		return pairs;
	}


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





	public static void main(String[] args) {

		int[] list = {1,7,5,9,2,12,3};

		KDifferencePairs kdp = new KDifferencePairs();
		ArrayList<Pair> pairs = kdp.getKDifferencePairs(list, 6);
		ArrayList<Pair> pairsOptimizedv1 = kdp.getKDifferencePairsOptimizedv1(list, 6);
		ArrayList<Pair> pairsOptimizedv2 = kdp.getKDifferencePairsOptimizedv2(list, 6);
		ArrayList<Pair> pairsOptimizedv3 = kdp.getKDifferencePairsOptimizedv3(list, 6);

		for (int i = 0; i < pairs.size() ;i++ ) {
			System.out.println(pairs.get(i).toString());
		}

		System.out.println("---------- optimized v1 -------------");
 
		for (int i = 0; i < pairsOptimizedv1.size() ;i++ ) {
			System.out.println(pairsOptimizedv1.get(i).toString());
		}

		System.out.println("---------- optimized Binary Search -------------");

		for (int i = 0; i < pairsOptimizedv2.size() ;i++ ) {
			System.out.println(pairsOptimizedv2.get(i).toString());
		}

		System.out.println("---------- optimized Hash Tables -------------");
		for (int i = 0; i < pairsOptimizedv3.size() ;i++ ) {
			System.out.println(pairsOptimizedv3.get(i).toString());
		}
	}
}