class Solution {
    public int minDistance(String word1, String word2) {
        Integer memo[][]=new Integer[word1.length()+1][word2.length()+1];
        return getDist(word1, word2, 0,0,memo);
    }

    public int getDist(String word1, String word2, int i, int j, Integer [][]memo){
        if(j==word2.length() && i==word1.length()){
            return 0;
        }

        if(i==word1.length()){
            return Math.abs(word2.length()-j);
        }

        if(j==word2.length()){
            return Math.abs(word1.length()-i);
        }

        if(memo[i][j]!=null) return memo[i][j];

        if(word1.charAt(i)==word2.charAt(j)){
            return getDist(word1, word2, i+1, j+1, memo);
        }

        int delete=getDist(word1, word2, i+1, j, memo);
        int insert=getDist(word1, word2, i, j+1, memo);
        int replace=getDist(word1, word2, i+1, j+1, memo);

        return memo[i][j]=1+Math.min(delete,Math.min(insert, replace));
    }
}
