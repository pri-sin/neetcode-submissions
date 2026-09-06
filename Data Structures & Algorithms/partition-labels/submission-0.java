/*class Solution {
    public List<Integer> partitionLabels(String s) {
        HashMap<Character,Integer> map=new HashMap<>();
        int n =s.length();
        for(int i=0;i<n;i++){
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c,0)+1);
        }
        int count=0;
        List<Integer> res=new ArrayList<>();
        boolean visited[]=new boolean[26];
        int i=0;
        int distinctchar=0;
        while(i<s.length()){
            char c = s.charAt(i);
            if(!visited[c-'a'] && distinctchar==0){
                if(count!=0) res.add(count);
                count=0;
            }

            while(map.get(c)>0){
                char z=s.charAt(i);
                if(!visited[z-'a']) {
                    visited[z-'a']=true;
                    distinctchar++;
                }
                count++;
                map.put(z, map.get(z)-1);
                if(map.get(z)==0){
                    distinctchar--;
                }
                i=i+1;
            }
        }
        res.add(count);
        return res;
    }
} // fails on abacbcd without distinctchar
*/

class Solution {
    public List<Integer> partitionLabels(String s) {
        int []map=new int[26];
        int n =s.length();
        for(int i=0;i<n;i++){
            map[s.charAt(i)-'a']=i;
        }

        int maxreach=0;
        int start=0;
        List<Integer> res=new ArrayList<>();
        for(int i=0;i<n;i++){
            maxreach=Math.max(maxreach, map[s.charAt(i)-'a']);

            if(i==maxreach){
                res.add(i-start+1);
                start=i+1;
            }
        }
        return res;
    }
}
