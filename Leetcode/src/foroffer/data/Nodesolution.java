package foroffer.data;

import java.util.*;

/*从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。

 

例如:
给定二叉树: [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回：

[3,9,20,15,7]

作者：Krahets
链接：https://leetcode-cn.com/leetbook/read/illustration-of-algorithm/9ackoe/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

/*层次遍历*/
public class Nodesolution {

    public Queue<Node> queue = new LinkedList<>();
    Stack<TreeNode> queue1 = new Stack<>();
    Node head = null;
    List<List<Integer>> lists = new ArrayList<>();


    public static void main(String[] args) {
        Nodesolution nodesolution = new Nodesolution();
        /*TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(2);
        treeNode.right.right = new TreeNode(4);
        treeNode.right.left.left = new TreeNode(3);
        treeNode.right.left.right = new TreeNode(1);*/
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        System.out.println(nodesolution.serialize(treeNode));
        System.out.println(nodesolution.deserialize("1,2"));
    }

    // 第一题静态
    public static void search(TreeNode root, int target, List<Integer> integers, List<List<Integer>> lists, int index) {
        if (root == null)
            ;
        else if (root.val == target && root.left == null && root.right == null) {
            integers.add(root.val);
            lists.add(new ArrayList<>(integers));
            integers.remove(integers.size() - 1);
        } else {
            integers.add(root.val);
            search(root.left, target - root.val, integers, lists, index + 1);
            search(root.right, target - root.val, integers, lists, index + 1);
            integers.remove(integers.size() - 1);
        }
    }

    // 第二题静态
    public static void mid_order_search(TreeNode root, Queue<Integer> queue) {
        if (root.left != null)
            mid_order_search(root.left, queue);
        queue.offer(root.val);
        if (root.right != null)
            mid_order_search(root.right, queue);
    }

    // 第四题.第七题静态
    public static void maxDepthsearch(TreeNode treeNode, int temp, int[] max) {
        if (treeNode != null) {
            temp++;
            if (max[0] < temp)
                max[0] = temp;
        } else
            return;
        if (treeNode.left != null) {
            maxDepthsearch(treeNode.left, temp, max);
        }
        if (treeNode.right != null) {
            maxDepthsearch(treeNode.right, temp, max);
        }
    }

    // 第五题静态
    public static void mid_order_search1(TreeNode root, Queue<TreeNode> queue) {
        if (root != null) {
            if (root.left != null)
                mid_order_search1(root.left, queue);
            queue.offer(root);
            if (root.right != null)
                mid_order_search1(root.right, queue);
        }
    }

