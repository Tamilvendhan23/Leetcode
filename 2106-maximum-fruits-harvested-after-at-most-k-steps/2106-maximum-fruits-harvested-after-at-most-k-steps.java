import java.util.*;

public class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int left = 0, total = 0, maxFruits = 0;

        for (int right = 0; right < n; right++) {
            total += fruits[right][1]; 

            while (left <= right) {
                int leftPos = fruits[left][0];
                int rightPos = fruits[right][0];

                int dist = Math.min(
                    Math.abs(startPos - leftPos) + (rightPos - leftPos),
                    Math.abs(startPos - rightPos) + (rightPos - leftPos)
                );

                if (dist <= k) break;

                total -= fruits[left][1];  
                left++;
            }

            maxFruits = Math.max(maxFruits, total);
        }

        return maxFruits;
    }
}