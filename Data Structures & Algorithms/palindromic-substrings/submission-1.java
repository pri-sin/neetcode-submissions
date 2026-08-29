class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        int totalPalindromes=0;
        for(int i=0;i<n;i++){
            totalPalindromes+=helper(s,i,i);
            totalPalindromes+=helper(s,i,i+1);
        }
        return totalPalindromes;
    }

    public int helper(String s, int i, int j){
        int count=0;
        while(i>=0 && j<s.length() && s.charAt(i)==s.charAt(j)){
            count++;
            i--;
            j++;
        }
        return count;
    }
}
