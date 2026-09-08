class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set=new HashSet<>();
        while(n!=1 && set.add(n)){
            int x=n;
            int newnum=0;
            while(x>0){
                int rem=x%10;
                newnum+=rem*rem;
                x=x/10;
            }
            n=newnum;
        }
        return n==1;
    }
}