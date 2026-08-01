class Solution {
    public void rotate(int[][] matrix) {
        int m=matrix.length;
        int n=matrix[0].length;
        // 1. Reverse each row horizontally
        for(int i=0;i<m;i++){
            int left=0;
            int right=n-1;
            while(left<right){
                int temp=matrix[i][left];
                matrix[i][left]=matrix[i][right];
                matrix[i][right]=temp;
                left++;
                right--;
            }
        }
        // 2. Transpose across the anti-diagonal (j < n - 1 - i avoids double swapping)
        for(int i=0;i<m-1;i++){
            for(int j=0;j<n-i-1;j++ ){
                int temp=matrix[i][j];
                matrix[i][j]=matrix[n-j-1][m-i-1];
                matrix[n-j-1][m-i-1]=temp;
            }
        }
    }
}

/*
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // 1. Main Diagonal Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 2. Reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }
}*/
