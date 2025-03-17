import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Traversal {
    // Edge class to represent an edge in the graph
    static class Edge {
        int src;  // Source vertex
        int dest; // Destination vertex
        int wt;   // Weight of the edge

        // Constructor to initialize an edge
        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    // Method to create a graph using adjacency list representation
    static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>(); // Initialize each index with an empty ArrayList
        }

        // Adding edges to the graph (undirected, weighted graph with weight = 1)
        graph[0].add(new Edge(0, 1, 1));
        graph[0].add(new Edge(0, 2, 1));

        graph[1].add(new Edge(1, 0, 1));
        graph[1].add(new Edge(1, 3, 1));

        graph[2].add(new Edge(2, 0, 1));
        graph[2].add(new Edge(2, 4, 1));

        graph[3].add(new Edge(3, 1, 1));
        graph[3].add(new Edge(3, 4, 1));
        graph[3].add(new Edge(3, 5, 1));

        graph[4].add(new Edge(4, 2, 1));
        graph[4].add(new Edge(4, 3, 1));
        graph[4].add(new Edge(4, 5, 1));

        graph[5].add(new Edge(5, 3, 1));
        graph[5].add(new Edge(5, 4, 1));
        graph[5].add(new Edge(5, 6, 1));

        graph[6].add(new Edge(6, 5, 1));
    }

    // Breadth-First Search (BFS) Traversal
    public static void BFSTraversal(ArrayList<Edge>[] graph) { // O(V + E)
        Queue<Integer> q = new LinkedList<>(); // Queue for BFS traversal
        boolean[] visited = new boolean[graph.length]; // Visited array to track visited nodes
        
        q.add(0); // Start BFS from vertex 0
        System.out.print("BFS Traversal: ");
        
        while (!q.isEmpty()) {
            int curr = q.remove();
            if (!visited[curr]) {
                System.out.print(curr + " ");
                visited[curr] = true;
                
                for (Edge e : graph[curr]) { // Iterate over neighbors
                    q.add(e.dest);
                }
            }
        }
        System.out.println();
    }

    // Depth-First Search (DFS) Traversal
    public static void DFSTraversal(ArrayList<Edge>[] graph, int curr, boolean[] visited) { // O(V + E)
        System.out.print(curr + " "); // Print the current node
        visited[curr] = true; // Mark the node as visited
        
        for (Edge e : graph[curr]) { // Iterate over neighbors
            if (!visited[e.dest]) {
                DFSTraversal(graph, e.dest, visited);
            }
        }
    }

    public static void main(String[] args) {
        int v = 7; // Number of vertices
        ArrayList<Edge>[] graph = new ArrayList[v];
        
        createGraph(graph); // Create the graph
        
        // Perform BFS Traversal
        BFSTraversal(graph);
        
        // Perform DFS Traversal
        System.out.print("DFS Traversal: ");
        DFSTraversal(graph, 0, new boolean[v]);
        System.out.println();
    }
}
