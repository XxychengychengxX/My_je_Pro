package circuit;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Valar Morghulis
 * @Date 2023/9/20
 */
public class Solution6 {

    private static int res = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        int[][] ints = new int[row][column];
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[0].length; j++) {
                ints[i][j] = scanner.nextInt();
            }
        }
        boolean[][] temp = new boolean[row][column];
        for (boolean[] booleans : temp) {
            Arrays.fill(booleans, false);
        }

        for (int i = 0; i < ints.length; i++) {
            if (ints[i][0] != 0) {
                temp[i][0] = true;
                getMinCount(ints, temp, i, 0, 0);
                temp[i][0] = false;
            }

        }
        System.out.println(res);
    }

    /*4 4
1 1 1 0
1 1 1 0
0 0 1 0
0 1 1 1*/
    private static void getMinCount(int[][] ints, boolean[][] temp, int row, int column, int count) {
        //如果已经到达最后一列
        if (column == ints[0].length - 1) {
            res = Math.min(count, res);
            return;
        }

        //否则判断下方

        if (column!=0){
            if (row + 1 < ints.length && ints[row + 1][column] != 0 && (!temp[row + 1][column])) {
                temp[row + 1][column] = true;
                getMinCount(ints, temp, row + 1, column, count + 1);
                temp[row + 1][column] = false;
            }

            if (row - 1 > 0 && ints[row - 1][column] != 0 && (!temp[row - 1][column])) {
                temp[row - 1][column] = true;
                getMinCount(ints, temp, row - 1, column, count + 1);
                temp[row - 1][column] = false;
            }
        }



        if (column + 1 < ints[0].length && ints[row][column + 1] != 0 && (!temp[row][column + 1])) {
            temp[row][column + 1] = true;
            getMinCount(ints, temp, row, column + 1, count + 1);
            temp[row][column + 1] = false;
        }


        if (column - 1 > 0 && ints[row][column - 1] != 0 && (!temp[row][column - 1])) {
            temp[row][column - 1] = true;
            getMinCount(ints, temp, row, column - 1, count + 1);
            temp[row][column - 1] = false;

        }


    }

}
