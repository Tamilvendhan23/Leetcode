class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char min = s.charAt(0), max = s.charAt(0);
        boolean isSorted = true;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) isSorted = false;
            if (s.charAt(i) < min) min = s.charAt(i);
            if (s.charAt(i) > max) max = s.charAt(i);
        }

        if (isSorted) return 0;
        if (n == 2) return -1;
        if (s.charAt(0) == min || s.charAt(n - 1) == max)
            return 1;

        for (int i = 1; i < n - 1; i++)
            if (s.charAt(i) == min || s.charAt(i) == max)
                return 2;

        return 3;
    }
}