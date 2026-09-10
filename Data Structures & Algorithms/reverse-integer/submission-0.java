/*class Solution {
    public int reverse(int x) {
        if(x==0) return x;
        int sign=x<0?-1:1;
        x=Math.abs(x);
        int newx=0;
        while(x!=0){
            int rem=x%10;
            newx=newx*10+rem;
            if(newx>Integer.MAX_VALUE) return 0;
            x=x/10;
        }

        newx=sign*newx;
        if(newx<Integer.MIN_VALUE){
            return 0;
        }
        return newx;
    }
}//Gives overflow error
*/

class Solution {
    public int reverse(int x) {
        int rev = 0;
        
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            
            // Check for positive overflow before multiplying by 10
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            // Check for negative overflow before multiplying by 10
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            
            rev = rev * 10 + pop;
        }
        
        return rev;
    }
}
