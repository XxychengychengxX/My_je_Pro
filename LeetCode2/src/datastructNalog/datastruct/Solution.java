package datastructNalog.datastruct;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Valar Morghulis
 * @Date 2023/9/10
 */
public class Solution {
    /*
    请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
作者：Krahets
链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/9p0yy1/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    Map<Node, Node> copyRandomListMap = new HashMap<>();

    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }
        if (copyRandomListMap.containsKey(head)) {
            return copyRandomListMap.get(head);
        }

        Node node = new Node(head.val);
        copyRandomListMap.put(head, node);
        node.next = copyRandomList(head.next);
        node.random = copyRandomList(head.random);
        return node;
    }

    /**
     * 剑指 Offer 59 - I. 滑动窗口的最大值 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int j = 0, i = 1 - k; j < nums.length; i++, j++) {
            // 删除 deque 中对应的 nums[i-1]
            if (i > 0 && deque.peekFirst() == nums[i - 1]) {
                deque.removeFirst();
            }
            // 保持 deque 递减
            while (!deque.isEmpty() && deque.peekLast() < nums[j]) {
                deque.removeLast();
            }
            deque.addLast(nums[j]);
            // 记录窗口最大值
            if (i >= 0) {
                res[i] = deque.peekFirst();
            }
        }
        return res;
    }

    public int strToInt(String str) {

        enum status {INITIAL, ABOVE_ZERO, BELOW_ZERO}
        String strip = str.strip();
        if (strip.length() == 0) {
            return 0;
        }
        char[] chars = strip.toCharArray();
        long sum = 0;
        int j = 0;
        status i = status.INITIAL;
        if (chars[j] == '+') {
            j++;
            i = status.ABOVE_ZERO;

        } else if (chars[j] >= '0' && chars[j] <= '9') {
            i = status.ABOVE_ZERO;
        } else if (chars[j] == '-') {
            j++;
            i = status.BELOW_ZERO;
        }

        for (int k = j; k < chars.length; k++) {
            if (chars[k] >= '0' && chars[k] <= '9') {
                //即使是long也要判断是否发生溢出
                if (sum > sum * 10 + chars[k] - '0') {
                    if (i == status.ABOVE_ZERO) {
                        return Integer.MAX_VALUE;
                    } else {
                        return Integer.MIN_VALUE;
                    }
                } else {
                    sum = sum * 10 + chars[k] - '0';
                }
            } else {
                break;
            }
        }
        if (i == status.INITIAL) {
            return 0;
        } else if (i == status.ABOVE_ZERO) {
            if (sum > (long) Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else {
                return (int) sum;
            }
        } else {
            if (sum > (long) Integer.MAX_VALUE + 1) {
                return Integer.MIN_VALUE;
            } else {
                return (int) sum * (-1);
            }
        }
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
