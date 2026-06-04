import java.util.*;

public class Solution {

    public static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        dfs(root, "", result);
        return result;
    }

    private void dfs(TreeNode node, String path, List<String> result) {
        if (node == null)
            return;

        path += node.val;

        if (node.left == null && node.right == null) {
            result.add(path);
            return;
        }

        path += "->";

        dfs(node.left, path, result);
        dfs(node.right, path, result);
    }

    // sample main for VS Code testing
    public static void main(String[] args) {
        Solution sol = new Solution();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);

        System.out.println(sol.binaryTreePaths(root));
    }
}