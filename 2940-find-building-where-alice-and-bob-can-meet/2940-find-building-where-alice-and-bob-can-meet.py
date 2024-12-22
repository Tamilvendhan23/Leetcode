class Solution:
    def leftmostBuildingQueries(self, heights: List[int], queries: List[List[int]]) -> List[int]:
        n = len(heights)
        st = [[0] * 20 for _ in range(n)]
        Log = [-1] * (n + 1)
        Log[0] = -1
        for i in range(1, n + 1):
            Log[i] = Log[i >> 1] + 1
        for i in range(n):
            st[i][0] = heights[i]
        for i in range(1, 20):
            for j in range(n):
                if j + (1 << i) <= n:
                    st[j][i] = max(st[j][i - 1], st[j + (1 << (i - 1))][i - 1])

        def Ask(l, r):
            k = Log[r - l + 1]
            return max(st[l][k], st[r - (1 << k) + 1][k])

        res = []
        for l, r in queries:
            if l > r:
                l, r = r, l
            if l == r:
                res.append(l)
                continue
            if heights[r] > heights[l]:
                res.append(r)
                continue
            max_height = max(heights[r], heights[l])
            left, right = r + 1, n
            while left < right:
                mid = (left + right) // 2
                if Ask(r + 1, mid) > max_height:
                    right = mid
                else:
                    left = mid + 1
            res.append(left if left != n else -1)
        return res