import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> graph = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            if (col >= 2 && col <= 9) {
                int mask = graph.getOrDefault(row, 0);
                mask |= (1 << (col - 2));
                graph.put(row, mask);
            }
        }
        
        // Base case: Assume 2 families per empty row
        int maxFamilies = 2 * n; 
        
        for (int mask : graph.values()) {
            boolean left = (mask & 15) == 0;    // Seats 2,3,4,5 (bits 0-3)
            boolean right = (mask & 240) == 0;  // Seats 6,7,8,9 (bits 4-7)
            boolean mid = (mask & 60) == 0;     // Seats 4,5,6,7 (bits 2-5)
            
            maxFamilies -= 2; // Deduct default 2 families
            
            if (left && right) {
                maxFamilies += 2;
            } else if (left || right || mid) {
                maxFamilies += 1;
            }
        }
        
        return maxFamilies;
    }
}