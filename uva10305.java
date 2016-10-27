import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class uva10305 {
	public static void dfs(ArrayList<ArrayList<Integer>> graph, int current, Stack<Integer> topSort, boolean[] marked) {
		marked[current] = true;
		
		for (int next : graph.get(current)) {
			if (!marked[next]) {
				dfs(graph, next, topSort, marked);
			}
		}
		
		topSort.push(current + 1);
	}
	
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
			boolean[] marked = new boolean[n];
			for (int i = 0; i < n; i++) {
				if (!marked[i]) {
					dfs(graph, i, topSort, marked);
				}
			}
			
			String print = "";
			while (!topSort.isEmpty()) {
				print += topSort.pop() + " ";
			}
			print = print.substring(0, print.length() - 1);
			System.out.println(print);
			n = console.nextInt();
			m = console.nextInt();
		}
		console.close();
	}
}
