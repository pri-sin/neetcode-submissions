/*class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> curr=new ArrayList<>();
        List<List<Integer>> res=new ArrayList<>();
        getResult(nums, target, res, curr, 0);
        return res;
    }

    public void getResult(int[] nums,int target, List<List<Integer>> res, List<Integer> curr, int start){
        if(target==0 && curr.size()==4){
            res.add(new ArrayList<>(curr));
            return;
        }

        if(curr.size()>=4) return;

        for(int i=start;i<nums.length;i++){
            if(i>start && nums[i]==nums[i-1]){
                continue;
            }

            curr.add(nums[i]);
            getResult(nums, target-nums[i], res, curr, i+1);
            curr.remove(curr.size()-1);
        }
    }
}*/ // will give TLE and integer overflow on subtraction
/*
Backtracking becomes the ideal approach when $k$ is variable (e.g., solving general $k$-Sum where $k$ is passed as a parameter).In general $k$-Sum, recursion/backtracking is used to reduce the problem from $k$-Sum down to $k-1$-Sum, until reaching the base case of 2Sum, which is solved using two pointers
*/

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for(int i=0;i<n-3;i++){
            if(i>0 && nums[i]==nums[i-1]){
                continue;
            }

            for(int j=i+1;j<n-2;j++){
                if(j>i+1 && nums[j]==nums[j-1]){
                    continue;
                }

                int left=j+1;
                int right=n-1;
                while(left<right){
                    long sum=(long)nums[i]+nums[j]+nums[left]+nums[right];
                    if(sum==target){
                        res.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        left++;
                        right--;
                        while(left<right && nums[left]==nums[left-1]){
                            left++;
                        }

                        while(left<right && nums[right]==nums[right+1]){
                            right--;
                        }

                    }

                    if(sum<target){
                        left++;
                    }

                    if(sum>target){
                        right--;
                    }
                }
            }
        }

        return res;
    }
}