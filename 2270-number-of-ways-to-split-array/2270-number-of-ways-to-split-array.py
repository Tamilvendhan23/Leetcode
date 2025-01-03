class Solution:
    def waysToSplitArray(self, nums: list[int]) -> int:
        n = len(nums)
        # Prefix sum array to store cumulative sums
        pref_sum = [0] * n
        pref_sum[0] = nums[0]

        # Build prefix sum array
        for i in range(1, n):
            pref_sum[i] = pref_sum[i - 1] + nums[i]

        # Check each possible split position
        count = sum(
            1 for i in range(n - 1) if pref_sum[i] >= pref_sum[-1] - pref_sum[i]
        )

        return count