import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TopologicalSortingUsingBFS {
    
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
     * Creates a directed graph using an adjacency list.
     * @param graph Array of ArrayLists representing adjacency list.
     */
    public static void createGraph(ArrayList<Edge>[] graph) {
        // Initialize each index with an empty ArrayList
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // Adding edges to the graph
        graph[2].add(new Edge(2, 3));  // Edge from 2 → 3

        graph[3].add(new Edge(3, 1));  // Edge from 3 → 1

        graph[4].add(new Edge(4, 0));  // Edge from 4 → 0
        graph[4].add(new Edge(4, 1));  // Edge from 4 → 1

        graph[5].add(new Edge(5, 0));  // Edge from 5 → 0
        graph[5].add(new Edge(5, 2));  // Edge from 5 → 2
    }

    /**
     * Calculates the indegree for each vertex.
     * @param graph Adjacency list of the graph.
     * @param indeg Array to store indegrees of vertices.
     */
    public static void calcIndeg(ArrayList<Edge>[] graph, int[] indeg) {
        for (int i = 0; i < graph.length; i++) {
            for (Edge e : graph[i]) { // Iterate over all outgoing edges
                indeg[e.dest]++; // Increment indegree of destination vertex
            }
        }
    }

    /**
     * Performs Topological Sorting using BFS (Kahn's Algorithm).
     * @param graph Adjacency list of the graph.
     */
    public static void topSort(ArrayList<Edge>[] graph) { // Kahn's Algorithm for Topological Sorting
        int[] indeg = new int[graph.length]; // Array to store indegree of each vertex
        calcIndeg(graph, indeg); // Compute indegree

        Queue<Integer> q = new LinkedList<>(); // Queue for processing nodes

        // Step 1: Enqueue all vertices with indegree 0
        for (int i = 0; i < indeg.length; i++) {
            if (indeg[i] == 0) {
                q.add(i);
            }
        }

        System.out.println("Topological Order:");
        
        // Step 2: Process nodes in BFS manner
        while (!q.isEmpty()) {
            int curr = q.poll(); // Dequeue element
            System.out.print(curr + " "); // Print topological order

            // Step 3: Reduce indegree of neighboring nodes
            for (Edge e : graph[curr]) {
                indeg[e.dest]--;
                
                // If any node's indegree becomes 0, enqueue it
                if (indeg[e.dest] == 0) {
                    q.add(e.dest);
                }
            }
        }
        System.out.println(); // New line after topological order
    }

    public static void main(String[] args) {
        int v = 6; // Number of vertices

        // Create an adjacency list representation of the graph
        ArrayList<Edge>[] graph = new ArrayList[v];
        createGraph(graph);

        // Perform Topological Sorting
        topSort(graph);
    }
}
