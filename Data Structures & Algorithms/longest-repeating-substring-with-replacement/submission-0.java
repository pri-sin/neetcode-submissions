class Solution {
    public int characterReplacement(String s, int k) {
        int charArray[]=new int[26];

        int left=0,right=0;
        int maxlen=0, maxcount=0;
        while(right<s.length()){
            char c=s.charAt(right);

            charArray[c-'A']++;
            maxcount=Math.max(maxcount, charArray[c-'A']);
            while((right-left+1)-maxcount>k){
                char e=s.charAt(left);
                charArray[e-'A']--;
                left++;
            }

            maxlen=Math.max(right-left+1,maxlen);
            right++;
        }
        return maxlen;
    }
}
