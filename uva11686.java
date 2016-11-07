import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class uva11686 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner console = new Scanner(System.in);
		int n = console.nextInt();
		int m = console.nextInt();
		while (n != 0 || m != 0) {
			ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
			
			for (int i = 0; i < n; i++) {
				graph.add(new ArrayList<Integer>());
			}
			
			for (int i = 0; i < m; i++) {
				int num1 = console.nextInt() - 1;
				int num2 = console.nextInt() - 1;
				
				graph.get(num1).add(num2);
			}
						
			Stack<Integer> topSort = new Stack<Integer>();
			int[] marked = new int[n];
			
			boolean hasCycle = false;
			for (int i = 0; i < n; i++) {
				if (marked[i] == 0) {
					if (!topSort(i, graph, marked, topSort)) {
						System.out.println("IMPOSSIBLE");
						hasCycle = true;
						break;
					}
				}
			}
			
			if (!hasCycle) {
				while (!topSort.isEmpty()) {
					System.out.println(topSort.pop() + 1);
				}
			}
			
			n = console.nextInt();
			m = console.nextInt();
		}
		console.close();
	}
	
	public static boolean topSort(int cur, ArrayList<ArrayList<Integer>> graph, int[] marked, Stack<Integer> topSort) {
		marked[cur] = 1;
		for (int i = 0; i < graph.get(cur).size(); i++) {
			int next = graph.get(cur).get(i);
			if (marked[next] == 0) {
				if (!topSort(next, graph, marked, topSort)) {
					return false;
				}
			} else if (marked[next] == 1) {
				return false;
			}
		}
	
		marked[cur] = 2;
		topSort.push(cur);
		return true;
	}
}
