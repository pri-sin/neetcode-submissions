class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<tokens.length;i++){
            String c=tokens[i];
            if(c.equals("+")){
                int b=stack.pop();
                int a=stack.pop();
                int res=a+b;
                stack.push(res);
            }else if(c.equals("-")){
                int b=stack.pop();
                int a=stack.pop();
                int res=a-b;
                stack.push(res);
            }else if(c.equals("*")){
                int b=stack.pop();
                int a=stack.pop();
                int res=a*b;
                stack.push(res);
            }else if(c.equals("/")){
                int b=stack.pop();
                int a=stack.pop();
                int res=a/b;
                stack.push(res);
            }else{
                stack.push(Integer.parseInt(c));
            }
        }
        return stack.pop();
    }
}
