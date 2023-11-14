package inorder;

import java.util.*;

public class Solution1 {
    //一下三个为40题专用
    List<int[]> freq = new ArrayList<int[]>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    List<Integer> sequence = new ArrayList<Integer>();

    public Solution1() {

    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        /*[10,1,2,7,6,1,5]
8*/
    }

    //31.中等
    public void nextPermutation(int[] nums) {
        //长度为一直接返回就行
        if (nums.length == 1)
            return;
        //从倒数第二个开始，判断每个元素到数组结尾组成的子数组是否是字典顺序最大的元素（即下一个排列是升序排列）
        // 若是，则扩大搜索范围，若不是，则从这子数组开始寻找下一个排列
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                //大于是，因为加入这第i个元素前，第i-1个元素之后的元素都为最大排列
                if (nums[j] > nums[i]) {
                    int[] permutation = findPermutation(Arrays.copyOfRange(nums, i, nums.length));
                    for (int i1 : permutation) {
                        nums[i++] = i1;
                    }
                    return;
                }
            }
        }
        Arrays.sort(nums);
    }

    public int[] findPermutation(int[] nums) {
        //开始找
        int k = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            //若找到了某个大数
            if (nums[k] > nums[i]) {
                //交换
                int temp = nums[k];
                nums[k] = nums[i];
                nums[i] = temp;
                //子数组排列
                int[] ints = Arrays.copyOfRange(nums, i + 1, nums.length);
                Arrays.sort(ints);
                for (int anInt : ints) {
                    nums[++i] = anInt;
                }
                return nums;
            } else if (i == 0) {
                k--;
                i = k;
            }
        }
        Arrays.sort(nums);
        return nums;
    }

    //32.困难
    public int longestValidParentheses(String s) {
        int temp = 0, max = 0;
        char[] chars = s.toCharArray();
        boolean[] ischecked = new boolean[chars.length];

        Stack<Character> characters = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ')') {
                if (!characters.isEmpty()) {
                    characters.pop();
                    ischecked[i] = true;
                    for (int j = i - 1; j >= 0; j--) {
                        if (!ischecked[j]) {
                            ischecked[j] = true;
                            break;
                        }
                    }
                } else {
                    characters.clear();
                }
            } else {
                characters.push('(');
            }
        }
        for (boolean b : ischecked) {
            if (b)
                temp++;
            else {
                if (temp > max)
                    max = temp;
                temp = 0;
            }
        }
        if (temp > max)
            max = temp;
        return max;
    }

    //33.中等 ————你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
    public int search(int[] nums, int target) {
        List<Integer> integers = Arrays.stream(nums).boxed().toList();
        return integers.indexOf(target);
    }

    //37.困难******

    //34.中等 *————你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
    public int[] searchRange(int[] nums, int target) {
            /*
 int[] 转 List<Integer>
List<Integer> list1 = Arrays.stream(data).boxed().collect(Collectors.toList());
// Arrays.stream(arr) 可以替换成IntStream.of(arr)。
// 1.使用Arrays.stream将int[]转换成IntStream。
// 2.使用IntStream中的boxed()装箱。将IntStream转换成Stream<Integer>。
// 3.使用Stream的collect()，将Stream<T>转换成List<T>，因此正是List<Integer>。

 int[] 转 Integer[]
Integer[] integers1 = Arrays.stream(data).boxed().toArray(Integer[]::new);
// 前两步同上，此时是Stream<Integer>。
// 然后使用Stream的toArray，传入IntFunction<A[]> generator。
// 这样就可以返回Integer数组。
// 不然默认是Object[]。

List<Integer> 转 Integer[]
Integer[] integers2 = list1.toArray(new Integer[0]);
// 调用toArray。传入参数T[] a。这种用法是目前推荐的。
// List<String>转String[]也同理。

List<Integer> 转 int[]
int[] arr1 = list1.stream().mapToInt(Integer::valueOf).toArray();
// 想要转换成int[]类型，就得先转成IntStream。
// 这里就通过mapToInt()把Stream<Integer>调用Integer::valueOf来转成IntStream
// 而IntStream中默认toArray()转成int[]。

Integer[] 转 int[]
int[] arr2 = Arrays.stream(integers1).mapToInt(Integer::valueOf).toArray();
// 思路同上。先将Integer[]转成Stream<Integer>，再转成IntStream。

Integer[] 转 List<Integer>
List<Integer> list2 = Arrays.asList(integers1);
// 最简单的方式。String[]转List<String>也同理。

// 同理
String[] strings1 = {"a", "b", "c"};
// String[] 转 List<String>
List<String> list3 = Arrays.asList(strings1);
// List<String> 转 String[]
String[] strings2 = list3.toArray(new String[0]);
*/
        List<Integer> integers = Arrays.stream(nums).boxed().toList();
        int[] ints = new int[2];
        ints[0] = integers.indexOf(target);
        ints[1] = integers.lastIndexOf(target);
        return ints;
    }

    //35.简单 (标准二分法)
    public int searchInsert(int[] nums, int target) {
        int head = 0;
        int last = nums.length - 1;
        while (head <= last) {
            int mid = (head + last) / 2;
            if (nums[mid] < target) {
                head = mid + 1;
            } else
                last = mid - 1;

        }
        return head;
    }

    //36.中等
    public boolean isValidSudoku(char[][] board) {
        //定义数字行内出现的次数
        int[][] row = new int[9][9];
        //定义数字列内出现的次数
        int[][] column = new int[9][9];
        //定义数字九宫格内出现的次数最大为9次
        int[][][] jiugongge = new int[3][3][9];
        //遍历数组
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                //只要存在数字
                if (c != '.') {
                    //把数字-1化成索引下标,c是字符串要减去字符串，-1会报错。
                    int index = c - '1';
                    //这个时候++意思是第i行这个c值次数+1，默认row第二位就是{1-9}-1；每一行都有可能是1-9
                    //例如现在是第一行第一列是9，就在row[1][8]号位置+1
                    row[i][index]++;
                    //列同理
                    column[j][index]++;
                    //并且九宫格内次数也要+1,例如也是第1行第一列，i/3 j/3会自动定位到所在的小宫格
                    jiugongge[i / 3][j / 3][index]++;
                    //次数大于1就不成立一个数独
                    if (row[i][index] > 1 || column[j][index] > 1 || jiugongge[i / 3][j / 3][index] > 1) return false;
                }
            }
        }
        return true;
    }

    //38.中等
    public String countAndSay(int n) {
        StringBuilder s = new StringBuilder();
        if (n == 1)
            return "1";
        else {
            String string = countAndSay(n - 1);
            char[] chars = string.toCharArray();
            //每个字符连续出现的次数；
            int count;
            //这里不需要i自加一，因为每次更新一次新的for循环之前，
            // 下面的while循环已经把i指向新的不连续字符了
            for (int i = 0; i < chars.length; ) {
                char c = chars[i];
                count = 0;
                //统计连续字符
                while (i < chars.length && chars[i] == c) {
                    count++;
                    i++;
                }
                s.append(count).append(c);
            }
        }
        return new String(s);
    }

    //39.中等
    // （官方写的很明了，自己写的很奇怪，并且时间复杂度高,因为用了Arraylist的contains判断是否已经存在耗费了大量的时间，
    // 所以改成Hashset会提高性能20%，但仍然赶不上官方）
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
            /*

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }


作者：LeetCode-Solution
链接：https://leetcode.cn/problems/combination-sum/solution/zu-he-zong-he-by-leetcode-solution/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
        //这里用Hashset而不用Arraylist，因为可以自动不添加重复元素
        HashSet<List<Integer>> integers1 = new HashSet<>();
        notaddCombination(candidates, target, 0, new ArrayList<>(), integers1);
        addCombination(candidates, target, 0, new ArrayList<>(), integers1);
        return new ArrayList<>(integers1);
    }

    //当前数字不加入
    public void notaddCombination(int[] candidates, int target, int index, List<Integer> integers,
                                  HashSet<List<Integer>> ans) {
        if (index != candidates.length - 1) {
            notaddCombination(candidates, target, index + 1, integers, ans);
            addCombination(candidates, target, index + 1, integers, ans);
        }
    }

    //当前数字加入
    public void addCombination(int[] candidates, int target, int index, List<Integer> integers,
                               HashSet<List<Integer>> ans) {
        if (target == 0) {
            //此时这里不需要进行contains判定，因为是Hashset
            ans.add(new ArrayList<>(integers));
            //迭代删除，否则会抛出ConcurrentModificationException
        } else if (target < 0) {
            return;
        } else {
            integers.add(candidates[index]);
            notaddCombination(candidates, target - candidates[index], index + 1, integers, ans);
            addCombination(candidates, target - candidates[index], index + 1, integers, ans);
            if (!integers.isEmpty())
                integers.remove(integers.size() - 1);
        }
    }

    //40.中等****
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);
        for (int num : candidates) {
            int size = freq.size();
            if (freq.isEmpty() || num != freq.get(size - 1)[0]) {
                freq.add(new int[]{num, 1});
            } else {
                ++freq.get(size - 1)[1];
            }
        }
        dfs(0, target);
        return ans;
    }

    public void dfs(int pos, int rest) {
        if (rest == 0) {
            ans.add(new ArrayList<Integer>(sequence));
            return;
        }
        if (pos == freq.size() || rest < freq.get(pos)[0]) {
            return;
        }

        dfs(pos + 1, rest);

        int most = Math.min(rest / freq.get(pos)[0], freq.get(pos)[1]);
        for (int i = 1; i <= most; ++i) {
            sequence.add(freq.get(pos)[0]);
            dfs(pos + 1, rest - i * freq.get(pos)[0]);
        }
        for (int i = 1; i <= most; ++i) {
            sequence.remove(sequence.size() - 1);
        }
    }

    //41.困难
    public int firstMissingPositive(int[] nums) {
        //先排序
        Arrays.sort(nums);
        int l;
        //找到第一个正数
        for (l = 0; l < nums.length - 1; l++) {
            if (nums[l] > 0)
                break;
        }
        //如果它不为一，直接返回就是了
        if (nums[l] != 1)
            return 1;
        //否则判断
        int ans = 1;
        for (int i = l; i < nums.length; i++) {
            if (nums[i] != ans) {
                if (nums[i] != ans + 1)
                    return ans + 1;
                else ans++;
            }
        }
        return ans + 1;
    }

    //42.困难*****
    public int trap(int[] height) {
        return 0;
    }

    //43.中等(竖式计算任何数的乘法，超过了计算机位数限制）
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        String[] shang = qiujiashu(chars1, chars2);
        qiushang(shang);
        return String.valueOf(new StringBuilder(shang[shang.length - 1]).reverse());
    }

    public String[] qiujiashu(char[] chars1, char[] chars2) {
        String[] shang = new String[chars2.length];
        //取每个被乘数求出每位乘完后的数（用StringBuilder存）
        for (int i = chars2.length - 1; i >= 0; i--) {
            char c = chars2[i];
            StringBuilder s = new StringBuilder();
            //进位符
            int carry = 0;

            int k = chars2.length - 1 - i;//运算完成后面需要补几个零
            //先补0（因为是反过来的）
            while (k != 0) {
                s.append('0');
                k--;
            }

            for (int j = chars1.length - 1; j >= 0 || carry != 0; j--) {
                int sum;
                if (j >= 0) {
                    char c1 = chars1[j];
                    sum = (c - '0') * (c1 - '0') + carry;
                } else {
                    sum = carry;
                }
                carry = sum / 10;
                s.append(sum % 10);
            }
            shang[i] = String.valueOf(s);
        }
        return shang;
    }

    public void qiushang(String[] shang) {
        for (int i = 1; i < shang.length; i++) {
            //计算加和，从第二个元素开始
            char[] he1 = shang[i].toCharArray();
            char[] he2 = shang[i - 1].toCharArray();
            StringBuilder s = new StringBuilder();
            int carry = 0;
            //位数对齐相加，所以只需要一个变量i1即可
            for (int i1 = 0; i1 < he1.length || i1 < he2.length || carry != 0; i1++) {

                if (i1 >= he1.length) {
                    if (i1 < he2.length) {
                        s.append((he2[i1] - '0' + carry) % 10);
                        carry = (he2[i1] - '0' + carry) / 10;
                    } else {
                        s.append(carry % 10);
                        carry = carry / 10;
                    }
                } else if (i1 >= he2.length) {
                    if (i1 < he1.length) {
                        s.append((he1[i1] - '0' + carry) % 10);
                        carry = (he1[i1] - '0' + carry) / 10;
                    } else {
                        s.append(carry % 10);
                        carry = carry / 10;
                    }
                } else {
                    s.append((he2[i1] - '0' + he1[i1] - '0' + carry) % 10);
                    carry = (he2[i1] - '0' + he1[i1] - '0' + carry) / 10;
                }
            }
            shang[i] = String.valueOf(s);
        }
    }

    //44.困难
    public boolean isMatch(String s, String p) {
        return true;
    }

    //45.中等(自己写的又超时）
    public int jump(int[] nums) {
        /* int position = nums.length - 1;
        int steps = 0;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;

作者：LeetCode-Solution
链接：https://leetcode.cn/problems/jump-game-ii/solution/tiao-yue-you-xi-ii-by-leetcode-solution/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
        if (nums.length == 1)
            return 0;
        //初始最小步数设为10000
        int min = 9000;
        int temp = 0;
        return jumpnum(nums, 0, temp, min);
    }

    public int jumpnum(int[] nums, int index, int temp, int min) {
        if (index == nums.length - 1)
            return temp;
            //如果遇到了0，表示永远跳不过去，直接返回10000
        else if (nums[index] == 0)
            return 10000;
        //跳到现在的步数，每次从这里开始都要复原（回溯）
        int now = temp;
        for (int i = nums[index]; i >= 1; i--) {
            //回溯
            temp = now;
            if (index + i < nums.length) {
                temp = jumpnum(nums, index + i, temp + 1, min);
                if (temp < min)
                    min = temp;
            }
        }
        return min;
    }

    //46.中等
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> integers = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        int a = nums.length;
        int b = 1;
        while (a != 0) {
            b *= a--;
        }
        for (int i = 0; i < b; i++) {
            List<Integer> integers1 = Arrays.stream(nums).boxed().toList();
            if (!ans.contains(integers1))
                ans.add(integers1);
            nextPermutation(nums);
        }
        return ans;
    }

    //47.中等
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int a = nums.length;
        int b = 1;
        while (a != 0) {
            b *= a--;
        }
        for (int i = 0; i < b; i++) {
            List<Integer> integers1 = Arrays.stream(nums).boxed().toList();
            if (!ans.contains(integers1))
                ans.add(integers1);
            nextPermutation(nums);
        }
        return ans;
    }

    //48.
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] matrix_new = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                //对于矩阵中第 i 行的第 j 个元素，在旋转后，它出现在倒数第 i 列的第 j 个位置。
                //因此对于矩阵中的元素matrix[row][col]，在旋转后，它的新位置为matrix[col][n−row−1]。
                matrix_new[j][n - i - 1] = matrix[i][j];
            }
        }
        /*用一个与matrix 大小相同的辅助数组matrix new
 ，临时存储旋转后的结果。我们遍历matrix 中的每一个元素，根据上述规则将该元素存放到 matrix new
  中对应的位置。在遍历完成之后，再将 matrix new中的结果复制到原数组中即可。*/
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = matrix_new[i][j];
            }
        }
    }



    //49.中等(用空间换时间）
    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> ans = new ArrayList<>();
        HashMap<String , List<Integer>> chListHashMap = new HashMap<>();
        //每次都遍历
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String s = String.valueOf(chars);
            if (!chListHashMap.containsKey(s))
                chListHashMap.put(s,new ArrayList<>(List.of(i)));
            else
                chListHashMap.get(s).add(i);

        }
        //对Hashmap中的每个元素进行一次遍历
        for (Map.Entry<String, List<Integer>> listEntry : chListHashMap.entrySet()) {
            List<Integer> value = listEntry.getValue();
            List<String> strings = new ArrayList<>();
            //将每个value对应的取出，加入strings
            for (Integer integer : value) {
                strings.add(strs[integer]);
            }
            ans.add(strings);
        }
        return ans;
    }

    //50.中等
    public double myPow(double x, int n) {
        return Math.pow(x, n);
    }
}




