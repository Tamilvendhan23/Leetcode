class Solution {

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;

        // Prefix sum array to calculate sum of any subarray in constant time
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        // Arrays to store the best sum and starting indices for up to 3 subarrays
        int[][] bestSum = new int[4][n + 1];
        int[][] bestIndex = new int[4][n + 1];

        // Compute the best sum and indices for 1, 2, and 3 subarrays
        for (int subarrayCount = 1; subarrayCount <= 3; subarrayCount++) {
            for (int endIndex = k * subarrayCount; endIndex <= n; endIndex++) {
                int currentSum =
                    prefixSum[endIndex] -
                    prefixSum[endIndex - k] +
                    bestSum[subarrayCount - 1][endIndex - k];

                // Check if the current configuration gives a better sum
                if (currentSum > bestSum[subarrayCount][endIndex - 1]) {
                    bestSum[subarrayCount][endIndex] = currentSum;
                    bestIndex[subarrayCount][endIndex] = endIndex - k;
                } else {
                    bestSum[subarrayCount][endIndex] =
                        bestSum[subarrayCount][endIndex - 1];
                    bestIndex[subarrayCount][endIndex] =
                        bestIndex[subarrayCount][endIndex - 1];
                }
            }
        }

        // Trace back the indices of the three subarrays
        int[] result = new int[3];
        int currentEnd = n;
        for (int subarrayIndex = 3; subarrayIndex >= 1; subarrayIndex--) {
            result[subarrayIndex - 1] = bestIndex[subarrayIndex][currentEnd];
            currentEnd = result[subarrayIndex - 1];
        }

        return result;
    }
}