class Solution {
    public int search(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;

            if(nums[mid]==target){
                return mid;
            }

            if(nums[left]<=nums[mid]){
                if(nums[left]<=target && target<nums[mid]){
                    right=mid-1;
                }else{
                    left=mid+1;
                }
            }else{
                if(nums[mid]<target && target<=nums[right]){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }
        return -1;
    }
}
/*
It means treating both `left` and `right` as **valid, unchecked indices** that could still contain your target—so the initialization, loop condition, and pointer updates lock together into one fixed template with zero guesswork.

In the **inclusive `[left, right]**` pattern, three rules always go together:

1. **Both bounds start inside the array:**
`left = 0` and `right = nums.length - 1` (both are valid indices to check).
2. **The loop runs while `left <= right`:**
Even when `left` and `right` meet at the exact same index (`[3, 3]`), there is still one unchecked element left, so the loop must run one final time.
3. **Updates always skip `mid` (`left = mid + 1` / `right = mid - 1`):**
Because you already checked `nums[mid]` and know it isn't the target, `mid` is excluded from the next range.

### Why this prevents bugs

There is another common binary search style—the **half-open `[left, right)**` pattern—where `right` is treated as an *excluded* boundary:

* `right` starts at `nums.length`
* The loop uses `while (left < right)`
* Moving left uses `right = mid` (not `mid - 1`)

Most off-by-one bugs and infinite loops happen when someone accidentally mixes lines from both styles (for example, writing `while (left <= right)` alongside `right = mid`). By committing strictly to the inclusive `[left, right]` pattern whenever you are searching for an exact match, you never have to pause and debate whether to use `<` or `<=`, or `mid` vs. `mid - 1`—it becomes automatic muscle memory.
/*class Solution {
    public int search(int[] nums, int target) {
        int l=0;
        int r=nums.length-1;

        while(l<=r){
            int mid=(l+r)/2;

            if(nums[mid]==target){
                return mid;
            }

            if(nums[l]<=nums[mid]){
                if(nums[l]<=target && target<nums[mid]){
                    r=mid-1;
                }else{
                    l=mid+1;
                }
            }else{
                if(nums[mid]<target && target<=nums[r]){
                    l=mid+1;
                }else{
                    r=mid-1;
                }
            }
        }
        return -1;
    }
}*/
