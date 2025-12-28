class Solution {
    public int countNegatives(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int i = m - 1, j = 0;

        int res = 0;

        while (i >= 0 && j < n) {
            if (grid[i][j] < 0) {
                res += n - j;
                i--;
            } else
                j++;
        }

        return res;
    }
}
