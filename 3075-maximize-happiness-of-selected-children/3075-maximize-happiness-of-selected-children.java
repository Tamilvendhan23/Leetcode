class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        long ans = 0;
        int n = happiness.length - 1;
        for(int i = 0; i < k; i++) {
            int val = happiness[n - i] - i;
            if(val > 0) ans += val;
            else break;
        }
        return ans;
    }
}