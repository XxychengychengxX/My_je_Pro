/**
 * @author Valar Morghulis
 * @Date 2022/12/14
 */
package EXP_IN_SCHOOL.ALOG6;

import java.util.ArrayList;

public class Rollback {

    //地图
    int[][] a =
            {{0, 10, 20, 5}, {10, 0, 15, 5}, {20, 15, 0, 5}, {5, 5, 5, 0}};

    //最优路径，初始化为999
    int best = 999;

    //达到最优解时走过的路径
    ArrayList<ArrayList<Integer>> trueroute = new ArrayList<>();

    //当前路径
    ArrayList<Integer> arrayList = new ArrayList<>();

    //当前路程
    int nowroute = 0;

    public static void main(String[] args) {
        Rollback rollback = new Rollback();
        rollback.arrayList.add(1);
        rollback.dfs(0, 1);

        System.out.println(rollback.trueroute);
        System.out.println("The shortest route length is ：" + rollback.best);
    }

    /**
     * dfs解决回溯问题
     * @param now  当前所在的城市编号
     * @param step 当前已经到第几个城市，等于5时相当于回到起点
     */
    public void dfs(int now, int step) {

        if (step == 5) {
                best = nowroute;
                trueroute.add(new ArrayList<>(arrayList));
        } else {
            if (step == 4) {
                //小小剪枝,如果回到1时加入的路程大于已经求出的路程，则剪纸
                if (nowroute + a[now][0] <= best) {
                    nowroute += a[now][0];
                    arrayList.add(1);
                    dfs(1, step + 1);

                    arrayList.remove(arrayList.size() - 1);
                    nowroute -= a[now][0];
                }
            } else {
                for (int i = 0; i < a[now].length; i++) {
                    if (!arrayList.contains(i + 1)) {
                        //走到下个城市，对应的数据结构加上对应数据
                        nowroute += a[now][i];
                        arrayList.add(i + 1);
                        //进入递归
                        dfs(i, step + 1);
                        //回溯
                        arrayList.remove(arrayList.size() - 1);
                        nowroute -= a[now][i];
                    }
                }
            }
        }
    }
}
