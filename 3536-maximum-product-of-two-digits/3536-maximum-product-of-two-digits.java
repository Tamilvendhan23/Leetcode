class Solution {
    public int maxProduct(int n) {
        int max1 = 0, max2 = 0;

        for (; n != 0; n /= 10) {
            int d = n % 10;
            if (d >= max1) {
                max2 = max1;
                max1 = d;
            } else if (d > max2) 
                max2 = d;
        }

        return max1 * max2;
    }
}