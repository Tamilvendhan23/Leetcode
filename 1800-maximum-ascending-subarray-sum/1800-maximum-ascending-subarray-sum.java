class Solution {

    public int maxAscendingSum(int[] nums) {
        int maxSum = 0;

        // Outer loop to start from each element in the array
        for (int startIdx = 0; startIdx < nums.length; startIdx++) {
            int currentSubarraySum = nums[startIdx];

            // Inner loop to check the next elements forming an ascending subarray
            for (
                int endIdx = startIdx + 1;
                endIdx < nums.length && nums[endIdx] > nums[endIdx - 1];
                endIdx++
            ) {
                currentSubarraySum += nums[endIdx];
            }

            // Update maxSum if we find a larger ascending subarray sum
            maxSum = Math.max(maxSum, currentSubarraySum);
        }

        return maxSum;
    }
}