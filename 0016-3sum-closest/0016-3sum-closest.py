class Solution:
    def threeSumClosest(self, nums: List[int], target: int) -> int:
    # def threeSumClosest(nums, target):
        nums.sort()
        closest_sum = float('inf')
        
        for i in range(len(nums) - 2):
            left, right = i + 1, len(nums) - 1
            while left < right:
                current_sum = nums[i] + nums[left] + nums[right]
                
                # Update the closest sum if necessary
                if abs(target - current_sum) < abs(target - closest_sum):
                    closest_sum = current_sum
                
                # Adjust pointers based on the comparison with target
                if current_sum < target:
                    left += 1
                elif current_sum > target:
                    right -= 1
                else:
                    return current_sum  # If the exact sum is found
        
        return closest_sum
