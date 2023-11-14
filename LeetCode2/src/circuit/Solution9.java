package circuit;

import java.util.Scanner;

/**
 * @author Valar Morghulis
 * @Date 2023/9/23
 */
public class Solution9 {
    public static int res = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //总人数
        int num = scanner.nextInt();
        //一次带走的人数
        int oneCount = scanner.nextInt();
        //能力值数组
        int[] ints = new int[num];
        //临时数组

        for (int i = 0; i < num; i++) {
            ints[i] = scanner.nextInt();
        }
        char[] chars = new char[num];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = 'C';
        }

        partition(ints, chars, num, oneCount);
        for (char aChar : chars) {
            System.out.print(aChar);
        }
    }


    public static void partition(int[] ints, char[] chars, int num, int oneCount) {
        //当前选人的教练
        char now = 'A';

        int count = 0;

        while (count < num) {
            int maxIndex = 0;
            int max = 0;
            //遍历获得当前最大值
            for (int i = 0; i < ints.length; i++) {
                //如果队员还没被选过，才遍历获得当前最大值
                if (chars[i] == 'C') {
                    if (max == 0) {
                        max = ints[i];
                        maxIndex = i;
                    } else {
                        if (ints[i] > max) {
                            max = ints[i];
                            maxIndex = i;
                        }
                    }
                }
            }
            chars[maxIndex] = now;
            count++;
            //拿到当前最大值下标，开始选人
            int index = 1;
            //标记该边有几人被选了，要跳过
            int selectedCount = 0;
            //左边
            while (maxIndex - index >= 0 && index <= oneCount + selectedCount) {
                //已经被选过
                if (chars[maxIndex - index] != 'C') {
                    index++;
                    selectedCount++;
                } else {
                    chars[maxIndex - index] = now;
                    index++;
                    count++;
                }

            }
            index = 1;
            selectedCount = 0;
            //右边
            while (maxIndex + index < num && index <= oneCount + selectedCount) {
                //已经被选过
                if (chars[maxIndex + index] != 'C') {
                    index++;
                    selectedCount++;
                } else {
                    chars[maxIndex + index] = now;
                    index++;
                    count++;
                }
            }

            now = (now == 'A') ? 'B' : 'A';
        }


    }

    /**
     * 统计矩阵中的某个方阵的四角之和的最大数
     *
     * @param ints
     * @param row
     * @param column
     */
    public static void getMaxCount(int[][] ints, int row, int column) {
        int index = 1;
        while (row + index < ints.length && column + index < ints[0].length) {
            res = Math.max(sum(ints[row][column], ints[row + index][column], ints[row][column + index],
                               ints[row + index][column + index]), res);
            index++;
        }

    }

    public static int sum(int upLeft, int downLeft, int upRight, int downRight) {
        return upLeft + downLeft + upRight + downRight;
    }
}
