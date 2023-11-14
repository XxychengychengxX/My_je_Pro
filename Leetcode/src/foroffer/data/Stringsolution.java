package foroffer.data;

import java.util.Arrays;

public class Stringsolution {
    public static void main(String[] args) {
        Stringsolution stringsolution = new Stringsolution();
        System.out.println();
    }

    public String replaceSpace(String s) {
        char[] a = s.toCharArray();
        StringBuilder s1 = new StringBuilder();
        for (char c : a) {
            if (c != 32)
                s1.append(c);
            else
                s1.append("%20");
        }
        String s2 = s1.toString();
        return s2;
    }

    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }


    /*
     * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: [10,2] 输出: "102" 示例 2:
     *
     * 输入: [3,30,34,5,9] 输出: "3033459"  
     *
     * 提示:
     *
     * 0 < nums.length <= 100 说明:
     *
     * 输出结果可能非常大，所以你需要返回一个字符串而不是整数 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
     *
     * 作者：Krahets
     * 链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/59ypcj/
     * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public String minNumber(int[] nums) {

        // 转为字符串数组
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        // 对X Y 进行排序
        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));
        // 输出
        StringBuilder res = new StringBuilder();
        for (String s : strs)
            res.append(s);
        return res.toString();
    }


    /*请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
    在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a
    .a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。

作者：Krahets
链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/9a1ypc/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

    /**
     * 例： s = "ABC" p = "A*B. "
     * <p>
     * db设计： p → s      "" A  *  B . ↓      ------------- ""|T 口 口 口 口 A |F 口 口 口 口 B |F 口 口 口 口 C |F 口 口 口 口
     * <p>
     * row：行下标， col：列下标， i：p的下标， j：s的下标。
     */
    public boolean isMatch(String s, String p) {

        // 要考虑到s和p前面存在""情况，因为下面"*"的匹配情况需要用到表格的同一行减2，得做好初始化 （dp[row][col]是否匹配取决于 dp[row][col-2]的成功匹配）
        // 由于s与"" mactch全是false, 而表格默认全是false, 第一列已经初始化false， 除了第一个""match""为 true
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();

        // ""match""为 true
        dp[0][0] = true;

        //初始化 ""与p match的情况
        for (int col = 1; col < dp[0].length; col++) {
            int j = col - 1;
            if (col == 1 && cp[j] == '*') {
                dp[0][col] = true;
            } else if (col > 1 && cp[j] == '*') {
                dp[0][col] = dp[0][col - 2];
            }
        }

        //填充表格，完成状态转移过程
        for (int row = 1; row < dp.length; row++) {
            for (int col = 1; col < dp[0].length; col++) {
                int i = row - 1;
                int j = col - 1;
                //情况1, 无*, dp[row][col]是否匹配取决于 dp[row-1][col-1]
                if (cs[i] == cp[j] || cp[j] == '.') {
                    dp[row][col] = dp[row - 1][col - 1];

                } else if (cp[j] == '*' && col > 1) {
                    //情况2, 出现*, dp[row][col]是否匹配取决于 dp[row][col-2]的成功匹配
                    if (dp[row][col - 2]) {
                        dp[row][col] = true;

                        //情况3, 出现*, dp[row][col-2]匹配失败，补救措施：如果当前字符s对应了上一个匹配字符p, 即(cs[i] == cp[j-1] || cp[j-1] == '.')
                        // , dp[row][col] 是否匹配取决于dp[row-1][col]
                    } else if (cs[i] == cp[j - 1] || cp[j - 1] == '.') {
                        dp[row][col] = dp[row - 1][col];
                    }
                }
            }
        }

        //返回最后一格
        return dp[dp.length - 1][dp[0].length - 1];
    }




    }






