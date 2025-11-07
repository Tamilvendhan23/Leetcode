class Solution {

    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long[] cnt = new long[n + 1];

        for (int i = 0; i < n; i++) {
            int left = Math.max(0, i - r);
            int right = Math.min(n, i + r + 1);
            cnt[left] += stations[i];
            cnt[right] -= stations[i];
        }

        long lo = Arrays.stream(stations).min().getAsInt();
        long hi = Arrays.stream(stations).asLongStream().sum() + k;
        long res = 0;

        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            if (check(cnt, mid, r, k)) {
                res = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return res;
    }

    private boolean check(long[] cnt, long val, int r, int k) {
        int n = cnt.length - 1;
        long[] diff = cnt.clone();
        long sum = 0;
        long remaining = k;

        for (int i = 0; i < n; i++) {
            sum += diff[i];
            if (sum < val) {
                long add = val - sum;
                if (remaining < add) {
                    return false;
                }
                remaining -= add;
                int end = Math.min(n, i + 2 * r + 1);
                diff[end] -= add;
                sum += add;
            }
        }
        return true;
    }
}