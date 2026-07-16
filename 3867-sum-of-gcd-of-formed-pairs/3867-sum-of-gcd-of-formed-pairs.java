class Solution {
    private int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }

    public long gcdSum(int[] A) {
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            max = Math.max(max, A[i]);
            A[i] = gcd(A[i], max);
        }

        Arrays.sort(A);

        long res = 0;        
        for (int i = 0, j = A.length - 1; i < j; i++, j--)
            res += gcd(A[i], A[j]);

        return res;
    }
}