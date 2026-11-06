class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
           int x=0, y=0;
           int i=0;
           int maxsim=0;
           Set<String> blocked=new HashSet<>();
           for(int[] p:obstacles){
                blocked.add(p[0]+","+p[1]);
           }
           int[] dx={0,1,0,-1};
           int[] dy={1,0,-1,0};

           for(int c:commands){
            if(c==-1){
                i=(i+1)%4;
            }else if(c==-2){
                i=(i+3)%4;
            }else{
                for(int k=0;k<c;k++){
                    int nx=x+dx[i];
                    int ny=y+dy[i];

                    if(blocked.contains(nx+","+ny)){
                        break;
                    }
                    x=nx;
                    y=ny;
                    maxsim=Math.max(maxsim,x*x+y*y);
                }
            }
           }

        return maxsim;
    }
}
//Recommendation: Prefer i = (i + 3) % 4 for ring-buffer or circular-index operations. It produces the exact same backward rotation as subtracting 1, but remains safe across all programming languages without needing special handling for negative remainders.