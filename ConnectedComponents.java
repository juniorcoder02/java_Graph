import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectedComponents {
    // Edge class representing a weighted edge in the graph
    static class Edge {
        int src;
        int dest;
        int wt;

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    // Function to create a graph using adjacency list
    static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // Adding edges to the graph
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

    // Function to perform BFS traversal on the graph
    public static void BFS(ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                BFSUtil(graph, i, visited);
            }
        }
    }

    // BFS traversal helper function
    public static void BFSUtil(ArrayList<Edge>[] graph, int start, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>(); // Queue for BFS traversal
        q.add(start); // Start BFS from the given vertex

        System.out.print("BFS Traversal from vertex " + start + ": ");
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

    // Function to perform DFS traversal on the graph
    public static void DFS(ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                System.out.print("DFS Traversal from vertex " + i + ": ");
                DFSUtil(graph, i, visited);
                System.out.println();
            }
        }
    }

    // DFS traversal helper function
    public static void DFSUtil(ArrayList<Edge>[] graph, int curr, boolean[] visited) {
        System.out.print(curr + " "); // Print the current node
        visited[curr] = true; // Mark the node as visited

        for (Edge e : graph[curr]) { // Iterate over neighbors
            if (!visited[e.dest]) {
                DFSUtil(graph, e.dest, visited);
            }
        }
    }

    public static void main(String[] args) {
        int v = 7; // Number of vertices
        ArrayList<Edge>[] graph = new ArrayList[v];
        createGraph(graph); // Create the graph

        System.out.println("Performing BFS:");
        BFS(graph); // Perform BFS traversal

        System.out.println("Performing DFS:");
        DFS(graph); // Perform DFS traversal
    }
}
