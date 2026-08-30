class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n=s.length();
        Boolean memo[]=new Boolean[n];
        return getWords(s, 0, wordDict, memo);
    }

    public boolean getWords(String s,int i,List<String> wordDict, Boolean []memo){
        if(i==s.length()){
            return true;
        }

        if(memo[i]!=null) return memo[i];
        for(int j=i;j<=s.length();j++){
            if(wordDict.contains(s.substring(i,j)) && getWords(s, j, wordDict,memo)){
                return true;
            }
        }
        return memo[i]=false;
    }
}
