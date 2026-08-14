class Solution {
    public int maximumLengthSubstring(String s) {
        int res = 0, l = 0;
        long mask = 0;
        
        for (int r = 0; r < s.length(); r++) {
            int k = (s.charAt(r) & 31) << 1;
            mask += 1L << k;
            
            while (((mask >> k) & 3) == 3)
                mask -= 1L << ((s.charAt(l++) & 31) << 1);
                
            res = Math.max(res, r - l + 1);
        }
        
        return res;
    }
}