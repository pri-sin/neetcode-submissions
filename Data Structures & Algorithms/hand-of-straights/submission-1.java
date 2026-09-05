class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if(hand.length % groupSize != 0) return false;
        Arrays.sort(hand);
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0;i<hand.length;i++){
            map.put(hand[i], map.getOrDefault(hand[i],0)+1);
        }

        for(int i=0;i<hand.length;i++){
            int x=hand[i];
            if(map.get(x)==0) continue;
            for(int j=0;j<groupSize;j++){
                if(map.containsKey(x) && map.get(x)>0){
                    map.put(x, map.get(x)-1);
                    x=x+1;
                }else{
                    return false;
                }
            }
        }
        return true;
    }
}


//1,2,2,3,3,4,4,5