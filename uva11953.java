import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class uva11953 {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner console = new Scanner(System.in);
		int T = console.nextInt();
		int caseNum = 1;
		while (T-- > 0) {
			int n = console.nextInt();
			console.nextLine();
			char[][] graph = new char[n][n];
			boolean[][] marked = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				String line = console.nextLine();
				for (int j = 0; j < n; j++) {
					graph[i][j] = line.charAt(j);
					if (graph[i][j] == '.') {
						marked[i][j] = true;
					}
				}
			}
			
			int count = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!marked[i][j] && graph[i][j] == 'x') {
						dfs(i, j, graph, marked);
						count++;
					}
				}
			}
			
			System.out.printf("Case %d: %d\n", caseNum++, count);
		}
		console.close();
	}
	
	public static void dfs(int i, int j, char[][] graph, boolean[][] marked) {
		marked[i][j] = true;
		
		for (int k = 0; k < 4; k++) {
			int nextI = i + dx[k];
			int nextJ = j + dy[k];
			
			if (inside(nextI, nextJ, graph.length) && !marked[nextI][nextJ]) {
				dfs(nextI, nextJ, graph, marked);
			}
		}
	}
	
	public static boolean inside(int i, int j, int n) {
		return i >= 0 && i < n && j >= 0 && j < n;
	}
}