    // 第七题静态
    public static boolean judgeAncestor(TreeNode root, TreeNode p) {
        TreeNode temp;
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null)
            queue.offer(root);
        while (!queue.isEmpty()) {
            temp = queue.remove();
            if (temp.val == p.val)
                return true;
            if (temp.left != null)
                queue.offer(temp.left);
            if (temp.right != null)
                queue.offer(temp.right);
        }
        return false;
    }

    // 第八题静态
    public static TreeNode buildsubTree(List<Integer> pre, List<Integer> in) {
        TreeNode root = new TreeNode(pre.get(0));
        int i = in.indexOf(pre.get(0));
        if (i > 0)
            root.left = buildsubTree(pre.subList(1, i + 1), in.subList(0, i));
        if (i < in.size() - 1)
            root.right = buildsubTree(pre.subList(i + 1, pre.size()), in.subList(i + 1, in.size()));
        return root;
    }

    // 第九题静态
    static boolean verify(int[] postorder, int i, int j) {
        /*
         * 作者：MacZhen
         * 链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/5vwxx5/?
         * discussion=jGaYZv 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         */
        if (i >= j)
            return true;
        int k = i;
        while (postorder[k] < postorder[j])
            k++;
        int mid = k;
        while (postorder[k] > postorder[j])
            k++;
        return k == j && verify(postorder, i, mid - 1) && verify(postorder, mid, j - 1);
    }


    /*
     * // List<Integer> 转 Integer[] Integer[] integers2 = list1.toArray(new
     * Integer[0]); // 调用toArray。传入参数T[] a。这种用法是目前推荐的。 // List<String>转String[]也同理。
     *
     * // List<Integer> 转 int[] int[] arr1 =
     * list1.stream().mapToInt(Integer::valueOf).toArray(); //
     * 想要转换成int[]类型，就得先转成IntStream。 //
     * 这里就通过mapToInt()把Stream<Integer>调用Integer::valueOf来转成IntStream //
     * 而IntStream中默认toArray()转成int[]。
     *
     * // Integer[] 转 int[] int[] arr2 =
     * Arrays.stream(integers1).mapToInt(Integer::valueOf).toArray(); //
     * 思路同上。先将Integer[]转成Stream<Integer>，再转成IntStream。
     *
     * // Integer[] 转 List<Integer> List<Integer> list2 = Arrays.asList(integers1);
     * // 最简单的方式。String[]转List<String>也同理。
     * 因为该方法返回的List对象不是java.util包下面的ArrayList，而是Arrays内部的ArrayList类型。改类没有实现add,
     * revmove, clear方法，所以任何对改集合结构修改的调用都将失败
     *
     *
     * // 同理 String[] strings1 = {"a", "b", "c"}; // String[] 转 List<String>
     * List<String> list3 = Arrays.asList(strings1); // List<String> 转 String[]
     * String[] strings2 = list3.toArray(new String[0]);
     */
    /* 返回数组 */
    public int[] levelOrder(TreeNode root) {
        int[] ints = {};
        if (root == null)
            return ints;
        Queue<TreeNode> treeNodes = new LinkedList<>();
        ArrayList<Integer> integers = new ArrayList<>();
        treeNodes.add(root);
        TreeNode temp;
        while (!treeNodes.isEmpty()) {
            temp = treeNodes.poll();
            integers.add(temp.val);
            if (temp.left != null)
                treeNodes.add(temp.left);
            if (temp.right != null)
                treeNodes.add(temp.right);
        }
        return integers.stream().mapToInt(Integer::valueOf).toArray();
    }

    /* 返回每一行 */
    public List<List<Integer>> levelOrder1(TreeNode root) {
        /*
         * List<List<Integer>> res = new ArrayList<>(); if(root == null){return res;}
         * Queue<TreeNode> q = new LinkedList<>(); q.add(root); while(!q.isEmpty()){ int
         * size = q.size(); List<Integer> tmp = new ArrayList<>();
         * //注意：循环这里第二个条件不能用q.size(),因为将左右节点加进去之后,q的大小发生了变化 for(int i = 0;i < size;i++){
         * TreeNode node = q.poll(); tmp.add(node.val); if(node.left != null){
         * q.add(node.left); } if(node.right !=null){ q.add(node.right); } }
         * res.add(tmp); } return res; } }
         *
         * 作者：是菜鸟不是咸鱼
         * 链接：https://leetcode-cn.com/leetbook/read/illustration-of-algorithm/5vawr3/?
         * discussion=k3yLMM 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         */
        List<List<Integer>> arrayLists = new ArrayList<>();
        Queue<TreeNode> treeNodes = new LinkedList<>();
        int i = 0;
        int count1 = 1;
        if (root == null)
            return arrayLists;
        treeNodes.add(root);
        TreeNode temp;
        while (!treeNodes.isEmpty()) {
            int count = 0;
            ArrayList<Integer> integers = new ArrayList<>();
            while (i < count1 && !treeNodes.isEmpty()) {
                temp = treeNodes.poll();
                integers.add(temp.val);
                if (temp.left != null) {
                    count += 1;
                    treeNodes.add(temp.left);
                }
                if (temp.right != null) {
                    count += 1;
                    treeNodes.add(temp.right);
                }
                i++;
            }
            i = 0;
            count1 = count;
            arrayLists.add(integers);
        }
        return arrayLists;
    }

    // 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
    public List<List<Integer>> levelOrder2(TreeNode root) {

        List<List<Integer>> arrayLists = new ArrayList<>();
        Queue<TreeNode> treeNodes = new LinkedList<>();
        int i = 0;
        int count1 = 1;
        int row = 1;
        if (root == null)
            return arrayLists;
        treeNodes.add(root);
        TreeNode temp;
        while (!treeNodes.isEmpty()) {
            int count = 0;
            ArrayList<Integer> integers = new ArrayList<>();
            while (i < count1 && !treeNodes.isEmpty()) {
                temp = treeNodes.poll();
                integers.add(temp.val);

                if (temp.left != null) {
                    count += 1;
                    treeNodes.add(temp.left);
                }
                if (temp.right != null) {
                    count += 1;
                    treeNodes.add(temp.right);
                }
                i++;
            }
            if (row % 2 != 1)
                Collections.reverse(integers);
//                ArrayList<Integer> integers1 = new ArrayList<>();
//                for (int ij = integers.size() - 1; ij >= 0; ij--)
//                    integers1.add(integers.get(ij));
//                arrayLists.add(integers1);
//                i = 0;
//                count1 = count;
//                row += 1;
            i = 0;
            count1 = count;
            row += 1;
            arrayLists.add(integers);

        }
        return arrayLists;
    }

    /* 请完成一个函数，输入一个二叉树，该函数输出它的镜像。 */
    public TreeNode mirrorTree(TreeNode root) {

        TreeNode treeNode;
        if (root == null)
            return root;
        else if (root.left == null && root.right == null)
            return root;
        if (root.left != null)
            root.left = mirrorTree(root.left);
        if (root.right != null)
            root.right = mirrorTree(root.right);
        treeNode = root.right;
        root.right = root.left;
        root.left = treeNode;
        return root;

    }

    /*
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构) B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {

        return (A != null && B != null) && (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));

    }

    boolean recur(TreeNode A, TreeNode B) {
        if (B == null)
            return true;
        if (A == null || A.val != B.val)
            return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }

    /* 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。 */
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return compare(root.left, root.right);
    }

    boolean compare(TreeNode left, TreeNode right) {
        return (left == null && right == null) || (left != null && right != null) && (left.val == right.val)
                && compare(left.left, right.right) && compare(left.right, right.left);
    }

    /* 第一题 */
    /*
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     *
     * 叶子节点 是指没有子节点的节点。
     *
     * 作者：Krahets
     * 链接：https://leetcode-cn.com/leetbook/read/illustration-of-algorithm/5dy6pt/
     * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        ArrayList<Integer> integers = new ArrayList<>();

        if (root == null)
            return lists;
        search(root, target, integers, lists, 0);
        return lists;
    }

    // 第二题
    /*
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
     *
     *  
     *
     * 为了让您更好地理解问题，以下面的二叉搜索树为例：
     *
     *  
     *
     *
     *
     *  
     *
     * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，
     * 最后一个节点的后继是第一个节点。
     *
     * 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
     *
     * 作者：Krahets
     * 链接：https://leetcode-cn.com/leetbook/read/illustration-of-algorithm/5dbies/
     * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public Node treeToDoublyList(Node root) {

        becomequeue(root);
        while (queue.size() != 1)
            todoublylist(queue.poll(), queue.peek());

        head.left = queue.peek();
        queue.peek().right = head;

        return head;
    }

    /*
     * //把每个结点的left看作双向链表里的pre，right看作双向链表里的next，更容易理解 Node head = null; Node pre =
     * null; public Node treeToDoublyList(Node root) { if(root==null){ return null;
     * } travel(root); //中序遍历处理过后只是形成了一个双向链表 //让头结点的pre指向尾结点，尾结点的next指向头，才形成循环
     * head.left = pre; pre.right = head; return head; } public void travel(Node
     * root){ if(root==null){ return; } travel(root.left); //如果头结点为空，说明这是双向链表里的第一个结点
     * //对头结点只需要处理它的next指针，也就是root.left = pre; if(head==null){ head = root; } else {
     * //不为头结点时不仅需要处理它自身的next指针（root.left = pre）
     * //还需要让链表前一结点的next指向当前节点，也就是pre.right = root; pre.right = root; } // root.left
     * = pre; pre = root; travel(root.right); } }
     *
     */
    public void todoublylist(Node pre, Node next) {
        if (head == null)
            head = pre;
        pre.left = next;
        next.right = pre;
    }
    /*
     * public boolean isBalanced(TreeNode root) { if (root == null) { return true; }
     * int leftHeight = height(root.left); int rightHeight = height(root.right);
     * return Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(root.left) &&
     * isBalanced(root.right); }
     *
     * public int height(TreeNode root) { if (root == null) { return 0; } return
     * Math.max(height(root.left), height(root.right)) + 1; }
     *
     * 作者：Heylian
     * 链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/9hzffg/?
     * discussion=F8RBes 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    public void becomequeue(Node root) {
        if (root.left != null)
            becomequeue(root.left);
        queue.offer(root);
        if (root.right != null)
            becomequeue(root.right);
    }// 先序遍历

    // 第三题
    /*
     * 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
     */
    public int kthLargest(TreeNode root, int k) {
        Queue<Integer> queue = new LinkedList<>();
        if (root != null)
            mid_order_search(root, queue);
        int i = 1;
        int ans = 0;
        for (Integer integer : queue) {
            if (i++ == k)
                ans = integer;
        }
        return ans;
    }

    // 第四题
    /*
     * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
     *
     * 例如：给定二叉树 [3,9,20,null,null,15,7]，
     *
     * 3 / \ 9 20 / \ 15 7 返回它的最大深度 3 。
     *
     *  
     *
     * 作者：Krahets
     * 链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/9hgr5i/
     * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int maxDepth(TreeNode root) {
        int[] ints = {0};
        int temp = 0;
        maxDepthsearch(root, temp, ints);
        return ints[0];

    }

    // 第五题
    /*
     * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
     *
     *  
     *
     * 示例 1:
     *
     * 给定二叉树 [3,9,20,null,null,15,7]
     *
     * 3 / \ 9 20 / \ 15 7 返回 true 。
     *
     * 示例 2:
     *
     * 给定二叉树 [1,2,2,3,3,null,null,4,4]
     *
     * 1 / \ 2 2 / \ 3 3 / \ 4 4 返回 false 。
     *
     * 作者：Krahets
     * 链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/9hzffg/
     * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean isBalanced(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        mid_order_search1(root, queue);
        while (!queue.isEmpty()) {
            int[] ints1 = {0};
            int temp1 = 0;
            int temp2 = 0;
            int[] ints2 = {0};
            maxDepthsearch(queue.peek().left, temp1, ints1);
            maxDepthsearch(queue.remove().right, temp2, ints2);
            if (Math.abs(ints1[0] - ints2[0]) > 1)
                return false;
        }
        return true;
    }

    // 第六题
    /*
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x
     * 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
     *
     *
     *
     *  
     *
     * 示例 1:
     *
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8 输出: 6 解释: 节点 2 和节点 8
     * 的最近公共祖先是 6。 示例 2:
     *
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4 输出: 2 解释: 节点 2 和节点 4
     * 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
     *
     * 作者：Krahets
     * 链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/575kd2/
     * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode working = root;
        while (true) {
            if ((working.val >= p.val && working.val <= q.val) || (working.val <= p.val && working.val >= q.val))
                return working;
            else if (working.val > p.val)
                working = working.left;
            else
                working = working.right;
        }
    }

    // 第七题
    /*
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x
     * 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
     *
     *
     *
     *  
     *
     * 示例 1:
     *
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1 输出: 3 解释: 节点 5 和节点 1
     * 的最近公共祖先是节点 3。 示例 2:
     *
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4 输出: 5 解释: 节点 5 和节点 4
     * 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。  
     *
     * 说明:
     *
     * 所有节点的值都是唯一的。 p、q 为不同节点且均存在于给定的二叉树中。
     *
     * 作者：Krahets
     * 链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/57euni/
     * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode temp;
        if (root != null)
            queue.offer(root);
        while (!queue.isEmpty()) {
            temp = queue.remove();
            if (temp.val == p.val || temp.val == q.val || (judgeAncestor(temp.left, p) && judgeAncestor(temp.right, q))
                    || (judgeAncestor(temp.left, q) && judgeAncestor(temp.right, p)))
                return temp;
            if (temp.left != null)
                queue.offer(temp.left);
            if (temp.right != null)
                queue.offer(temp.right);
        }
        return null;
    }

    // 第八题
    /*
     * 剑指 Offer 07. 重建二叉树 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
     *
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     *
     *  
     *
     * 示例 1:
     *
     *
     * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7] Output:
     * [3,9,20,null,null,15,7] 示例 2:
     *
     * Input: preorder = [-1], inorder = [-1] Output: [-1]
     *
     * 作者：Krahets
     * 链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/99lxci/
     * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0)
            return null;
        List<Integer> pre = Arrays.stream(preorder).boxed().toList();
        List<Integer> in = Arrays.stream(inorder).boxed().toList();
        return buildsubTree(pre, in);
    }

    // 第九题
    /*
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
     *
     *  
     *
     * 参考以下这颗二叉搜索树：
     *
     * 5 / \ 2 6 / \ 1 3 示例 1：
     *
     * 输入: [1,6,3,2,5] 输出: false 示例 2：
     *
     * 输入: [1,3,2,6,5] 输出: true
     *
     * 作者：Krahets
     * 链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/5vwxx5/
     * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean verifyPostorder(int[] postorder) {
        return verify(postorder, 0, postorder.length - 1);
    }

    //第十题
	/*请实现两个函数，分别用来序列化和反序列化二叉树。

你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

 

作者：Krahets
链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/990pf2/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
            return "";
        StringBuilder s = new StringBuilder();
        String[] strings1 = level_order1(root);//层次遍历

        int index = 0;
        for (int i = 0; i < strings1.length; i++) {
            if (!strings1[i].equals("null"))
                index = i;
        }
        for (int i = 0; i < index; i++) {
            s.append(strings1[i]).append(',');
        }
        s.append(strings1[index]);
        return s.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals(""))
            return null;
        String[] vals = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        Queue<TreeNode> queue = new LinkedList<>() {{
            add(root);
        }};//利用queue来进行反序列化，也是进行层次遍历
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (i < vals.length) {
                if (!vals[i].equals("null")) {
                    node.left = new TreeNode(Integer.parseInt(vals[i]));
                    queue.add(node.left);
                }
            }
            i++;
            if (i < vals.length) {
                if (!vals[i].equals("null")) {
                    node.right = new TreeNode(Integer.parseInt(vals[i]));
                    queue.add(node.right);
                }
                i++;
            }
        }
        return root;

    }

    public String[] level_order1(TreeNode root) {
        if (root == null)
            return new String[0];
        Queue<TreeNode> treeNodes = new LinkedList<>();
        ArrayList<String> strings = new ArrayList<>();
        treeNodes.add(root);
        TreeNode temp;
        while (!treeNodes.isEmpty()) {
            temp = treeNodes.poll();
            if (temp != null) {
                strings.add(String.valueOf(temp.val));
                treeNodes.add(temp.left);
                treeNodes.add(temp.right);
            } else strings.add("null");
        }
        return strings.toArray(new String[0]);
    }






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
    }

    public class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
