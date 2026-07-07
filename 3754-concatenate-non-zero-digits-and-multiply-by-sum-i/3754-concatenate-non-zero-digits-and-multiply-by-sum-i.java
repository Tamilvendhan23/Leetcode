class Solution {

    public long sumAndMultiply(int n) {
        long x = 0;
        long sum = 0;
        String s = String.valueOf(n);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = c - '0';
            sum += d;
            if (d > 0) {
                x = x * 10 + d;
            }
        }
        return x * sum;
    }
}