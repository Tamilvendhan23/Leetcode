class Solution {
  private static final int MOD = 1_000_000_007;
  public int lengthAfterTransformations(String s, int t) {
    int[] freq = new int[26];
    for (char c : s.toCharArray()) freq[c-'a']++;
    while (t-- > 0) {
      int[] temp = new int[26];
      for (int j=0; j<25; j++) temp[j+1] = freq[j];
      temp[0] = (temp[0] + freq[25]) % MOD;
      temp[1] = (temp[1] + freq[25]) % MOD;
      freq = temp;
    }
    long ans=0;
    for (int x: freq) ans=(ans+x)%MOD;
    return (int)ans;
  }
}