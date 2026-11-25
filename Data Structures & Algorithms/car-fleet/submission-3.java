/*class Solution {
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
}*/


class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        List<Pair> cars=new ArrayList<>(); 
        for(int i=0;i<position.length;i++){
            double time=(double)(target-position[i])/speed[i];
            cars.add(new Pair(position[i], time));
        }

        Collections.sort(cars, (a,b)->Integer.compare(b.pos, a.pos));

        Stack<Double> st=new Stack<>();

        for(int i=0;i<cars.size();i++){
            Pair car=cars.get(i);
            if(st.isEmpty() || st.peek()<car.time){
                st.push(car.time);
            }
        }

        return st.size();
    }
}

class Pair{
    int pos;
    double time;
    public Pair(int pos, double time){
        this.pos=pos;
        this.time=time;
    }
}


