import java.util.*;

class Solution {
    public boolean isValid(String s) {
        // Stack to store opening brackets
        Stack<Character> stack = new Stack<>();

        // Iterate through each character in the string
        for (char c : s.toCharArray()) {
            // Push opening brackets onto the stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } 
            // Check closing brackets
            else {
                // If the stack is empty or doesn't match, it's invalid
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') || 
                    (c == '}' && top != '{') || 
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }

        // If the stack is empty, all brackets matched correctly
        return stack.isEmpty();
    }
}
