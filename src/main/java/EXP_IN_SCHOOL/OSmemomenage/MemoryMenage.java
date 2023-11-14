/**
 * @author Valar Morghulis
 * @Date 2022/11/28
 */
package EXP_IN_SCHOOL.OSmemomenage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class MemoryMenage {
    private static FileOutputStream fileStream;

    static {
        try {
            fileStream = new FileOutputStream("E:\\Java\\MY_je_projects\\src" +
                    "\\main\\OSmemomenage\\res.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        //指定物理内存块大小
        int size = 4;
        //a是生成的随机地址序列
        int[] a = new int[100];
        //b是根据生成的页面访问序列
        int[] b = new int[100];
        a[0] = (int) (Math.random() * 100) % 10;
        //生成地址序列
        for (int i = 1; i < 100; ++i) {
            double random = Math.random();
            if (random < 0.7)
                a[i] = a[i - 1] + 1;
            else if (random < 0.9)
                if (a[i - 1] == 0)
                    a[i] = 0;
                else
                    a[i] = ((int) (Math.random() * 100)) % a[i - 1];
            else
                a[i] = ((int) (Math.random() * 100)) % (100 - a[i - 1] + 1) + a[i - 1];
            if (a[i] > 99)
                a[i] = 99;
        }
        //生成页面访问串
        for (int i = 0; i < 100; ++i) {
            b[i] = a[i] / 10;
        }

        try {
            fifo(b, size);
            lru(b, size);
            optimal(b, size);
            //打印a数组
            fileStream.write((Arrays.toString(a)+"\n").getBytes(StandardCharsets.UTF_8));
            //打印b数组
            fileStream.write((Arrays.toString(b)+"\n").getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void fifo(int[] b, int size) throws IOException {
        ArrayList<Integer> integers = new ArrayList<>();
        //是否打印的标志
        boolean prinflag = false;
        fileStream.write("FIFO :\n".getBytes(StandardCharsets.UTF_8));
        //主要算法
        for (int j : b) {
            //若不包含
            if (!integers.contains(j)) {
                if (integers.size() == size)
                    integers.remove(0);
                integers.add(j);
                prinflag = true;
            }
            //每次打印都代表缺页
            if (prinflag) {
                String string = integers + "\t缺页\n";
                fileStream.write(string.getBytes(StandardCharsets.UTF_8));
                prinflag = false;
            }
        }

    }


    public static void lru(int[] b, int size) throws IOException {
        ArrayList<Integer> integers = new ArrayList<>();
        fileStream.write("LRU :\n".getBytes(StandardCharsets.UTF_8));
        boolean lackflag = false;
        boolean printflag = false;
        for (int j : b) {
            //如果页面序列中包含该页面
            if (integers.contains(j)) {
                int index = integers.indexOf(j);
                if (index != integers.size() - 1) {
                    Integer remove = integers.remove(index);
                    integers.add(remove);
                    printflag = true;
                }
            } else if (integers.size() == size) {
                integers.remove(0);
                integers.add(j);
                lackflag = true;
                printflag = true;
            } else {
                integers.add(j);
                lackflag = true;
                printflag = true;
            }
            if (printflag) {
                String string = "";
                if (lackflag) {
                    string = integers + "\t缺页\n";
                    lackflag = false;
                } else
                    string = integers + "\n";
                fileStream.write(string.getBytes(StandardCharsets.UTF_8));
                printflag = false;
            }
        }
    }


    public static void optimal(int[] b, int size) throws IOException {
        ArrayList<Integer> integers = new ArrayList<>();
        fileStream.write("OPTIMAL :\n".getBytes(StandardCharsets.UTF_8));
        boolean flag = false;
        for (int i = 0; i < b.length; i++) {
            if (!integers.contains(b[i])) {
                if (integers.size() < size)
                    integers.add(b[i]);
                else {
                    boolean[] getvictim = getvictim(b, size, i + 1, integers);
                    for (int j = 0; j < getvictim.length; j++) {
                        if (getvictim[j]) {
                            integers.remove(j);
                            integers.add(b[i]);
                            flag = true;
                            break;
                        }
                    }
                }
            }
            if (flag) {
                String string = integers + "\t缺页\n";
                fileStream.write(string.getBytes(StandardCharsets.UTF_8));
                flag = false;
            }
        }

    }

    /**
     * 返回一个等长数组决定哪个元素被替换
     *
     * @param b        页面数组
     * @param size     b的大小
     * @param index    从哪个元素开始判断
     * @param integers 当前的内存里的页面序列
     */
    private static boolean[] getvictim(int[] b, int size, int index, ArrayList<Integer> integers) {
        boolean[] booleans = new boolean[size];
        Arrays.fill(booleans, true);
        //计数，若booleans数组中只剩下最后一个元素没有判断，则直接跳出循环（说明已经找到牺牲帧）
        int count = size - 1;
        //超过数组长度或者只剩下最后一个元素没有判断则跳出循环
        for (int i = index; i < b.length && count > 0; i++) {
            if (integers.contains(b[i])) {
                int index1 = integers.indexOf(b[i]);
                if (booleans[index1]) {
                    booleans[index1] = false;
                    count--;
                }
            }
        }
        return booleans;
    }
}
