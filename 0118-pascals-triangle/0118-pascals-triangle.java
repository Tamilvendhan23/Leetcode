class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        // First row is always [1]
        if (numRows >= 1) {
            triangle.add(new ArrayList<>());
            triangle.get(0).add(1);
        }

        // Start from second row
        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = triangle.get(i - 1);

            // First element is always 1
            row.add(1);

            // Each middle element is sum of two elements above it
            for (int j = 1; j < i; j++) {
                row.add(prevRow.get(j - 1) + prevRow.get(j));
            }

            // Last element is always 1
            row.add(1);

            triangle.add(row);
        }

        return triangle;
    }
}