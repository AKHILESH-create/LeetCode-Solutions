import java.util.*;

class Solution {

    class FenwickTree {
        int[] tree;

        FenwickTree(int n) {
            tree = new int[n + 2];
        }

        void update(int idx, int val) {
            while (idx < tree.length) {
                tree[idx] = Math.max(tree[idx], val);
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            int res = 0;

            while (idx > 0) {
                res = Math.max(res, tree[idx]);
                idx -= idx & -idx;
            }

            return res;
        }
    }

    public List<Boolean> getResults(int[][] queries) {

        int limit = 50000;

        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);
        obstacles.add(limit);

        for (int[] q : queries) {
            if (q[0] == 1) {
                obstacles.add(q[1]);
            }
        }

        FenwickTree bit = new FenwickTree(limit + 2);

        Integer prev = null;
        for (int pos : obstacles) {
            if (prev != null) {
                bit.update(pos, pos - prev);
            }
            prev = pos;
        }

        List<Boolean> ans = new ArrayList<>();

        for (int i = queries.length - 1; i >= 0; i--) {

            int[] q = queries[i];

            if (q[0] == 1) {

                int x = q[1];

                Integer next = obstacles.higher(x);
Integer p = obstacles.lower(x);

if (next != null && p != null) {
    bit.update(next, next - p);
}

obstacles.remove(x);

            } else {

                int x = q[1];
                int sz = q[2];

                Integer leftObstacle = obstacles.floor(x);

                int maxGap = Math.max(
                        bit.query(leftObstacle),
                        x - leftObstacle
                );

                ans.add(maxGap >= sz);
            }
        }

        Collections.reverse(ans);
        return ans;
    }
}