import java.util.*;

public class DetectCycle {
    // Approach:
    // 1. Represent the graph using an adjacency list.
    // 2. Use BFS (Breadth-First Search) to traverse each component of the graph.
    // 3. Maintain a 'visited' array to track visited nodes.
    // 4. Use a 'parent' array to track the parent of each node in BFS traversal.
    // 5. If a node is visited and is not the parent of the current node, a cycle exists.

    // Time Complexity: O(V + E) where V = vertices and E = edges
    // Space Complexity: O(V + E) for adjacency list representation

    // Function to add an edge between two vertices in an adjacency list
    public static void addEdge(ArrayList<Integer>[] adj, int u, int v) {
        adj[u].add(v);
        adj[v].add(u); // Since it's an undirected graph
    }

    // Function to check if a cycle exists in a connected component using BFS
    public static boolean isCyclicConnected(ArrayList<Integer>[] adj, int start, int v, boolean[] visited) {
        int[] parent = new int[v]; // Parent array to keep track of the node's parent
        Arrays.fill(parent, -1); // Initialize parent array with -1
        Queue<Integer> queue = new LinkedList<>(); // BFS queue

        visited[start] = true; // Mark the start node as visited
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll(); // Get the front node from queue

            // Traverse all adjacent nodes
            for (int neighbor : adj[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true; // Mark the node as visited
                    queue.add(neighbor); // Add it to the queue for further traversal
                    parent[neighbor] = current; // Set parent of neighbor
                } else if (parent[current] != neighbor) {
                    // If the adjacent node is visited and not the parent, a cycle exists
                    return true;
                }
            }
        }
        return false; // No cycle found in this component
    }

    // Function to check if the graph has a cycle (handles disconnected components)
    public static boolean hasCycle(ArrayList<Integer>[] adj, int v) {
        boolean[] visited = new boolean[v]; // Visited array to track visited nodes

        // Traverse all components of the graph
        for (int i = 0; i < v; i++) {
            if (!visited[i]) { // If a node is unvisited, check for a cycle in its component
                if (isCyclicConnected(adj, i, v, visited)) {
                    return true;
                }
            }
        }
        return false; // No cycle found in any component
    }

    public static void main(String[] args) {
        int v = 4; // Number of vertices
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] adj = new ArrayList[v]; // Adjacency list representation
        
        // Initialize adjacency list
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }

        // Adding edges
        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 0);
        addEdge(adj, 2, 3);

        // Check if the graph contains a cycle
        if (hasCycle(adj, v)) {
            System.out.println("Yes, the graph contains a cycle.");
        } else {
            System.out.println("No, the graph does not contain a cycle.");
        }
    }
}
