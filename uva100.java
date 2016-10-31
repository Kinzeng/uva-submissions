import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class uva100 {
	static int[] dp;
	static final int MAXN = 1000000;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner console = new Scanner(System.in);
		dp = new int[MAXN];
		
		int count = 1;
		for (int i = 1; i < MAXN; i *= 2) {
			dp[i] = count;
			count++;
		}
		
		while (console.hasNext()) {
			int i = console.nextInt();
			int j = console.nextInt();
			int max = Integer.MIN_VALUE;
			for (int k = Math.min(i, j); k <= Math.max(i, j); k++) {
				max = Math.max(count(k), max);
			}
			System.out.println(i + " " + j + " " + max);
		}

		console.close();
	}
	
	public static int count(int num) {
		if (num < MAXN && dp[num] != 0) return dp[num];
		
		int count = 1;
		count += num % 2 == 1 ? count(3 * num + 1) : count(num / 2);
		if (num < MAXN) dp[num] = count;
		
		return count;
	}
}
