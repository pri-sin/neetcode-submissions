class Solution {
    public String minWindow(String s, String t) {
        int shortest=Integer.MAX_VALUE;
        HashMap<Character,Integer> map=new HashMap<>();
        //create a map of all the characters in string t
        for(int i=0;i<t.length();i++){
            map.put(t.charAt(i),map.getOrDefault(t.charAt(i),0)+1);
        }
        int count=t.length();

        int left=0,right=0, start=0;
        while(right<s.length()){
            //extract character from right
            char c=s.charAt(right);

            //check if it is there in the map
            if(map.containsKey(c)){
                //decrement count if yes
                if(map.get(c)>0){
                    count--;
                }
                map.put(c, map.getOrDefault(c,0)-1);
                
            }
            
            while(count==0){
                if(shortest>(right-left+1)){
                    shortest=right-left+1;
                    start=left;
                }

                char l=s.charAt(left);
                if(map.containsKey(l)){
                    map.put(l, map.getOrDefault(l,0)+1);
                    if(map.get(l)>0)
                    count++;
                }
                left++;
            }
            right++;
        }
        return shortest==Integer.MAX_VALUE?"":s.substring(start,start+shortest);
    }
}
