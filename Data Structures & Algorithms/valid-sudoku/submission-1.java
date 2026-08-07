class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<Character> set=new HashSet<>();

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]!='.' && !set.add(board[i][j])){
                    return false;
                }
            }
            set.clear();
        }

        for(int j=0;j<9;j++){
            for(int i=0;i<9;i++){
                if(board[i][j]!='.' && !set.add(board[i][j])){
                    return false;
                }
            }
            set.clear();
        }

        int k=0;
        while(k<9){
            int y=(k/3)*3;
            int x=(k%3)*3;
            for(int i=y;i<y+3;i++){
                for(int j=x;j<x+3;j++){
                    if(board[i][j]!='.' && !set.add(board[i][j])){
                        return false;
                    }
                }
            }
            set.clear();
            k++;
        }

        return true;
    }
}
