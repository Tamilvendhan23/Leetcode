class Solution {

    public int[] closestPrimes(int left, int right) {
        // Step 1: Get all prime numbers up to 'right' using sieve
        int[] sieveArray = sieve(right);

        List<Integer> primeNumbers = new ArrayList<>(); // Store all primes in the range [left, right]
        for (int num = left; num <= right; num++) {
            // If number is prime add to the primeNumbers list
            if (sieveArray[num] == 1) {
                primeNumbers.add(num);
            }
        }

        // Step 2: Find the closest prime pair
        if (primeNumbers.size() < 2) return new int[] { -1, -1 }; // Less than two primes available

        int minDifference = Integer.MAX_VALUE;
        int[] closestPair = new int[2];
        // setting initial values
        Arrays.fill(closestPair, -1);

        for (int index = 1; index < primeNumbers.size(); index++) {
            int difference =
                primeNumbers.get(index) - primeNumbers.get(index - 1);
            if (difference < minDifference) {
                minDifference = difference;
                closestPair[0] = primeNumbers.get(index - 1);
                closestPair[1] = primeNumbers.get(index);
            }
        }

        return closestPair;
    }

    private int[] sieve(int upperLimit) {
        // Initiate an int array to mark prime numbers (1 = prime, else it's not)
        int[] sieve = new int[upperLimit + 1];
        // Assuming all numbers as prime initially
        Arrays.fill(sieve, 1);

        // 0 and 1 are not prime
        sieve[0] = 0;
        sieve[1] = 0;

        for (int number = 2; number * number <= upperLimit; number++) {
            if (sieve[number] == 1) {
                // Mark all multiples of 'number' as non-prime
                for (
                    int multiple = number * number;
                    multiple <= upperLimit;
                    multiple += number
                ) {
                    sieve[multiple] = 0;
                }
            }
        }
        return sieve;
    }
}