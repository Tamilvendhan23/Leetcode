public class Solution {

    public String processStr(String s) {
        StringBuilder result = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == '*') {
                if (result.length() > 0) result.deleteCharAt(
                    result.length() - 1
                );
            } else if (ch == '#') {
                result.append(result.toString());
            } else if (ch == '%') {
                result.reverse();
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
}