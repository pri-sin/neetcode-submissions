class Solution {
    public int scoreOfString(String s) {
        int score=0;
        for(int i=0;i<s.length()-1;i++){
            int c=s.charAt(i);
            int cnext=s.charAt(i+1);
            score+=Math.abs(cnext-c);
        }
        return score;
    }
}