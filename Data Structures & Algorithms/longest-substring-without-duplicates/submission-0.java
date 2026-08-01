class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer> map=new HashMap<>();

        int left=0,right=0;
        int maxLength=0, len=0;
        while(right<s.length()){
            char c=s.charAt(right);

            map.put(c, map.getOrDefault(c,0)+1);

            while(map.get(c)>1){
                char e=s.charAt(left);
                map.put(e, map.getOrDefault(e,0)-1);
                left++;
            }
            maxLength=Math.max(right-left+1,maxLength);
            right++;
            
        }
        return maxLength;
    }
}
