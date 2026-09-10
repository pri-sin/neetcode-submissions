class Solution {
    public int missingNumber(int[] nums) {
        //XOR
        int res=0;
        for(int i=1;i<=nums.length;i++){
            res=res^i^nums[i-1];
        }
        return res;
    }
}

/*
class Solution {
    public int missingNumber(int[] nums) {
        int res=nums.length;
        for(int i=0;i<nums.length;i++){
            res=res^nums[i]^i;
        }
        return res;
    }
}
*/