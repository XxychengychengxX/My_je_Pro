package hot100;

import java.util.*;

/**
 * @author Valar Morghulis
 * @Date 2023/9/17
 */
public class Hot100 {


    public static void main(String[] args) {
        Hot100 hot100 = new Hot100();
        System.out.println(hot100.findAnagrams("abab", "ab"));
    }

    /*-----------------------------------------------Hash---------------------------------------------------*/
    /*-----------------------------------------------Hash---------------------------------------------------*/
    /*-----------------------------------------------Hash---------------------------------------------------*/

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> characterIntegerHashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (characterIntegerHashMap.containsKey(nums[i])) {
                if (nums[i] == target / 2) {
                    return new int[]{characterIntegerHashMap.get(nums[i]), i};
                }
            } else {
                characterIntegerHashMap.put(nums[i], i);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (characterIntegerHashMap.containsKey(target - nums[i]) && characterIntegerHashMap.get(
                    target - nums[i]) != i) {
                return new int[]{characterIntegerHashMap.get(target - nums[i]), i};
            }
        }
        return new int[2];
    }

    /**
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。 字母异位词 是由重新排列源单词的所有字母得到的一个新单词
     *
     * @param strs 字符串数组
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] a = str.toCharArray();
            char[] clone = a.clone();
            Arrays.sort(clone);
            if (map.containsKey(String.valueOf(clone))) {
                map.get(String.valueOf(clone)).add(String.valueOf(a));
            } else {
                ArrayList<String> strings = new ArrayList<>();
                strings.add(String.valueOf(a));
                map.put(String.valueOf(clone), strings);
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<String, List<String>> stringListEntry : map.entrySet()) {
            res.add(stringListEntry.getValue());
        }

        return res;
    }
    /*-----------------------------------------------双指针---------------------------------------------------*/
    /*-----------------------------------------------双指针---------------------------------------------------*/
    /*-----------------------------------------------双指针---------------------------------------------------*/

    public int longestConsecutive(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        nums = Arrays.stream(nums).distinct().toArray();
        Arrays.sort(nums);
        int max = 0;
        int i = 0;
        while (i < nums.length) {
            int j = i;
            int temp = 1;
            while (j + 1 < nums.length && nums[j + 1] - nums[j] == 1) {
                temp++;
                j++;
            }
            max = Math.max(temp, max);
            i = j + 1;

        }
        return max;
    }

    /**
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 返回容器可以储存的最大水量。 说明：你不能倾斜容器。
     *
     * @param height
     * @return 返回容器可以储存的最大水量
     */
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        /*
         *  这里思路就是用双指针遍历，从两端开始判断，因为移动短板才有可能是结果增大(移动长板只会使越来越小，因为短板效应），
         *   所以每次判断结果有没有增大然后移动短板就可以了
         * */
        while (i < j) {
            res = height[i] < height[j] ? Math.max(res, (j - i) * height[i++]) : Math.max(res, (j - i) * height[j--]);
        }
        return res;

       /* 作者：Krahets
        链接：https://leetcode.cn/problems/container-with-most-water/solutions/11491/container-with-most-water-shuang
        -zhi-zhen-fa-yi-do/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        if (nums == null) {
            return;
        }
        //第一次遍历的时候，j指针记录非0的个数，只要是非0的统统都赋给nums[j]
        int j = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        //非0的元素统计完了，剩下的都是0了
        //所以第二次遍历把末尾的元素都赋为0即可
        for (int i = j; i < nums.length; ++i) {
            nums[i] = 0;
        }
    }

    /**
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k]
     * == 0 。请 你返回所有和为 0 且不重复的三元组。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        //定义一个结果集
        List<List<Integer>> res = new ArrayList<>();
        //数组的长度
        int len = nums.length;
        //当前数组的长度为空，或者长度小于3时，直接退出
        if (nums == null || len < 3) {
            return res;
        }
        //将数组进行排序
        Arrays.sort(nums);
        //遍历数组中的每一个元素
        for (int i = 0; i < len; i++) {
            //如果遍历的起始元素大于0，就直接退出
            //原因，此时数组为有序的数组，最小的数都大于0了，三数之和肯定大于0
            if (nums[i] > 0) {
                break;
            }
            //去重，当起始的值等于前一个元素，那么得到的结果将会和前一次相同
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //定义一个在当前节点右边的指针
            int l = i + 1;
            //再定义一个在数组末尾的指针
            int r = len - 1;
            //当 l 不等于 r时就继续遍历
            while (l < r) {
                //将三数进行相加
                int sum = nums[i] + nums[l] + nums[r];
                //如果等于0，将结果对应的索引位置的值加入结果集中
                if (sum == 0) {
                    // 将三数的结果集加入到结果集中
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    //在将左指针和右指针移动的时候，先对左右指针的值，进行判断
                    //如果重复，直接跳过。
                    //去重，因为 i 不变，当此时 l取的数的值与前一个数相同，所以不用在计算，直接跳
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    //去重，因为 i不变，当此时 r 取的数的值与前一个相同，所以不用在计算
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    //将 左指针右移，将右指针左移。
                    l++;
                    r--;
                    //如果结果小于0，将左指针右移
                } else if (sum < 0) {
                    l++;
                    //如果结果大于0，将右指针左移
                } else if (sum > 0) {
                    r--;
                }
            }
        }
        return res;
    }



    /*-------------------------------------滑动窗口和子串--------------------------------------------*/
    /*-------------------------------------滑动窗口和子串--------------------------------------------*/
    /*-------------------------------------滑动窗口和子串--------------------------------------------*/

    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/trapping-rain-water/solutions/692342/jie-yu-shui-by-leetcode-solution-tuvc/
     * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        //从左边看过去的
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        //从右边看过来
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

        /*作者：力扣官方题解
链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters/solutions/227999/wu-zhong-fu-zi-fu-de
-zui-chang-zi-chuan-by-leetc-2/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

    public int lengthOfLongestSubstring(String s) {
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    /**
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
     *
     * @param s 模板串
     * @param p 子串
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        int pLength = p.length();
        int sLength = s.length();
        char[] a = p.toCharArray();
        Arrays.sort(a);
        String pstr = String.valueOf(a);
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < sLength - pLength + 1; i++) {
            char[] chars = s.substring(i, i + pLength).toCharArray();
            Arrays.sort(chars);
            if (String.valueOf(chars).equals(pstr)) {
                integers.add(i);
            }
        }
        return integers;
        /*ArrayList<Integer> ans = new ArrayList<>();
        int sLen = s.length();
        int pLen = p.length();

        if (sLen < pLen) {
            return ans;
        }
        //建立两个数组存放字符串中字母出现的词频，并以此作为标准比较
        int[] scount = new int[26];
        int[] pcount = new int[26];

        //当滑动窗口的首位在s[0]处时 （相当于放置滑动窗口进入数组）
        for (int i = 0; i < pLen; i++) {
            //记录s中前pLen个字母的词频
            ++scount[s.charAt(i) - 'a'];
            //记录要寻找的字符串中每个字母的词频(只用进行一次来确定)
            ++pcount[p.charAt(i) - 'a'];
        }

        //判断放置处是否有异位词     (在放置时只需判断一次)
        if (Arrays.equals(scount, pcount)) {
            ans.add(0);
        }

        //开始让窗口进行滑动
        //i是滑动前的首位
        for (int i = 0; i < sLen - pLen; i++) {

            //将滑动前首位的词频删去
            --scount[s.charAt(i) - 'a'];

            //增加滑动后最后一位的词频（以此达到滑动的效果）
            ++scount[s.charAt(i + pLen) - 'a'];

            //判断滑动后处，是否有异位词
            if (Arrays.equals(scount, pcount)) {
                ans.add(i + 1);
            }
        }

        return ans;*/
    }

    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。
     * <p>
     * 子数组是数组中元素的连续非空序列。
     *
     * @param nums
     * @param k
     * @return
     */

    public int subarraySum(int[] nums, int k) {
        /* public class Solution {
            public int subarraySum(int[] nums, int k) {
                int count = 0, pre = 0;
                HashMap < Integer, Integer > mp = new HashMap < > ();
                mp.put(0, 1);
                for (int i = 0; i < nums.length; i++) {
                    pre += nums[i];
                    if (mp.containsKey(pre - k)) {
                        count += mp.get(pre - k);
                    }
                    mp.put(pre, mp.getOrDefault(pre, 0) + 1);
                }
                return count;
            }
        }
作者：力扣官方题解
链接：https://leetcode.cn/problems/subarray-sum-equals-k/solutions/238572/he-wei-kde-zi-shu-zu-by-leetcode-solution/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
*/

        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;

       /* 作者：力扣官方题解
        链接：https://leetcode.cn/problems/subarray-sum-equals-k/solutions/238572/he-wei-kde-zi-shu-zu-by-leetcode
        -solution/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }

    /**
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     *
     * @param nums
     * @param k
     * @return 滑动窗口中的最大值 。
     * <p>
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/sliding-window-maximum/solutions/543426/hua-dong-chuang-kou-zui-da-zhi-by
     * -leetco-ki6m/ 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }


    /*
     * -------------------------------------------普通数组-----------------------------------------------
     * -------------------------------------------普通数组-----------------------------------------------
     * -------------------------------------------普通数组-----------------------------------------------
     * */


    /**
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 子数组 是数组中的一个连续部分。
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        //定义一维数组，其中result[i]代表以nums[i]为结尾的元素的子数组最大值
        int[] result = new int[nums.length];
        result[0] = nums[0];
        int max = result[0];
        for (int i = 1; i < result.length; i++) {
            //以第i个元素结尾的最大值就是第i-1个元素的最大值加上当前元素  与 当前元素的值的比较
            int temp = Math.max(result[i - 1] + nums[i], nums[i]);
            result[i] = temp;
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    /**
     * 作者：
     * <p>
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * </p>
     * 作者：力扣官方题解 链接：https://leetcode.cn/problems/merge-intervals/solutions/203562/he-bing-qu-jian-by-leetcode-solution/
     * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        //首先进行二维数组的排序
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            int L = interval[0], R = interval[1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }
    /*
     * -------------------------------------------链表-----------------------------------------------
     * -------------------------------------------链表-----------------------------------------------
     * -------------------------------------------链表-----------------------------------------------
     * */

    /**
     * 作者：力扣官方题解 链接：https://leetcode.cn/problems/merge-k-sorted-lists/solutions/219756/he-bing-kge-pai-xu-lian-biao-by
     * -leetcode-solutio-2/ 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }



    /*
     * -------------------------------------------二叉树-----------------------------------------------
     * -------------------------------------------二叉树-----------------------------------------------
     * -------------------------------------------二叉树-----------------------------------------------
     * */

    /**
     * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> integers = new ArrayList<>();
        inorderTraverSalSearch(root, integers);
        return integers;
    }

    private void inorderTraverSalSearch(TreeNode root, List<Integer> integers) {
        if (root != null) {
            inorderTraverSalSearch(root.left, integers);
            integers.add(root.val);
            inorderTraverSalSearch(root.right, integers);
        }
    }

    /**
     * 给定一个二叉树 root ，返回其最大深度。 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(1 + maxDepth(root.left), 1 + maxDepth(root.right));
    }

    /**
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        root.left = invertTree(root.left);
        root.right = invertTree(root.right);
        TreeNode right = root.right;
        root.right = root.left;
        root.left = right;
        return root;
    }


    /**
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        LinkedList<TreeNode> left = new LinkedList<>();
        LinkedList<TreeNode> right = new LinkedList<>();
        if (root.left == null && root.right == null) {
            return true;
        }
        left.offer(root.left);
        right.offer(root.right);
        while (!left.isEmpty() && !right.isEmpty()) {
            TreeNode lefNode = left.remove();
            TreeNode rightNode = right.remove();
            if ((lefNode == null && rightNode != null) || (rightNode == null && lefNode != null)) {
                return false;
            }
            if (lefNode != null && rightNode != null) {
                if (lefNode.val != rightNode.val) {
                    return false;
                }
                right.offer(rightNode.left);
                right.offer(rightNode.right);
                left.offer(lefNode.right);
                left.offer(lefNode.left);
            }
        }
        return left.isEmpty() && right.isEmpty();
    }

    /**
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。 有效 二叉搜索树定义如下： 节点的左子树只包含 小于 当前节点的数。 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     *
     * @param root
     * @return
     */

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left != null) {
            if (left.val >= root.val) {
                return false;
            }
        }
        if (right != null) {
            if (right.val <= root.val) {
                return false;
            }
        }
        return isValidBST(left) && isValidBST(right);
    }

    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            --k;
            if (k == 0) {
                break;
            }
            root = root.right;
        }
        return root.val;

    }


    /*--------------------------------dp---------------------------------------*/
    /*--------------------------------dp---------------------------------------*/
    /*--------------------------------dp---------------------------------------*/

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统， 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * <p>
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        /*int[] dp1 = new int[nums.length];
        int[] dp2 = new int[nums.length];
        if (nums.length < 2) {
            return nums[0];
        }
        dp1[0] = nums[0];
        dp2[0] = 0;
        int i = 1;
        while (i < nums.length) {
            dp1[i] = nums[i] + dp2[i - 1];
            dp2[i] = Math.max(dp1[i - 1], dp2[i - 1]);
            i++;
        }
        return Math.max(dp1[nums.length - 1], dp2[nums.length - 1]);*/
        if (nums.length < 2) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]);
        int i = 2;
        while (i < nums.length) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            i++;
        }
        return Math.max(dp[nums.length - 1], dp[nums.length - 2]);
    }


    /*--------------------------------LinkedList---------------------------------------*/
    /*--------------------------------LinkedList---------------------------------------*/
    /*--------------------------------LinkedList---------------------------------------*/

    /**
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
        /*作者：房建斌学算法
        链接：https://leetcode.cn/problems/intersection-of-two-linked-lists/solutions/10774/tu-jie-xiang-jiao-lian-biao
        -by-user7208t/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }

    /**
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    /**
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     * <p>
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递
     * 。仅仅是为了标识链表的实际情况。
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        return null;
    }
    /*--------------------------------matrix---------------------------------------*/
    /*--------------------------------matrix---------------------------------------*/
    /*--------------------------------matrix---------------------------------------*/

    /**
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
       /* int[] row = new int[matrix.length];
        int[] column = new int[matrix[0].length];
        Arrays.fill(row, 0);
        Arrays.fill(column, 0);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    column[j] = 1;
                }
            }
        }
        for (int i = 0; i < row.length; i++) {
            if (row[i] == 1) {
                Arrays.fill(matrix[i], 0);
            }
        }
        for (int i = 0; i < column.length; i++) {
            if (column[i] == 1) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }*/


        int m = matrix.length, n = matrix[0].length;
        boolean flagCol0 = false, flagRow0 = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
        }
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                flagRow0 = true;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (flagCol0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        if (flagRow0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
               /*作者：力扣官方题解
        链接：https://leetcode.cn/problems/set-matrix-zeroes/solutions/669901/ju-zhen-zhi-ling-by-leetcode-solution-9ll7/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }

    public int orangesRotting(int[][] grid) {
        int minute = 0;
        LinkedList<int[]> locLeft = new LinkedList<>();
        LinkedList<int[]> locRight = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    locLeft.add(new int[]{i, j});
                }
            }
        }
        while (!locLeft.isEmpty() || !locRight.isEmpty()) {
            boolean rightFlag = false;
            boolean leftFlag = false;

            if (!locRight.isEmpty()) {
                rightFlag = true;
            } else if (!locLeft.isEmpty()) {
                leftFlag = true;
            }
            if (leftFlag) {
                while (!locLeft.isEmpty()) {
                    int[] remove = locLeft.remove();
                    int row = remove[0];
                    int column = remove[1];
                    if (row + 1 < grid.length && (grid[row + 1][column] == '1')) {
                        grid[row + 1][column] = '2';
                        locRight.add(new int[]{row + 1, column});
                    }
                    if (row - 1 >= 0 && (grid[row - 1][column] == '1')) {
                        grid[row - 1][column] = '2';
                        locRight.add(new int[]{row - 1, column});
                    }
                    if (column + 1 < grid[0].length && (grid[row][column + 1] == '1')) {
                        grid[row][column + 1] = '2';
                        locRight.add(new int[]{row, column + 1});
                    }
                    if (column - 1 >= 0 && (grid[row][column - 1] == '1')) {
                        grid[row][column - 1] = '2';
                        locRight.add(new int[]{row + 1, column});
                    }
                }
                minute++;
            }
            if (rightFlag) {
                while (!locRight.isEmpty()) {
                    int[] remove = locRight.remove();
                    int row = remove[0];
                    int column = remove[1];
                    if (row + 1 < grid.length && (grid[row + 1][column] == '1')) {
                        grid[row + 1][column] = '2';
                        locLeft.add(new int[]{row + 1, column});
                    }
                    if (row - 1 >= 0 && (grid[row - 1][column] == '1')) {
                        grid[row - 1][column] = '2';
                        locLeft.add(new int[]{row - 1, column});
                    }
                    if (column + 1 < grid[0].length && (grid[row][column + 1] == '1')) {
                        grid[row][column + 1] = '2';
                        locLeft.add(new int[]{row, column + 1});
                    }
                    if (column - 1 >= 0 && (grid[row][column - 1] == '1')) {
                        grid[row][column - 1] = '2';
                        locLeft.add(new int[]{row + 1, column});
                    }
                }
                minute++;

            }

        }
        return minute;
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






