class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        getColored(image, sr, sc, color, image[sr][sc]);
        return image;
    }

    public void getColored(int[][] image, int sr, int sc, int color, int startcolor){
        if(sr<0 || sr>=image.length || sc<0 || sc>=image[0].length || image[sr][sc]!=startcolor || image[sr][sc]==color){
            return;
        }

        image[sr][sc]=color;
        getColored(image, sr+1, sc, color, startcolor);
        getColored(image, sr-1, sc, color, startcolor);
        getColored(image, sr, sc+1, color, startcolor);
        getColored(image, sr, sc-1, color, startcolor);
    }
}