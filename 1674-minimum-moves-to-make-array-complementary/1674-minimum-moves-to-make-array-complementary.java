class Solution {

    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] diff = new int[2 * limit + 2];

        for (int i = 0; i < n / 2; i++) {
            int a = Math.min(nums[i], nums[n - 1 - i]);
            int b = Math.max(nums[i], nums[n - 1 - i]);

            diff[2] += 2;
            diff[a + 1] -= 1;
            diff[a + b] -= 1;
            diff[a + b + 1] += 1;
            diff[b + limit + 1] += 1;
        }

        int minOps = n;
        int currentOps = 0;

        for (int c = 2; c <= 2 * limit; c++) {
            currentOps += diff[c];
            minOps = Math.min(minOps, currentOps);
        }

        return minOps;
    }
}