class Solution {
    // Fenwick Tree (Binary Indexed Tree) for range maximum queries
    class FenwickTree {
        private int[] vals;
        
        public FenwickTree(int n) {
            vals = new int[n + 1];
        }
        
        // Update position i with value val (maximization)
        public void add(int i, int val) {
            while (i < vals.length) {
                vals[i] = Math.max(vals[i], val);
                i += lowbit(i);
            }
        }
        
        // Get maximum value from 1 to i
        public int get(int i) {
            int res = 0;
            while (i > 0) {
                res = Math.max(res, vals[i]);
                i -= lowbit(i);
            }
            return res;
        }
        
        private static int lowbit(int i) {
            return i & -i;
        }
    }
    
    public List<Boolean> getResults(int[][] queries) {
        // Limit coordinate range (max coordinate that matters)
        final int n = Math.min(50000, queries.length * 3);
        List<Boolean> ans = new ArrayList<>();
        
        FenwickTree tree = new FenwickTree(n + 1);
        // TreeSet with sentinel values for edge cases
        TreeSet<Integer> obstacles = new TreeSet<>(Arrays.asList(0, n));
        
        // Step 1: Add all type-1 obstacles to TreeSet first
        for (int[] query : queries) {
            if (query[0] == 1) {
                obstacles.add(query[1]);
            }
        }
        
        // Step 2: Initialize Fenwick Tree with gaps between all obstacles
        Iterator<Integer> it = obstacles.iterator();
        int x1 = it.next();
        while (it.hasNext()) {
            int x2 = it.next();
            tree.add(x2, x2 - x1);  // gap size
            x1 = x2;
        }
        
        // Step 3: Process queries in REVERSE order
        for (int i = queries.length - 1; i >= 0; --i) {
            int type = queries[i][0];
            int x = queries[i][1];
            
            if (type == 1) {
                // Remove obstacle (reverse of adding)
                Integer next = obstacles.higher(x);
                Integer prev = obstacles.lower(x);
                
                if (next != null) {
                    // Update gap that was split by this obstacle
                    tree.add(next, next - prev);
                }
                obstacles.remove(x);
            } else {
                // Query: check if space of size sz fits at position x
                int sz = queries[i][2];
                Integer prev = obstacles.floor(x);
                
                // Check two possibilities:
                // 1. Gap to the left of x (from prev to x)
                // 2. Maximum gap in range [0, prev]
                boolean canFit = tree.get(prev) >= sz || (x - prev) >= sz;
                ans.add(canFit);
            }
        }
        
        // Reverse answer since we processed in reverse order
        Collections.reverse(ans);
        return ans;
    }
}