class Solution {
    public int snakesAndLadders(int[][] board) {
        int size = board.length;
        int target = size * size;

        // Flatten board to 1D
        short[] flattened = new short[target + 1];
        int index = 1;

        for (int row = size - 1; row >= 0; row--) {
            for (int col = 0; col < size; col++) {
                flattened[index++] = (short) board[row][col];
            }
            if (--row < 0) break;
            for (int col = size - 1; col >= 0; col--) {
                flattened[index++] = (short) board[row][col];
            }
        }

        // Array-based BFS queue for constant time enqueue/dequeue
        short[] queue = new short[target];
        int head = 0, tail = 0;
        queue[tail++] = 1;

        // Tracks visited positions and step counts; 0 indicates unvisited
        int[] steps = new int[target + 1];
        steps[1] = 1;

        while (head != tail) {
            int position = queue[head++];
            head %= target;

            // Early exit if target is within one dice roll
            if (position + 6 >= target) {
                return steps[position];
            }

            int maxNeutral = 0;
            for (int roll = 6; roll >= 1; roll--) {
                int next = position + roll;

                if (flattened[next] >= 0) {
                    next = flattened[next];
                    if (next == target) return steps[position];
                } else {
                    // Retain highest neutral roll if no ladder or snake
                    if (roll < maxNeutral) continue;
                    maxNeutral = roll;
                }

                if (steps[next] == 0) {
                    steps[next] = steps[position] + 1;
                    queue[tail++] = (short) next;
                    tail %= target;

                    // Detect buffer overflow in circular queue
                    if (head == tail) return 0;
                }
            }
        }

        return -1;
    }
}

