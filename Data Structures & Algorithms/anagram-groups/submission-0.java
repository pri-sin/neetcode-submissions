class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map=new HashMap<>();
        List<List<String>> result= new ArrayList<>();
        for(int i=0;i<strs.length;i++){
            char sorted[]=strs[i].toCharArray();
            Arrays.sort(sorted);
            String sortedString=Arrays.toString(sorted);
            map.computeIfAbsent(sortedString, k -> new ArrayList<>()).add(strs[i]);
        }
        for(Map.Entry<String, List<String>> entry:map.entrySet()){
            List<String> dummy=new ArrayList<>();
            for(int i=0;i<entry.getValue().size();i++){
                dummy.add(entry.getValue().get(i));
            }
            result.add(dummy);
        }
        return result;
    }
}
