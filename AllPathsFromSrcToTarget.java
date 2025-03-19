import java.util.ArrayList;

public class AllPathsFromSrcToTarget {
    
    // Class representing an Edge in the directed graph
    static class Edge {
        int src;  // Source vertex
        int dest; // Destination vertex

        // Constructor to initialize an edge
        Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    /**
     * Creates a directed graph using adjacency list representation.
     * @param graph Array of ArrayLists representing adjacency list.
     */
    public static void createGraph(ArrayList<Edge>[] graph) {
        // Initialize each index with an empty ArrayList
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // Adding edges to the graph
        graph[0].add(new Edge(0, 3));  // Edge from 0 → 3

        graph[2].add(new Edge(2, 3));  // Edge from 2 → 3

        graph[3].add(new Edge(3, 1));  // Edge from 3 → 1

        graph[4].add(new Edge(4, 0));  // Edge from 4 → 0
        graph[4].add(new Edge(4, 1));  // Edge from 4 → 1

        graph[5].add(new Edge(5, 0));  // Edge from 5 → 0
        graph[5].add(new Edge(5, 2));  // Edge from 5 → 2
    }

    /**
     * Recursively finds and prints all paths from src to dest.
     * @param graph Adjacency list of the graph.
     * @param src   Current vertex in the path.
     * @param dest  Destination vertex.
     * @param path  String to store the path traversal.
     */
    public static void printAllPaths(ArrayList<Edge>[] graph, int src, int dest, String path) {
        // Base case: If we reach the destination, print the path
        if (src == dest) {
            System.out.println(path + dest);  // Print complete path
            return;
        }

        // Explore all adjacent nodes (outgoing edges)
        for (int i = 0; i < graph[src].size(); i++) {
            Edge e = graph[src].get(i); // Get the next edge
            printAllPaths(graph, e.dest, dest, path + src + " → "); // Recursively call with updated path
        }
    }

    public static void main(String[] args) {
        int v = 6; // Number of vertices in the graph

        // Create an adjacency list representation of the graph
        ArrayList<Edge>[] graph = new ArrayList[v];
        createGraph(graph);

        int src = 5;  // Source vertex
        int dest = 1; // Destination vertex

        // Print all paths from source to destination
        System.out.println("All paths from " + src + " to " + dest + ":");
        printAllPaths(graph, src, dest, "");
    }
}
