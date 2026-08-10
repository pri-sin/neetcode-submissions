class TimeMap {
    class Pair{
        int timestamp;
        String value;

        public Pair(int tims, String v){
            timestamp=tims;
            value=v;
        }
    }
    Map<String, List<Pair>> timeMap;
    public TimeMap() {
        timeMap=new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if(!timeMap.containsKey(key)){
            timeMap.put(key, new ArrayList<>());
        }
        timeMap.get(key).add(new Pair(timestamp, value));
        
    }
    
    public String get(String key, int timestamp) {
        String res="";
        if(!timeMap.containsKey(key)){
            return "";
        }
        List<Pair> list=timeMap.get(key);
        int l=0,r=list.size()-1;

        while(l<=r){
            int mid=l+(r-l)/2;

            if(list.get(mid).timestamp<=timestamp){
                res=list.get(mid).value;
                l=mid+1;
            }else{
                r=mid-1;
            }
        }
        return res;
    }
}
