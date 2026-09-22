class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<int []> pq=new PriorityQueue<>((a, b) -> { 
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]); // Primary: smaller distance first
            }
            return Integer.compare(a[1], b[1]);     // Secondary: smaller value first
        });

        for(int i=0;i<arr.length;i++){
            pq.offer(new int[]{Math.abs(x-arr[i]),arr[i]});
        }

        List<Integer> res=new ArrayList<>();
        while(pq.size()>0 && k>0){
            res.add(pq.poll()[1]);
            k--;
        }

        return res.stream().sorted().toList();
    }
}