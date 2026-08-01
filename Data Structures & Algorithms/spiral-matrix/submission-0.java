class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;

        int m = matrix.length;        // rows
        int n = matrix[0].length;     // columns

        int top = 0, bottom = m - 1;
        int left = 0, right = n - 1;

        while (top <= bottom && left <= right) {
            // 1. Traverse Right (Top Row)
            for (int k = left; k <= right; k++) {
                result.add(matrix[top][k]);
            }
            top++;

            // 2. Traverse Down (Right Column)
            for (int k = top; k <= bottom; k++) {
                result.add(matrix[k][right]);
            }
            right--;

            // 3. Traverse Left (Bottom Row) - Guard against single-row overlap
            if (top <= bottom) {
                for (int k = right; k >= left; k--) {
                    result.add(matrix[bottom][k]);
                }
                bottom--;
            }

            // 4. Traverse Up (Left Column) - Guard against single-column overlap
            if (left <= right) {
                for (int k = bottom; k >= top; k--) {
                    result.add(matrix[k][left]);
                }
                left++;
            }
        }

        return result;
    }
}