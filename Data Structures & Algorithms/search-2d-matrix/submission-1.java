class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n=matrix.length;
        int m=matrix[0].length;
        int lr=0;
        int rr=n-1;

        while(lr<=rr){
            int midr=lr+(rr-lr)/2;
            if(matrix[midr][0]==target){
                return true;
            }
            if(matrix[midr][0]<target){
                if(matrix[midr][m-1]>=target){
                    //apply bin search here
                    int lc=0;
                    int rc=m-1;
                    while(lc<=rc){
                        int midc = lc+(rc-lc)/2;
                        if(matrix[midr][midc]==target){
                            return true;
                        }
                        if(matrix[midr][midc]<target){
                            lc=midc+1;
                        }else{
                            rc=midc-1;
                        }
                    }
                    return false;
                }
                else{
                    lr=midr+1;
                }
            }else if(matrix[midr][0]>target){
                rr=midr-1;
            }
        }
        return false;
    }
}
