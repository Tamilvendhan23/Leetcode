class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int lo = 0, hi = n - 1;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            
            if (nums[mid] > nums[n - 1]) lo = mid + 1;
            else hi = mid;
        }

        int rot = lo;
        lo = 0; hi = n - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int real = (mid + rot) % n;

            if (nums[real] == target)
                return real;
            
            if (nums[real] < target) lo = mid + 1;
            else hi = mid - 1;
        }

        return -1;
    }
}