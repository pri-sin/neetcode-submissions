class Solution {
    int memo[][];
    public int mincostTickets(int[] days, int[] costs) {
        memo=new int[days.length][396];
        for(int []row:memo){
            Arrays.fill(row,-1);
        }
        
        return solve(days, costs, 0, 0);
    }

    public int solve(int[] days, int[] costs,int  i, int validtill){
        if(i==days.length) return 0;
        if(days[i]<validtill){
            return solve(days, costs, i+1, validtill);
        }

        if(memo[i][validtill]!=-1) return memo[i][validtill];

        int take0=costs[0]+solve(days, costs, i+1, days[i]+1);
        int take1=costs[1]+solve(days, costs, i+1, days[i]+7);
        int take2=costs[2]+solve(days,costs,i+1,days[i]+30);

        return memo[i][validtill]= Math.min(take0, Math.min(take1, take2));
    }
}