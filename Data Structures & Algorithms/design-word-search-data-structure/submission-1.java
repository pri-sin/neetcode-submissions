class WordDictionary {
    private Trienode root;

    public WordDictionary() {
        root = new Trienode();
    }

    public void addWord(String word) {
        Trienode curr = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (curr.children[index] == null) {
                curr.children[index] = new Trienode();
            }
            curr = curr.children[index];
        }
        curr.isEndOfWord = true;
    }

    public boolean search(String word) {
        // Start DFS from index 0 and root node
        return searchInNode(word, 0, root);
    }

    private boolean searchInNode(String word, int index, Trienode node) {
        if (node == null) return false;
        if (index == word.length()) return node.isEndOfWord;

        char c = word.charAt(index);

        // Case 1: Wildcard '.' -> check all valid child branches
        if (c == '.') {
            for (Trienode child : node.children) {
                if (child != null && searchInNode(word, index + 1, child)) {
                    return true; // Match found in one of the branches
                }
            }
            return false;
        } 
        // Case 2: Exact character matching
        else {
            int childIndex = c - 'a';
            if (node.children[childIndex] == null) {
                return false;
            }
            return searchInNode(word, index + 1, node.children[childIndex]);
        }
    }
}

class Trienode {
    Trienode[] children = new Trienode[26];
    boolean isEndOfWord;
}
