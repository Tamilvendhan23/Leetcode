class Solution {
    public int longestSubsequence(int[] A) {
        int tot = 0, n = A.length;
        boolean nonZero = false;

        for (int x : A) {
            nonZero |= x > 0;
            tot ^= x;
        }

        if (!nonZero) return 0;
        return tot == 0 ? n - 1 : n;
    }
}