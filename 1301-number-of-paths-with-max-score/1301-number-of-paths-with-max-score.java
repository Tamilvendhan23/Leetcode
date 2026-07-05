class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        final int MOD = 1_000_000_007;
        int n = board.size();

        int[] nextScore = new int[n + 1];
        int[] nextWays = new int[n + 1];

        Arrays.fill(nextScore, -1);

        for (int i = n - 1; i >= 0; i--) {
            int[] currScore = new int[n + 1];
            int[] currWays = new int[n + 1];

            Arrays.fill(currScore, -1);

            for (int j = n - 1; j >= 0; j--) {
                char cell = board.get(i).charAt(j);

                if (cell == 'X') {
                    continue;
                }

                if (cell == 'S') {
                    currScore[j] = 0;
                    currWays[j] = 1;
                    continue;
                }

                int best = Math.max(
                    nextScore[j],
                    Math.max(currScore[j + 1], nextScore[j + 1])
                );

                if (best == -1) {
                    continue;
                }

                long ways = 0;

                if (nextScore[j] == best) {
                    ways += nextWays[j];
                }
                if (currScore[j + 1] == best) {
                    ways += currWays[j + 1];
                }
                if (nextScore[j + 1] == best) {
                    ways += nextWays[j + 1];
                }

                int value = (cell == 'E') ? 0 : cell - '0';

                currScore[j] = best + value;
                currWays[j] = (int) (ways % MOD);
            }

            nextScore = currScore;
            nextWays = currWays;
        }

        if (nextScore[0] == -1) {
            return new int[]{0, 0};
        }

        return new int[]{nextScore[0], nextWays[0]};
    }
}