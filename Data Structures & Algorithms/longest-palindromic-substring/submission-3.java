/*class Solution {
    public String longestPalindrome(String s) {
        String []dp=new String[s.length()+1];
        return getResult(s, 0, dp);
    }

    public String getResult(String s, int start, String dp[]){
        if(start>=s.length()){
            return "";
        }

        if(dp[start]!=null) return dp[start];
        String ans="";
        for(int i=start;i<s.length();i++){
            if(isPalindrome(s.substring(start, i+1))){
                if(ans.length()<(i-start+1)){
                    ans=s.substring(start, i+1);
                }
            }

            String recurans=getResult(s, i+1, dp);
            if(ans.length()<recurans.length()){
                ans=recurans;
            }

        }
        return dp[start]=ans;
    }

    public boolean isPalindrome(String s){
        int low=0;
        int high=s.length()-1;

        while(low<=high){
            if(s.charAt(low)!=s.charAt(high)){
                return false;
            }
            low++;
            high--;
        }
        return true;
    }
}// Gives TLE
*/

class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            // Odd length palindrome (centered at i)
            int len1 = expandAroundCenter(s, i, i);
            // Even length palindrome (centered between i and i + 1)
            int len2 = expandAroundCenter(s, i, i + 1);

            int maxLen = Math.max(len1, len2);

            // Update boundaries if a longer palindrome is found
            if (maxLen > end - start + 1) {
                start = i - (maxLen - 1) / 2;
                end = i + maxLen / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Length of the valid palindrome found
        return right - left - 1;
    }
}


















/*





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
}*/
