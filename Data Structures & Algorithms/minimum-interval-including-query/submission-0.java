/*class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, (a,b)->Integer.compare(a[0],b[0]));
        int output[]=new int[queries.length];
        Arrays.fill(output,-1);
        for(int j=0;j<queries.length;j++){
            for(int i=0;i<intervals.length;i++){
                if(intervals[i][0]<=queries[j]){
                    if(queries[j]<=intervals[i][1]){
                        if(output[j]==-1 || output[j]>(intervals[i][1]-intervals[i][0]+1)){
                            output[j]=(intervals[i][1]-intervals[i][0]+1);
                        }
                    }
                }else{
                    break;
                }
            }
        }
        return output;
    }
}// Gives TLE
*/


class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, (a,b)->Integer.compare(a[0],b[0]));
        int output[]=new int[queries.length];
        HashMap<Integer, Integer> map=new HashMap<>();
        PriorityQueue<int []> pq=new PriorityQueue<>((a,b)->a[0]-b[0]);
        int i=0;
        for(int q:Arrays.stream(queries).sorted().toArray()){
            while(i<intervals.length && intervals[i][0]<=q){
                int r=intervals[i][1];
                int l=intervals[i][0];
                pq.add(new int[]{r-l+1, r});
                i++;
            }

            while(!pq.isEmpty() && pq.peek()[1]<q){
                pq.poll();
            }

            if(pq.size()==0){
                map.put(q, -1);
            }else{
                map.put(q,pq.peek()[0]);
            }
        }

        for(i=0;i<queries.length;i++){
            output[i]=map.get(queries[i]);
        }

        return output;
    }
}