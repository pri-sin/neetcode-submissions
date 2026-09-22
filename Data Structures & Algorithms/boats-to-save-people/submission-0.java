class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        
        int left = 0;
        int right = people.length - 1;
        int count = 0;

        while (left <= right) {
            // If the lightest and heaviest person can share a boat, move left pointer
            if (people[left] + people[right] <= limit) {
                left++;
            }
            // The heaviest person always gets offboarded into a boat
            right--;
            count++;
        }

        return count;
    }
}


/*
class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int n = people.length;
        int left=0, right=n-1;
        int count=0, sum=0;
        while(left<=right){
            sum+=people[left]+people[right];
            if(sum<=limit){
                sum=0;
                count++;
                left++;
                right--;
            }else if(sum>limit){
                sum=0;
                count++;
                right--;
            }else{
                sum=people[left];
                left++;
            }
        }
        return count;
    }
}*///This wont work even when more than 2 people allowed 
/*The Conceptual Issue: Bin Packing ProblemWhen you remove the 2-person limit and allow arbitrary numbers of people per boat (subject only to the weight limit), the problem transforms into the Bin Packing Problem, which is NP-hard.The two-pointer greedy algorithm fails here. For example:Limit = 10, People = [2, 3, 4, 5, 6]Optimal boats (2 boats): Boat 1: [6, 4] (sum 10), Boat 2: [5, 3, 2] (sum 10).Two-pointer attempt: Matches 6 with 2, 5 with 3, leaving 4 alone $\rightarrow$ 3 boats (suboptimal).*/