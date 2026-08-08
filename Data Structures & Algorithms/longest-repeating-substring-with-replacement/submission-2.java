class Solution {
    public int characterReplacement(String s, int k) {
        int left = 0, right = 0;
        int longest = 0;
        int maxcount = 0;
        int charcount[] = new int[26];

        while (right < s.length()) {
            char c = s.charAt(right);
            charcount[c - 'A']++;

            // maxcount tracks the HISTORICAL MAX frequency of a single character in any window.
            // We only update it when a character count INCREASES because only a higher peak 
            // can beat our current 'longest' record.
            maxcount = Math.max(charcount[c - 'A'], maxcount);

            while (right - left + 1 - maxcount > k) {
                char r = s.charAt(left);
                charcount[r - 'A']--;
                left++;

                // NOTE: We intentionally DO NOT decrement or recalculate maxcount here.
                // Even if maxcount is temporarily stale/outdated, it cannot fake a new record.
                // To break our best 'longest' length, we need a character to achieve a new 
                // higher frequency than this peak anyway.
            }

            longest = Math.max(longest, right - left + 1);
            right++;
        }
        return longest;
    }
}