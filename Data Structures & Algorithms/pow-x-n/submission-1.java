/*class Solution {
    public double myPow(double x, int n) {
        double res=1;
        int sign=1;

        if(n<0){
            sign=-1;
            n=n*-1;
        }
        while(n>0){
            res=res*x;
            n--;
        }
        return sign==-1? 1/res : res;
    }
}// Gives TLE
*/

class Solution {
    public double myPow(double x, int n) {
        int sign=1;
        if(n<0){
            sign=-1;
            n=n*-1;
        }
        double res=recurse(x,n);
        return sign==-1? 1/res : res;
    }

    public double recurse(double x, int n){
        if(n==0){
            return 1;
        }

        if(n==1){
            return x;
        }

        if(n%2==0){
            double pow=recurse(x,n/2);
            return pow*pow;
        }else{
            double pow=recurse(x,n/2);
            return x*pow*pow;
        }
    }
}
