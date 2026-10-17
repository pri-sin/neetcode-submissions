class Solution {
    public boolean isSubsequence(String s, String t) {
        int ls=0, rs=s.length();
        int lt=0, rt=t.length();

        while(ls<rs && lt<rt){
            char cs=s.charAt(ls);
            char ct=t.charAt(lt);

            if(cs==ct){
                ls++;
            }

            lt++;
        }

        return ls==rs;

    }
}