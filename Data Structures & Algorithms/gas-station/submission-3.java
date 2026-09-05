class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int start = 0;
        int netgas = 0;
        int totalgas=0, totalcost=0;

        for(int i=0;i<n;i++){
            totalcost+=cost[i];
            totalgas+=gas[i];
            netgas += gas[i] - cost[i];
            
            if (netgas < 0) {
                start = i + 1; // Jump past all stations up to i
                netgas = 0;
            }
        }

        return totalgas>=totalcost?start:-1;
    }
}