class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        // * Step 1: Drink all - Now numBottles display number of empty bottles
        int drink = numBottles;
        // Exchange while possible
        while (numExchange <= numBottles) {
            // * 2a: Add a bottle after exchange (drink now)
            ++drink;
            // * 2b: Decrease numExchange bottles from numBottles (empty bottles)
            numBottles -= numExchange;
            // * 2c: Increase exchange
            ++numExchange;
            // * 2d: Increase empty bottles
            ++numBottles;
        }
        return drink;
    }
}