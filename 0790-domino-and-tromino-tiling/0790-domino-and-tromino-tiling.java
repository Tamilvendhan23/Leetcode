class Solution {
    final long mod = 1000000007;
    Long[][] dp;

    public int numTilings(int n) {
        dp = new Long[n + 1][2]; // dp[i][0 or 1]: 0 - no hanging tile, 1 - hanging tile
        return (int)dominoes(0, n, false);
    }

    private long dominoes(int i, int n, boolean possible) {
        if (i == n) return possible ? 0 : 1;
        if (i > n) return 0;

        int state = possible ? 1 : 0;
        if (dp[i][state] != null) return dp[i][state];

        long res;
        if (possible) {
            res = (dominoes(i + 1, n, false) + dominoes(i + 1, n, true)) % mod;
        } else {
            res = (dominoes(i + 1, n, false)
                 + dominoes(i + 2, n, false)
                 + 2 * dominoes(i + 2, n, true)) % mod;
        }

        dp[i][state] = res;
        return res;
    }
}
