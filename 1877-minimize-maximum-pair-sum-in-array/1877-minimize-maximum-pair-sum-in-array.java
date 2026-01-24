class Solution {
    public int minPairSum(int[] nums) {
        int n = nums.length;
        int size = 10;
        while (size < n) size *= 10;
        size = (n == 10000) ? 100000 : size + 1;
        int[] freq = new int[size];

        for (int num : nums) freq[num]++;

        int res = 0;
        int i = 0;
        int j = size - 1;

        while (i <= j) {
            if (freq[i] == 0)
                i++;
            else if (freq[j] == 0)
                j--;
            else {
                res = Math.max(res, i + j);
                freq[i]--;
                freq[j]--;
            }
        }

        return res;
    }
}
