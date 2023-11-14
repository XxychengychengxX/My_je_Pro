package circuit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author Valar Morghulis
 * @Date 2023/10/16
 */
public class Solution11 {
    /*Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别

        int i = in.nextInt();
        String s = String.valueOf(i);
        int[] ints = new int[s.length()];
        for (int j = 0; j < s.length(); j++) {
            ints[j] = i % 10;
            i = i / 10;
        }
        int[] ints1 = Arrays.stream(ints).distinct().toArray();
        for (int i1 : ints1) {
            System.out.print(i1);
        }*/

    static ArrayList<HashSet<Integer>> arrayList = new ArrayList<>();
    static int res = -1;

    /**/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int column = in.nextInt();
        int startRow = in.nextInt() - 1;
        int startColumn = in.nextInt() - 1;
        int endRow = in.nextInt() - 1;
        int endColumn = in.nextInt() - 1;

        char[][] chars = new char[row][column];
        in.nextLine();
        for (int i = 0; i < row; i++) {
            String s = in.nextLine();
            char[] chars1 = s.toCharArray();
            chars[i] = chars1;
        }

        chars[startRow][startColumn] = 1;
        getMinMove(chars, startRow, startColumn, endRow, endColumn, 0);
        System.out.println(res);
    }

    private static void getMinMove(char[][] chars, int startRow, int startColumn, int endRow, int endColumn, int i) {

        if (startRow == endRow && startColumn == endColumn) {
            if (res == -1) {
                res = i;
            } else
                res = Math.min(res, i);

        }
        //上
        if (startRow - 1 >= 0 && chars[startRow - 1][startColumn] == '.') {
            chars[startRow - 1][startColumn] = 1;
            i++;
            getMinMove(chars, startRow - 1, startColumn, endRow, endColumn, i);
            i--;
            chars[startRow - 1][startColumn] = 0;
        }
        //左
        if (startColumn - 1 >= 0 && chars[startRow][startColumn - 1] == '.') {
            chars[startRow][startColumn - 1] = 1;
            i++;
            getMinMove(chars, startRow, startColumn - 1, endRow, endColumn, i);
            i--;
            chars[startRow][startColumn - 1] = 0;
        }

        if (startRow + 1 < chars.length && chars[startRow + 1][startColumn] == '.') {
            chars[startRow + 1][startColumn] = 1;
            i++;
            getMinMove(chars, startRow + 1, startColumn, endRow, endColumn, i);
            i--;
            chars[startRow + 1][startColumn] = 0;
        }

        if (startColumn + 1 < chars.length && chars[startRow][startColumn + 1] == '.') {
            chars[startRow][startColumn + 1] = 1;
            i++;
            getMinMove(chars, startRow, startColumn + 1, endRow, endColumn, i);
            i--;
            chars[startRow][startColumn + 1] = 0;

        }
    }

    private static void select(int[] ints, int index, int target) {
        /*Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] ints = new int[n];
        Arrays.fill(ints, 0);
        //5 5
        select(ints, 0, m);

        arrayList.forEach(integers -> {
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.println();
        });*/

        if (target < 0) {
            return;
        } else if (target == 0) {
            HashSet<Integer> integers = new HashSet<>();
            for (int i = 0; i < ints.length; i++) {
                if (ints[i] == 1) {
                    integers.add(i + 1);
                }
            }
            arrayList.add(integers);
            return;
        }
        while (index < ints.length) {
            //不能往回倒
            if (ints[index] == 0) {
                ints[index] = 1;
                select(ints, index + 1, target - index - 1);
                ints[index] = 0;
            }
            index++;
        }


    }
}
