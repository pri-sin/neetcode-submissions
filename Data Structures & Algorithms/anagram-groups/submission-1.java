

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // Edge case: if input is empty, return an empty list
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        Map<String, List<String>> anagramMap = new HashMap<>();
        
        for (String str : strs) {
            // Create the 26-element frequency array for lowercase 'a'-'z'
            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            
            // Build a unique string key from the frequency array
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append('#'); // Delimiter to prevent collision (e.g., 1 and 11 vs 11 and 1)
                sb.append(count[i]);
            }
            String key = sb.toString();
            
            // Group the string under its respective key
            anagramMap.putIfAbsent(key, new ArrayList<>());
            anagramMap.get(key).add(str);
        }
        
        // Return all grouped lists
        return new ArrayList<>(anagramMap.values());
    }
}