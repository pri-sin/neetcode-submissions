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

/*
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> curr=new ArrayList<>();
        solve(candidates, target, res, curr, 0);
        return res;
    }

    public void solve(int[] candidates, int target, List<List<Integer>> res, List<Integer> curr, int start){
        if(target==0){
            res.add(new ArrayList<>(curr));
            return;
        }
        //if(target<0) return; can use when we dont have the if condition inside forloop

        for(int i=start;i<candidates.length;i++){
            if(target-candidates[i]>=0){
                curr.add(candidates[i]);
                solve(candidates, target-candidates[i], res, curr, i);
                curr.remove(curr.size()-1);
            }
        }
    }
}*/
