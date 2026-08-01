class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean []rowzeros=new boolean[m];
        boolean []colzeros=new boolean[n];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    rowzeros[i]=true;
                    colzeros[j]=true;
                }
            }
        }

        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(rowzeros[i] || colzeros[j]){
                    matrix[i][j]=0;
                }
            }
        }
    }
}
