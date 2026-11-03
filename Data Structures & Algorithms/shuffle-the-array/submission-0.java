class Solution {
    public int[] shuffle(int[] nums, int n) {
        int res[]=new int[nums.length];
        int k=0;
        for(int i=0;i<n;i++){
            res[k++]=nums[i];
            res[k++]=nums[i+n];
        }
        return res;
    }
}
