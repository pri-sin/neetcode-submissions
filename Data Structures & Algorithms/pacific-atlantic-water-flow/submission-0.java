/*class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        Set<List<Integer>> pacific=new HashSet<>();
        Set<List<Integer>> atlantic=new HashSet<>();

        int m =heights.length;
        int n =heights[0].length;

        for(int j=0;j<n;j++){
            if(!pacific.contains(List.of(m - 1, j))){
                solveAtlantic(heights, pacific, m-1, j, heights[m-1][j]);
            }
        }

        for(int j=0;j<n;j++){
            if(!atlantic.contains(List.of(0, j))){
                solvePacific(heights, pacific, 0, j, heights[0][j]);
            }
        }

        pacific.retainAll(atlantic);
        return new ArrayList<>(pacific);
    }

    public void solvePacific(int [][]heights, Set<List<Integer>> pacific, int i, int j, int max){
        if(i>=heights.length || j>=heights[0].length || heights[i][j]<max || pacific.contains(List.of(i,j))){
            return;
        }

        pacific.add(List.of(i,j));
        max=heights[i][j];

        solvePacific(heights, pacific, i, j+1, max);
        solvePacific(heights, pacific, i+1, j, max);
    }

    public void solveAtlantic(int [][]heights, Set<List<Integer>> atlantic, int i, int j, int max){
        if(i<0 || j>=heights[0].length || heights[i][j]<max || atlantic.contains(List.of(i,j))){
            return;
        }

        atlantic.add(List.of(i,j));
        max=heights[i][j];

        solveAtlantic(heights, atlantic, i, j+1, max);
        solveAtlantic(heights, atlantic, i-1, j, max);
    }
}//doesnt work
*/

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        Set<List<Integer>> pacific = new HashSet<>();
        Set<List<Integer>> atlantic = new HashSet<>();

        // Start DFS from Left/Right columns
        for (int i = 0; i < m; i++) {
            dfs(heights, pacific, i, 0, heights[i][0]);            // Pacific (Left)
            dfs(heights, atlantic, i, n - 1, heights[i][n - 1]);    // Atlantic (Right)
        }

        // Start DFS from Top/Bottom rows
        for (int j = 0; j < n; j++) {
            dfs(heights, pacific, 0, j, heights[0][j]);            // Pacific (Top)
            dfs(heights, atlantic, m - 1, j, heights[m - 1][j]);    // Atlantic (Bottom)
        }

        // Find intersection of coordinates reachable by both oceans
        pacific.retainAll(atlantic);
        return new ArrayList<>(pacific);
    }

    private void dfs(int[][] heights, Set<List<Integer>> visited, int i, int j, int prevHeight) {
        // Out of bounds check
        if (i < 0 || i >= heights.length || j < 0 || j >= heights[0].length) {
            return;
        }

        // Stop if current cell is lower than previous cell or already visited
        if (heights[i][j] < prevHeight || visited.contains(List.of(i, j))) {
            return;
        }

        visited.add(List.of(i, j));

        // Traverse all 4 directions
        dfs(heights, visited, i + 1, j, heights[i][j]); // Down
        dfs(heights, visited, i - 1, j, heights[i][j]); // Up
        dfs(heights, visited, i, j + 1, heights[i][j]); // Right
        dfs(heights, visited, i, j - 1, heights[i][j]); // Left
    }
}
