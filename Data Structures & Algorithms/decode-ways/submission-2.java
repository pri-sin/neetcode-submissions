class Solution {
    public int numDecodings(String s) {
        Integer memo[]=new Integer[s.length()];
        return getResult(s, 0, memo);
    }

    public int getResult(String s, int i, Integer []memo){
        if(i>=s.length()){
            return 1;
        }

        if(memo[i]!=null) return memo[i];
        //take 1;
        int take1=0;
        if(s.charAt(i)!='0'){
            take1=getResult(s, i+1, memo);
        }

        //take 2
        int take2=0;
        if((i+2)<=s.length()){
        int x=Integer.parseInt(s.substring(i,i+2));

        if(x>=10 && x<=26){
            take2=getResult(s, i+2, memo);
        }
        }

        return memo[i]=take1+take2;
    }
}

/*
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
*/