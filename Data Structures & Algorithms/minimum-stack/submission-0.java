class MinStack {
    Stack<Integer> s;
    Stack<Integer> mins;

    public MinStack() {
        s=new Stack<>();
        mins=new Stack<>();
    }
    
    public void push(int val) {
        s.push(val);
        if(!mins.isEmpty() && mins.peek()<val){
            mins.push(mins.peek());
        }else{
            mins.push(val);
        }
    }
    
    public void pop() {
        if(!s.isEmpty()){
            s.pop();
            mins.pop();
        }
    }
    
    public int top() {
        return s.peek();
    }
    
    public int getMin() {
        return mins.peek();
    }
}
