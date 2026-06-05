import java.util.*;

class SummaryRanges {

    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return result;
        }

        int start = nums[0];

        for (int i = 1; i <= nums.length; i++) {

            // end of range OR break in sequence OR end of array
            if (i == nums.length || nums[i] != nums[i - 1] + 1) {

                int end = nums[i - 1];

                if (start == end) {
                    result.add(String.valueOf(start));
                } else {
                    result.add(start + "->" + end);
                }

                if (i < nums.length) {
                    start = nums[i];
                }
            }
        }

        return result;
    }

    // 🔥 Main method for VS Code testing
    public static void main(String[] args) {

        int[] nums = { 0, 1, 2, 4, 5, 7 };

        System.out.println(summaryRanges(nums));
    }
}
