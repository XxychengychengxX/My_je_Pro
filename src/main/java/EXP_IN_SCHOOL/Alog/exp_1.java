package EXP_IN_SCHOOL.Alog;

public class exp_1 {
    int sum = Integer.MIN_VALUE;

    public static void main(String[] args) {
        exp_1 exp_1 = new exp_1();
        //设置成100，1000，10000000均可
        int[] ints = new int[10000];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * 200 - 100);
        }
        //用毫秒数来决定谁跑的快
        long start;
        long end;
        start = System.currentTimeMillis();
        System.out.println(exp_1.maxsumN(ints));
        end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    //穷举法
    public int maxsumN3(int[] source) {
        int sum = Integer.MIN_VALUE;
        int n = source.length;
        for (int i = 0; i < n; i++) {

            for (int j = i; j < n; j++) {
                int ThisSum = 0;
                /* 5*/
                for (int k = i; k <= j; k++)
                    /* 6*/
                    ThisSum += source[k]; /* sum from A[ i ] to A[ j ] */
                /* 7*/
                if (ThisSum > sum)
                    /* 8*/
                    sum = ThisSum; /* update max sum */
            } /* end for-j and for-i */
        }
        return sum;
    }

    //枚举法
    public int maxsumN2(int[] source) {
        int sum = Integer.MIN_VALUE;
        int n = source.length;
        //以source[i]为起点
        for (int i = 0; i < n; i++) {

            int thisSum = 0;
            //分别求出长度为source.length-1-i的子数组的和
            for (int j = i; j < n; j++) {
                thisSum += source[j];
                if (thisSum > sum) {

                    sum = thisSum;

                }
            }
        }
        return sum;
    }

    //分治法
    public int maxsumNlogN(int[] source, int left, int right) {
        //如果只有一个元素就返回max{a[left],sum}
        if (left == right) {
            return Math.max(source[left], sum);
        }
        int leftsum = 0, rightsum = 0;
        int center = (left + right) / 2;
        leftsum = maxsumNlogN(source, left, center);//求出左半边的最大字段和
        rightsum = maxsumNlogN(source, center + 1, right);//求出右半边的最大字段和
        //跨越左右两边的最大字段和
        //从center向左到left
        int sumleft = 0, templeft = 0;
        for (int i = center; i >= 0; i--) {
            templeft += source[i];
            if (templeft > sumleft)
                sumleft = templeft;
        }
        //从center向右到right
        int sumright = 0, tempright = 0;
        for (int i = center + 1; i <= right; i++) {
            tempright += source[i];
            if (tempright > sumright)
                sumright = tempright;
        }
        int bordersum = sumleft + sumright;
        //取三者中的最大值
        int sumAns = Math.max(leftsum, bordersum);
        sumAns = Math.max(sumAns, rightsum);
        return sumAns;
    }


    //动态规划
    public int maxsumN(int[] source) {
        /*用 f(i)代表以第 i 个数结尾的「连续子数组的最大和」
        则显然f(i+1)是f(i)+source[i+1]和source[i+1]中的最大值*/
        int sum = source[0];
        int pre = 0;
        for (int i : source) {
            pre = Math.max(pre + i, i);
            sum = Math.max(pre, sum);
        }
        return sum;
    }

}
