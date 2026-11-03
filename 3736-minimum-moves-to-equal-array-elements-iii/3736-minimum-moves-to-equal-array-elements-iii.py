class Solution:
    def minMoves(self, nums: List[int]) -> int:
        mx = max(nums)
        res = 0
        for x in nums:
            res += mx - x
        return res