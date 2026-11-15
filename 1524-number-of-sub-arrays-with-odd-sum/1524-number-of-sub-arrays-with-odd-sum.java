class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;

        long count = 0;
        long evenPrefixes = 1; // empty prefix has sum 0, which is even
        long oddPrefixes = 0;
        int parity = 0;        // parity of current prefix sum

        for (int num : arr) {
            parity ^= (num & 1); // update prefix parity

            if (parity == 1) {
                count = (count + evenPrefixes) % MOD;
                oddPrefixes++;
            } else {
                count = (count + oddPrefixes) % MOD;
                evenPrefixes++;
            }
        }

        return (int) count;
    }
}