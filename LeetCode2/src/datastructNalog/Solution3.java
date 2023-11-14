package datastructNalog;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @author Valar Morghulis
 * @Date 2023/9/16
 */
public class Solution3 {
    public static void main(String[] args) {
        int[][] ints = {{1, 2, 3, 4, 5, 6, 7}, {8, 9, 10, 11, 12, 13, 14}, {15, 16, 17, 18, 19, 20, 21}, {22, 23, 24,
                25, 26, 27, 28}};
        new Solution3().getLuckyNum(ints);
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param nums int整型二维数组 一段时间内的中奖号码数组
     * @return int整型一维数组
     */
    public int[] getLuckyNum(int[][] nums) {
        // write code here
        int index = 0;
        int[] ints = new int[7];
        int[][] temp = new int[2][33];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] value : temp) {
            Arrays.fill(value, 0);
        }
        for (int i = 0; i < temp[0].length; i++) {
            temp[0][i] = i + 1;
        }

        for (int[] num : nums) {
            for (int target : num) {
                temp[1][target - 1]++;
            }
        }
        for (int i = 0; i < temp[1].length; i++) {
            if (temp[1][i] == 0) {
                ints[index++] = i + 1;
                if (index==7){
                    break;
                }
            }
        }
        for (int i = index; i < ints.length; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int j = 0; j < temp[0].length; j++) {
                if (temp[1][j] != 0 && temp[1][j] < min) {
                    min = temp[1][j];
                    minIndex = j;
                }
            }
            ints[i] = minIndex + 1;
            temp[1][minIndex] = 0;
        }
        Arrays.sort(ints);
        return ints;
    }
}
