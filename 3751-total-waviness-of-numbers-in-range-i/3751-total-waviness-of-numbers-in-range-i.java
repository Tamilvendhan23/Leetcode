class Solution {
    private static int MAX = 100001;
    private static int[] dp = new int[MAX], pref = new int[MAX];

    static {
        for (int i = 100; i < MAX; i++) {
            int r = i % 10;
            int m = (i / 10) % 10;
            int l = (i / 100) % 10;

            int isWave = m > Math.max(l, r) || m < Math.min(l, r) ? 1 : 0;
            dp[i] = dp[i / 10] + isWave;
            pref[i] = pref[i - 1] + dp[i];
        }
    }

    public int totalWaviness(int A, int B) {
        return pref[B] - pref[A - 1];
    }
}
//this trick slow for java