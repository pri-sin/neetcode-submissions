/*class Solution {
    public int maxCoins(int[] nums) {
        int n=nums.length;
        int maxcount=0;
        for(int i=0;i<n;i++){
            int []leftarr=new int[n];
            int []rightarr=new int[n];

            leftarr[0]=-1;
            rightarr[n-1]=-1;
            for(int j=0;j<n;j++){
                if(j!=0) leftarr[j]=j-1;
                if(j!=n-1)rightarr[j]=j+1;
            }
            maxcount=Math.max(maxcount,getMaxCoins(nums, i, leftarr,rightarr));
        }
        return maxcount;
    }

    public int getMaxCoins(int []nums, int i, int []leftarr, int []rightarr){
        if(i<0 || i>=nums.length){
            return 0;
        }
        
        int res=0;
        int l=leftarr[i];
        int r=rightarr[i];
        if(l!=-1 && r!=-1){
            res=nums[l]*nums[i]*nums[r];
            leftarr[r]=l;
            rightarr[l]=r;
        }else if(l==-1 && r==-1){
            res=nums[i];
        }else if(l==-1){
            res=nums[i]*nums[r];
            leftarr[r]=l;
        }else if(r==-1){
            res=nums[i]*nums[l];
            rightarr[l]=r;
        }

        int []larr=leftarr.clone();
        int []rarr=rightarr.clone();
        int deletenr=getMaxCoins(nums, r, leftarr, rightarr);
        int deletenl=getMaxCoins(nums, l, larr, rarr);

        return res+Math.max(deletenr, deletenl);
    }
}*/

/*class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int maxcount = 0;
        for (int i = 0; i < n; i++) {
            int[] leftarr = new int[n];
            int[] rightarr = new int[n];

            // Properly initialize doubly-linked index pointers
            for (int j = 0; j < n; j++) {
                leftarr[j] = j - 1;
                rightarr[j] = j + 1;
            }
            leftarr[0] = -1;
            rightarr[n - 1] = -1;

            maxcount = Math.max(maxcount, getMaxCoins(nums, i, leftarr, rightarr));
        }
        return maxcount;
    }

    public int getMaxCoins(int[] nums, int i, int[] leftarr, int[] rightarr) {
        if (i < 0 || i >= nums.length) {
            return 0;
        }

        int l = leftarr[i];
        int r = rightarr[i];

        // Boundary elements default to 1
        int leftVal = (l == -1) ? 1 : nums[l];
        int rightVal = (r == -1) ? 1 : nums[r];
        int res = leftVal * nums[i] * rightVal;

        // Clone arrays for the right branch to isolate state
        int[] rLeft = leftarr.clone();
        int[] rRight = rightarr.clone();
        if (l != -1) rRight[l] = r;
        if (r != -1) rLeft[r] = l;
        int deletenr = getMaxCoins(nums, r, rLeft, rRight);

        // Clone arrays for the left branch to isolate state
        int[] lLeft = leftarr.clone();
        int[] lRight = rightarr.clone();
        if (l != -1) lRight[l] = r;
        if (r != -1) lLeft[r] = l;
        int deletenl = getMaxCoins(nums, l, lLeft, lRight);

        return res + Math.max(deletenr, deletenl);
    }
}//Gives TLE, also implementing DP kind of impossible here
*/

class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        // Pad array with boundary 1s
        int[] padded = new int[n + 2];
        padded[0] = 1;
        padded[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            padded[i + 1] = nums[i];
        }

        int[][] memo = new int[n + 2][n + 2];
        return getMaxCoins(padded, 0, n + 1, memo);
    }

    private int getMaxCoins(int[] nums, int left, int right, int[][] memo) {
        // Base case: no balloons between left and right boundaries
        if (left + 1 >= right) {
            return 0;
        }

        if (memo[left][right] > 0) {
            return memo[left][right];
        }

        int maxCoins = 0;
        // k represents the LAST balloon to burst in (left, right)
        for (int k = left + 1; k < right; k++) {
            int coinsFromK = nums[left] * nums[k] * nums[right];
            int leftSubproblem = getMaxCoins(nums, left, k, memo);
            int rightSubproblem = getMaxCoins(nums, k, right, memo);

            maxCoins = Math.max(maxCoins, coinsFromK + leftSubproblem + rightSubproblem);
        }

        memo[left][right] = maxCoins;
        return maxCoins;
    }
}
