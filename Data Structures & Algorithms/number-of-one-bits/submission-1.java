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
/*
Brian Kernighan's AlgorithmThe Key IntuitionIn binary arithmetic, subtracting $1$ from a number flips all the bits after the rightmost set bit (the lowest 1), including that bit itself.When you perform the bitwise AND operation $n \& (n - 1)$, it clears the lowest set bit of $n$ to 0, leaving all other bits untouched.Example: Let $n = 12$ (1100 in binary)$n - 1 = 11$ (1011 in binary)$n \& (n - 1) =$ 1100 & 1011 $= 8$ (1000 in binary)Notice how the lowest 1 bit vanished!By repeatedly applying $n = n \& (n - 1)$ in a loop until $n$ becomes 0, the loop will run only as many times as there are 1 bits.
*/