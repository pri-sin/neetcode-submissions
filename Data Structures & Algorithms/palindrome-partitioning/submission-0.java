class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> curr = new ArrayList<>();

        getPartitions(s, 0, curr, result);
        return result;
    }

    public void getPartitions(String s, int start, List<String> curr,List<List<String>> result){
        if(start==s.length()){
            result.add(new ArrayList<>(curr));
            return;
        }

        for(int i=start;i < s.length();i++){
            if(isPalindrome(s, start, i)){
                curr.add(s.substring(start, i+1));
                getPartitions(s,i+1, curr, result);
                curr.remove(curr.size()-1);
            }
        }
    }

    public boolean isPalindrome(String s, int low, int high){
        while(low<=high){
            if(s.charAt(low++) !=s.charAt(high--)){
                return false;
            } 
        }
        return true;
    }
}
