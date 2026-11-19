class Solution {
    public int minimizeSum(int[] nums) {
        int[] small = new int[3], neg = new int[3];  // the 3 smallest values, and the 3 smallest negated ones: minus the 3 largest
        Arrays.fill(small, Integer.MAX_VALUE);
        Arrays.fill(neg, Integer.MAX_VALUE);
        for (int x : nums) {
            keep(small, x);
            keep(neg, -x);
        }
        return Math.min(-neg[0] - small[2], Math.min(-neg[1] - small[1], -neg[2] - small[0]));
    }

    private void keep(int[] three, int x) {  // three: the 3 smallest values so far, ascending
        if (x >= three[2])
            return;
        int i = 2;
        for (; i > 0 && three[i - 1] > x; i--)
            three[i] = three[i - 1];
        three[i] = x;
    }
}