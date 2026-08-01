class Solution {
    public int numSquares(int n) {
        if (n <= 0) return 0;

        int maxI = (int) Math.sqrt(n);
        // memo[i][n] stores the answer for target n using candidate squares from 1^2 up to i^2
        Integer[][] memo = new Integer[maxI + 1][n + 1];

        return solve(n, maxI, memo);
    }

    private int solve(int n, int i, Integer[][] memo) {
        // Base case 1: Target reached
        if (n == 0) return 0;
        
        // Base case 2: Ran out of candidates (i < 1)
        if (i == 0) return (int) 1e9;

        if (memo[i][n] != null) return memo[i][n];

        // If candidate square i^2 exceeds remaining target n, we must skip to i - 1
        if (i * i > n) {
            return memo[i][n] = solve(n, i - 1, memo);
        }

        // Option 1: Take square (i * i), stay at index 'i' (allows using the same square again)
        int take = 1 + solve(n - i * i, i, memo);

        // Option 2: Skip square (i * i) and move to smaller candidate (i - 1)
        int skip = solve(n, i - 1, memo);

        return memo[i][n] = Math.min(take, skip);
    }
}