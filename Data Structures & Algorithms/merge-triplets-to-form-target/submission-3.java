class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) { 
        int count=0;
        int a=0,b=0,c=0;

        for(int i=0;i<triplets.length;i++){
            if((triplets[i][0]<=target[0]) &&
               triplets[i][1]<=target[1] &&
               triplets[i][2]<=target[2]){

                a = a==1 || triplets[i][0]==target[0] ? 1:0;
                b = b==1 || triplets[i][1]==target[1] ? 1:0;
                c = c==1 || triplets[i][2]==target[2] ? 1:0;

                if(a==1 && b==1 && c==1){
                    return true;
                }
            } 
        }
        return false;

    }
}

/*
class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int a = 0, b = 0, c = 0;

        for (int i = 0; i < triplets.length; i++) {
            if (triplets[i][0] <= target[0] &&
                triplets[i][1] <= target[1] &&
                triplets[i][2] <= target[2]) {

                a = (a == 1 || triplets[i][0] == target[0]) ? 1 : 0;
                b = (b == 1 || triplets[i][1] == target[1]) ? 1 : 0;
                c = (c == 1 || triplets[i][2] == target[2]) ? 1 : 0;

                if (a == 1 && b == 1 && c == 1) {
                    return true;
                }
            }
        }
        
        return false;
    }
}*/