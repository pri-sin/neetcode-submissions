/*class Solution {
    public void solve(char[][] board) {
        int m=board.length;
        int n=board[0].length;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='O'){
                    solveBoard(board, i,j);
                }
            }
        }
    }

    public void solveBoard(char[][] board, int i,int j){
        if(i<=0 || i>=board.length-1 || j<=0 || j>=board[0].length-1 || board[i][j]=='X'){
            return;
        }

        board[i][j]='X';

        solveBoard(board, i+1, j);
        solveBoard(board, i-1, j);
        solveBoard(board, i, j+1);
        solveBoard(board, i, j-1);
    }
}*///WORKS ONLY FOR THESE TEST CASES, MISSES THE ONE WHERE THE 0s are actually connected to the border zeros

class Solution {
    public void solve(char[][] board) {
        int m=board.length;
        int n=board[0].length;

        for(int i=0;i<m;i++){
            if(board[i][0]=='O'){
                solveBoard(board, i,0);
            }

            if(board[i][n-1]=='O'){
                solveBoard(board, i,n-1);
            }
        }

        for(int j=0;j<n;j++){
            if(board[0][j]=='O'){
                solveBoard(board, 0,j);
            }

            if(board[m-1][j]=='O'){
                solveBoard(board, m-1,j);
            }
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='O'){
                    board[i][j]='X';
                }else if(board[i][j]=='#'){
                    board[i][j]='O';
                }
            }
        }
    }

    public void solveBoard(char[][] board, int i,int j){
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]=='X' || board[i][j]=='#'){
            return;
        }

        board[i][j]='#';

        solveBoard(board, i+1, j);
        solveBoard(board, i-1, j);
        solveBoard(board, i, j+1);
        solveBoard(board, i, j-1);
    }
}
