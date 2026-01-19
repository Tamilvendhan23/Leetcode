class Solution {

    private boolean isValid(int[][] pref, int k, int limit) {
        int n = pref.length;
        int m = pref[0].length;

        for (int i = k - 1; i < n; i++) {
            for (int j = k - 1; j < m; j++) {
                int x1 = i - k + 1;
                int y1 = j - k + 1;

                int sum = pref[i][j];
                if (x1 > 0) sum -= pref[x1 - 1][j];
                if (y1 > 0) sum -= pref[i][y1 - 1];
                if (x1 > 0 && y1 > 0) sum += pref[x1 - 1][y1 - 1];

                if (sum <= limit)
                    return true;
            }
        }
        return false;
    }

    public int maxSideLength(int[][] mat, int threshold) {
        int n = mat.length;
        int m = mat[0].length;

        int[][] pref = new int[n][m];

        // Copy matrix
        for (int i = 0; i < n; i++)
            System.arraycopy(mat[i], 0, pref[i], 0, m);

        // Row-wise prefix sum
        for (int i = 0; i < n; i++)
            for (int j = 1; j < m; j++)
                pref[i][j] += pref[i][j - 1];

        // Column-wise prefix sum
        for (int j = 0; j < m; j++)
            for (int i = 1; i < n; i++)
                pref[i][j] += pref[i - 1][j];

        int low = 1, high = Math.min(n, m);
        int ans = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (isValid(pref, mid, threshold)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }
}