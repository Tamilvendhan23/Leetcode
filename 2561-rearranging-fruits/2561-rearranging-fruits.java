class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> freq = new HashMap<>();
        int n = basket1.length;
        int minFruit = Integer.MAX_VALUE;

        // Count frequency and track minimum fruit
        for (int i = 0; i < n; i++) {
            freq.put(basket1[i], freq.getOrDefault(basket1[i], 0) + 1);
            freq.put(basket2[i], freq.getOrDefault(basket2[i], 0) - 1);
            minFruit = Math.min(minFruit, Math.min(basket1[i], basket2[i]));
        }

        // List to store the extra fruits that need to be swapped
        List<Integer> excess = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int fruit = entry.getKey();
            int count = entry.getValue();

            // If any difference is odd, it's impossible
            if (count % 2 != 0) return -1;

            // Add only surplus from basket1 (positive difference)
            for (int i = 0; i < Math.abs(count) / 2; i++) {
                excess.add(fruit);
            }
        }

        // Only need to swap half of them
        Collections.sort(excess);
        long cost = 0;
        for (int i = 0; i < excess.size() / 2; i++) {
            cost += Math.min(excess.get(i), 2 * minFruit);
        }

        return cost;
    }
}