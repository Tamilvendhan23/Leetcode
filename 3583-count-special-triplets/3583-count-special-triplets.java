class Solution {

    public int specialTriplets(int[] nums) {
        final int MOD = 1000000007;
        Map<Integer, Integer> numCnt = new HashMap<>();
        Map<Integer, Integer> numPartialCnt = new HashMap<>();

        for (int v : nums) {
            numCnt.put(v, numCnt.getOrDefault(v, 0) + 1);
        }

        long ans = 0;
        for (int v : nums) {
            int target = v * 2;
            int lCnt = numPartialCnt.getOrDefault(target, 0);
            numPartialCnt.put(v, numPartialCnt.getOrDefault(v, 0) + 1);
            int rCnt =
                numCnt.getOrDefault(target, 0) -
                numPartialCnt.getOrDefault(target, 0);
            ans = (ans + (long) lCnt * rCnt) % MOD;
        }

        return (int) ans;
    }
}