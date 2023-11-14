package algorithm.diveconque;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode treeNode = solution.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        System.out.println("helloworld");
    }


    /**
     * 105给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历， 请构造二叉树并返回其根节点。
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 1 || inorder.length == 1) {
            return new TreeNode(preorder[0]);
        } else {
            List<Integer> preorderlist = Arrays.stream(preorder).boxed().toList();
            List<Integer> inorderlist = Arrays.stream(inorder).boxed().toList();
            return buildmyTree(preorderlist, inorderlist);
        }
    }

    private TreeNode buildmyTree(List<Integer> preorderlist, List<Integer> inorderlist) {
        if (preorderlist.size() == 0) {
            return null;
        }
        int i = preorderlist.get(0);
        TreeNode treeNode = new TreeNode(i);
        int sublistlen = inorderlist.indexOf(i);
        int size = inorderlist.size();
        if (size != 1) {
            treeNode.left = buildmyTree(preorderlist.subList(1, sublistlen + 1),
                    inorderlist.subList(0,
                            sublistlen));
        } else {
            treeNode.left = null;
        }
        if (sublistlen + 1 < size) {
            treeNode.right = buildmyTree(preorderlist.subList(sublistlen + 1, size),
                    inorderlist.subList(sublistlen + 1, size));
        } else {
            treeNode.right = null;
        }
        return treeNode;
    }

    /**
     * 106给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历， 请你构造并返回这颗 二叉树 。
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (postorder.length == 1 || inorder.length == 1) {
            return new TreeNode(postorder[0]);
        } else {
            List<Integer> postorderlist = Arrays.stream(postorder).boxed().toList();
            List<Integer> inorderlist = Arrays.stream(inorder).boxed().toList();
            return buildmyTree2(postorderlist, inorderlist);
        }
    }

    private TreeNode buildmyTree2(List<Integer> postorderlist, List<Integer> inorderlist) {
        if (postorderlist.size() == 0) {
            return null;
        }
        //后序遍历的最后的一个节点即为根节点
        int size = inorderlist.size();
        int i = postorderlist.get(size - 1);
        TreeNode treeNode = new TreeNode(i);
        int sublistlen = inorderlist.indexOf(i);
        if (size != 1) {
            treeNode.left = buildmyTree2(postorderlist.subList(0, sublistlen),
                    inorderlist.subList(0,
                            sublistlen));
        }
        else {
            treeNode.left = null;
        }
        if (sublistlen + 1 < size) {
            treeNode.right = buildmyTree2(postorderlist.subList(sublistlen, size - 1),
                    inorderlist.subList(sublistlen + 1, size));
        } else {
            treeNode.right = null;
        }
        return treeNode;
    }

    /**
     * 108 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
     * <p>
     * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     *
     * @param nums
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        List<Integer> integers = Arrays.stream(nums).boxed().toList();
        return ListtoBST(integers);
    }

    private TreeNode ListtoBST(List<Integer> integers) {
        if (integers.size() == 0) {
            return null;
        }
        int size = integers.size();
        int i = integers.get(size / 2);
        TreeNode treeNode = new TreeNode(i);
        treeNode.left = ListtoBST(integers.subList(0, size / 2));
        treeNode.right = ListtoBST(integers.subList(size / 2 + 1, size));
        return treeNode;
    }

    /**
     * @param head
     * @return
     */

    public TreeNode sortedListToBST(ListNode head) {
        ArrayList<Integer> integers = new ArrayList<>();
        while (head != null) {
            integers.add(head.val);
            head = head.next;
        }
        TreeNode treeNode = ListtoBST(integers);
        return treeNode;
    }


    /**
     * 148 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = merge(list1, list2);
        return sorted;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }

    /**
     * 169 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 190 颠倒给定的 32 位无符号整数的二进制位。
     */
    public int reverseBits(int n) {
        return reverseBits(n, 16);
    }

    public int reverseBits(int n, int index) {
        if (index == 1) {
            switch (n) {
                case 1, -2:
                    return ~n;
                case -1:
                case 0:
                    return n;
            }
        } else {
            int i = -1 << index;
            int j = i >> index;

            return reverseBits(n >> index << index, index / 2) + reverseBits(n << index >> index,
                    index / 2);
        }
        return 0;
    }

    public class TreeNode {
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
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}