import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class uva10912 {
	static int[][][] dp;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner console = new Scanner(System.in);
		int caseNum = 1;
		int L = console.nextInt();
		int S = console.nextInt();
		dp = new int[26][26][10000];
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 26; j++) {
				for (int k = 0; k < 10000; k++) {
					dp[i][j][k] = -1;
				}
			}
		}

		while (L != 0 || S != 0) {
			System.out.printf("Case %d: %d\n", caseNum++, count(L, S, -1));
			L = console.nextInt();
			S = console.nextInt();
		}

		console.close();
	}
	
	public static int count(int L, int S, int lastChar) {
		if (L > 26 || L < 0 || S < 0) return 0;
		if (L == 0 && S == 0) return 1;
		if (lastChar >= 0 && dp[L][lastChar][S] != -1) return dp[L][lastChar][S];
		
		int count = 0;
		for (int i = lastChar + 1; i < 26; i++) {
			count += count(L - 1, S - (i + 1), i);
		}
		
		if (lastChar >= 0) dp[L][lastChar][S] = count;
		return count;
	}
}
