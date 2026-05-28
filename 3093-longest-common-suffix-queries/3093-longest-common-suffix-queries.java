class TrieNode {

    TrieNode[] children = new TrieNode[26];
    int minLen = Integer.MAX_VALUE;
    int idx = Integer.MAX_VALUE;

    TrieNode() {
        for (int i = 0; i < 26; i++) {
            children[i] = null;
        }
    }
}

class Trie {

    TrieNode root = new TrieNode();

    void insert(String s, int idx) {
        int len = s.length();
        TrieNode node = root;

        if (len < node.minLen) {
            node.minLen = len;
            node.idx = idx;
        }

        for (char ch : s.toCharArray()) {
            int c = ch - 'a';
            if (node.children[c] == null) {
                node.children[c] = new TrieNode();
            }
            node = node.children[c];

            if (len < node.minLen) {
                node.minLen = len;
                node.idx = idx;
            }
        }
    }

    int query(String s) {
        TrieNode node = root;

        for (char ch : s.toCharArray()) {
            int c = ch - 'a';
            if (node.children[c] != null) {
                node = node.children[c];
            } else {
                break;
            }
        }

        return node.idx;
    }
}

class Solution {

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        Trie trie = new Trie();

        for (int i = 0; i < wordsContainer.length; i++) {
            String reversed = new StringBuilder(wordsContainer[i])
                .reverse()
                .toString();
            trie.insert(reversed, i);
        }

        int[] res = new int[wordsQuery.length];
        for (int i = 0; i < wordsQuery.length; i++) {
            String query = wordsQuery[i];
            String reversed = new StringBuilder(query).reverse().toString();
            res[i] = trie.query(reversed);
        }

        return res;
    }
}