class Solution {
    public boolean canJump(int[] nums) {
        int []memo=new int[nums.length];
        return dfs(nums, 0, memo);
    }
    
    public boolean dfs(int[] nums,int i, int memo[]){
        if(i>=nums.length-1) return true;

        if(memo[i]!=0) return memo[i]==1;

        for(int j=1;j<=nums[i];j++){
            if(dfs(nums, i+j, memo)){
                memo[i]=1;
                return true;
            }
        }

        memo[i]=-1;
        return false;
    }
}
