class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s2.length()<s1.length()) return false;
        int s1count[]=new int[26];

        for(int i=0;i<s1.length();i++){
            s1count[s1.charAt(i)-'a']++;
        }

        int counts1=s1.length();
        int left=0, right=0;
        while(right<s2.length()){
            char c = s2.charAt(right);

            if(s1count[c-'a']>0){
                counts1--;
            }

            s1count[c-'a']--;

            while(right-left+1>s1.length()){
                 char r = s2.charAt(left);
                 left++;
                 s1count[r-'a']++;
                 if(s1count[r-'a']>0){
                    counts1++;
                 }
            }

            if(counts1==0 && right-left+1==s1.length()) return true;
            right++;
        }
        return false;
    }
}


/*
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] s1count = new int[26];
        for (char c : s1.toCharArray()) {
            s1count[c - 'a']++;
        }

        int left = 0, right = 0, required = s1.length();

        while (right < s2.length()) {
            char rightChar = s2.charAt(right);

            // If rightChar is needed, decrement the total required count
            if (s1count[rightChar - 'a'] > 0) {
                required--;
            }
            s1count[rightChar - 'a']--;
            right++;

            // Valid permutation found
            if (required == 0) return true;

            // Maintain window size equal to s1.length()
            if (right - left == s1.length()) {
                char leftChar = s2.charAt(left);
                
                // If leftChar was part of a valid match, restore required count
                if (s1count[leftChar - 'a'] >= 0) {
                    required++;
                }
                s1count[leftChar - 'a']++; // Restore character frequency
                left++;
            }
        }
        return false;
    }
}
*/
