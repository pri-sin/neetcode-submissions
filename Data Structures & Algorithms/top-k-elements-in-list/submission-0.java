class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map=new HashMap<>();

        for(int i=0;i<nums.length;i++){
            map.put(nums[i], map.getOrDefault(nums[i],0)+1);
        }
         
        PriorityQueue<Map.Entry<Integer,Integer>> fq= new PriorityQueue<>(
            (a,b)-> a.getValue()-b.getValue()
        );

        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            fq.add(entry);
            if(fq.size()>k){
                fq.poll();
            }
        }

        int res[]=new int[k];
        for(int i=0;i<k;i++){
            res[i]=fq.poll().getKey();
        }

        return res;
    }
}
