import java.util.*;

class Solution {
    void dfs(int r, int c, String dir, int[][] vis, Map<String, Integer> mp) {
        int n = vis.length;
        int m = vis[0].length;
        if (r < 0 || c < 0 || r >= n || c >= m) return;
        if (mp.containsKey(r + "," + c)) return;
        vis[r][c] = 1;

        if (dir.equals("r")) dfs(r, c + 1, "r", vis, mp);
        if (dir.equals("l")) dfs(r, c - 1, "l", vis, mp);
        if (dir.equals("u")) dfs(r - 1, c, "u", vis, mp);
        if (dir.equals("d")) dfs(r + 1, c, "d", vis, mp);
    }

    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] vis = new int[m][n];
        Queue<int[]> q = new LinkedList<>();
        Map<String, Integer> mp = new HashMap<>();

        for (int[] g : guards) {
            q.add(g);
            mp.put(g[0] + "," + g[1], 1);
            vis[g[0]][g[1]] = 1;
        }

        for (int[] w : walls) {
            mp.put(w[0] + "," + w[1], 1);
            vis[w[0]][w[1]] = 1;
        }

        for (int[] g : guards) {
            int r = g[0], c = g[1];
            dfs(r, c + 1, "r", vis, mp);
            dfs(r, c - 1, "l", vis, mp);
            dfs(r + 1, c, "d", vis, mp);
            dfs(r - 1, c, "u", vis, mp);
        }

        int cnt = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (vis[i][j] == 0) cnt++;
        return cnt;
    }
}