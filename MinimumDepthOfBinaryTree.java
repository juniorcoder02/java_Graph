import java.util.LinkedList;
import java.util.Queue;

// Class to represent a Binary Tree
public class MinimumDepthOfBinaryTree {
    
    /**
     * Approach:
     * - We use **Level Order Traversal (BFS)** to find the minimum depth of the binary tree.
     * - The first leaf node encountered gives the minimum depth.
     * - We maintain a queue to store nodes along with their depth levels.
     * - We process nodes level by level and stop as soon as we reach the first leaf node.
     * 
     * Time Complexity: O(N) (Each node is visited once)
     * Space Complexity: O(N) (In worst case, all nodes are stored in the queue)
     */
    
    // Class representing a Tree Node
    static class Node {
        int data;
        Node left, right;
    }
    
    // Class representing an item in the queue (Node + its depth)
    static class QueueItem {
        Node node;
        int depth;
        
        public QueueItem(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
    
    /**
     * Function to find the minimum depth of a binary tree
     * @param root Root node of the binary tree
     * @return Minimum depth of the tree
     */
    public static int minDepth(Node root) {
        // If the tree is empty, return 0
        if (root == null) return 0;
        
        // Queue for BFS traversal
        Queue<QueueItem> queue = new LinkedList<>();
        queue.add(new QueueItem(root, 1)); // Start with the root at depth 1
        
        // Level Order Traversal (BFS)
        while (!queue.isEmpty()) {
            // Get the front item from the queue
            QueueItem qItem = queue.poll();
            Node node = qItem.node;
            int depth = qItem.depth;
            
            // If we encounter a leaf node, return its depth
            if (node.left == null && node.right == null) {
                return depth;
            }
            
            // Add left child to the queue if it exists
            if (node.left != null) {
                queue.add(new QueueItem(node.left, depth + 1));
            }
            
            // Add right child to the queue if it exists
            if (node.right != null) {
                queue.add(new QueueItem(node.right, depth + 1));
            }
        }
        return 0; // This should never be reached
    }
    
    /**
     * Helper function to create a new tree node
     */
    public static Node newNode(int data) {
        Node temp = new Node();
        temp.data = data;
        temp.left = temp.right = null;
        return temp;
    }
    
    /**
     * Driver function to test minDepth function
     */
    public static void main(String[] args) {
        // Constructing a sample binary tree
        Node root = newNode(1);
        root.left = newNode(2);
        root.right = newNode(3);
        root.left.left = newNode(4);
        root.left.right = newNode(5);
        
        // Print the minimum depth
        System.out.println("Minimum Depth of Binary Tree: " + minDepth(root));
    }
}