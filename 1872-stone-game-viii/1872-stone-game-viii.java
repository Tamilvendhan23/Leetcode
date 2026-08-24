class Solution {
    public int stoneGameVIII(int[] A) {
        int n = A.length;
        for (int i = 1; i < n; i++)
            A[i] += A[i - 1];

        int ans = A[n - 1];
        for (int i = n - 2; i > 0; i--)
            ans = Math.max(ans, A[i] - ans);

        return ans;
    }
}