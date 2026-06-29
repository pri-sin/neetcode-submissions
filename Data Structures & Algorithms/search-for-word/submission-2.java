class Solution {
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        // Step 1: Look for the first letter of the word to start the search
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == word.charAt(0)) {
                    // Start backtracking search from here
                    if (dfs(board, word, r, c, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int r, int c, int index) {
        // Base Case 1: Successfully found all characters in the word
        if (index == word.length()) {
            return true;
        }

        // Base Case 2: Out of bounds OR current cell doesn't match the required character
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(index)) {
            return false;
        }

        // Step 3: "Make Choice" -> Mark the current cell as visited
        char temp = board[r][c];
        board[r][c] = '#'; 

        // Step 4: "Recurse" -> Explore all 4 adjacent directions
        boolean found = dfs(board, word, r + 1, c, index + 1) || // Down
                        dfs(board, word, r - 1, c, index + 1) || // Up
                        dfs(board, word, r, c + 1, index + 1) || // Right
                        dfs(board, word, r, c - 1, index + 1);   // Left

        // Step 5: "Backtrack" -> Restore the original character for other search paths
        board[r][c] = temp;

        return found;
    }
}