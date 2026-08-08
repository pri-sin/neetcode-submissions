class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left=0, right=0;
        HashMap<Character, Integer> map=new HashMap<>();
        int maxcount=0;

        while(right<s.length()){
            char c=s.charAt(right);

            map.put(c,map.getOrDefault(c,0)+1);

            while(map.get(c)>1){
                char r=s.charAt(left);
                left++;
                map.put(r,map.getOrDefault(r,0)-1);
            }
            maxcount=Math.max(maxcount, right-left+1);
            right++;
        }
        return maxcount;
    }
}
