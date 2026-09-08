class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set=new HashSet<>();
        while(n!=1 && set.add(n)){
            int x=n;
            int newnum=0;
            while(x>0){
                int rem=x%10;
                newnum+=rem*rem;
                x=x/10;
            }
            n=newnum;
        }
        return n==1;
    }
}

/*
Why Your Code is Great
The most elegant part of your solution is this specific line:

Java
while(n!=1 && set.add(n))
Instead of manually checking set.contains(n) and then adding it inside the loop, you used the fact that Java's HashSet.add() method returns a boolean. It returns true if the item was successfully added, and false if it was already in the set. This single line simultaneously checks for the endless cycle and updates your historical record, keeping your code incredibly clean.*/


/*
class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = getNext(n);
        
        // Loop until either the fast runner reaches 1, 
        // or the fast runner laps the slow runner
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);             // Moves 1 step
            fast = getNext(getNext(fast));    // Moves 2 steps
        }
        
        // If the fast runner reached 1, it's a happy number
        return fast == 1;
    }
    
    // Helper method to calculate the sum of the squares of digits
    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            totalSum += d * d;
            n = n / 10;
        }
        return totalSum;
    }
}*/