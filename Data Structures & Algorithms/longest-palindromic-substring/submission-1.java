class Solution {
    public String longestPalindrome(String s) {
        int n=s.length();
        String memo[][]=new String[n][n];
        return getPalindromes(s,0,n-1,memo);//always take care its n-1 here else gives java.lang.StringIndexOutOfBoundsException error
    }

    public String getPalindromes(String s,int i, int j,String memo[][]){
        if(i>j){
            return "";
        }

        if(memo[i][j]!=null){
            return memo[i][j];
        }

        if(isPalindrome(s,i,j)){
            memo[i][j]=s.substring(i,j+1);
            return memo[i][j];
        }

        String leftExploration=getPalindromes(s,i+1,j,memo);
        String rightExploration=getPalindromes(s,i,j-1,memo);

        memo[i][j]=leftExploration.length()>rightExploration.length()?leftExploration:rightExploration;
        return memo[i][j];
    }

    public boolean isPalindrome(String s, int low, int high){

        while(low<high){
            if(s.charAt(low++)!=s.charAt(high--)){
                return false;
            }
        }
        return true;
    }
}
