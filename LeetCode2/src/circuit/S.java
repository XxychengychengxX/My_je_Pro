package circuit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author Valar Morghulis
 * @Date 2023/10/14
 */
public class S {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();

        StringBuilder a = new StringBuilder();
        StringBuilder b = new StringBuilder();
        int i = 0;
        while (i < count) { // 注意 while 处理多个 case
            a.append(in.nextInt());
            i++;
        }
        i = 0;
        while (i < count) { // 注意 while 处理多个 case
            b.append(in.nextInt());
            i++;
        }
        in.close();
        int res = 0;
        String as = a.toString();

        for (int j = 0; j < as.length(); j++) {
            for (int k = j + 1; k <= as.length(); k++) {
                String substring = a.substring(j, k);
                StringBuilder replace = a.replace(j, k, String.valueOf(new StringBuilder(substring).reverse()));
                if ((String.valueOf(replace)).equals(String.valueOf(b))) {
                    res++;
                }
                a.replace(j, k, substring);
            }
        }
        System.out.println(res);
    }

    public static void test2(Scanner in) {
        // 注意 hasNext 和 hasNextLine 的区别
        int count = in.nextInt();
        HashSet<Integer> integers = new HashSet<>();
        int i = 0;
        while (in.hasNextInt() && i < count) { // 注意 while 处理多个 case
            integers.add(in.nextInt());
            i++;
        }
        int res = 0;
        int target = in.nextInt();
        int last = target / 2;
        int first = 1;
        while (first < last) {
            if (!(integers.contains(first) || integers.contains(target - first))) {
                res++;
            }
            first++;
        }
        if (target % 2 == 0 && !(integers.contains(target / 2))) {
            res = res * 2 + 1;
        } else {
            res *= 2;
        }
        System.out.println(res);
    }

    public static void test(Scanner in) {
        int classCount = in.nextInt();
        int[] scores = new int[classCount];
        int i = 0;
        while (in.hasNextInt() && i < classCount) { // 注意 while 处理多个 case
            scores[i++] = in.nextInt();
        }
        int midStandard = in.nextInt();
        int avgStandard = in.nextInt();
        int avgMisStandard = in.nextInt();
        Arrays.sort(scores);

        double average = Arrays.stream(scores).summaryStatistics().getAverage();
        long sum = Arrays.stream(scores).summaryStatistics().getSum();
        long l = sum - scores[0] - scores[classCount - 1];

        int mid;
        if (classCount % 2 == 0) {
            mid = (scores[classCount / 2 - 1] + scores[classCount / 2]) / 2;
        } else {
            mid = scores[classCount / 2];
        }

        int avgMis = (int) (l / (classCount - 2));
        if (avgMis >= avgMisStandard || mid >= midStandard || average >= avgStandard) {
            System.out.println("Yes");
        } else
            System.out.println("No");
    }
}
