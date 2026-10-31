/*class Solution {
    public boolean makesquare(int[] matchsticks) {
        int sum=0;
        for(int match:matchsticks){
            sum+=match;
        }

        if(sum%4!=0) return false;
        int subsum=sum/4;

        boolean visited[]=new boolean[matchsticks.length];
        return getResult(matchsticks, 0, subsum, visited)==4?true:false;
    }

    public int getResult(int[] matchsticks, int i, int target, boolean []visited){
        if(target==0){
            return 1;
        }

        int count=0;
        for(int j=i;j<matchsticks.length;j++){
            if(!visited[j]){
                visited[j]=true;
                count+=getResult(matchsticks, j+1, target-matchsticks[j], visited);
                visited[j]=false;;
            }
            
        }
        return count;

    }
}//this will fail when [1, 1, 1, 1, 1, 1, 1, 1]
*/


class Solution {
    public boolean makesquare(int[] matchsticks) {
        if (matchsticks == null || matchsticks.length < 4) {
            return false;
        }

        int totalSum = 0;
        for (int match : matchsticks) {
            totalSum += match;
        }

        if (totalSum % 4 != 0) {
            return false;
        }

        int target = totalSum / 4;
        int n = matchsticks.length;
        int numStates = 1 << n;

        int[] dp = new int[numStates];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int mask = 0; mask < numStates; mask++) {
            if (dp[mask] == -1) {
                continue;
            }

            for (int i = 0; i < n; i++) {
                // If the i-th matchstick is not yet used
                if ((mask & (1 << i)) == 0) {
                    if (dp[mask] + matchsticks[i] <= target) {
                        // Reset accumulated length to 0 whenever a full side reaches target
                        dp[mask | (1 << i)] = (dp[mask] + matchsticks[i]) % target;
                    }
                }
            }
        }

        return dp[numStates - 1] == 0;
    }
}