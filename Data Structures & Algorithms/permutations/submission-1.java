class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        List<Integer> curr=new ArrayList<>();
        boolean []visited=new boolean[nums.length];
        getpermutations(nums, visited, curr, result);
        return result;
    }

    public void getpermutations(int[] nums,boolean []visited, List<Integer> curr,List<List<Integer>> result){
        if(curr.size()==nums.length){
            result.add(new ArrayList<>(curr));
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(visited[i]) continue;
            curr.add(nums[i]);
            visited[i]=true;
            getpermutations(nums, visited, curr, result);
            visited[i]=false;
            curr.remove(curr.size()-1);
        }
    }
}
