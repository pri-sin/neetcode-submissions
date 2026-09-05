class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        Integer memo[]=new Integer[n+1];
        return getCount(nums, 0, memo);
    }

    public int getCount(int []nums,int i, Integer memo[]){
        if(i>=nums.length-1){
            return 0;
        }

        if(memo[i]!=null) return memo[i];

        int count=Integer.MAX_VALUE;
        for(int j=nums[i];j>0;j--){
            int x=getCount(nums, i+j, memo);
            if(x==Integer.MAX_VALUE) continue;
            count=Math.min(count, 1+x);
        }
        return memo[i]=count;
    }
}
