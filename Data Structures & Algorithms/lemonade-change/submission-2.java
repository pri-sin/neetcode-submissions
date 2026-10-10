/*class Solution {
    public boolean lemonadeChange(int[] bills) {
        List<Integer> inhand=new ArrayList<>();

        for(int i=0;i<bills.length;i++){
            if(bills[i]==5){
                inhand.add(5);
            }else if(bills[i]==10){
                if(inhand.contains(5)){
                    inhand.remove(Integer.valueOf(5));
                    inhand.add(10);
                }else{
                    return false;
                }
            }else{
                if(inhand.contains(10) && inhand.contains(5)){
                    inhand.remove(Integer.valueOf(10));
                    inhand.remove(Integer.valueOf(5));
                    inhand.add(20);
                }else if(Collections.frequency(inhand, 5)>=3){
                    inhand.remove(Integer.valueOf(5));
                    inhand.remove(Integer.valueOf(5));
                    inhand.remove(Integer.valueOf(5));
                    inhand.add(20);
                }else{
                    return false;
                }
            }
        }

        return true;
    }
}// Very high Time and Space complexity
*/

class Solution {
    public boolean lemonadeChange(int[] bills) {
        int t=0, f=0;

        for(int i=0;i<bills.length;i++){
            int b=bills[i];

            if(b==5) f++;
            if(b==10){
                t++;
                f--;
            }

            if(b==20){
                if(t>0){
                    t--;
                    f--;
                }else{
                    f=f-3;
                }
            }

            if(f<0) return false;
        }
        return true;
    }
}
