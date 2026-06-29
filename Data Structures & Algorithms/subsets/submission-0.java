class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currset = new ArrayList<>();
        getAllSubsets(nums, 0, currset, result);
        return result;
    }

    public void getAllSubsets(int[] nums, int start, List<Integer> currset, List<List<Integer>> result){
        result.add(new ArrayList<>(currset));

        for(int i=start;i<nums.length;i++){
            currset.add(nums[i]);
            getAllSubsets(nums, i+1, currset, result);
            currset.remove(currset.size()-1);
        }
    }
}
