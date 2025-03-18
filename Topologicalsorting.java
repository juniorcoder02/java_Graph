import java.util.ArrayList;
import java.util.Stack;

public class TopologicalSorting {
    
    // Class to represent directed edges in the graph
    static class Edge {
        int src, dest;

        Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    // Method to create a directed graph using adjacency list representation
    public static void createGraph(ArrayList<Edge>[] graph) {
        // Initialize adjacency list for each vertex
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // Add directed edges to the graph
        graph[2].add(new Edge(2, 3));

        graph[3].add(new Edge(3, 1));

        graph[4].add(new Edge(4, 0));
        graph[4].add(new Edge(4, 1));

        graph[5].add(new Edge(5, 0));
        graph[5].add(new Edge(5, 2));
    }

    // Method to perform Topological Sorting using DFS (Time Complexity: O(V + E))
    public static void topSort(ArrayList<Edge>[] graph) {
        int v = graph.length; // Number of vertices
        boolean[] visited = new boolean[v]; // Track visited nodes
        Stack<Integer> stack = new Stack<>(); // Stack to store topological order

        // Perform DFS from each unvisited node
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                topSortUtil(graph, i, visited, stack);
            }
        }

        // Print topological order (pop elements from stack)
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    // Recursive helper method for DFS-based Topological Sorting
    private static void topSortUtil(ArrayList<Edge>[] graph, int curr, boolean[] visited, Stack<Integer> stack) {
        visited[curr] = true; // Mark current node as visited

        // Recur for all adjacent vertices
        for (Edge edge : graph[curr]) {
            if (!visited[edge.dest]) {
                topSortUtil(graph, edge.dest, visited, stack);
            }
        }

        // Push the current vertex to stack after all its neighbors are visited
        stack.push(curr);
    }

    // Main method to execute the topological sorting
    public static void main(String[] args) {
        int v = 6; // Number of vertices
        ArrayList<Edge>[] graph = new ArrayList[v]; // Adjacency list representation
        createGraph(graph); // Construct the graph
        System.out.println("Topological Sort Order:");
        topSort(graph); // Perform topological sorting
    }
}
