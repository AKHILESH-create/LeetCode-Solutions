class Solution {

    public long maxTotalValue(int[] nums, int k) {

        int n = nums.length;

        long[] maxCount = getMaxContribution(nums);
        long[] minCount = getMinContribution(nums);

        long[] score = new long[n];

        for (int i = 0; i < n; i++) {
            score[i] = (maxCount[i] - minCount[i]) * (long) nums[i];
        }

        java.util.Arrays.sort(score);

        long ans = 0;

        for (int i = n - 1; i >= n - k; i--) {
            ans += score[i];
        }

        return ans;
    }

    // ✅ MUST BE INSIDE CLASS

    private long[] getMaxContribution(int[] nums) {

        int n = nums.length;
        long[] res = new long[n];

        java.util.Stack<Integer> st = new java.util.Stack<>();

        for (int i = 0; i <= n; i++) {

            while (!st.isEmpty() &&
                    (i == n || nums[st.peek()] < (i < n ? nums[i] : Integer.MAX_VALUE))) {

                int mid = st.pop();
                int left = st.isEmpty() ? -1 : st.peek();
                int right = i;

                res[mid] += (long)(mid - left) * (right - mid);
            }

            if (i < n) st.push(i);
        }

        return res;
    }

    private long[] getMinContribution(int[] nums) {

        int n = nums.length;
        long[] res = new long[n];

        java.util.Stack<Integer> st = new java.util.Stack<>();

        for (int i = 0; i <= n; i++) {

            while (!st.isEmpty() &&
                    (i == n || nums[st.peek()] > (i < n ? nums[i] : Integer.MIN_VALUE))) {

                int mid = st.pop();
                int left = st.isEmpty() ? -1 : st.peek();
                int right = i;

                res[mid] += (long)(mid - left) * (right - mid);
            }

            if (i < n) st.push(i);
        }

        return res;
    }
}