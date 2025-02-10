class Solution {

    public String clearDigits(String s) {
        int charIndex = 0;

        StringBuilder sb = new StringBuilder(s);

        // Until we reach the end of the string
        while (charIndex < sb.length()) {
            if (Character.isDigit(sb.charAt(charIndex))) {
                // Remove the digit from the string
                sb.deleteCharAt(charIndex);
                // If there is a character to the left of the digit, remove it
                if (charIndex > 0) {
                    sb.deleteCharAt(charIndex - 1);
                    // Adjust the index to account for the removed character
                    charIndex--;
                }
            } else {
                // Move to the next character if it's not a digit
                charIndex++;
            }
        }
        return sb.toString();
    }
}