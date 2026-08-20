
class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
    }

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        List<String> res = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                search(board, i, j, root, res);
            }
        }
        return res;
    }

    private void search(char[][] board, int i, int j, TrieNode curr, List<String> res) {
        // Boundary check
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;

        char c = board[i][j];
        // Check visited or non-matching Trie path BEFORE modifying the board
        if (c == '#' || curr.children[c - 'a'] == null) return;

        curr = curr.children[c - 'a'];

        // Found a word: add to result and set to null to avoid duplicates
        if (curr.word != null) {
            res.add(curr.word);
            curr.word = null;
        }

        board[i][j] = '#'; // Mark visited

        // Continue DFS in 4 directions (do NOT return early)
        search(board, i + 1, j, curr, res);
        search(board, i - 1, j, curr, res);
        search(board, i, j + 1, curr, res);
        search(board, i, j - 1, curr, res);

        board[i][j] = c; // Backtrack properly
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode curr = root;
            for (char c : w.toCharArray()) {
                int idx = c - 'a';
                if (curr.children[idx] == null) {
                    curr.children[idx] = new TrieNode();
                }
                curr = curr.children[idx];
            }
            curr.word = w;
        }
        return root;
    }
}