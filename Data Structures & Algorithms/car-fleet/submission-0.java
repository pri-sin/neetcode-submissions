class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        List<Pair> list=new ArrayList<>();
        int n=position.length;

        for(int i=0;i<n;i++){
            list.add(new Pair(position[i], speed[i]));
        }

        Collections.sort(list, (a,b)->b.pos-a.pos);

        Stack<Double> s=new Stack<>();

        for(int i=0;i<n;i++){
            int poscurr=list.get(i).pos;
            int speedcrur=list.get(i).speed;
            double time=(double)(target-poscurr)/speedcrur;
            if(!s.isEmpty() && s.peek()>=time){
                continue;
            }
            s.push(time);
        }

        return s.size();
    }
}


class Pair{
    int pos;
    int speed;

    public Pair(int pos,int speed){
        this.pos=pos;
        this.speed=speed;
    }
}
