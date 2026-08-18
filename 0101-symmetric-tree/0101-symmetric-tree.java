class Solution {
    public boolean isSymmetric(TreeNode root) {

        return check(root.left, root.right);
    }

    public boolean check(TreeNode left, TreeNode right) {

        // Both null
        if (left == null && right == null) {
            return true;
        }

        // One null
        if (left == null || right == null) {
            return false;
        }

        // Values different
        if (left.val != right.val) {
            return false;
        }

        return check(left.left, right.right)
                && check(left.right, right.left);
    }
}