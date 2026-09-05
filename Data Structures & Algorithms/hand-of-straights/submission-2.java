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


/*Cleaned up

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;

        Arrays.sort(hand);
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int card : hand) {
            countMap.put(card, countMap.getOrDefault(card, 0) + 1);
        }

        for (int card : hand) {
            // Skip cards already consumed by previous groups
            if (countMap.get(card) == 0) continue;

            // Try to build a group of size 'groupSize' starting from 'card'
            for (int i = 0; i < groupSize; i++) {
                int currentCard = card + i;

                if (countMap.getOrDefault(currentCard, 0) == 0) {
                    return false; // Consecutive card missing
                }

                countMap.put(currentCard, countMap.get(currentCard) - 1);
            }
        }

        return true;
    }
}

*/

//1,2,2,3,3,4,4,5