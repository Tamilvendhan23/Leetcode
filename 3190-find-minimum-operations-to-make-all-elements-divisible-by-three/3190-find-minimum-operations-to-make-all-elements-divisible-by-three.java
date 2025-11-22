class Solution {

    public int minimumOperations(int[] nums) {
        int sum = 0;
        for (int x : nums) {
            int rem = x % 3;
            sum += Math.min(rem, 3 - rem);
        }
        return sum;
    }
}