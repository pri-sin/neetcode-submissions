class Solution {
    public int[] productExceptSelf(int[] nums) {
        int prefix[] = new int[nums.length];
        int suffix[]= new int[nums.length];
        int []res=new int[nums.length];
        prefix[0]=1;
        int n=nums.length;
        suffix[n-1]=1;
        for(int i=1;i<n;i++){
            prefix[i]=prefix[i-1]*nums[i-1];
            suffix[n-1-i]=suffix[n-i]*nums[n-i];
        }
    
        for(int i=0;i<nums.length;i++){
            res[i]=prefix[i]*suffix[i];
        }

        return res;
    }
}  
