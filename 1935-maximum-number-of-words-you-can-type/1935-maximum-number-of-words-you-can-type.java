class Solution {
    public int canBeTypedWords(String text, String broken) {
        int mask = 0;
        for (int i = 0; i < broken.length(); i++)
            mask |= 1 << (broken.charAt(i) - 97);

        int count = 0;
        int wordMask = 0;
        for (int i = 0; i <= text.length(); i++) {
            if (i < text.length())
                wordMask |= mask & (1 << (text.charAt(i) - 97));

            if (i == text.length() || text.charAt(i) == ' ') {
                if (wordMask == 0)
                    count++;
                wordMask = 0;
            }
        }
        return count;
    }
}