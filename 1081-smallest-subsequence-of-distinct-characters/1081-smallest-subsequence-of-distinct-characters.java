class Solution {
    public String smallestSubsequence(String s) {
        int[] freq = new int[27];
        boolean[] seen = new boolean[27];
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++)
            freq[s.charAt(i) & 31]++;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int x = c & 31;
            freq[x]--;

            if (seen[x])
                continue;

            while (!stack.isEmpty()) {
                if (stack.peek() <= c)
                    break;

                if (freq[stack.peek() & 31] == 0)
                    break;

                seen[stack.peek() & 31] = false;
                stack.pop();
            }

            stack.push(c);
            seen[x] = true;
        }

        StringBuilder res = new StringBuilder();

        for (char c : stack)
            res.append(c);

        return res.toString();
    }
}