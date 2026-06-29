class Solution {
    public boolean isHappy(int n) {
        List<Integer> list=new ArrayList<>();
        while(true){
            int x=0;
            while(n!=0){
                int r=n%10;
                n=n/10;
                x+=r*r;
            }
            if(x==1){return true;}
            else if(list.contains(x)){
                return false;
            }
            list.add(x);
            n=x;
        }
    }
}
