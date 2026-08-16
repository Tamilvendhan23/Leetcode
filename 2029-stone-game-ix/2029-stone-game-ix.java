class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] f = {0, 0, 0};

        for (int s : stones)
            f[s % 3]++;

        if ((f[0] & 1) == 0)
            return Math.min(f[1], f[2]) > 0;

        return Math.abs(f[1] - f[2]) > 2;
    }
}