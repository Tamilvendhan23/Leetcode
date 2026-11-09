class Solution {
    record Job(int at, int server) {}  // at: the time its server frees up

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        PriorityQueue<Integer> free = new PriorityQueue<>();  // each free server, keyed by its next turn
        for (int x = 0; x < k; x++) free.add(x);  // at request 0, server x's next turn is x
        PriorityQueue<Job> busy = new PriorityQueue<>(Comparator.comparingInt(Job::at));

        int[] handled = new int[k];
        for (int i = 0; i < arrival.length; i++) {
            while (!busy.isEmpty() && busy.peek().at() <= arrival[i]) {  // done by the arrival
                int x = busy.poll().server();
                free.add(i + Math.floorMod(x - i, k));  // its first turn from i on
            }
            if (free.isEmpty()) continue;  // none free: the request is dropped
            int x = free.poll() % k;
            busy.add(new Job(arrival[i] + load[i], x));
            handled[x]++;
        }

        int most = Arrays.stream(handled).max().getAsInt();
        return IntStream.range(0, k).filter(x -> handled[x] == most).boxed().toList();
    }
}