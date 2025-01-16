class Solution:
    def xorAllNums(self, nums1: List[int], nums2: List[int]) -> int:
# def xorAllNums(nums1, nums2):
        xor1 = 0
        xor2 = 0
        
        # Compute XOR of nums1
        for num in nums1:
            xor1 ^= num
        
        # Compute XOR of nums2
        for num in nums2:
            xor2 ^= num
        
        # Determine the result based on odd/even counts
        m, n = len(nums1), len(nums2)
        result = 0
        
        if n % 2 == 1:
            result ^= xor1
        if m % 2 == 1:
            result ^= xor2
        
        return result
