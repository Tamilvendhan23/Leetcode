class Solution {
    private static final int MOD = 1_000_000_007;

    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }

    private long modInv(long n) {
        return power(n, MOD - 2);
    }

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int limit = (int) Math.sqrt(n);
        
        // Group queries with small k for later processing
        Map<Integer, List<int[]>> lightK = new HashMap<>();

        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            
            if (k >= limit) { 
                // Large k: apply brute force
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int) ((1L * nums[i] * v) % MOD);
                }
            } else { 
                // Small k: process later
                lightK.computeIfAbsent(k, x -> new ArrayList<>()).add(q);
            }
        }

        for (Map.Entry<Integer, List<int[]>> entry : lightK.entrySet()) {
            int k = entry.getKey();
            List<int[]> queryList = entry.getValue();
            
            // Process small queries grouped by step size k
            long[] diff = new long[n];
            Arrays.fill(diff, 1L);
            
            for (int[] q : queryList) {
                int l = q[0], r = q[1], v = q[3];
                
                // Multiply starting position
                diff[l] = (diff[l] * v) % MOD;
                
                // Cancel the multiplication using modular inverse
                int steps = (r - l) / k;
                int next = l + (steps + 1) * k;
                if (next < n) {
                    diff[next] = (diff[next] * modInv(v)) % MOD;
                }
            }
            
            // Propagate the multipliers with a step size of k
            for (int i = 0; i < n; i++) {
                if (i >= k) {
                    diff[i] = (diff[i] * diff[i - k]) % MOD;
                }
                nums[i] = (int) ((1L * nums[i] * diff[i]) % MOD);
            }
        }

        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }

        return ans;
    }
}