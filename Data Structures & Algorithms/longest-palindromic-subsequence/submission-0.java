class Solution {
    public int longestPalindromeSubseq(String s) {
        String reversed=new StringBuilder(s).reverse().toString();
        Integer memo[][]=new Integer[s.length()][s.length()];
        return solve(s, reversed, 0,0, memo);
    }

    public int solve(String s, String r, int i,int j, Integer [][]memo){
        if(i>=s.length() || j>=r.length()) return 0;

        if(memo[i][j]!=null) return memo[i][j];

        if(s.charAt(i)==r.charAt(j)){
            return memo[i][j]=1+solve(s,r,i+1,j+1,memo);
        }

        int skip1=solve(s,r,i+1,j,memo);
        int skip2=solve(s,r,i,j+1,memo);

        return memo[i][j]=Math.max(skip1,skip2);
    }
}