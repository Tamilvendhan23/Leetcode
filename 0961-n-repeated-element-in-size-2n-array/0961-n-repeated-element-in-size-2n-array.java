class Solution {
    public int repeatedNTimes(int[] A) {
        for (int i = 0; i < A.length - 2; i++)
            if (A[i] == A[i + 1] || A[i] == A[i + 2])
                return A[i];
        return A[A.length - 1];
    }
}
