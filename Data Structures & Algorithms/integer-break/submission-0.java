class Solution {
    public int integerBreak(int n) {
        return getResult(n, 1);
    }

    public int getResult(int n, int i){
        if(n==0){
            return 1;
        }

        if(i>=n){
            return n;
        }

        int withbreak=i*getResult(n-i, i);
        int nobreak=0;
        if(i+1<n){
            nobreak=getResult(n,i+1);
        }

        return Math.max(withbreak, nobreak);
    }
}