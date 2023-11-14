package circuit;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Valar Morghulis
 * @Date 2023/9/20
 */
public class Solution5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        int[] ints = new int[i];
        for (int j = 0; j < ints.length; j++) {
            ints[j] = scanner.nextInt();
        }
        int target = scanner.nextInt();
        scanner.close();
        int min = ints[0];
        int start = -1, end = -1;
        //分开的地方
        int index = 0;

        for (int j = 0; j < ints.length; j++) {
            if (ints[j] < min) {
                index = j;
                break;
            }
        }
        //如果这个是第二组
        if (index != 0) {
            int[] front = Arrays.copyOfRange(ints, 0, index);
            int[] out = Arrays.copyOfRange(ints, index, ints.length);
            for (int j = 0; j < front.length; j++) {
                if (front[j] == target) {
                    end = j;
                }
            }
            for (int j = 0; j < out.length; j++) {
                if (out[j] == target) {
                    start = j + front.length;
                    break;
                }
            }
        } else {
            for (int j = 0; j < ints.length; j++) {
                if (ints[j] == target) {
                    if (start == -1) {
                        start = j;
                    }
                    end = j;
                }
            }

        }
        System.out.println(start + " " + end);

    }


    public int[] find() {
        return null;
    }

}
