class Solution {
    public int openLock(String[] deadends, String target) {
        HashSet<String> visit=new HashSet<>(Arrays.asList(deadends));

        if(visit.contains("0000")) return -1;

        Queue<String> q=new ArrayDeque<>();
        q.add("0000");
        visit.add("0000");
        int turns=0;
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                String lock=q.poll();
                if(target.equals(lock)) return turns;

                for(String child:children(lock)){
                    if(!visit.contains(child)){
                        q.add(child);
                        visit.add(child);
                    }
                }
            }
            turns++;
        }
        return -1;
    }

    public List<String> children(String lock){
        List<String> res=new ArrayList<>();
        for(int i=0;i<4;i++){
            char[] arr = lock.toCharArray();
            arr[i] = (char) (((arr[i] - '0' + 1) % 10) + '0');
            res.add(new String(arr));

            arr = lock.toCharArray();
            arr[i] = (char) (((arr[i] - '0' - 1 + 10) % 10) + '0');
            res.add(new String(arr));
        }
        return res;
    }
}