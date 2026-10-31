class Solution {
    public int uniquePaths(int[][] grid) {
        final int mod = 1_000_000_007;
        int[] down = new int[grid[0].length];  // ways to enter each cell from above
        down[0] = 1;
        for (int[] row : grid) {
            int right = 0;  // ways to enter the current cell from the left
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 1) {  // a mirror swaps the flows
                    int t = right;
                    right = down[j];
                    down[j] = t;
                } else {  // an empty cell merges them
                    right = down[j] = (right + down[j]) % mod;
                }
            }
        }
        return down[down.length - 1];
    }
}