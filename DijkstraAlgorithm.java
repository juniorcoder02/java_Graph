import java.util.ArrayList;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {
    // Edge class representing a directed weighted edge
    static class Edge {
        int src, dest, wt;

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    // Create adjacency list for the graph
    static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // Adding edges
        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));
        graph[1].add(new Edge(1, 3, 7));
        graph[1].add(new Edge(1, 2, 1));
        graph[2].add(new Edge(2, 4, 3));
        graph[3].add(new Edge(3, 5, 1));
        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 5));
    }

    // Pair class to store vertex and path weight
    static class Pair implements Comparable<Pair> {
        int n, path;

        public Pair(int n, int path) {
            this.n = n;
            this.path = path;
        }

        public int compareTo(Pair p2) {
            return this.path - p2.path; // Min-heap priority queue
        }
    }

    // Dijkstra's Algorithm using PriorityQueue
    public static void dijkstra(ArrayList<Edge>[] graph, int src) { // time complexity : O((V + E)log V)
        int[] dist = new int[graph.length];
        boolean[] visited = new boolean[graph.length];

        // Initialize distances
        for (int i = 0; i < graph.length; i++) {
            dist[i] = (i == src) ? 0 : Integer.MAX_VALUE;
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            if (visited[curr.n]) continue; // Skip visited nodes

            visited[curr.n] = true;

            for (Edge e : graph[curr.n]) {
                if (dist[curr.n] + e.wt < dist[e.dest]) {
                    dist[e.dest] = dist[curr.n] + e.wt;
                    pq.add(new Pair(e.dest, dist[e.dest]));
                }
            }
        }

        // Print shortest distances
        for (int d : dist) {
            System.out.print(d + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int v = 6;
        ArrayList<Edge>[] graph = new ArrayList[v];
        createGraph(graph);
        dijkstra(graph, 0);
    }
}
