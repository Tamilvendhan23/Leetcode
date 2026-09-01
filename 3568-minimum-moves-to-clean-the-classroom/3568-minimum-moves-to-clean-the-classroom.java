class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int[][] id = new int[m][n];

        for (int r = 0; r < m; r++) {
            java.util.Arrays.fill(id[r], -1);
        }

        int k = 0;
        int sr = 0, sc = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (classroom[r].charAt(c) == 'S') {
                    sr = r;
                    sc = c;
                } else if (classroom[r].charAt(c) == 'L') {
                    id[r][c] = k++;
                }
            }
        }

        if (k == 0) return 0;

        int totalMask = (1 << k) - 1;

        int[][][] best = new int[m][n][1 << k];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                java.util.Arrays.fill(best[r][c], -1);
            }
        }

        class State {
            int r, c, mask, e, moves;

            State(int r, int c, int mask, int e, int moves) {
                this.r = r;
                this.c = c;
                this.mask = mask;
                this.e = e;
                this.moves = moves;
            }
        }

        java.util.ArrayDeque<State> queue = new java.util.ArrayDeque<>();

        best[sr][sc][0] = energy;
        queue.offer(new State(sr, sc, 0, energy, 0));

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                    continue;

                if (classroom[nr].charAt(nc) == 'X')
                    continue;

                int ne = cur.e - 1;

                if (ne < 0)
                    continue;

                int nmask = cur.mask;

                if (classroom[nr].charAt(nc) == 'R') {
                    ne = energy;
                }

                if (classroom[nr].charAt(nc) == 'L') {
                    nmask |= (1 << id[nr][nc]);
                }

                if (nmask == totalMask) {
                    return cur.moves + 1;
                }

                if (ne <= best[nr][nc][nmask])
                    continue;

                best[nr][nc][nmask] = ne;

                queue.offer(new State(nr, nc, nmask, ne, cur.moves + 1));
            }
        }

        return -1;
    }
}