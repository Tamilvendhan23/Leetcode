class Solution {
    public int maximumDifference(int[] nums) {
        int n = nums.length;
        int maxD = -1;
        int xMinL = (int) 1e9;
        int xMaxR = -1;

        for (int l = 0, r = n - 1; l < r; l++) {
            xMinL = Math.min(nums[l], xMinL);
            while (l < r && nums[l + 1] > nums[l]) {
                l++;
                maxD = Math.max(maxD, nums[l] - xMinL);
            }

            xMaxR = Math.max(nums[r], xMaxR);
            while (l < r && nums[r - 1] < nums[r]) {
                r--;
                maxD = Math.max(maxD, nums[r] - xMaxR);
            }

            maxD = Math.max(maxD, xMaxR - xMinL);
        }

        return maxD == 0 ? -1 : maxD;
    }
}