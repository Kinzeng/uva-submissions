import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class uva753 {
	static int V = 403;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner console = new Scanner(System.in);
		int T = console.nextInt();
		while (T-- > 0) {
			int[][] graph = new int[V][V];
			int source = 0;
			int target = 1;
			
			HashMap<String, Integer> indices = new HashMap<String, Integer>();

			int n = console.nextInt();
			int index = 2;
			for (int i = 0; i < n; i++) {
				String plug = console.next();
				if (!indices.containsKey(plug)) {
					indices.put(plug, index++);
				}
				
				graph[indices.get(plug)][target]++;
			}
			
			int m = console.nextInt();
			for (int i = 0; i < m; i++) {
				console.next();
				String plug = console.next();
				if (!indices.containsKey(plug)) {
					indices.put(plug, index++);
				}
				
				graph[source][indices.get(plug)]++;
			}
			
			int k = console.nextInt();
			for (int i = 0; i < k; i++) {
				String plug1 = console.next();
				String plug2 = console.next();
				if (!indices.containsKey(plug1)) {
					indices.put(plug1, index++);
				}
				if (!indices.containsKey(plug2)) {
					indices.put(plug2, index++);
				}
				
				graph[indices.get(plug1)][indices.get(plug2)] = 100000;
			}
			
			System.out.println((m - maxflow(graph, source, target)));
			if (T > 0) {
				System.out.println();
			}
		}
		
		console.close();
	}
	
	// code below taken from http://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
	static boolean bfs(int rGraph[][], int s, int t, int parent[])
    {
        // Create a visited array and mark all vertices as not
        // visited
        boolean visited[] = new boolean[V];
        for(int i=0; i<V; ++i)
            visited[i]=false;
 
        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s]=-1;
 
        // Standard BFS Loop
        while (queue.size()!=0)
        {
            int u = queue.poll();
 
            for (int v=0; v<V; v++)
            {
                if (visited[v]==false && rGraph[u][v] > 0)
                {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
 
        // If we reached sink in BFS starting from source, then
        // return true, else false
        return (visited[t] == true);
    }
 
    // Returns tne maximum flow from s to t in the given graph
    static int maxflow(int graph[][], int s, int t)
    {
        int u, v;
 
        // Create a residual graph and fill the residual graph
        // with given capacities in the original graph as
        // residual capacities in residual graph
 
        // Residual graph where rGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If rGraph[i][j] is 0, then there is
        // not)
        int rGraph[][] = new int[V][V];
 
        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];
 
        // This array is filled by BFS and to store path
        int parent[] = new int[V];
 
        int max_flow = 0;  // There is no flow initially
 
        // Augment the flow while tere is path from source
        // to sink
        while (bfs(rGraph, s, t, parent))
        {
            // Find minimum residual capacity of the edhes
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            int path_flow = Integer.MAX_VALUE;
            for (v=t; v!=s; v=parent[v])
            {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }
 
            // update residual capacities of the edges and
            // reverse edges along the path
            for (v=t; v != s; v=parent[v])
            {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }
 
            // Add path flow to overall flow
            max_flow += path_flow;
        }
 
        // Return the overall flow
        return max_flow;
    }
 
}
