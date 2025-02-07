from typing import List

class Solution:
    def queryResults(self, limit: int, queries: List[List[int]]) -> List[int]:
        result = []
        color_map = {}
        ball_array = {}

        for ball, color in queries:
            prev_color = ball_array.get(ball)

            if prev_color is not None:
                color_map[prev_color] -= 1
                if color_map[prev_color] == 0:
                    del color_map[prev_color]

            ball_array[ball] = color
            color_map[color] = color_map.get(color, 0) + 1

            result.append(len(color_map))

        return result
