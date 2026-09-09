class Solution {
    public long countCommas(long n) {
        long count = 0;

        for (long p = 1000; p <= n; p *= 1000)
            count += n - p + 1;

        return count;
    }
}