import java.util.ArrayList;

public class CycleDetectionII {
    
    // Edge class represents a directed edge in a graph
    static class Edge {
        int src;
        int dest;

        Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    // Function to create a directed graph using an adjacency list
    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>(); // Initialize adjacency list for each vertex
        }

        // Adding directed edges to represent the graph
        graph[0].add(new Edge(0, 2));
        graph[1].add(new Edge(1, 0));
        graph[2].add(new Edge(2, 3));
        graph[3].add(new Edge(3, 0));
    }

    /*
     * Function to check if the directed graph contains a cycle
     * Approach:
     * - Use DFS traversal and a recursion stack (boolean array `stack`)
     * - If a node is already in the recursion stack (`stack[curr]` is true), cycle exists
     * - Otherwise, continue DFS traversal
     */
    public static boolean isCycle(ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length]; // Track visited nodes
        boolean[] stack = new boolean[graph.length]; // Recursion stack to track back edges

        // Check for cycles in all components of the graph
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) { // Start DFS if the node is unvisited
                if (isCycleUtil(graph, i, visited, stack)) {
                    return true; // Cycle detected
                }
            }
        }
        return false; // No cycle found
    }

    /*
     * Recursive DFS function to detect cycles in a directed graph
     * Parameters:
     * - graph: The adjacency list representation of the graph
     * - curr: The current node in DFS traversal
     * - visited: Array to mark visited nodes
     * - stack: Recursion stack to track nodes in the current path
     */
    public static boolean isCycleUtil(ArrayList<Edge>[] graph, int curr, boolean[] visited, boolean[] stack) {
        visited[curr] = true; // Mark current node as visited
        stack[curr] = true; // Mark node as part of the recursion stack

        // Traverse all adjacent nodes
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            
            if (stack[e.dest]) { // If the neighbor is in the recursion stack, cycle detected
                return true;
            }
            if (!visited[e.dest] && isCycleUtil(graph, e.dest, visited, stack)) {
                return true; // Cycle detected in deeper DFS call
            }
        }
        stack[curr] = false; // Remove node from recursion stack before backtracking
        return false;
    }

    public static void main(String[] args) {
        int v = 4; // Number of vertices
        ArrayList<Edge>[] graph = new ArrayList[v];
        createGraph(graph); // Create the graph

        // Check for cycles in the graph
        if (isCycle(graph)) {
            System.out.println("Cycle Detected in the Graph");
        } else {
            System.out.println("No Cycle Detected in the Graph");
        }
    }
}