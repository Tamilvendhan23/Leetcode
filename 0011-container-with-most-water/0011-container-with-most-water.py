class Solution:
    def maxArea(self, height: List[int]) -> int:
        
        left, right = 0, len(height) - 1
        max_area = 0
        
        while left < right:
            # Calculate area
            area = min(height[left], height[right]) * (right - left)
            max_area = max(max_area, area)
            
            # Move the pointer of the shorter line
            if height[left] < height[right]:
                left += 1
            else:
                right -= 1
        
        return max_area

            