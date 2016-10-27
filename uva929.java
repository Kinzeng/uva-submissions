import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;

class uva929 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	private static class Node implements Comparable<Node> {
		int distance;
		int i;
		int j;
		
		public Node(int distance, int i, int j) {
			this.distance = distance;
			this.i = i;
			this.j = j;
		}
		
		public int compareTo(Node other) {
			return this.distance - other.distance;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner console = new Scanner(System.in);
		int T = console.nextInt();
		while (T-- > 0) {
			int n = console.nextInt();
			int m = console.nextInt();
			
			int[][] graph = new int[n][m];
			int[][] distance = new int[n][m];
			boolean[][] marked = new boolean[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					graph[i][j] = console.nextInt();
					distance[i][j] = Integer.MAX_VALUE;
				}
			}
			distance[0][0] = graph[0][0];
			
			PriorityQueue<Node> queue = new PriorityQueue<Node>();
			queue.add(new Node(distance[0][0], 0, 0));
			while (!queue.isEmpty()) {
				Node current = queue.poll();
				marked[current.i][current.j] = true;
				if (current.i == n - 1 && current.j == m - 1) {
					System.out.println(current.distance);
					break;
				}
				
				for (int i = 0; i < 4; i++) {
					int newX = current.i + dx[i];
					int newY = current.j + dy[i];
					if (inside(n, m, newX, newY) && !marked[newX][newY]) {
						int newDistance = current.distance + graph[newX][newY];
						if (newDistance < distance[newX][newY]) {
							distance[newX][newY] = newDistance;
							queue.add(new Node(newDistance, newX, newY));
						}
					}
				}
			}
		}
		console.close();
	}
	
	public static boolean inside(int N, int M, int i, int j) {
		return i >= 0 && i < N && j >= 0 && j < M;
	}
}
