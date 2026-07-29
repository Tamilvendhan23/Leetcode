class Solution {
    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        
        int[] half = new int[26];
        StringBuilder mid = new StringBuilder();
        int m = 0;
        
        for (int i = 0; i < 26; ++i) {
            if (freq[i] % 2 != 0) {
                mid.append((char) (i + 'a'));
            }
            half[i] = freq[i] / 2;
            m += half[i];
        }
        
        if (getWays(half, k) < k) {
            return "";
        }
        
        StringBuilder firstHalf = new StringBuilder();
        for (int i = 0; i < m; ++i) {
            for (int c = 0; c < 26; ++c) {
                if (half[c] > 0) {
                    half[c]--;
                    long ways = getWays(half, k);
                    
                    if (ways >= k) {
                        firstHalf.append((char) (c + 'a'));
                        break;
                    } else {
                        k -= ways;
                        half[c]++;
                    }
                }
            }
        }
        
        StringBuilder res = new StringBuilder(firstHalf);
        res.append(mid);
        res.append(firstHalf.reverse());
        return res.toString();
    }
    
    private long getWays(int[] f, long targetK) {
        long ways = 1;
        int currLen = 0;
        for (int count : f) {
            if (count > 0) {
                currLen += count;
                long n = currLen;
                long r = count;
                
                if (r > n - r) r = n - r;
                long curNCr = 1;
                
                for (int i = 1; i <= r; ++i) {
                    curNCr = curNCr * (n - i + 1) / i;
                    if (curNCr > targetK) {
                        curNCr = targetK + 1;
                        break;
                    }
                }
                ways *= curNCr;
                if (ways > targetK) return targetK + 1;
            }
        }
        return ways;
    }
}