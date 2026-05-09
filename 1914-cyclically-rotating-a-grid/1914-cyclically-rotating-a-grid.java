class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;

        int layers = Math.min(m, n) / 2;

        for(int layer = 0; layer < layers; layer++) {

            ArrayList<Integer> vals = new ArrayList<>();

            int top = layer;
            int left = layer;

            int bottom = m - layer - 1;
            int right = n - layer - 1;

            // top row
            for(int j = left; j <= right; j++) {
                vals.add(grid[top][j]);
            }

            // right column
            for(int i = top + 1; i <= bottom - 1; i++) {
                vals.add(grid[i][right]);
            }

            // bottom row
            for(int j = right; j >= left; j--) {
                vals.add(grid[bottom][j]);
            }

            // left column
            for(int i = bottom - 1; i >= top + 1; i--) {
                vals.add(grid[i][left]);
            }

            int sz = vals.size();

            int idx = k % sz;

            // top row
            for(int j = left; j <= right; j++) {

                grid[top][j] = vals.get(idx);

                idx++;

                if(idx == sz) {
                    idx = 0;
                }
            }

            // right column
            for(int i = top + 1; i <= bottom - 1; i++) {

                grid[i][right] = vals.get(idx);

                idx++;

                if(idx == sz) {
                    idx = 0;
                }
            }

            // bottom row
            for(int j = right; j >= left; j--) {

                grid[bottom][j] = vals.get(idx);

                idx++;

                if(idx == sz) {
                    idx = 0;
                }
            }

            // left column
            for(int i = bottom - 1; i >= top + 1; i--) {

                grid[i][left] = vals.get(idx);

                idx++;

                if(idx == sz) {
                    idx = 0;
                }
            }
        }

        return grid;
    }
}