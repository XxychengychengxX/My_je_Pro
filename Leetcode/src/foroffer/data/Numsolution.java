package foroffer.data;

import java.util.*;

public class Numsolution {
    public Numsolution() {
    }

    public static void main(String[] args) {

        Numsolution numsolution = new Numsolution();
        System.out.println(numsolution.hammingWeight(111101111));
    }


    /*编写一个函数，输入是一个无符号整数（以二进制串的形式），
    返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量).）。*/
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += n & 1;
            n = n >> 1;
        }
        return count;
    }

    /*
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：x = 2.00000, n = 10 输出：1024.00000 示例 2：
     *
     * 输入：x = 2.10000, n = 3 输出：9.26100 示例 3：
     *
     * 输入：x = 2.00000, n = -2 输出：0.25000 解释：2-2 = 1/22 = 1/4 = 0.25
     *
     * 作者：Krahets
     * 链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/57rwmg/
     * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public double myPow(double x, int n) {
        if (x == 0.0f) {
            return 0.0d;
        }
        long b = n;
        double res = 1.0;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        while (b > 0) {
            if ((b & 1) == 1)// b是奇数
            {
                res *= x;
            }
            x *= x;
            b >>= 1;
        }
        return res;
        /*
         * 作者：Krahets
         * 链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/57p2pv/
         * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         */
    }


    /*
     * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0
     * ，可以看成任意数字。A 不能视为 14。
     *
     * 作者：Krahets
     * 链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/57mpoj/
     * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean isStraight(int[] nums) {

        Arrays.sort(nums);
        int num0 = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (num0 < 0) {
                return false;
            } else if (nums[i] == 0) {
                num0++;
            } else if (nums[i + 1] - nums[i] - 1 < 0) {
                return false;
            } else {
                num0 -= nums[i + 1] - nums[i] - 1;
            }
        }
        return true;
    }

    /*
     * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字， 则最小的4个数字是1、2、3、4。
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        int[] ints = new int[k];
        System.arraycopy(arr, 0, ints, 0, k);
        return ints;
    }

    /*
     * 求 1+2+...+n ，要求不能使用乘除法、 for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     */
    public int sumNums(int n) {
        if (n == 1) {
            return 1;
        } else {
            return sumNums(n - 1) + n;
        }
    }

    /*
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
     */
    public int[] printNumbers(int n) {
        int[] ints = new int[(int) (Math.pow(10, n) - 1)];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = i + 1;
        }
        return ints;
    }

    /*
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: [7,5,6,4] 输出: 5
     *
     * 作者：Krahets
     * 链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/o58jfs/
     * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int reversePairs(int[] nums) {
        /*class Solution {
    int[] nums, tmp;
    public int reversePairs(int[] nums) {
        this.nums = nums;
        tmp = new int[nums.length];
        return mergeSort(0, nums.length - 1);
    }
    private int mergeSort(int l, int r) {
        // 终止条件
        if (l >= r) return 0;
        // 递归划分
        int m = (l + r) / 2;
        int res = mergeSort(l, m) + mergeSort(m + 1, r);
        // 合并阶段
        int i = l, j = m + 1;
        for (int k = l; k <= r; k++)
            tmp[k] = nums[k];
        for (int k = l; k <= r; k++) {
            if (i == m + 1)
                nums[k] = tmp[j++];
            else if (j == r + 1 || tmp[i] <= tmp[j])
                nums[k] = tmp[i++];
            else {
                nums[k] = tmp[j++];
                res += m - i + 1; // 统计逆序对
            }
        }
        return res;
    }
}

作者：Krahets
链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/o53yjd/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
        int b = 0;
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            //双指针？？？
            for (int j = i + 1, k = nums.length - 1; j <= k; j++, k--) {
                if (j == k) {
                    if (nums[j] < a)
                        b++;
                } else {
                    if (nums[j] < a)
                        b++;
                    if (nums[k] < a)
                        b++;
                }
            }
        }
        return b;
    }

    /*写一个函数，求两个整数之和，要求在函数体内不得使用 “
    +”、“-”、“*”、“/” 四则运算符号。*/
    public int add(int a, int b) {
        if (a == 0)
            return b;
        if (b == 0)
            return a;
        return add(a ^ b, (a & b) << 1);
    }

    /*一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
     * */
    public int[] singleNumbers(int[] nums) {
        if (nums.length == 2)
            return nums;
        Arrays.sort(nums);
        int[] ints = new int[2];
        int index = 0;
        for (int i = 0; i < nums.length; i += 1) {
            //最左边
            if (i == 0) {
                if (nums[i] != nums[i + 1])
                    ints[index++] = nums[i];
            }
            //最右边
            else if (i == nums.length - 1) {
                if (nums[i] != nums[i - 1])
                    ints[index++] = nums[i];
            }
            //中间
            else if (nums[i] != nums[i + 1] && nums[i] != nums[i - 1])
                ints[index++] = nums[i];
            if (index == 2)
                break;
        }
        return ints;
    }

    /*在一个数组 nums 中除一个数字只出现一次之外，
    其他数字都出现了三次。请找出那个只出现一次的数字。

    官方解法思路：https://leetcode.cn/leetbook/read/illustration-of-algorithm/9hctss/
    */
    public int singleNumber(int[] nums) {
        /*
        *  int ones = 0, twos = 0;
        for(int num : nums){
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;

作者：Krahets
链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/9hctss/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i += 1) {
            //最左边
            if (i == 0) {
                if (nums[i] != nums[i + 1])
                    return nums[i];
            }
            //最右边
            else if (i == nums.length - 1) {
                if (nums[i] != nums[i - 1])
                    return nums[i];
            }
            //中间
            else if (nums[i] != nums[i + 1] && nums[i] != nums[i - 1])
                return nums[i];
        }
        return 0;
    }

    /*数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
你可以假设数组是非空的，并且给定的数组总是存在多数元素。*/
    public int majorityElement(int[] nums) {
/*出现次数超过一半的话，排序之后的位于中间的元素肯定是它:
*          Arrays.sort(nums);
        int mid = nums.length;
        return nums[mid/2];
        * */
        HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>();
        for (int num : nums) {
            if (!integerIntegerHashMap.containsKey(num))
                integerIntegerHashMap.put(num, 1);
            else
                integerIntegerHashMap.compute(num, (k, v) -> v += 1);
            if (integerIntegerHashMap.get(num) > nums.length / 2)
                return num;
        }
        return 0;
    }

    /*给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积,
    即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。

作者：Krahets
链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/57d8cm/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    public int[] constructArr(int[] a) {
        /*class Solution {
    public int[] constructArr(int[] a) {
        int len = a.length;
        if(len == 0) return new int[0];
        int[] b = new int[len];
        b[0] = 1;
        int tmp = 1;
        for(int i = 1; i < len; i++) {
            b[i] = b[i - 1] * a[i - 1];
        }
        for(int i = len - 2; i >= 0; i--) {
            tmp *= a[i + 1];
            b[i] *= tmp;
        }
        return b;
    }
}

作者：Krahets
链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/570i11/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
        int[] b = new int[a.length];
        for (int i = 0; i < b.length; i++) {
            int sum = 1;
            for (int j = 0; j < a.length; j++) {
                if (j == i)
                    ;

                else if (a[j] == 0) {
                    b[i] = 0;
                    break;
                } else {
                    sum *= a[j];
                    b[i] = sum;
                }
            }
        }
        return b;
    }

    /*给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问
     k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

作者：Krahets
链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/5v1026/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    public int cuttingRope(int n) {
        if (n == 2)
            return 1;
        else if (n == 3)
            return 2;
        else
            return cuttingrope(n);
    }

    public int cuttingrope(int n) {
        if (n == 2 || n == 3 || n == 1)
            return n;
        else
            return Math.max(2 * cuttingrope(n - 2), 3 * cuttingrope(n - 3));
    }

    /*输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。

序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。

作者：Krahets
链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/eufzm7/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    public int[][] findContinuousSequence(int target) {
        int i = 1;
        double j = 2.0;
        List<int[]> res = new ArrayList<>();
        while (i < j) {
            j = (-1 + Math.sqrt(1 + 4 * (2 * target + (long) i * i - i))) / 2;
            if (j == (int) j) {
                int[] ans = new int[(int) j - i + 1];
                for (int k = i; k <= (int) j; k++)
                    ans[k - i] = k;
                res.add(ans);
            }
            i++;
        }
        return res.toArray(new int[0][]);
    }

    /*0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。

例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。

作者：Krahets
链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/oxrkot/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    public int lastRemaining(int n, int m) {
        /*f(n) 可由 f(n - 1)f(n−1) 得到，f(n - 1)f(n−1) 可由 f(n - 2)f(n−2) 得到，……，f(2)f(2) 可由 f(1)f(1) 得到；因此，若给定 f(1)f(1)
        的值，就可以递推至任意 f(n)f(n) 。而「1, m1,m 问题」的解 f(1) = 0f(1)=0 恒成立，即无论 mm 为何值，长度为 1 的数字环留下的是一定是数字 00 。

以上数学推导本质是得出动态规划的 转移方程 和 初始状态 。

动态规划解析：
状态定义： 设「i, mi,m 问题」的解为 dp[i]dp[i] ；
转移方程： 通过以下公式可从 dp[i - 1]dp[i−1] 递推得到 dp[i]dp[i] ；
dp[i] = (dp[i - 1] + m) \% i
dp[i]=(dp[i−1]+m)%i

初始状态：「1, m1,m 问题」的解恒为 00 ，即 dp[1] = 0dp[1]=0 ；
返回值： 返回「n, mn,m 问题」的解 dp[n]dp[n]

作者：Krahets
链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/oxp3er/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            integers.add(i);
        }
        int count = 0;
        while (integers.size() != 1) {
            count += m - 1;
            while (count > integers.size() - 1)
                count -= integers.size();
            integers.remove(count);
        }
        return integers.get(0);
    }

    /*输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。

     

    示例 1：

    输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
    输出：[1,2,3,6,9,8,7,4,5]

    作者：Krahets
    链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/5vfh9g/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0)
            return new int[0];
        else if (matrix.length == 1) {
            int[] ints = new int[matrix[0].length];
            System.arraycopy(matrix[0], 0, ints, 0, ints.length);
            return ints;
        } else if (matrix[0].length == 1) {
            int[] ints = new int[matrix.length];
            for (int i = 0; i < ints.length; i++) {
                ints[i] = matrix[i][0];
            }
            return ints;
        }
        int[] ints = new int[matrix.length * matrix[0].length];
        return shengcheng(matrix, 0, 0, 0, ints);
    }

    public int[] shengcheng(int[][] a, int row_start, int colunm_start, int num, int[] b) {
        //boolean[][] booleans = new boolean[a.length][a[0].length];会更加方便

        int num1 = num;
        int row = row_start;
        int colunm = colunm_start;
        if (colunm_start > a[0].length / 2 || row_start > a.length / 2 || num1 == b.length)//显然超过了一半或者是数组已完成
            return b;
        else if (colunm_start == a[0].length / 2 && row_start == a.length / 2) {//最后一个在中间
            b[num1++] = a[row_start][colunm_start];
            return b;
        } else {
            //如果用bool二维矩阵会更加方便，不用每次循环都判断是否num1==b。length
            while (colunm < a[0].length - colunm_start) {
                if (num1 == b.length)
                    return b;//用布尔矩阵
                b[num1++] = a[row][colunm++];
            }
            num1--;
            colunm--;
            while (row < a.length - row_start) {
                if (num1 == b.length)
                    return b;
                b[num1++] = a[row++][colunm];
            }
            num1--;
            row--;
            while (colunm > colunm_start) {
                if (num1 == b.length)
                    return b;
                b[num1++] = a[row][colunm--];
            }

            while (row > row_start) {
                if (num1 == b.length)
                    return b;
                b[num1++] = a[row--][colunm];
            }

            return shengcheng(a, row_start + 1, colunm_start + 1, num1, b);
        }
    }

    /*输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5}
     是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。

作者：Krahets
链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/5wh1hj/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> integers = new Stack<>();
        if (pushed.length == 0)
            return true;
        else
            integers.push(pushed[0]);
        for (int i = 0, j = 1; i < popped.length || j < pushed.length; ) {
            try {
                if (!integers.isEmpty() && integers.peek() == popped[i]) {
                    integers.pop();
                    i++;
                } else
                    integers.push(pushed[j++]);
            } catch (Exception e) {
                return false;
            }
        }
        return integers.isEmpty();
    }

    /*请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。

数值（按顺序）可以分成以下几个部分：

若干空格
一个 小数 或者 整数
（可选）一个 'e' 或 'E' ，后面跟着一个 整数
若干空格
小数（按顺序）可以分成以下几个部分：

（可选）一个符号字符（'+' 或 '-'）
下述格式之一：
至少一位数字，后面跟着一个点 '.'
至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
一个点 '.' ，后面跟着至少一位数字
整数（按顺序）可以分成以下几个部分：

（可选）一个符号字符（'+' 或 '-'）
至少一位数字
部分数值列举如下：

["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
部分非数值列举如下：

["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]

作者：Krahets
链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/5d6vi6/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    public boolean isNumber(String s) {
        /* Map[] states = {
            new HashMap<>() {{ put(' ', 0); put('s', 1); put('d', 2); put('.', 4); }}, // 0.
            new HashMap<>() {{ put('d', 2); put('.', 4); }},                           // 1.
            new HashMap<>() {{ put('d', 2); put('.', 3); put('e', 5); put(' ', 8); }}, // 2.
            new HashMap<>() {{ put('d', 3); put('e', 5); put(' ', 8); }},              // 3.
            new HashMap<>() {{ put('d', 3); }},                                        // 4.
            new HashMap<>() {{ put('s', 6); put('d', 7); }},                           // 5.
            new HashMap<>() {{ put('d', 7); }},                                        // 6.
            new HashMap<>() {{ put('d', 7); put(' ', 8); }},                           // 7.
            new HashMap<>() {{ put(' ', 8); }}                                         // 8.
        };
        int p = 0;
        char t;
        for(char c : s.toCharArray()) {
            if(c >= '0' && c <= '9') t = 'd';
            else if(c == '+' || c == '-') t = 's';
            else if(c == 'e' || c == 'E') t = 'e';
            else if(c == '.' || c == ' ') t = c;
            else t = '?';
            if(!states[p].containsKey(t)) return false;
            p = (int)states[p].get(t);
        }
        return p == 2 || p == 3 || p == 7 || p == 8;

作者：Krahets
链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/5dkal2/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
        String s1 = s.strip();
        char[] chars = s1.toCharArray();
        try {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == 'e' || chars[i] == 'E') {
                    return (isXiaoshu(s1.substring(0, i).toCharArray()) || isZhengshu(
                            s1.substring(0, i).toCharArray())) && isZhengshu(s1.substring(i + 1).toCharArray());
                }
            }
            return isXiaoshu(s1.toCharArray()) || isZhengshu(s1.toCharArray());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isXiaoshu(char[] a) throws Exception {
        int count = 1;
        if (a[0] == '+' || a[0] == '-') {
            if (a[1] == '.') {//第二个是点
                count--;
                if (a.length == 2)
                    return false;//如果只有+和.则排除
                for (int i = 2; i < a.length; i++) {//后面跟着至少一个数字
                    if (a[i] == '.')
                        count--;
                    else if (a[i] < '0' || a[i] > '9')
                        return false;

                }
            } else if (a[1] >= '0' && a[1] <= '9') {//如果第二个不是点

                for (int i = 2; i < a.length; i++) {
                    if (a[i] == '.')
                        count--;
                    else if (a[i] < '0' || a[i] > '9')
                        return false;
                    if (count < 0)
                        return false;
                }
            }
        } else if (a[0] == '.') {//第一个是点
            count--;
            if (a.length == 1)//如果后面没有数字
                return false;
            for (int i = 1; i < a.length; i++) {//后面跟着至少一个数字
                if (a[i] == '.')
                    count--;
                else if (a[i] < '0' || a[i] > '9')
                    return false;
            }
        } else if (a[0] >= '0' && a[0] <= '9') {//如果第一个不是点

            for (int i = 1; i < a.length; i++) {
                if (a[i] == '.')
                    count--;
                else if (a[i] < '0' || a[i] > '9')
                    return false;
            }
        }
        return count == 0;
    }

    public boolean isZhengshu(char[] a) throws Exception {
        if (a[0] == '+' || a[0] == '-') {
            if (a.length == 1) //第二个是点
                return false;
            for (int i = 1; i < a.length; i++) {//后面跟着至少一个数字
                if (a[i] < '0' || a[i] > '9')
                    return false;
            }
        } else
            for (char c : a) {//后面跟着至少一个数字
                if (c < '0' || c > '9')
                    return false;
            }
        return true;
    }

    /*写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。

 

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0。

说明：

假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。

作者：Krahets
链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/58pq8g/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    //伪自动机做法
    public int strToInt(String str) {

        enum status {INITIAL, ABOVE_ZERO, BELOW_ZERO}
        String strip = str.strip();
        if (strip.length() == 0)
            return 0;
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
                if (sum > sum * 10 + chars[k] - '0') {//发生溢出
                    if (i == status.ABOVE_ZERO)
                        return Integer.MAX_VALUE;
                    else
                        return Integer.MIN_VALUE;
                } else
                    sum = sum * 10 + chars[k] - '0';
            } else
                break;
        }
        if (i == status.INITIAL)
            return 0;
        else if (i == status.ABOVE_ZERO) {
            if (sum > (long) Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            else
                return (int) sum;
        } else {
            if (sum > (long) Integer.MAX_VALUE + 1)
                return Integer.MIN_VALUE;
            else
                return (int) sum * (-1);
        }
    }

    /*给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。*/
    public int[] maxSlidingWindow(int[] nums, int k) {
        /*  if(nums.length == 0 || k == 0) return new int[0];
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for(int j = 0, i = 1 - k; j < nums.length; i++, j++) {
            // 删除 deque 中对应的 nums[i-1]
            if(i > 0 && deque.peekFirst() == nums[i - 1])
                deque.removeFirst();
            // 保持 deque 递减
            while(!deque.isEmpty() && deque.peekLast() < nums[j])
                deque.removeLast();
            deque.addLast(nums[j]);
            // 记录窗口最大值
            if(i >= 0)
                res[i] = deque.peekFirst();
        }
        return res;

作者：Krahets
链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/58rgqe/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
        if (nums.length == 0)
            return new int[0];
        int j = 0;
        int[] tool = new int[k];
        int[] ints = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            System.arraycopy(nums, i, tool, 0, k);
            ints[j++] = Findmax(tool, k - 1);
        }
        return ints;
    }

    public int Findmax(int[] a, int i) {
        Arrays.sort(a);
        return a[i];
    }

    /*把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。

 
输入: 1
输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
示例 2:

输入: 2
输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]

你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。

作者：Krahets
链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/ozzl1r/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    public double[] dicesProbability(int n) {
        double[] doubles = {1.0 / 6, 1.0 / 6, 1.0 / 6, 1.0 / 6, 1.0 / 6, 1.0 / 6};
        if (n == 1)
            return doubles;
        else
            return myProbability(doubles, n - 1);
    }

    public double[] myProbability(double[] pro, int a) {
        double[] doubles = new double[pro.length + 5];
        double[] doubles1 = {1.0 / 6, 1.0 / 6, 1.0 / 6, 1.0 / 6, 1.0 / 6, 1.0 / 6};
        for (int i = 0; i < pro.length; i++) {
            for (int j = 0; j < doubles1.length; j++) {
                doubles[i + j] += pro[i] * doubles1[j];
            }
        }
        if (a == 1)
            return doubles;
        else
            return myProbability(doubles, a - 1);
    }

    /*  我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。

     

    示例:

    输入: n = 10
    输出: 12
    解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。

    作者：Krahets
    链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/9h3im5/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    public int nthUglyNumber(int n) {
         /*作者：Krahets
        链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/9hq0r6/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if (dp[i] == n2)
                a++;
            if (dp[i] == n3)
                b++;
            if (dp[i] == n5)
                c++;
        }
        return dp[n - 1];
    }

}



