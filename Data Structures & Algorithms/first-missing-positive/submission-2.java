class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int i=0;
        while(i<n){
            int correctidx=nums[i]-1;
            if(nums[i]>0 && nums[i]<=n && nums[i]!=nums[correctidx]){
                int temp=nums[correctidx];
                nums[correctidx]=nums[i];
                nums[i]=temp;
            }else{
                i++;
            }
        }

        for(int j=0;j<n;j++){
            if(nums[j]!=j+1){
                return j+1;
            }
        }

        return n+1;
    }

    /*public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int i=0;
        int left=0;
        while(i<n){
            if(nums[i]>0 && nums[i]<n+1 && nums[i]!=i+1){
                int inew=nums[i]-1;
                int temp=nums[inew];
                nums[inew]=nums[i];
                nums[i]=temp;
            }

            if(nums[i]==i+1 || nums[i]>=n+1 || nums[i]<=0 || nums[i]<left){
                i++;
            }

            if(nums[left]==left+1){
                left++;
            }
        }
        return left+1;
    }//Gives TLE*/
}