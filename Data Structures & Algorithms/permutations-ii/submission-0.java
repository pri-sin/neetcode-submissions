class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> curr=new ArrayList<>();
        boolean visited[]=new boolean[nums.length];
        getResult(nums,res, curr, visited);
        return res;
    }

    public void getResult(int []nums,List<List<Integer>> res, List<Integer> curr, boolean []visited){
        if(curr.size()==nums.length){
            if(!res.contains(curr)){
                res.add(new ArrayList<>(curr));
            }
            return;
        }


        for(int i=0;i<nums.length;i++){
            if(!visited[i]){
                visited[i]=true;
                curr.add(nums[i]);
                getResult(nums,res, curr, visited);
                curr.remove(curr.size()-1);
                visited[i]=false;
            }
            
        }
    }

}