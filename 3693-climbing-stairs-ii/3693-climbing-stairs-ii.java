class Solution {
    public int climbStairs(int n, int[] costs) {
        costs = IntStream.concat(IntStream.of(0), Arrays.stream(costs)).toArray();
        int a = costs[n], b = Integer.MAX_VALUE / 2, c = Integer.MAX_VALUE / 2;  // dp[i + 1], dp[i + 2], dp[i + 3] for i = n - 1
        for (int i = n - 1; i >= 0; i--) {
            int cur = costs[i] + Math.min(a + 1, Math.min(b + 4, c + 9));  // dp[i]
            c = b;
            b = a;
            a = cur;
        }
        return a;
    }
}