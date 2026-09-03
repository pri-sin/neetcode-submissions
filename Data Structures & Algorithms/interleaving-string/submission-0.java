/*class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length()) return false;
        return getResult(s1, s2, s3, 0,0,0, 1, 1);
    }

    public boolean getResult(String s1, String s2, String s3,int start,int i,int j,int n,int m){
        if(i==s1.length() && j==s2.length() && start==s3.length()){
            return true;
        }

        if(Math.abs(n-m)>1){
            return false;
        }

        boolean s1res=false, s2res=false;
        for(int k=start+1;k<=s3.length();k++){
            int x= i+(k-start);
            int y=j+(k-start);
            if(x<=s1.length() && s3.substring(start,k).equals(s1.substring(i,x))){
                s1res=getResult(s1,s2,s3,k,x,j,n+1,m);
            }
            if(y<=s2.length() && s3.substring(start,k).equals(s2.substring(j,y))){
                s2res=getResult(s1,s2,s3,k,i,y,n,m+1);
            }
        }

        return s1res || s2res;
    }
} // TLE */


class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length()) return false;
        Boolean memo[][]=new Boolean [s1.length()+1][s2.length()+1];
        return getResult(s1, s2, s3, 0,0, memo);
    }

    public boolean getResult(String s1, String s2, String s3,int i,int j,Boolean [][]memo){
        if(i==s1.length() && j==s2.length()){
            return true;
        }

        if(memo[i][j]!=null) return memo[i][j];

        int k=i+j;

        boolean ans=false;

        if(i<s1.length() && s1.charAt(i)==s3.charAt(k)){
            ans=getResult(s1,s2,s3,i+1,j,memo);
        }

        if(!ans && j<s2.length() && s2.charAt(j)==s3.charAt(k)){
            ans=getResult(s1,s2,s3,i,j+1,memo);
        }

        return memo[i][j]=ans;
    }
}
