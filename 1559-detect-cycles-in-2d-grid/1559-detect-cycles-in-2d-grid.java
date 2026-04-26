class Solution {
    static int[][] dirs = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[] visit = new boolean[m * n];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (!visit[i * n + j] && dfs(i, j, -1, -1, grid, visit, m, n))
                    return true;

        return false;
    }

    private static boolean dfs(int r, int c, int pr, int pc, char[][] grid, boolean[] visit, int m, int n) {
        visit[r * n + c] = true;

        for (int k = 0; k < 4; k++) {
            int nr = r + dirs[k][0];
            int nc = c + dirs[k][1];

            if ((nr != pr || nc != pc) &&
                    nr >= 0 && nr < m && nc >= 0 &&
                    nc < n && grid[nr][nc] == grid[r][c])
                if (visit[nr * n + nc] || dfs(nr, nc, r, c, grid, visit, m, n))
                    return true;
        }

        return false;
    }

}