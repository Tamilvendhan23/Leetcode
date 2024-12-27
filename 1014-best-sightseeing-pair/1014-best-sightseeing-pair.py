class Solution:
    def maxScoreSightseeingPair(self, values):
        n = len(values)
        # Initialize a list to store the maximum left scores up to each index.
        max_left_score = [0] * n
        # The left score at the first index is just the value of the first element.
        max_left_score[0] = values[0]

        max_score = 0

        for i in range(1, n):
            current_right_score = values[i] - i
            # Update the maximum score by combining the best left score so far with the current right score.
            max_score = max(
                max_score, max_left_score[i - 1] + current_right_score
            )

            current_left_score = values[i] + i
            # Update the maximum left score up to the current index.
            max_left_score[i] = max(max_left_score[i - 1], current_left_score)

        return max_score  