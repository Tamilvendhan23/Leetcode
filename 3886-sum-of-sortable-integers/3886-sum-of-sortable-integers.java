class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] desc = new int[n];  // desc[i]: number of descents nums[j] > nums[j + 1] with j < i
        for (int i = 1; i < n; i++) desc[i] = desc[i - 1] + (nums[i - 1] > nums[i] ? 1 : 0);
        int[] premax = nums.clone(), sufmin = nums.clone();
        Arrays.parallelPrefix(premax, Math::max);  // premax[i]: max of nums[0..i]
        for (int i = n - 2; i >= 0; i--) sufmin[i] = Math.min(sufmin[i], sufmin[i + 1]);  // sufmin[i]: min of nums[i..n-1]
        boolean[] cut = new boolean[n];  // cut[m]: nums[0..m-1] holds exactly the m smallest values
        cut[0] = true;
        for (int m = 1; m < n; m++) cut[m] = premax[m - 1] <= sufmin[m];

        BiPredicate<Integer, Integer> rotatable = (l, r) ->  // nums[l..r] is a rotated sorted run: at most one cyclic descent
            desc[r] - desc[l] + (nums[r] > nums[l] ? 1 : 0) <= 1;
        IntPredicate sortable = k ->  // every block starts at a valid cut and can be rotated into order
            IntStream.iterate(0, l -> l < n, l -> l + k).allMatch(l -> cut[l] && rotatable.test(l, l + k - 1));

        IntStream divisors = IntStream.rangeClosed(1, n).filter(k -> n % k == 0);
        return divisors.filter(sortable).sum();
    }
}