
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int level = 1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                String currentWord = queue.poll();
                if (currentWord.equals(endWord)) {
                    //use .equals for string comparison
                    return level;
                }

                // Mutate character array in-place to avoid excessive string allocations
                char[] chars = currentWord.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char originalChar = chars[j];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue;
                        chars[j] = c;
                        String nextWord = new String(chars);

                        if (wordSet.contains(nextWord)) {
                            wordSet.remove(nextWord); // Mark as visited
                            queue.add(nextWord);
                        }
                    }
                    chars[j] = originalChar; // Reset character back
                }
            }
            level++;
        }

        return 0;
    }
}