class Solution {
    public int[] countBits(int n) {
        int []res=new int[n+1];
        for(int i=0;i<=n;i++){
            //res[i]=Integer.bitCount(i);
            int count=0;
            int k=i;
            while(k!=0){
                k&=(k-1);
                count++;
            }
            res[i]=count;
        }
        return res;
    }
}
