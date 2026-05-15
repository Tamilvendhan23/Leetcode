class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        int lastElement = nums[n - 1];
        
        int left = 0;
        int right = n - 1;
        int minIndex = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // If the middle element is less than or equal to the last element,
            // the minimum must be at 'mid' or to the left of it.
            if (nums[mid] <= lastElement) {
                minIndex = mid;
                right = mid - 1;
            } else {
                // Otherwise, the minimum must be to the right of 'mid'.
                left = mid + 1;
            }
        }

        return nums[minIndex];
    }
}