package circuit;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Valar Morghulis
 * @Date 2023/9/15
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param n    int整型 数组长度
     * @param nums int整型一维数组 数组
     * @return int整型一维数组
     */
    public int[] findQ(int n, int[] nums) {

        // write code here
        HashSet<Integer> integers = new HashSet<>();
        ArrayList<Integer> integers1 = new ArrayList<>();

        for (int i = n - 1; i > -1; i--) {
            if (!integers.contains(nums[i])) {
                integers1.add(nums[i]);
                integers.add(nums[i]);
            }

        }
        int[] ints = new int[integers1.size()];
        int k=ints.length-1;
        for (Integer integer : integers1) {
            ints[k--]=integer;
        }

        return ints;
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 输出在数组中第一个大于等于查找值的位置
     *
     * @param n int整型 数组长度
     * @param v int整型 待查找值
     * @param a int整型一维数组 原始数组
     * @return int整型
     */
    public int find(int n, int v, int[] a) {
        // write code here
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (a[mid] > v) {
                r = mid - 1;
            } else if (a[mid] < v) {
                l = mid + 1;

            } else {
                if ((mid - 1) >= 0 && a[mid - 1] == v) {
                    r--;
                } else {
                    return mid + 1;
                }
            }
        }
        return n + 1;
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 单向链表长度
     *
     * @param head ListNode类 链表头节点
     * @return int整型
     */
    public int lengthL(ListNode head) {
        // write code here
        HashSet<Integer> integers = new HashSet<>();
        int res = 0;
        while (head != null) {
            if (!integers.contains(head.val)) {
                integers.add(head.val);
                res++;
            }
            head = head.next;
        }
        return res;
    }

    public class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
