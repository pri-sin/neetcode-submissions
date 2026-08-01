class Solution {
    public int hammingWeight(int n) {
        //return Integer.bitCount(n);

        int count=0;
        while(n!=0){
            n&=(n-1); // Clears the lowest set bit
            count++;
        }
        return count;
    }
}
