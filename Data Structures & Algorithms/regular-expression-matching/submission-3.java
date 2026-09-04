/*class Solution {
    public boolean isMatch(String s, String p) {
        int m=s.length();
        int n=p.length();
        Boolean memo[][]=new Boolean[m][n];
        return getRes(s,p,0,0,memo);
    }

    public boolean getRes(String s, String p, int i, int j, Boolean memo[][]){
        if(i==s.length() && j==p.length()){
            return true;
        }

        if(i==s.length() || j==p.length()){
            return false;
        }

        char c = p.charAt(j);

        if(c!=s.charAt(i) && c!='.' && c!='*'){
            return false;
        }

        if(memo[i][j]!=null) return memo[i][j];
        boolean starpath=false;
        if(c=='*'){
            starpath=getRes(s,p,i+1,j, memo);
        }
        
        boolean npath=getRes(s,p,i+1,j+1, memo);

        return memo[i][j]=starpath || npath;

    }
} // this works when * definition is just 1 or more characters
*/

/*class Solution {
    public boolean isMatch(String s, String p) {
        int m=s.length();
        int n=p.length();
        Boolean memo[][]=new Boolean[m][n];
        return getRes(s,p,0,0,memo);
    }

    public boolean getRes(String s, String p, int i, int j, Boolean memo[][]){
        if(i==s.length() && j==p.length()){
            return true;
        }

        if(i==s.length() || j==p.length()){
            return false;
        }

        if(memo[i][j]!=null) return memo[i][j];
        ///from here 
        char c = p.charAt(j);
        char ch = s.charAt(i);
        boolean starpath=false;
        //c is either a char or . or a 
        //1-if . -> (s, p, i+1, j+1) done
        //2-if c == ch -> (s, p, i+1, j+1) done
        //3-if c != ch ->
            // if p[j+1]==* -> (s,p,i,j+1) done
            //else return false;     done
        //4-if * -> cp=p.charAt(j-1);
            //41-if cp==ch -> (s,p,i+1,j);
                        //(s,p,i+1,j+1);
            //if . -> r=s.charAt(i-1);
                //if(ch==r) ->41
                //if(ch!=r) ->(s,p,i,j+1);
        //till here 
        if(c!='.' && c!='*' && c!=ch && (j+1<p.length() && p.charAt(j+1)!='*')){
            return memo[i][j] = false;
        }

        if(c=='*'){
            char cp=p.charAt(j-1);
            if(cp==ch || (cp=='.')){
                starpath=getRes(s,p,i+1,j, memo);
                if(!starpath){
                    starpath=getRes(s,p,i+1,j+1, memo);
                }
            }else{
                starpath=getRes(s,p,i,j+1, memo);
            }
        }else if(c=='.'){
            starpath=getRes(s,p,i+1,j+1,memo);
        }else if(c!=ch){
            starpath=getRes(s,p,i,j+1, memo);
        }else if(c==ch){
            if((j+1<p.length() && p.charAt(j+1)=='*')){
                starpath=getRes(s,p,i,j+1, memo);
            }else{
                starpath=getRes(s,p,i+1,j+1,memo);
            }
        }
        
        return memo[i][j]=starpath;

    }
}// Fails on s="aaa" p="ab*a*c*a" made change at 91-96 now fails for s="a" p="ab*"*/


class Solution {
    public boolean isMatch(String s, String p) {
        int m=s.length();
        int n=p.length();
        Boolean memo[][]=new Boolean[m+1][n+1];
        return getRes(s,p,0,0,memo);
    }

    public boolean getRes(String s, String p, int i, int j, Boolean memo[][]){
        if(j==p.length() && i==s.length()){
            return true;
        }

        if(j>=p.length()) return false;

        if(memo[i][j]!=null) return memo[i][j];

        boolean charmatch=i<s.length() && (s.charAt(i)==p.charAt(j) || p.charAt(j)=='.');

        boolean res=false;
        if(j+1<p.length() && p.charAt(j+1)=='*'){
            res=getRes(s,p,i,j+2,memo) || charmatch && getRes(s,p,i+1,j,memo);
        }else{
            res=charmatch && getRes(s,p,i+1,j+1,memo);
        }

        return memo[i][j]=res;
    }
}
