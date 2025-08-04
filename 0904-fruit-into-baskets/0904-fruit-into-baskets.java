class Solution {
    public int totalFruit(int[] fruits) {
        int firstType = fruits[0];      // First fruit type in current window
        int firstIndex = 0;            // Start index of first type
        int secondType = -1;           // Second fruit type (initially none)
        int secondIndex = -1;          // Start index of second type
        int maxFruits = 0;             // Maximum fruits collected
        
        for (int i = 0; i < fruits.length; i++) {
            // Current fruit matches one of our two types
            if (fruits[i] == firstType || (secondIndex != -1 && fruits[i] == secondType)) {
                continue;
            }
            
            // Encountering second type for the first time
            if (secondIndex == -1) {
                secondType = fruits[i];
                secondIndex = i;
            } 
            // Encountering a third type - need to adjust window
            else {
                // Update maximum before adjusting window
                maxFruits = Math.max(maxFruits, i - firstIndex);
                
                // Find where the previous streak of same fruit ends
                int prevFruit = fruits[i-1];
                int newStart = i-1;
                while (newStart >= 0 && fruits[newStart] == prevFruit) {
                    newStart--;
                }
                newStart++;
                
                // Update window to exclude the older fruit type
                firstType = prevFruit;
                firstIndex = newStart;
                secondType = fruits[i];
                secondIndex = i;
            }
        }
        
        // Final check for the last window
        return Math.max(maxFruits, fruits.length - firstIndex);
    }
}