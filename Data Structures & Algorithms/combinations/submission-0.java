class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> curr=new ArrayList<>();
        getResult(n,1,  k, res, curr);
        return res;
    }
    
    public void getResult(int n, int i,int k,List<List<Integer>> res,List<Integer> curr){
        if(k==0){
            res.add(new ArrayList<>(curr));
            return;
        }


        for(int j=i;j<=n;j++){
            curr.add(j);
            getResult(n, j+1, k-1, res, curr);
            curr.remove(curr.size()-1);
        }
    }

}