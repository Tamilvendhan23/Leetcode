class Window {
    private final int[] nums;
    private final int x, odd;
    private final Comparator<Integer> order;  // by value, then by index
    private final TreeSet<Integer> s;         // indices of the window
    private int mid;                          // index of the (h+1)-th smallest: everything before it is the low half
    private long low, total;

    Window(int[] nums, int x) {
        this.nums = nums;
        this.x = x;
        odd = x & 1;
        order = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : Integer.compare(a, b);
        s = new TreeSet<>(order);
        for (int i = 0; i < x; i++) {
            s.add(i);
            total += nums[i];
        }
        Iterator<Integer> it = s.iterator();
        for (int i = 0; i < x / 2; i++)
            low += nums[it.next()];
        mid = it.next();
    }

    long cost() {  // larger half minus smaller half
        return total - 2 * low - (long) odd * nums[mid];
    }

    long slide(int in) {
        int out = in - x;
        s.add(in);
        if (order.compare(in, mid) < 0) {  // in joins the low half and pushes its largest up
            mid = s.lower(mid);
            low += nums[in] - nums[mid];
        }
        if (order.compare(out, mid) < 0) {  // out leaves the low half: mid moves down into it
            low += nums[mid] - nums[out];
            mid = s.higher(mid);
        } else if (out == mid) {
            mid = s.higher(mid);
        }
        s.remove(out);
        total += nums[in] - nums[out];
        return cost();
    }
}

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        Window win = new Window(nums, x);
        long[] cost = new long[n - x + 1];
        cost[0] = win.cost();
        for (int i = x; i < n; i++)
            cost[i - x + 1] = win.slide(i);

        long[] best = new long[n + 1];  // best[i]: min ops for the windows placed so far inside nums[0..i)
        for (int j = 0; j < k; j++) {
            long[] layer = new long[n + 1];
            Arrays.fill(layer, 0, x, Long.MAX_VALUE / 2);
            for (int i = x; i <= n; i++)
                layer[i] = Math.min(layer[i - 1], best[i - x] + cost[i - x]);
            best = layer;
        }
        return best[n];
    }
}