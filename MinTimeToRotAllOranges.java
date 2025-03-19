import java.util.LinkedList;
import java.util.Queue;

// Class to represent a cell in the grid
class Cell {
    int x, y;

    // Constructor to initialize the cell's coordinates
    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class MinTimeToRotAllOranges {
    public final static int ROWS = 3;
    public final static int COLS = 5;

    // Function to check if a cell is within grid boundaries
    public static boolean isValid(int i, int j) {
        return (i >= 0 && j >= 0 && i < ROWS && j < COLS);
    }

    // Function to check if the queue contains a delimiter
    public static boolean isDelimiter(Cell temp) {
        return (temp.x == -1 && temp.y == -1);
    }

    // Function to check if any fresh oranges are left
    public static boolean hasFreshOranges(int grid[][]) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j] == 1) {
                    return true; // At least one fresh orange remains
                }
            }
        }
        return false;
    }

    // Function to find the minimum time required to rot all oranges
    static int minTimeToRot(int grid[][]) {
        Queue<Cell> queue = new LinkedList<>();
        int minutes = 0;

        // Step 1: Insert all initially rotten oranges into the queue
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new Cell(i, j));
                }
            }
        }

        // Step 2: Adding a delimiter to mark the end of the first minute
        queue.add(new Cell(-1, -1));

        // Directions for moving in 4 possible ways: up, down, left, right
        int[] dX = {-1, 1, 0, 0};
        int[] dY = {0, 0, -1, 1};

        // Step 3: Process the queue using BFS
        while (!queue.isEmpty()) {
            boolean newRotten = false;
            
            while (!isDelimiter(queue.peek())) {
                Cell temp = queue.poll();

                // Check all four possible adjacent cells
                for (int i = 0; i < 4; i++) {
                    int newX = temp.x + dX[i];
                    int newY = temp.y + dY[i];

                    // If adjacent cell contains a fresh orange, rot it
                    if (isValid(newX, newY) && grid[newX][newY] == 1) {
                        if (!newRotten) {
                            minutes++; // Increase time when the first fresh orange rots in this cycle
                            newRotten = true;
                        }
                        grid[newX][newY] = 2; // Mark the orange as rotten
                        queue.add(new Cell(newX, newY)); // Add it to queue for further processing
                    }
                }
            }

            // Remove the delimiter
            queue.poll();

            // If there are still oranges left to process, add a new delimiter
            if (!queue.isEmpty()) {
                queue.add(new Cell(-1, -1));
            }
        }

        // Step 4: Check if any fresh oranges are still left
        return hasFreshOranges(grid) ? -1 : minutes;
    }

    public static void main(String[] args) {
        int grid[][] = {
                { 2, 1, 0, 2, 1 },
                { 1, 0, 1, 2, 1 },
                { 1, 0, 0, 2, 1 }
        };

        int result = minTimeToRot(grid);
        if (result == -1) {
            System.out.println("All oranges cannot rot.");
        } else {
            System.out.println("Minimum time required for all oranges to rot = " + result);
        }
    }
}