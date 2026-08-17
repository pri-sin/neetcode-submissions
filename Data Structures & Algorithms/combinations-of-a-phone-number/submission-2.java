public class Solution {
    // Mapping of digits to corresponding letters
    private static final String[] PHONE_MAP = {
        "",     "",     "abc",  "def", 
        "ghi",  "jkl",  "mno", 
        "pqrs", "tuv",  "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        
        // Base case: if the input is empty
        if (digits == null || digits.isEmpty()) {
            return combinations;
        }
        
        // Start the backtracking process
        backtrack(0, new StringBuilder(), digits, combinations);
        return combinations;
    }

    private void backtrack(int index, StringBuilder path, String digits, List<String> combinations) {
        // If the path length equals the digits length, a full combination is formed
        if (path.length() == digits.length()) {
            combinations.add(path.toString());
            return;
        }

        // Get the letters that the current digit maps to
        String possibleLetters = PHONE_MAP[digits.charAt(index) - '0'];

        for (char letter : possibleLetters.toCharArray()) {
            // Append the letter to the current path
            path.append(letter);
            
            // Move onto the next digit
            backtrack(index + 1, path, digits, combinations);
            
            // Backtrack: remove the last character before trying the next one
            path.deleteCharAt(path.length() - 1);
        }
    }
}