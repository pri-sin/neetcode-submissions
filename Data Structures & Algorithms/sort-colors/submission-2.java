/*class Solution {
    public void sortColors(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;

        for (int num : nums) {
            if (num == 0) count0++;
            else if (num == 1) count1++;
            else count2++;
        }

        int i = 0;
        while (count0-- > 0) nums[i++] = 0;
        while (count1-- > 0) nums[i++] = 1;
        while (count2-- > 0) nums[i++] = 2;
    }
}
*/
//can just count each of 0,1 and 2. and update the array accordingly

class Solution {
    public void sortColors(int[] nums) {
        int left=0, mid=0, right=nums.length-1;

        while(mid<=right){
            if(nums[mid]==0){
                swap(nums, left++, mid++);
            }else if(nums[mid]==1){
                mid++;
            }else{
                swap(nums, mid,right--);
            }
        }
    }

    public void swap(int []nums, int i, int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}