/*class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int res[]=new int[n];
        if(k>=n){
            k=k%n;
        }
        for(int i=0;i<n;i++){
            int x= (i+k)%n;
            res[x]=nums[i];
        }

        for(int i=0;i<n;i++){
            nums[i]=res[i];
        }
    }
}*/// this is not in place

class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if(k>=n){
            k=k%n;
        }
        for(int i=0;i<k;i++){
            int dummy=nums[n-1];
            for(int j=n-1;j>=1;j--){
                nums[j]=nums[j-1];
            }
            nums[0]=dummy;
        }

    }
}