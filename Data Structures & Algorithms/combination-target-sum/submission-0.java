class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> result=new ArrayList();
        List<Integer> currlist=new ArrayList<>();
        getCombinations(nums, target, 0, currlist, result);
        return result;
    }

    public void getCombinations(int[] nums, int target,int start, List<Integer> currlist,List<List<Integer>> result){
        if(target==0){
            result.add(new ArrayList<>(currlist));
            return;
        }

        if(target<0) return;

        for(int i=start;i<nums.length;i++){
            currlist.add(nums[i]);
            getCombinations(nums, target-nums[i],i, currlist, result);
            currlist.remove(currlist.size()-1);
        }
    }
}
