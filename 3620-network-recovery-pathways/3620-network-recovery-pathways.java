class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        int[] indegree = new int[n];
        int maxCost = 0;
        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            indegree[e[1]]++;
            maxCost = Math.max(maxCost, e[2]);
        }
        List<Integer> topo = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        int[] deg = indegree.clone();
        for (int i = 0; i < n; i++) if (deg[i] == 0) q.add(i);
        while (!q.isEmpty()) {
            int u = q.poll();
            topo.add(u);
            for (int[] e : graph[u]) if (--deg[e[0]] == 0) q.add(e[0]);
        }
        int lo = 0, hi = maxCost, ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            final int limit = mid;
            long[] dp = new long[n];
            Arrays.fill(dp, Long.MAX_VALUE / 2);
            dp[0] = 0;
            for (int u : topo) {
                if (dp[u] == Long.MAX_VALUE / 2) continue;
                if (u != 0 && u != n - 1 && !online[u]) continue;
                for (int[] e : graph[u]) {
                    int v = e[0], w = e[1];
                    if (w < limit) continue;
                    if (v != n - 1 && !online[v]) continue;
                    dp[v] = Math.min(dp[v], dp[u] + w);
                }
            }
            if (dp[n - 1] <= k) { ans = mid; lo = mid + 1; }
            else hi = mid - 1;
        }
        return ans;
    }
}