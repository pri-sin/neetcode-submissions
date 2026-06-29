class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result=new ArrayList();
        List<Integer> currlist=new ArrayList<>();
        Arrays.sort(candidates);
        getCombinations2(candidates, target, 0, currlist, result);
        return result;
    }

    public void getCombinations2(int[] nums, int target,int start, List<Integer> currlist,List<List<Integer>> result){
        if(target==0){
            result.add(new ArrayList<>(currlist));
            return;
        }

        if(target<0) return;

        for(int i=start;i<nums.length;i++){
            if(i>start && nums[i-1]==nums[i]) continue;
            currlist.add(nums[i]);
            getCombinations2(nums, target-nums[i],i+1, currlist, result);
            currlist.remove(currlist.size()-1);
        }
    }
}
