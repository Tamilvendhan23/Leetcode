class Solution {
    static class Node {char leftChar; char rightChar; int length; int prefix; int suffix;
int best;
        Node(char leftChar, char rightChar, int length, int prefix, int suffix, int best) {
            this.leftChar = leftChar;
            this.rightChar = rightChar;
            this.length = length;
            this.prefix = prefix;
            this.suffix = suffix;
            this.best = best;
        }
    }

    private Node[] tree;

    private Node merge(Node left, Node right) {
        int length = left.length + right.length;
        int prefix = left.prefix;

        if (left.rightChar == right.leftChar && left.prefix == left.length) {
            prefix = left.length + right.prefix;
        }

        int suffix = right.suffix;

        if (left.rightChar == right.leftChar && right.suffix == right.length) {
            suffix = right.length + left.suffix;
        }

        int best = Math.max(left.best, right.best);

        if (left.rightChar == right.leftChar) {
            best = Math.max(best, left.suffix + right.prefix);
        }

        return new Node(left.leftChar, right.rightChar, length, prefix, suffix, best);
    }

    private void build( int node, int start, int end, String s) {
        if (start == end) {
            tree[node] = new Node( s.charAt(start), s.charAt(start), 1, 1, 1, 1);
            return;
        }

        int mid = (start + end) / 2;

        build(node * 2, start, mid, s);
        build(node * 2 + 1, mid + 1, end, s);

        tree[node] = merge(tree[node * 2],tree[node * 2 + 1]
        );
    }

    private void update( int node, int start, int end, int index, char ch) {
        if (start == end) {
            tree[node] = new Node(ch, ch, 1, 1, 1, 1);
            return;
        }

        int mid = (start + end) / 2;

        if (index <= mid) {
            update(node * 2, start, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, end, index, ch);
        }

        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        tree = new Node[4 * n];
        build(1, 0, n - 1, s);
        int[] answer = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            answer[i] = tree[1].best;
        }

        return answer;
    }
}