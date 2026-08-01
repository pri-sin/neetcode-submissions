class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> current = new ArrayList<>();
        backtrack(s, 0, current, result);
        return result;
    }

    private void backtrack(String s, int start, List<String> current, List<List<String>> result) {
        // Base Case: If we have successfully partitioned the entire string
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        // 'i' represents the end index of our current substring choice
        for (int i = start; i < s.length(); i++) {
            // Only proceed if the substring from 'start' to 'i' is a palindrome
            if (isPalindrome(s, start, i)) {
                // Make Choice: Cut the substring and add it to our path
                current.add(s.substring(start, i + 1));
                
                // Recurse: Move the start pointer right past our cut
                backtrack(s, i + 1, current, result);
                
                // Backtrack: Remove the cut to try a different size substring
                current.remove(current.size() - 1);
            }
        }
    }

    // Helper method to check if a substring is a palindrome
    private boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--)) {
                return false;
            }
        }
        return true;
    }
}