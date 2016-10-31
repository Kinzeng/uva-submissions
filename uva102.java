import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class uva102 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner console = new Scanner(System.in);
		while (console.hasNext()) {
			int[][] bins = new int[3][3];
			int total = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					bins[i][j] = console.nextInt();
					total += bins[i][j];
				}
			}
			int[] perm = {0, 1, 2};

			int min = Integer.MAX_VALUE;
			String minPerm = "";
			do {
				int numMoves = total;
				for (int i = 0; i < 3; i++) {
					numMoves -= bins[i][perm[i]];
				}
				
				if (numMoves < min) {
					min = numMoves;
					minPerm = "";
					for (int i = 0; i < 3; i++) {
						String bin = "";
						switch (perm[i]) {
							case 0: bin = "B"; break;
							case 1: bin = "G"; break;
							case 2: bin = "C"; break;
						}
						
						minPerm += bin;
					}
				} else if (numMoves == min) {
					String temp = "";
					for (int i = 0; i < 3; i++) {
						String bin = "";
						switch (perm[i]) {
							case 0: bin = "B"; break;
							case 1: bin = "G"; break;
							case 2: bin = "C"; break;
						}
						
						temp += bin;
					}
					
					if (temp.compareTo(minPerm) < 0) {
						minPerm = temp;
					}
				}
			} while (nextPermutation(perm));
			
			System.out.println(minPerm + " " + min);
		}
		console.close();
	}
	
	// algorithm found at https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
	public static boolean nextPermutation(int[] array) {
	    // Find longest non-increasing suffix
	    int i = array.length - 1;
	    while (i > 0 && array[i - 1] >= array[i])
	        i--;
	    // Now i is the head index of the suffix
	    
	    // Are we at the last permutation already?
	    if (i <= 0)
	        return false;
	    
	    // Let array[i - 1] be the pivot
	    // Find rightmost element that exceeds the pivot
	    int j = array.length - 1;
	    while (array[j] <= array[i - 1])
	        j--;
	    // Now the value array[j] will become the new pivot
	    // Assertion: j >= i
	    
	    // Swap the pivot with j
	    int temp = array[i - 1];
	    array[i - 1] = array[j];
	    array[j] = temp;
	    
	    // Reverse the suffix
	    j = array.length - 1;
	    while (i < j) {
	        temp = array[i];
	        array[i] = array[j];
	        array[j] = temp;
	        i++;
	        j--;
	    }
	    
	    // Successfully computed the next permutation
	    return true;
	}
}
