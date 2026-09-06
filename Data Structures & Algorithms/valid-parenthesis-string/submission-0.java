class Solution {
    public boolean checkValidString(String s) {
        Boolean memo[][]=new Boolean[s.length()+1][s.length()+1];
        return isValid(s, 0, 0, memo);
    }
    public boolean isValid(String s, int i, int count, Boolean memo[][]){
        if(i==s.length() && count==0){
            return true;
        }

        if(count<0 || i>=s.length()){
            return false;
        }

        if(memo[i][count]!=null) return memo[i][count];

        char c= s.charAt(i);
        boolean isleft=false, isright=false, empty=false;
        if(c=='(' || c=='*'){
            isleft=isValid(s,i+1,count+1,memo);
        }

        if(c==')' || c=='*'){
            isright=isValid(s,i+1,count-1, memo);
        }

        if(c=='*'){
            empty=isValid(s,i+1,count, memo);
        }

        return memo[i][count]=isleft || isright || empty;
    }
} //will give TLE
