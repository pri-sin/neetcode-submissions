/*class Solution {
    public int trap(int[] height) {
        int count=0;
        for(int i=1;i<height.length;i++){
            int left=i-1;
            int right=i+1;
            int leftmax=0;
            int rightmax=0;

            while(left>=0){
                if(leftmax<height[left]){
                    leftmax=height[left];
                }
                left--;
            }

            while(right<height.length){
                if(rightmax<height[right]){
                    rightmax=height[right];
                }
                right++;
            }

            int minheight=Math.min(leftmax,rightmax);
            if(minheight>height[i]){
                count+=minheight-height[i];
            }
        }
        return count;
    }
}//Gives TLE
*/
class Solution {
    public int trap(int[] height) {
        int left=0, right=height.length-1;
        int leftmax=0, rightmax=0, count=0;

        while(left<right){
            if(height[left]<=height[right]){
                if(leftmax<height[left]){
                    leftmax=height[left];
                }else{
                    count+=leftmax-height[left];
                }
                left++;
            }else{
                if(rightmax<height[right]){
                    rightmax=height[right];
                }else{
                    count+=rightmax-height[right];
                }
                right--;
            }
        }
        return count;
    }
}

