class Solution {
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
}