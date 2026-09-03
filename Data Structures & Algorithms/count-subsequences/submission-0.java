class Solution {
    public int numDistinct(String s, String t) {
        Integer memo[][]=new Integer[s.length()+1][t.length()+1];
        return getResult(s, t, 0, 0, memo);
    }

    public int getResult(String s, String t, int i, int j, Integer [][]memo){
        if(t.length()==j){
            return 1;

        }
        if(i==s.length()){
            return 0;
        }

        if(memo[i][j]!=null) return memo[i][j];

        int match=0;
        if(s.charAt(i)==t.charAt(j)){
            match=getResult(s,t,i+1,j+1, memo);
        }

        int skip=getResult(s,t,i+1,j, memo);

        return memo[i][j] = match+skip;
    }
}
