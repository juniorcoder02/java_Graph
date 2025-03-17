import java.util.ArrayList;

public class GraphCreation {
    // Edge class to represent a weighted edge in the graph
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

    public static void main(String[] args) {
        int v = 5; // Number of vertices in the graph

        // Create an adjacency list for the graph
        ArrayList<Edge>[] graph = new ArrayList[v];

        // Initialize each index of the adjacency list with an empty ArrayList
        for (int i = 0; i < v; i++) {
            graph[i] = new ArrayList<>();
        }

        // Adding edges to the graph
        // Edge format: graph[source].add(new Edge(source, destination, weight));

        // Vertex 0 connections
        graph[0].add(new Edge(0, 1, 5));

        // Vertex 1 connections
        graph[1].add(new Edge(1, 0, 5));
        graph[1].add(new Edge(1, 2, 1));
        graph[1].add(new Edge(1, 3, 3));

        // Vertex 2 connections
        graph[2].add(new Edge(2, 1, 1));
        graph[2].add(new Edge(2, 3, 1));
        graph[2].add(new Edge(2, 4, 4));

        // Vertex 3 connections
        graph[3].add(new Edge(3, 1, 3));
        graph[3].add(new Edge(3, 2, 1));

        // Vertex 4 connections
        graph[4].add(new Edge(4, 2, 2));

        // Display all neighbors of vertex 2 along with their weights
        System.out.println("Neighbors of vertex 2:");
        for (Edge e : graph[2]) { 
            System.out.println("Destination: " + e.dest + ", Weight: " + e.wt);
        }
    }
}
