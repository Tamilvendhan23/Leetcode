class Solution {
    public int minScore(int n, int[][] roads) {

        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int w = road[2];

            adj.get(u).add(new int[]{v, w});
            adj.get(v).add(new int[]{u, w});
        }

        boolean[] vis = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();

        q.offer(1);
        vis[1] = true;

        int ans = Integer.MAX_VALUE;

        while (!q.isEmpty()) {

            int node = q.poll();

            for (int[] edge : adj.get(node)) {
                int nei = edge[0];
                int wt = edge[1];

                ans = Math.min(ans, wt);

                if (!vis[nei]) {
                    vis[nei] = true;
                    q.offer(nei);
                }
            }
        }

        return ans;
    }
}