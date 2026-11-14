class Solution {
    public int coinChange(int[] coins, int amount) {
        Integer [][]memo=new Integer[amount+1][coins.length];
        int res= getResult(coins, 0 , amount, memo);
        return res==(int)1e9?-1:res;
    }

    public int getResult(int []coins, int i, int amount, Integer memo[][]){
        if(amount==0){
            return 0;
        }

        if(amount<0 || i>=coins.length){
            return (int)1e9;
        }

        if(memo[amount][i]!=null) return memo[amount][i];

        int take=1+getResult(coins, i, amount-coins[i], memo);
        int skip=getResult(coins, i+1, amount, memo);

        return memo[amount][i]=Math.min(take, skip);
    }
}
/*
































class Solution {
    public int coinChange(int[] coins, int amount) {
        int memo[]=new int[amount+1];
        Arrays.fill(memo,-2);
        
        return dfs(coins, amount, memo);
    }

    public int dfs(int []coins, int amount, int memo[]){
        if(amount==0) return 0;
        if(amount<0) return -1;

        if(memo[amount]!=-2){
            return memo[amount];
        }

        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = dfs(coins, amount - coin, memo);
            // If a valid path was found from the subproblem
            if (res >= 0) {
                minCoins = Math.min(minCoins, 1 + res);
            }
        }

        memo[amount]=minCoins==Integer.MAX_VALUE?-1:minCoins;
        return memo[amount];
    }
}
*/