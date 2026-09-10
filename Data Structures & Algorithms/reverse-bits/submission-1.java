class Solution {
    public int reverseBits(int n) {
        int res=0;
        for(int i=0;i<32;i++){
            res=(res<<1) | (n&1);
            n>>>=1;
        }
        return res;
    }
}

/*
class Solution {
    public int reverseBits(int n) {
        int res=0;
        for(int i=0;i<32;i++){
            int x=n&1;
            res=(res<<1) + x;
            n=n>>1;
        }
        return res;
    }
}*/