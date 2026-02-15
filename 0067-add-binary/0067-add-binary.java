class Solution {
    public String addBinary(String a, String b) {
        int n1 = a.length(), n2 = b.length();
        int max = Math.max(n1, n2);
        int C = 0, i = 0;

        StringBuilder sb = new StringBuilder();

        while (i < max || C > 0) {
            int A = i < n1 ? a.charAt(n1 - 1 - i) - '0' : 0;
            int B = i < n2 ? b.charAt(n2 - 1 - i) - '0' : 0;

            int S = (A ^ B) ^ C;
            int C_out = ((A ^ B) & C) | (A & B);

            sb.append((char)(S + '0'));
            C = C_out;

            i++;
        }

        return sb.reverse().toString();
    }
}
