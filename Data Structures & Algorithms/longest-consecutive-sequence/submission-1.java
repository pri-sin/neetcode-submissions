class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for(int i=0;i<nums.length;i++){
            set.add(nums[i]);
        }
        int count=1;
        int maxcount=0;
        for(int i=0;i<nums.length;i++){
            if(!set.contains(nums[i]-1)){
                int numsi=nums[i];
                while(set.contains(numsi+1)){
                    count++;
                    numsi=numsi+1;
                }
                maxcount=Math.max(maxcount,count);
                count=1;
            }
        }
        return maxcount;
    }
}
