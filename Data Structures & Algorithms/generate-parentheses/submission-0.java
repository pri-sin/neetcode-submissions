class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result= new ArrayList<>();
        StringBuilder sb =new StringBuilder();

        getParenthesis(n,sb,result, 0,0);
        return result;
    }

    public void getParenthesis(int n, StringBuilder sb, List<String> result, int open, int close ){
        if(sb.length()==n*2){
            result.add(sb.toString());
            return;
        }

        if(open<n){
            sb.append("(");
            getParenthesis(n, sb,result, open+1,close);
            sb.deleteCharAt(sb.length()-1);
        }

        if(close<open){
            sb.append(")");
            getParenthesis(n, sb,result, open,close+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
