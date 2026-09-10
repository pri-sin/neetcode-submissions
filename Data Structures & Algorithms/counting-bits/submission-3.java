/*class Solution {
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
}*/


class Solution {
    public int[] countBits(int n) {
        int res[]=new int[n+1];
        int k=0;
        for(int i=0;i<=n;i++){
            int x=i;
            int count=0;
            while(x>0){
                int y=x&1;
                if(y==1) count++;
                x=x>>1;
            }
            res[k++]=count;
        }
        return res;
    }
}