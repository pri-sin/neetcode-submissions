/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */

class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int n = mountainArr.length();

        //find the peak
        int l=1, r=n-2;
        int peak=-1;
        while(l<=r){
            int m=(l+r)/2;
            int left=mountainArr.get(m-1);
            int mid=mountainArr.get(m);
            int right=mountainArr.get(m+1);

            if(left<mid && mid<right){
                l=m+1;
            }

            if(left>mid && mid>right){
                r=m-1;
            }

            if(left<mid && mid>right){
                peak=m;
                break;
            }
        }

        //Search left portion
        l=0;
        r=peak;
        while(l<=r){
            int m=(l+r)/2;
            int mid=mountainArr.get(m);
            if(mid==target){
                return m;
            }else if(mid<target){
                l=m+1;
            }else{
                r=m-1;
            }
        }

        //Search right portion
        l=peak;
        r=n-1;
        while(l<=r){
            int m=(l+r)/2;
            int mid=mountainArr.get(m);
            if(mid==target){
                return m;
            }else if(mid<target){
                r=m-1;
            }else{
                l=m+1;
            }
        }
        return -1;   
    }
}


/*class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int left=0;
        int right=mountainArr.length()-1;
        int ans=-1;
        while(left<=right){
            int mid=left+(right-left)/2;

            if(mountainArr.get(mid)==target){
                ans=mid;
                right=mid-1;
            }else if(mountainArr.get(left)<mountainArr.get(mid)){
                if(mountainArr.get(left)<=target && target<mountainArr.get(mid)){
                    right=mid-1;
                }else{
                    left=mid+1;
                }
            }else{
                if(mountainArr.get(mid)>target && target>=mountainArr.get(right)){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }
        return ans;
    }
}// Fails some cases, [1,5,2] target=2. This is the rotated sorted array method*/