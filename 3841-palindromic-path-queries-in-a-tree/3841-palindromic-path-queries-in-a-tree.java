class Tree {  // Euler tour: the subtree of x is tin[x] .. tout[x] - 1; LCA by a sparse table over the DFS order
    final int[] parent, tin, tout, order;  // the root is its own parent; order: the nodes in DFS order
    final int[][] first;  // first[k][i]: the earliest parent among order[i .. i + 2^k - 1]

    Tree(int n, int[][] edges) {
        int[] degree = new int[n];
        for (int[] e : edges) {
            degree[e[0]]++;
            degree[e[1]]++;
        }
        int[][] graph = new int[n][];
        for (int x = 0; x < n; x++) graph[x] = new int[degree[x]];
        for (int[] e : edges) {
            graph[e[0]][--degree[e[0]]] = e[1];
            graph[e[1]][--degree[e[1]]] = e[0];
        }

        parent = new int[n];
        tin = new int[n];
        tout = new int[n];
        order = new int[n];
        int[] stack = new int[2 * n];
        int top = 0, time = 0;
        stack[top++] = 0;
        while (top > 0) {  // DFS without recursion; ~x marks the moment x's subtree is done
            int x = stack[--top];
            if (x < 0) {
                tout[~x] = time;
                continue;
            }
            tin[x] = time;
            order[time++] = x;
            stack[top++] = ~x;
            for (int y : graph[x])
                if (y != parent[x]) {
                    parent[y] = x;
                    stack[top++] = y;
                }
        }

        first = new int[32 - Integer.numberOfLeadingZeros(n)][];
        first[0] = new int[n];
        for (int i = 0; i < n; i++) first[0][i] = tin[parent[order[i]]];
        for (int k = 1; k < first.length; k++) {
            first[k] = new int[n - (1 << k) + 1];
            for (int i = 0; i < first[k].length; i++) first[k][i] = Math.min(first[k - 1][i], first[k - 1][i + (1 << (k - 1))]);
        }
    }

    int lca(int u, int v) {  // the parent that comes first in DFS order among the nodes after u up to v
        if (u == v) return u;
        int l = Math.min(tin[u], tin[v]), r = Math.max(tin[u], tin[v]);
        int k = 31 - Integer.numberOfLeadingZeros(r - l);
        return order[Math.min(first[k][l + 1], first[k][r - (1 << k) + 1])];
    }
}

class RootPaths {  // get(x): XOR of the letter bits from the root down to x
    private final Tree tree;
    private final int[] start, fenwick;  // the masks for the initial letters, and a Fenwick tree over the Euler tour with the changes since the start

    RootPaths(Tree tree, int[] bit) {
        this.tree = tree;
        start = bit.clone();
        for (int x : tree.order)  // a parent comes before its children in DFS order
            if (x != tree.parent[x]) start[x] ^= start[tree.parent[x]];
        fenwick = new int[bit.length + 1];
    }

    void flip(int x, int d) {  // XOR d into the root path of every node in x's subtree
        for (int i : new int[] {tree.tin[x] + 1, tree.tout[x] + 1})  // d starts at x's subtree and cancels out after it
            for (; i < fenwick.length; i += i & -i) fenwick[i] ^= d;
    }

    int get(int x) {
        int mask = start[x];
        for (int i = tree.tin[x] + 1; i > 0; i -= i & -i) mask ^= fenwick[i];
        return mask;
    }
}

class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        Tree tree = new Tree(n, edges);
        int[] bit = new int[n];  // bit[x]: the bit of x's letter
        for (int x = 0; x < n; x++) bit[x] = 1 << (s.charAt(x) - 'a');
        RootPaths paths = new RootPaths(tree, bit);

        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            int end = q.length();
            if (q.charAt(0) == 'u') {  // "update x c"
                int x = Integer.parseInt(q, 7, end - 2, 10), newBit = 1 << (q.charAt(end - 1) - 'a');
                paths.flip(x, bit[x] ^ newBit);  // the new letter changes the root path of every node in x's subtree
                bit[x] = newBit;
            } else {  // "query u v"
                int space = q.indexOf(' ', 6);
                int u = Integer.parseInt(q, 6, space, 10), v = Integer.parseInt(q, space + 1, end, 10);
                int mask = paths.get(u) ^ paths.get(v) ^ bit[tree.lca(u, v)];  // the LCA cancels out in get(u) ^ get(v), so add it back
                ans.add(Integer.bitCount(mask) <= 1);  // at most one letter is odd
            }
        }
        return ans;
    }
}