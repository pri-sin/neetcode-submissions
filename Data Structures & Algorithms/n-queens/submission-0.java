class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res=new ArrayList<>();
        char [][] arr=new char[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                arr[i][j]='.';
            }
        }
        solveNQueens(arr, res, 0);
        return res;
    }

    public void solveNQueens(char [][] arr, List<List<String>> res, int r){
        if(r==arr.length){
            List<String> curr=new ArrayList<>();
            for(char []c:arr){
                curr.add(new String(c));
            }
            res.add(curr);
            return;
        }

        for(int c=0;c<arr.length;c++){
            if(isSafe(arr,r,c)){
                if(isSafe(arr, r, c)){
                    arr[r][c]='Q';
                    solveNQueens(arr, res, r+1);
                    arr[r][c]='.';
                }
            }
        }
    }

    public boolean isSafe(char [][]curr, int r, int c){
        for(int i=r-1;i>=0;i--){
            if(curr[i][c]=='Q') return false;
        }

        //positive diag
        for(int i=r-1, j=c-1; i>=0 && j>=0;i--,j--){
            if(curr[i][j]=='Q') return false;
        }

        //negative diag
        for(int i=r-1, j=c+1; i>=0 && j<curr.length;i--,j++){
            if(curr[i][j]=='Q') return false;
        }

        return true;
    }
}
