import java.util.*;

public class WordLadder {
    /**
     * Approach:
     * 1. Use **BFS (Breadth-First Search)** since it finds the shortest path in an unweighted graph.
     * 2. Each word in the dictionary represents a node, and an edge exists between two nodes if they differ by one character.
     * 3. Start from the `start` word and explore all possible words by changing one character at a time.
     * 4. If the `target` word is reached, return the number of transformations.
     * 5. Use a queue for BFS and a **set** for fast lookup and removal of words to avoid cycles.
     * 
     * Time Complexity: O(N^2 * M), where N is the number of words in the dictionary, and M is the word length.
     * Space Complexity: O(N * M), storing words in the queue and set.
     */
    static int shortestChainLen(String start, String target, Set<String> dictionary) {
        // Edge cases
        if (start.equals(target)) return 0; // If start and target are the same
        if (!dictionary.contains(target)) return 0; // If target is not in dictionary, transformation is impossible

        int level = 0; // Number of transformations
        int wordLength = start.length(); // Length of each word
        Queue<String> queue = new LinkedList<>(); // Queue for BFS
        queue.add(start);

        // BFS traversal
        while (!queue.isEmpty()) {
            ++level; // Increase level (transformation step)
            int queueSize = queue.size(); // Number of elements in the current level

            for (int i = 0; i < queueSize; ++i) {
                char[] word = queue.poll().toCharArray(); // Get current word
                
                // Try changing each character in the word
                for (int pos = 0; pos < wordLength; ++pos) {
                    char originalChar = word[pos]; // Store original character
                    
                    // Try all possible replacements from 'a' to 'z'
                    for (char c = 'a'; c <= 'z'; ++c) {
                        word[pos] = c;
                        String newWord = String.valueOf(word);

                        // If we reach the target word, return transformation count
                        if (newWord.equals(target)) return level + 1;

                        // If newWord is in dictionary, process it
                        if (dictionary.contains(newWord)) {
                            dictionary.remove(newWord); // Remove to prevent cycles
                            queue.add(newWord); // Add to queue for next level processing
                        }
                    }
                    word[pos] = originalChar; // Restore original character
                }
            }
        }

        return 0; // If no transformation sequence exists
    }

    public static void main(String[] args) {
        // Set of words (dictionary)
        Set<String> dictionary = new HashSet<>(Arrays.asList("poon", "plee", "same", "poie", "plie", "poin", "plea"));
        
        String start = "toon";
        String target = "plea";

        int result = shortestChainLen(start, target, dictionary);
        System.out.println("Length of shortest transformation chain is: " + result);
    }
}
