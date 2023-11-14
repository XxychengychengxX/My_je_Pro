package circuit;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Valar Morghulis
 * @Date 2023/9/19
 */
public class Solution4 {
    int total = Integer.MAX_VALUE;

    /**
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * 你可以认为每种硬币的数量是无限的。
     * <p>
     * 示例 1： 输入：coins = [1, 2, 5], amount = 11 输出：3 解释：11 = 5 + 5 + 1 示例 2： 输入：coins = [2], amount = 3 输出：-1 示例 3：
     * 输入：coins = [1], amount = 0 输出：0 提示：
     * <p>
     * 1 <= coins.length <= 12 1 <= coins[i] <= 231 - 1 0 <= amount <= 104
     */
    public static void main(String[] args) {
        int[] ints = new int[10];
        Arrays.sort(ints);
        int want = 10;
        ArrayList<Integer> integers = new ArrayList<>();
        System.out.println(new Solution4().getTotal(ints, want, integers));
    }

    private int getTotal(int[] ints, int want, ArrayList<Integer> arrayList) {
        if (want == 0) {
            return arrayList.size();
        }
        int i = ints.length - 1;
        while (i > 0) {
            if (ints[i] > want) {
                i--;
                continue;
            }
            arrayList.add(ints[i]);
            int temp = getTotal(ints, want - ints[i], arrayList);
            if (temp != -1) {
                total = Math.min(temp, total);
            }
            arrayList.remove(arrayList.size() - 1);
            i--;
        }
        return -1;
    }


}
