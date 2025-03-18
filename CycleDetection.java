import java.util.ArrayList;

public class CycleDetection {
    
    // Edge class represents a connection between two nodes in an undirected graph
    static class Edge {
        int src;
        int dest;

        Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    // Function to create a graph using adjacency list representation
    static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>(); // Initialize adjacency list for each vertex
        }

        // Adding edges to represent the graph
        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 3));

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 2));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 1));

        graph[3].add(new Edge(3, 0));
        graph[3].add(new Edge(3, 4));

        graph[4].add(new Edge(4, 3));
    }

    /*
     * Function to check if the graph contains a cycle
     * Approach:
     * - We traverse each node using DFS
     * - If we encounter a previously visited node that is not the parent of the current node, 
     *   it means there is a cycle.
     * - We use a boolean visited[] array to track visited nodes.
     */
    public static boolean detectCycle(ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length]; // Track visited nodes
        
        // Check for cycles in all components of the graph
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) { // Start DFS if the node is unvisited
                if (detectCycleUtil(graph, visited, i, -1)) {
                    return true; // If cycle found, return true
                }
            }
        }
        return false; // No cycle found
    }

    /*
     * Recursive DFS function to detect cycles
     * Parameters:
     * - graph: The adjacency list representation of the graph
     * - visited: Array to mark visited nodes
     * - curr: The current node in DFS traversal
     * - parent: The previous node (to avoid considering the edge back to parent as a cycle)
     */
    public static boolean detectCycleUtil(ArrayList<Edge>[] graph, boolean[] visited, int curr, int parent) {
        visited[curr] = true; // Mark the current node as visited

        // Traverse through all adjacent nodes
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            
            if (!visited[e.dest]) { // If the neighbor is not visited, do DFS
                if (detectCycleUtil(graph, visited, e.dest, curr)) {
                    return true; // Cycle detected
                }
            } 
            // If the neighbor is already visited and it's not the parent, cycle exists
            else if (visited[e.dest] && e.dest != parent) {
                return true;
            }
        }
        return false; // No cycle detected
    }

    public static void main(String[] args) {
        int v = 5; // Number of vertices
        ArrayList<Edge>[] graph = new ArrayList[v];
        
        createGraph(graph); // Creating the graph
        
        // Check for cycles in the graph
        if (detectCycle(graph)) {
            System.out.println("Cycle Detected in the Graph");
        } else {
            System.out.println("No Cycle Detected in the Graph");
        }
    }
}
