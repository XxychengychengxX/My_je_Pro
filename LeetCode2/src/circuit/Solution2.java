package circuit;

import java.util.Arrays;

/**
 * @author Valar Morghulis
 * @Date 2023/9/16
 */
public class Solution2 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param salary      int整型一维数组
     * @param subordinate int整型二维数组
     * @param id          int整型
     * @return int整型
     */
    public int getTotalSalary(int[] salary, int[][] subordinate, int id) {
        // write code here
        int sum = 0;
        if (subordinate[id - 1].length != 0) {
            int[] employee = subordinate[id - 1];
            for (int i : employee) {
                sum += getTotalSalary(salary, subordinate, i);
            }
        }
        sum += salary[id - 1];
        return sum;
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param scores int整型一维数组 分数
     * @return int整型
     */
    public int prize(int[] scores) {
        // write code here
        //用来存储每个人分的的玩偶情况
        int[] temp = new int[scores.length];
        //初始化
        Arrays.fill(temp, 1);
        culculate(scores, temp, 0, scores.length - 1);

        return Arrays.stream(temp).sum();
    }

    private void culculate(int[] scores, int[] temp, int left, int right) {
        if (left > right) {
            return;
        }
        int min = scores[left];
        int minIndex = left;
        for (int i = left; i <= right; i++) {
            //首先遍历一遍拿到最小值以及他的引用
            if (scores[i] < min) {
                min = scores[i];
                minIndex = i;
            }
        }
        int r = 0;
        int l = 0;

        //如果左边是score的元素
        if (minIndex - 1 >= 0) {
            if (scores[minIndex - 1] < scores[minIndex]) {
                l = temp[minIndex - 1];
            }
        }

        //判断右边
        if (minIndex + 1 <= scores.length - 1) {
            if (scores[minIndex + 1] < scores[minIndex]) {
                r = temp[minIndex + 1];
            }
        }
        temp[minIndex] = Math.max(r, l) + 1;

        culculate(scores, temp, left, minIndex - 1);
        culculate(scores, temp, minIndex + 1, right);
    }
}
