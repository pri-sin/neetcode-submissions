class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->Double.compare(calc(b),calc(a)));

        for(int []point:points){
            pq.offer(point);
            if(pq.size()>k){
                pq.poll();
            }
        }

        return pq.toArray(new int[pq.size()][2]);
    }

    public double calc(int []point){
        double xsq=point[0]*point[0];
        double ysq=point[1]*point[1];
        return (double)Math.sqrt(xsq+ysq);
    }
}
