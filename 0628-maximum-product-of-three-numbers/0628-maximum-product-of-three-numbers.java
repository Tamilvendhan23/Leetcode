class Solution {
    public int maximumProduct(int[] A) {
        Arrays.sort(A);
        int n = A.length;        
        return Math.max(
            A[n - 1] * A[n - 2] * A[n - 3],
            A[n - 1] * A[0] * A[1]
        );
    }
}