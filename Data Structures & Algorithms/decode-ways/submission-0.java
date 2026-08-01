class Solution {
    int count=0;
    public int numDecodings(String s) {
        int memo[]=new int[s.length()];
        return dfs(s, 0, memo);
    }

    public int dfs(String s,int i, int memo[]){
        if(i==s.length()){
            return 1;
        }

        if(s.charAt(i)=='0') return 0;

        if(memo[i]!=0) return memo[i];


       int ways=dfs(s, i+1, memo);

       if(i+1<s.length()){
            int x=Integer.parseInt(s.substring(i,i+2));
            if(x<=26){
                ways+=dfs(s, i+2, memo);
            }
       }

        return memo[i]=ways;
    }
}
