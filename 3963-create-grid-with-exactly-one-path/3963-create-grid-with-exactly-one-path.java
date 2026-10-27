class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];
        
        for (char[] row : grid)
            Arrays.fill(row, '#');
            
        int r = 0, c = 0;
        grid[r][c] = '.';
        
        while (r < m - 1 || c < n - 1) {
            if (r < m - 1 && (c == n - 1 || Math.abs((r + 1) * (n - 1) - c * (m - 1)) <= Math.abs(r * (n - 1) - (c + 1) * (m - 1))))
                r++;
            else
                c++;
                
            grid[r][c] = '.';
        }
        
        String[] res = new String[m];
        
        for (int i = 0; i < m; i++)
            res[i] = new String(grid[i]);
            
        return res;
    }
}