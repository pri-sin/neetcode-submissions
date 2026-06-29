class Solution {

    public String encode(List<String> strs) {
        StringBuilder s=new StringBuilder();
        for(int i=0;i<strs.size();i++){
            s.append("#"+strs.get(i).length()+"@"+strs.get(i));
        }
        return s.toString();
    }

    public List<String> decode(String str) {
        List<String> list=new ArrayList<>();
        int i=0;
        while(i < str.length()){
            int atSignIndex = str.indexOf('@', i);
            //length
            int length=Integer.parseInt(str.substring(i+1, atSignIndex));
            String s = str.substring(atSignIndex+1, atSignIndex+1+length);
            list.add(s);
            i=atSignIndex+length+1;
        }
        return list;
    }
}
