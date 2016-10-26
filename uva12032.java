import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		
		int T = console.nextInt();
		int caseNum = 1;
		while (T-- > 0) {
			int n = console.nextInt();
			int[] rungs = new int[n + 1];
			rungs[0] = 0;
			for (int i = 1; i <= n; i++) {
				rungs[i] = console.nextInt();
			}
			
			int lo = 1;
			int hi = 10000000;
			int oldMid = 1;
			while (lo < hi) {
				int mid = (lo + hi) / 2;
				if (mid == oldMid) break;
				int k = mid;
				boolean success = true;
				for (int i = 0; i < n; i++) {
					if (rungs[i + 1] - rungs[i] > k) {
						success = false;
						break;
					} else if (rungs[i + 1] - rungs[i] == k) {
						k--;
					}
				}
				
				if (success) {
					hi = mid;
					oldMid = mid;
				} else {
					lo = mid;
					oldMid = mid;
				}
			}

			System.out.format("Case %d: %d\n", caseNum++, hi);
		}
		
		console.close();
	}
}
