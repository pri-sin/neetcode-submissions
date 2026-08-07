class Solution {
    public int[] productExceptSelf(int[] nums) {
        int prefix[]=new int[nums.length];

        prefix[0]=1;
        for(int i=1;i<nums.length;i++){
            prefix[i]=prefix[i-1]*nums[i-1];
        }
        int k=1;
        for(int i=nums.length-2;i>=0;i--){
            k=nums[i+1]*k;
            prefix[i]=prefix[i]*k;
        }

        return prefix;
    }
}  
