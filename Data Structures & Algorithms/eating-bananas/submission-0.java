class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int maxK=0;
        for(int i=0;i<piles.length;i++){
            if(maxK<piles[i]){
                maxK=piles[i];
            }
        }

        int l=1, r=maxK;
        int k = maxK;
        while(l<=r){
            int mid=l+(r-l)/2;
            long counth=0;
            for(int j=0;j<piles.length;j++){
                counth += (piles[j] + mid - 1L) / mid;
            }

            if(counth<=h){
                k=mid;
                r=mid-1;
            } else {
                l=mid+1;
            }

        }

        return k;
    }
}
