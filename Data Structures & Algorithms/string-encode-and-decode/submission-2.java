class Solution {

    public String encode(List<String> strs) {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<strs.size();i++){
            sb.append(strs.get(i).length());
            sb.append("#");
            sb.append(strs.get(i));
        }
        return sb.toString();
    }

    public List<String> decode(String str) {
        List<String> ls=new ArrayList<>();
        int i=0;
        while(i<str.length()){
            int j=i;
            while(str.charAt(j)!='#'){
                j++;
            }

            int length=Integer.parseInt(str.substring(i,j));
            int startOfStr=j+1;
            int endOfStr=startOfStr+length;

            ls.add(str.substring(startOfStr,endOfStr));
            i=endOfStr;
        }
        return ls;
    }
}
