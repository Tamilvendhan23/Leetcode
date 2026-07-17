class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int n = nums.length;
        int maxValue = Arrays.stream(nums).max().orElse(0);
        int[] divisorCount = new int[maxValue + 1];

        for (int number : nums) {
            for (int i = 1; i * i <= number; i++) {
                if (number % i == 0) {
                    divisorCount[i]++;
                    if (i != number / i) {
                        divisorCount[number / i]++;
                    }
                }
            }
        }

        long[] gcdPairCount = new long[maxValue + 1];

        for (int gcd = maxValue; gcd >= 1; gcd--) {
            long count = divisorCount[gcd];
            gcdPairCount[gcd] = count * (count - 1) / 2;
            for (int multiple = 2 * gcd; multiple <= maxValue; multiple += gcd) {
                gcdPairCount[gcd] -= gcdPairCount[multiple];
            }
        }

        long[] prefixSum = new long[maxValue + 1];
        for (int gcd = 1; gcd <= maxValue; gcd++) {
            prefixSum[gcd] = prefixSum[gcd - 1] + gcdPairCount[gcd];
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long query = queries[i];
            long left = 1, right = maxValue, answer = -1;
            while (left <= right) {
                long mid = (left + right) / 2;
                if (prefixSum[(int) mid] > query) {
                    answer = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            result[i] = (int) answer;
        }

        return result;
    }
}