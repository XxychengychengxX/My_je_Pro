package circuit;

import java.util.Arrays;

/**
 * @author Valar Morghulis
 * @Date 2023/9/24
 */
public class Solution10 {
    public static void main(String[] args) {


    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param nums int整型一维数组
     * @return int整型
     */
    public int maxAbsoluteSum(int[] nums) {
        // write code here
        if (nums.length <= 1) {
            return nums[0];
        }
        boolean[] booleans = new boolean[nums.length];
        int[] temp = new int[nums.length];
        if (nums[0] < 0) {
            booleans[0] = true;
        }
        temp[0] = Math.abs(nums[0]);
        for (int i = 1; i < temp.length; i++) {

            int tempNum = temp[i - 1];
            if (booleans[i - 1]) {
                tempNum = -tempNum;
            }
            int i1 = nums[i] + tempNum;
            int max = Math.max(Math.abs(i1), Math.abs(nums[i]));
            if (i1 < 0 && max == -i1) {
                booleans[i] = true;
            }
            if (nums[i] < 0 && max == -nums[i]) {
                booleans[i] = true;
            }

            temp[i] = max;
        }
        int max = Arrays.stream(temp).max().getAsInt();
        int min = Arrays.stream(temp).min().getAsInt();

        return Math.max(Math.abs(max), Math.abs(min));
    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param nums int整型一维数组 有序数组
     * @return int整型
     */
    public int removeDuplicates(int[] nums) {
        // write code here

        if (nums.length <= 1) { 
            return nums.length;
        }
        int i = 0;
        int j = 1;
        int count = 0;
        while (j < nums.length) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];

            }else {
                count++;
            }
            j++;
        }
        return nums.length  - count;
    }
}
