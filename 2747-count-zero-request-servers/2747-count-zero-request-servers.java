class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int[][] reqs = Arrays.copyOf(logs, logs.length + 1);
        reqs[logs.length] = new int[] {0, Integer.MAX_VALUE};  // sentinel: no window ever reaches it
        Arrays.sort(reqs, 0, logs.length, Comparator.comparingInt(r -> r[1]));  // by time, the sentinel stays last
        Integer[] order = new Integer[queries.length];
        Arrays.setAll(order, i -> i);
        Arrays.sort(order, Comparator.comparingInt(i -> queries[i]));  // query positions in time order

        int[] cnt = new int[n + 1], ans = new int[queries.length];  // cnt: requests per server inside the window [q - x, q]
        int busy = 0, lo = 0, hi = 0;  // busy: servers with at least one request in it
        for (int i : order) {
            int q = queries[i];
            for (; reqs[hi][1] <= q; hi++)  // requests up to q come in
                if (cnt[reqs[hi][0]]++ == 0) busy++;
            for (; reqs[lo][1] < q - x; lo++)  // requests before q - x go out
                if (--cnt[reqs[lo][0]] == 0) busy--;
            ans[i] = n - busy;
        }
        return ans;
    }
}