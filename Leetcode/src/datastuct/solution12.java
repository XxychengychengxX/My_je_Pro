package datastuct;

import java.util.ArrayList;
import java.util.List;

public class solution12 {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}

		public static void pre_visit(TreeNode root, List<Integer> integers) {
			if (root == null) {
				return;
			}
			integers.add(root.val);
			if (root.left != null) {
				pre_visit(root.left, integers);
			}

			if (root.right != null) {
				pre_visit(root.right, integers);
			}
		}

		public static void medium_visit(TreeNode root, List<Integer> integers) {
			if (root == null)
				return;

			if (root.left != null)
				medium_visit(root.left, integers);
			integers.add(root.val);
			if (root.right != null)
				medium_visit(root.right, integers);
		}

		public static void last_visit(TreeNode root, List<Integer> integers) {
			if (root == null) {
				return;
			}

			if (root.left != null) {
				last_visit(root.left, integers);
			}

			if (root.right != null) {
				last_visit(root.right, integers);
			}
			integers.add(root.val);
		}

		public List<Integer> preorderTraversal(TreeNode root) {
			List<Integer> integers = new ArrayList<>();
			if (root == null)
				return integers;
//            if (root.left != null)
			integers.add(root.val);
			pre_visit(root.left, integers);

			// if (root.right != null)
			pre_visit(root.right, integers);
			return integers;
		}

		public List<Integer> inorderTraversal(TreeNode root) {
			List<Integer> integers = new ArrayList<>();
			if (root == null)
				return integers;
//            if (root.left != null)
			medium_visit(root.left, integers);
			integers.add(root.val);

			// if (root.right != null)
			medium_visit(root.right, integers);
			return integers;
		}

		public List<Integer> postorderTraversal(TreeNode root) {
			List<Integer> integers = new ArrayList<>();
			if (root == null) {
				return integers;
			}
//            if (root.left != null)
			last_visit(root.left, integers);

			// if (root.right != null)
			last_visit(root.right, integers);
			integers.add(root.val);
			return integers;
		}
	}
}
