class Solution {
    public int[] resultArray(int[] nums) {
        int[] nums1 = new int[nums.length];
        int[] nums2 = new int[nums.length];
        int n1 = 0;
        int n2 = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                nums1[n1++] = nums[i];
            } else if (i == 1) {
                nums2[n2++] = nums[i];
            } else {
                if (nums1[n1 - 1] > nums2[n2 - 1]) {
                    nums1[n1++] = nums[i];
                } else {
                    nums2[n2++] = nums[i];
                }
            }
        }

        for (int i = 0; i < n2; i++) {
            nums1[n1++] = nums2[i];
        }

        return nums1;
    }
}