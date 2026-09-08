/*class Solution {
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
}*/ //This solution uses m+n space

class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean col1=false, row1=false;
        for(int i=0;i<m;i++){
            if(matrix[i][0]==0) col1=true;
        }
        for(int i=0;i<n;i++){
            if(matrix[0][i]==0) row1=true;
        }

        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(matrix[i][j]==0){
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }
        }


        for(int i=1;i<m;i++){
            if(matrix[i][0]==0){
                for(int j=1;j<n;j++){
                    matrix[i][j]=0;
                }
            }
        }

        for(int j=1;j<n;j++){
            if(matrix[0][j]==0){
                for(int i=1;i<m;i++){
                    matrix[i][j]=0;
                }
            }
        }

        if(col1){
            for(int i=0;i<m;i++){
                matrix[i][0]=0;
            }
        }

        if(row1){
            for(int i=0;i<n;i++){
                matrix[0][i]=0;
            }
        }
    }
}
