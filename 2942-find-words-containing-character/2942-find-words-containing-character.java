class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (char ch : words[i].toCharArray()) {
                if (ch == x) {
                    res.add(i);
                    break;
                }
            }
        }
        return res;
    }
}