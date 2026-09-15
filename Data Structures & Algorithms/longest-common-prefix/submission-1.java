class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        Trie root = new Trie();
        String smallestStr = strs[0];

        for (String str : strs) {
            insert(str, root);
            if (str.length() < smallestStr.length()) {
                smallestStr = str;
            }
        }

        return getPrefix(smallestStr, root, strs.length);
    }

    public void insert(String word, Trie root) {
        Trie curr = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (curr.children[index] == null) {
                curr.children[index] = new Trie();
            }
            curr.count[index]++;
            curr = curr.children[index];
        }
    }

    public String getPrefix(String str, Trie root, int n) {
        Trie curr = root;
        StringBuilder sb = new StringBuilder();

        for (char c : str.toCharArray()) {
            int index = c - 'a';
            // Stop immediately if node doesn't exist or character count < total strings
            if (curr.children[index] == null || curr.count[index] != n) {
                break;
            }
            sb.append(c);
            curr = curr.children[index];
        }

        return sb.toString();
    }
}

class Trie {
    Trie[] children = new Trie[26];
    int[] count = new int[26];
}