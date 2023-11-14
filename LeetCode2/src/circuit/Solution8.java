package circuit;

import java.util.Scanner;

/**
 * @author Valar Morghulis
 * @Date 2023/9/21
 */
public class Solution8 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int oneTotal = 0, zeroTotal = 0;
        int oneCount = 0, zeroCount = 0;

        String s = scanner.nextLine();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            oneTotal = aChar == '1' ? oneTotal + 1 : oneTotal;
            zeroTotal = aChar == '0' ? zeroTotal + 1 : zeroTotal;
        }
        int index = 0;
        int work = 0;
        //使用双指针
        //首先统计两边的
        int left=0;
        int right=chars.length-1;
        while (left<right){
            oneCount+=0;

        }


    }
}
