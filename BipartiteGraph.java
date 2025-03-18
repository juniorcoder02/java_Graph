import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BipartiteGraph {
    
    // Class to represent an edge between two nodes
    static class Edge {
        int src;   // Source node
        int dest;  // Destination node

        Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    /**
     * Creates an adjacency list representation of the graph
     * @param graph - Adjacency list of graph
     */
    static void createGraph(ArrayList<Edge>[] graph) {
        // Initialize adjacency lists for each vertex
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // Adding edges (undirected graph)
        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 4));

        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 4));

        graph[4].add(new Edge(4, 2));
        graph[4].add(new Edge(4, 3));
    }

    /**
     * Checks if the graph is bipartite using BFS
     * @param graph - Adjacency list representation of the graph
     * @return true if the graph is bipartite, false otherwise
     */
    public static boolean isBipartite(ArrayList<Edge>[] graph) {
        int v = graph.length;
        int[] color = new int[v]; // Array to store colors of nodes

        // Initialize all nodes as uncolored (-1)
        for (int i = 0; i < v; i++) {
            color[i] = -1;
        }

        // Queue for BFS traversal
        Queue<Integer> queue = new LinkedList<>();

        // Check for each component in case of disconnected graph
        for (int i = 0; i < v; i++) {
            if (color[i] == -1) { // If node is uncolored, start BFS
                queue.add(i);
                color[i] = 0; // Assign first color (e.g., 0)

                // BFS traversal
                while (!queue.isEmpty()) { 
                    int curr = queue.poll(); // Remove front node from queue

                    // Process all adjacent nodes
                    for (Edge e : graph[curr]) {
                        if (color[e.dest] == -1) { // If neighbor is uncolored
                            color[e.dest] = 1 - color[curr]; // Assign opposite color
                            queue.add(e.dest);
                        } 
                        // If adjacent node has the same color, the graph is not bipartite
                        else if (color[e.dest] == color[curr]) { 
                            return false;
                        }
                    }
                }
            }
        }
        return true; // If no conflicts, graph is bipartite
    }

    public static void main(String[] args) {
        int v = 5; // Number of vertices
        ArrayList<Edge>[] graph = new ArrayList[v]; // Adjacency list
        createGraph(graph); // Create the graph

        // Check if the graph is bipartite
        if (isBipartite(graph)) {
            System.out.println("The graph is bipartite.");
        } else {
            System.out.println("The graph is NOT bipartite.");
        }
    }
}
