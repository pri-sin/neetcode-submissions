class Solution {
    public int change(int amount, int[] coins) {
        Integer memo[][]=new Integer[coins.length][amount+1];
        return dfs(amount, coins, 0, memo);
    }

    public int dfs(int amount, int[] coins, int i, Integer [][]memo){
        if(amount==0) return 1;

        if(i>=coins.length || amount<0) return 0;

        if(memo[i][amount]!=null) return memo[i][amount];

        int take=dfs(amount-coins[i], coins, i, memo);
        int skip=dfs(amount, coins, i+1, memo);

        return memo[i][amount]=take+skip;
    }
}
