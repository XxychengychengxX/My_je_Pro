package inorder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution2 {
    public Solution2() {
    }


    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.totalNQueens(4));
    }

    //51.困难
    public List<List<String>> solveNQueens(int n) {
        if (n == 1)
            return new ArrayList<>(Collections.singleton(new ArrayList<>(Collections.singleton("Q"
            ))));
        else if (n == 2 || n == 3)
            return new ArrayList<>(new ArrayList<>());
        List<List<String>> ans = new ArrayList<>();
        char[][] chars = new char[n][n];
        for (char[] aChar : chars) {
            Arrays.fill(aChar, '.');
        }
        //第一行依次取
        findNqueens(chars, 0, ans);
        return ans;
    }

    public void findNqueens(char[][] chars, int row, List<List<String>> ans) {
        if (row == chars.length) {
            ans.add(charToList(chars));
            return;
        }
        boolean same_column = false;
        boolean xie = false;
        for (int i = 0; i < chars[row].length; i++) {
            //检查同一列
            for (char[] aChar : chars) {
                if (aChar[i] == 'Q') {
                    same_column = true;
                    break;
                }
            }
            if (same_column) {
                same_column = false;
                continue;
            }
            //检查斜方 因为是逐行放置的   所以无需校验  左下  和右下     只需校验 两条斜线的上半部分即可
            //左上方
            int a = row, b = i;
            while (a >= 0 && b >= 0) {
                if (chars[a][b] == 'Q') {
                    xie = true;
                    break;
                } else {
                    a--;
                    b--;
                }
            }
            if (xie) {
                xie = false;
                continue;
            }
            //右上方
            a = row;
            b = i;
            while (a >= 0 && b < chars.length) {
                if (chars[a][b] == 'Q') {
                    xie = true;
                    break;
                } else {
                    a--;
                    b++;
                }
            }
            if (xie) {
                xie = false;
                continue;
            }

            //都没有异常就开始搞事
            chars[row][i] = 'Q';
            findNqueens(chars, row + 1, ans);
            chars[row][i] = '.';
        }
    }

    public List<String> charToList(char[][] chars) {
        ArrayList<String> strings = new ArrayList<>();
        for (char[] aChar : chars) {
            strings.add(String.valueOf(aChar));
        }
        return strings;
    }

    //52.困难
    public int totalNQueens(int n) {
        if (n == 1)
            return 1;
        else if (n == 2 || n == 3)
            return 0;
        int[] ints = new int[1];
        char[][] chars = new char[n][n];
        for (char[] aChar : chars) {
            Arrays.fill(aChar, '.');
        }
        //第一行依次取
        findtotalNqueens(chars, 0, ints);
        return ints[0];
    }

    private void findtotalNqueens(char[][] chars, int row, int[] ans) {
        if (row == chars.length) {
            ans[0]++;
            return;
        }
        boolean same_column = false;
        boolean xie = false;
        for (int i = 0; i < chars[row].length; i++) {
            //检查同一列
            for (char[] aChar : chars) {
                if (aChar[i] == 'Q') {
                    same_column = true;
                    break;
                }
            }
            if (same_column) {
                same_column = false;
                continue;
            }
            //检查斜方 因为是逐行放置的   所以无需校验  左下  和右下     只需校验 两条斜线的上半部分即可
            //左上方
            int a = row, b = i;
            while (a >= 0 && b >= 0) {
                if (chars[a][b] == 'Q') {
                    xie = true;
                    break;
                } else {
                    a--;
                    b--;
                }
            }
            if (xie) {
                xie = false;
                continue;
            }
            //右上方
            a = row;
            b = i;
            while (a >= 0 && b < chars.length) {
                if (chars[a][b] == 'Q') {
                    xie = true;
                    break;
                } else {
                    a--;
                    b++;
                }
            }
            if (xie) {
                xie = false;
                continue;
            }
            //都没有异常就开始搞事
            chars[row][i] = 'Q';
            findtotalNqueens(chars, row + 1, ans);
            chars[row][i] = '.';
        }
    }

    //53.

    //54.中等
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        ArrayList<Integer> ans = new ArrayList<>();
        if (m == 1)
            return Arrays.stream(matrix[0]).boxed().toList();
        int row_start = 0, row_end = n, colu_start = 0, colu_end = m;
        readspiral(matrix, ans, row_start, row_end, colu_start, colu_end);

    return null;
    }

    private void readspiral(int[][] matrix, ArrayList<Integer> ans, int row_start, int row_end,
                            int colu_start,
                            int colu_end) {
        if (row_start > row_end || colu_start > colu_end)
            return;
        int i = row_start;
        int j= colu_start;
        while (j < colu_end) {
            ans.add(matrix[i][j++]);
        }
    }
}
