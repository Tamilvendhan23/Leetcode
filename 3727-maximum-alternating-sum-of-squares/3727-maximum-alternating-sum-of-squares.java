class Solution {
    public long maxAlternatingSum(int[] nums) {
        // Step 1: square every number (long, so it cannot overflow)
        long[] a = new long[nums.length];
        for (int i = 0; i < nums.length; i++)
            a[i] = (long) nums[i] * nums[i];

        // Step 2: sort — smallest on the left, biggest on the right
        Arrays.sort(a);

        long ans = 0;
        int k = nums.length / 2;   // number of minus places

        // Step 3: subtract the k smallest squares
        for (int i = 0; i < k; i++)
            ans -= a[i];

        // Step 4: add all the bigger squares
        for (int i = k; i < nums.length; i++)
            ans += a[i];

        return ans;
    }
}