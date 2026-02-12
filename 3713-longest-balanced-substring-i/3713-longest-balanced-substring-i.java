class Solution {
    public int longestBalanced(String s) {
        int n = s.length();

        // Transform char -> int
        int[] a = new int[n];
        for (int i = 0; i < n; i++) 
            a[i] = s.charAt(i) - 'a';

        int result = 0;
        for (int l = 0; l < n; l++) {
            // Early exit, can't be bigger
            if (n - l <= result) 
                break;

            int[] cnt = new int[26]; // Counts of every char
            int uniq = 0, maxfreq = 0; // Number of uniq chars and maximum frequency
            for (int r = l; r < n; r++) {
                int i = a[r];

                // There was no this char before => one more uniq
                if (cnt[i] == 0) 
                    uniq++;

                cnt[i]++;
                // Update max frequency
                if (cnt[i] > maxfreq) 
                    maxfreq = cnt[i];

                // Check if all uniq chars have maxfreq frequency then update the result
                int cur = r - l + 1;
                if (uniq * maxfreq == cur && cur > result)
                    result = cur;
            }
        }
        return result;
    }
}