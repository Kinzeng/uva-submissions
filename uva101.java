import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class uva101 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner console = new Scanner(System.in);
		int n = console.nextInt();
		ArrayList<ArrayDeque<Integer>> blocks = new ArrayList<ArrayDeque<Integer>>();
		int[] positions = new int[n];
		for (int i = 0; i < n; i++) {
			blocks.add(new ArrayDeque<Integer>());
			blocks.get(i).add(i);
			positions[i] = i;
		}
		
		while (console.hasNext()) {
			String command1 = console.next();
			if (command1.equals("quit")) break;
			int a = console.nextInt();
			String command2 = console.next();
			int b = console.nextInt();
			
			if (positions[a] == positions[b]) continue;
			
			if (command1.equals("move")) {
				int cur;
				while ((cur = blocks.get(positions[a]).peekLast()) != a) {
					blocks.get(cur).addLast(blocks.get(positions[a]).pollLast());
					positions[cur] = cur;
				}
				
				if (command2.equals("onto")) {
					while ((cur = blocks.get(positions[b]).peekLast()) != b) {
						blocks.get(cur).addLast(blocks.get(positions[b]).pollLast());
						positions[cur] = cur;
					}
				}
				
				blocks.get(positions[b]).addLast(blocks.get(positions[a]).pollLast());
				positions[a] = positions[b];
			} else if (command1.equals("pile")) {
				int cur;
				if (command2.equals("onto")) {
					while ((cur = blocks.get(positions[b]).peekLast()) != b) {
						blocks.get(cur).addLast(blocks.get(positions[b]).pollLast());
						positions[cur] = cur;
					}
				}

				Stack<Integer> temp = new Stack<Integer>();
				while ((cur = blocks.get(positions[a]).peekLast()) != a) {
					temp.push(blocks.get(positions[a]).pollLast());
					positions[cur] = positions[b];
				}
				temp.push(blocks.get(positions[a]).pollLast());
				positions[cur] = positions[b];
				
				while (!temp.isEmpty()) {
					blocks.get(positions[b]).addLast(temp.pop());
				}
			} else {
				break;
			}
		}
		
		for (int i = 0; i < blocks.size(); i++) {
			System.out.print(i + ":");
			ArrayDeque<Integer> section = blocks.get(i);
			while (!section.isEmpty()) {
				System.out.print(" " + section.pollFirst());
			}
			System.out.println();
		}

		console.close();
	}
}
