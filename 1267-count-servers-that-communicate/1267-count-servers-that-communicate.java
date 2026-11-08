class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] rows = new int[m], cols = new int[n];  // servers on each row and column
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                rows[i] += grid[i][j];
                cols[j] += grid[i][j];
            }

        int ans = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 1 && rows[i] + cols[j] > 2)  // it's counted in both, so more than 2 means company
                    ans++;
        return ans;
    }
}