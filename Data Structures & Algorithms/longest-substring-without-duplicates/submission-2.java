/*class Solution {
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
}*/


class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> countMap=new HashMap<>();
        int left=0;
        int right=0;
        int maxlen=0;
        while(right<s.length()){
            char c =s.charAt(right);
            countMap.put(c, countMap.getOrDefault(c,0)+1);

            while(countMap.get(c)>1){
                char ch=s.charAt(left);
                left++;
                countMap.put(ch, countMap.get(ch)-1);
            }
            maxlen=Math.max(maxlen, right-left+1);
            right++;
        }
        return maxlen;
    }
}