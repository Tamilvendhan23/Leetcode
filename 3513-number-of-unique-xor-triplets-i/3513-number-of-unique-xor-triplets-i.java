class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        int m = n;
        
        m |= m >> 1;
        m |= m >> 2;
        m |= m >> 4;
        m |= m >> 8;
        m |= m >> 16;
        
        return (m + 1) >> (3 / (n + 1));
    }
}