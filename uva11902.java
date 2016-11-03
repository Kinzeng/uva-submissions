import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class uva11902 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner console = new Scanner(System.in);
		int T = console.nextInt();
		int caseNum = 1;
		while (T-- > 0) {
			int n = console.nextInt();
			boolean[][] graph = new boolean[n][n];
			boolean[][] dominates = new boolean[n][n];
			boolean[] firstVisited = new boolean[n];
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					graph[i][j] = console.nextInt() == 1;
				}
			}
			
			dfs(0, graph, firstVisited, -1);
			
			for (int i = 0; i < n; i++) {
				boolean[] visited = new boolean[n];
				dfs(0, graph, visited, i);
				for (int j = 0; j < n; j++) {
					dominates[i][j] = firstVisited[j] && !visited[j];
				}
			}
			String separator = "+-";
			for (int i = 1; i < n; i++) {
				separator += "--";
			}
			separator += "+\n";

			String print = separator;
			for (int i = 0; i < n; i++) {
				String line = "|";
				for (int j = 0; j < n; j++) {
					line += dominates[i][j] ? "Y|" : "N|";
				}
				print += line + "\n" + separator;
			}
			System.out.print("Case " + caseNum++ + ":\n" + print);
		}
		console.close();
	}
	
	public static void dfs(int u, boolean[][] graph, boolean[] visited, int without) {
		if (u == without) return;
		visited[u] = true;
		
		for (int i = 0; i < graph.length; i++) {
			if (i != without && !visited[i] && graph[u][i]) {
				dfs(i, graph, visited, without);
			}
		}
	}
}
