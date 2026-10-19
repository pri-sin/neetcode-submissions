class Solution {
    public int lengthOfLastWord(String s) {
        boolean flag=false;
        int count=0;
        
        for(int i=s.length()-1;i>=0;i--){
            char c = s.charAt(i);
            if(c!=' '){
                flag=true;
                count++;
            }

            if(c==' ' && flag){
                break;
            }
        }
        return count;
    }
}