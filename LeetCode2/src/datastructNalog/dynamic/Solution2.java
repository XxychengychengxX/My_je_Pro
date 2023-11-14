package datastructNalog.dynamic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Valar Morghulis
 * @Date 2023/9/10
 */
public class Solution2 {
    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.maxSubArray(new int[]{5, 4, -1, 7, 8}));
    }

    /**
     * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a
     * .a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。 作者：Krahets
     * 链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/9a1ypc/ 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean isMatch(String s, String p) {
        int m = s.length() + 1, n = p.length() + 1;
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        // 初始化首行
        for (int j = 2; j < n; j += 2) {
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        }
        // 状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //dp[i][j]对应的是s[i-1]和p[j-1]的对应情况
                if (p.charAt(j - 1) == '*') {
                    if (dp[i][j - 2]) {
                        // 1.如果前第二个字符对应，则无论如何都能对应（这里取‘0’即可）
                        dp[i][j] = true;
                    } else if (dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)) {
                        // 2.该字符对应
                        dp[i][j] = true;
                    } else if (dp[i - 1][j] && p.charAt(j - 2) == '.') {
                        // 3.
                        dp[i][j] = true;
                    }
                } else {
                    if (dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)) {
                        // 1.
                        dp[i][j] = true;
                    } else if (dp[i - 1][j - 1] && p.charAt(j - 1) == '.') {
                        // 2.
                        dp[i][j] = true;
                    }
                }
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a
     * .a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
     * <p>
     * 作者：Krahets 链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/9a1ypc/ 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
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
     * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成
     * “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。 作者：Krahets
     * 链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/99wd55/ 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int translateNum(int num) {
        if (num == 0) {
            return 1;
        }
        if (num / 10 == 0) {
            return 1;
        }
        String s = String.valueOf(num);
        char[] chars = s.toCharArray();
        //从尾到头进行翻译，如果当前递归翻译的最后一个数字是0-5则判断
        boolean b =
                chars[chars.length - 1] - '0' >= 0 && chars[chars.length - 1] - '0' <= 5 && chars[chars.length - 2] - '0' == 2;
        boolean b1 =
                chars[chars.length - 1] - '0' >= 0 && chars[chars.length - 1] - '0' <= 9 && chars[chars.length - 2] - '0' == 1;
        if (b || b1) {
            return translateNum(num / 100) + translateNum(num / 10);

        } else {
            return translateNum(num / 10);
        }
    }

    /**
     * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于
     * 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？ 作者：Krahets
     * 链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/5vokvr/ 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int maxValue(int[][] grid) {
        //使用动态规划
        int row = grid.length;
        int column = grid[0].length;
        //先初始化行和列
        for (int i = 1; i < row; i++) {
            grid[i][0] = grid[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < column; i++) {
            grid[0][i] = grid[0][i - 1] + grid[0][i];
        }
        //开始动态规划
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                int max = Math.max(grid[i - 1][j] + grid[i][j], grid[i][j - 1] + grid[i][j]);
                grid[i][j] = max;
            }
        }
        return grid[row - 1][column - 1];
    }

    /**
     * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
     * https://leetcode.cn/leetbook/read/illustration-of-algorithm/5dgr0c/
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        int res = 0, tmp = 0, len = s.length();
        for(int j = 0; j < len; j++) {
            // 获取索引 i
            int i = dic.getOrDefault(s.charAt(j), -1);
            // 更新哈希表
            dic.put(s.charAt(j), j);
            // dp[j - 1] -> dp[j]
            tmp = tmp < j - i ? tmp + 1 : j - i;
            // max(dp[j - 1], dp[j])
            res = Math.max(res, tmp);
        }
        return res;
    }

    /**
     * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
     * https://leetcode.cn/leetbook/read/illustration-of-algorithm/9h3im5/
     */
    public int nthUglyNumber(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if(dp[i] == n2) {
                a++;
            }
            if(dp[i] == n3) {
                b++;
            }
            if(dp[i] == n5) {
                c++;
            }
        }
        return dp[n - 1];
    }

    /**
     * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
     * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
     * 作者：Krahets
     * 链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/ozzl1r/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public double[] dicesProbability(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);
        for (int i = 2; i <= n; i++) {
            double[] tmp = new double[5 * i + 1];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    //这里采用正向递推
                    tmp[j + k] += dp[j] / 6.0;
                }
            }
            dp = tmp;
        }
        return dp;
    }

    /**
     * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
     * https://leetcode.cn/leetbook/read/illustration-of-algorithm/58nn7r/
     */
    public int maxProfit(int[] prices) {
        int cost = Integer.MAX_VALUE, profit = 0;
        for(int price : prices) {
            cost = Math.min(cost, price);
            profit = Math.max(profit, price - cost);
        }
        return profit;
    }



}
