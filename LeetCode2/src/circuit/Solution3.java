package circuit;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Valar Morghulis
 * @Date 2023/9/18
 */
public class Solution3 {

    private static ReentrantLock reentrantLock = new ReentrantLock(true);

    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        int weight = 0, want = 0;
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            weight = in.nextInt();
            want = in.nextInt();
            int i = 0;
            while (i < want) {
                integers.add(in.nextInt());
                i++;
            }

        }

        getTotal(integers, weight, want);
    }

    private static int getTotal(ArrayList<Integer> arrayList, int weight, int want) {
        if (want == 0) {
            return 1;
        }
        if (weight < 0||arrayList.isEmpty()) {
            return 0;
        }
        Integer remove = arrayList.remove(0);
        int total = getTotal(arrayList, weight - remove, want - remove);
        arrayList.add(remove);
        return total;
    }


    public static class SonThread extends Thread {
        private int flag;

        public SonThread(int i) {
            this.flag = i;
        }


        @Override
        public void run() {
            reentrantLock.lock();
            try {
                switch (flag) {
                    case 1:
                        System.out.println("A");
                        break;
                    case 2:
                        System.out.println("B");
                        break;
                    case 3:
                        System.out.println("C");
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                reentrantLock.unlock();
            }
        }
    }
}
