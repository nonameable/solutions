import java.util.ArrayList;
import java.util.Arrays;

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



	public static void main(String[] args) {

		int[] list = {1,7,5,9,2,12,3};

		KDifferencePairs kdp = new KDifferencePairs();
		ArrayList<Pair> pairs = kdp.getKDifferencePairs(list, 6);
		ArrayList<Pair> pairsOptimizedv1 = kdp.getKDifferencePairsOptimizedv1(list, 6);

		for (int i = 0; i < pairs.size() ;i++ ) {
			System.out.println(pairs.get(i).toString());
		}

		System.out.println("---------- optimized v1-------------");
 
		for (int i = 0; i < pairsOptimizedv1.size() ;i++ ) {
			System.out.println(pairsOptimizedv1.get(i).toString());
		}
	}
}