import java.util.*;

public class LargestRegionInBooleanMatrix {
    // Time Complexity: O(ROW * COL) - Each cell is visited once.
    // Space Complexity: O(ROW * COL) - For the visited array and recursion stack (in worst case).

    static int ROW, COL, count;

    /**
     * Approach:
     * 1. We iterate through the entire matrix to find an unvisited cell with a value of 1.
     * 2. When found, we apply Depth First Search (DFS) to traverse all connected 1's.
     * 3. The DFS function explores all 8 possible directions (including diagonals).
     * 4. We track the size of the region and update the maximum size encountered.
     * 5. Return the largest region found.
     */

    // Utility function to check if a given cell (row, col) can be part of a region
    public static boolean isSafe(int[][] M, int row, int col, boolean[][] visited) {
        return (row >= 0 && row < ROW && col >= 0 && col < COL && M[row][col] == 1 && !visited[row][col]);
    }

    // Perform Depth First Search (DFS) to traverse all connected 1's
    public static void DFS(int[][] M, int row, int col, boolean[][] visited) {
        // Arrays to check all 8 possible directions (up, down, left, right, and diagonals)
        int[] rowNbr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colNbr = {-1, 0, 1, -1, 1, -1, 0, 1};
        
        // Mark the current cell as visited
        visited[row][col] = true;
        
        // Check all 8 neighbors
        for (int k = 0; k < 8; k++) {
            int newRow = row + rowNbr[k];
            int newCol = col + colNbr[k];
            
            // If the neighbor is a valid cell and contains 1, continue DFS
            if (isSafe(M, newRow, newCol, visited)) {
                count++; // Increment region size
                DFS(M, newRow, newCol, visited);
            }
        }
    }

    // Function to find the largest connected region of 1's in the matrix
    public static int largestRegion(int[][] M) {
        ROW = M.length;
        COL = M[0].length;
        boolean[][] visited = new boolean[ROW][COL]; // Track visited cells
        int result = 0; // Store the largest region found

        // Iterate through every cell in the matrix
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                // If the cell is a 1 and has not been visited, start a new region search
                if (M[i][j] == 1 && !visited[i][j]) {
                    count = 1; // Reset count for the new region
                    DFS(M, i, j, visited); // Perform DFS to find the full region
                    result = Math.max(result, count); // Update max region size if needed
                }
            }
        }
        return result; // Return the largest connected region size
    }

    public static void main(String args[]) {
        int M[][] = {
            {0, 0, 1, 1, 0},
            {1, 0, 1, 1, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1}
        };
        System.out.println("Largest connected region size: " + largestRegion(M));
    }
}