class Solution {
    public int triangleNumber(int[] nums) {
      Arrays.sort(nums); // sort the array first
        int n = nums.length;
        int count = 0;

        // fix the largest side nums[i]
        for (int i = n - 1; i >= 2; i--) {
            int left = 0;
            int right = i - 1;

            // use two pointers to find valid pairs
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    // all pairs between left and right are valid
                    count += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }

        return count;  
    }
}