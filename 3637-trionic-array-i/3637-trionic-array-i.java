class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        // Precompute strictly increasing till each index
        boolean[] incLeft = new boolean[n];
        incLeft[0] = true;
        for (int i = 1; i < n; i++) {
            incLeft[i] = incLeft[i-1] && nums[i-1] < nums[i];
        }
        // Precompute strictly increasing from each index to end
        boolean[] incRight = new boolean[n];
        incRight[n-1] = true;
        for (int i = n-2; i >= 0; i--) {
            incRight[i] = incRight[i+1] && nums[i] < nums[i+1];
        }
        // Now check all valid p, q
        for (int p = 1; p < n-2; p++) {
            if (!incLeft[p]) continue;
            for (int q = p+1; q < n-1; q++) {
                // Check decreasing from p to q
                boolean dec = true;
                for (int k = p; k < q; k++) {
                    if (nums[k] <= nums[k+1]) {
                        dec = false;
                        break;
                    }
                }
                if (!dec) continue;
                if (incRight[q]) return true;
            }
        }
        return false;
    }
}
