class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        int[] inDegree = new int[n];

        for (int[] inv : invocations) {
            edges[inv[0]].add(inv[1]);
            inDegree[inv[1]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(k);
        boolean[] sus = new boolean[n];
        sus[k] = true;


        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : edges[u]) {
                inDegree[v]--;

                if (!sus[v]) {
                    queue.offer(v);
                    sus[v] = true;
                }
            }
        }

        boolean canRemoveAll = true;
        List<Integer> rem = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (sus[i] && inDegree[i] > 0) {
                canRemoveAll = false;
                break;
            } else if (!sus[i]) {
                rem.add(i);
            }
        }

        if (!canRemoveAll) {
            List<Integer> allNodes = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                allNodes.add(i);
            }
            return allNodes;
        }

        return rem;
    }
}