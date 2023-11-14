package EXP_IN_SCHOOL.Alog2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class SortAlog {
    public static void main(String[] args) throws IOException {
        int[] ints = new int[10];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * 30);
        }
        //统计比较和移动次数的数组，count[0]表示比较次数，count[1]表示移动次数
        /*int[] count = new int[2];
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\Java\\MY_je_projects\\src" +
                "\\main\\Alog2\\data.txt");
        String s = "排序前 :" + Arrays.toString(ints)+ "\r\n"+ "\r\n";
        fileOutputStream.write(s.getBytes(StandardCharsets.UTF_8));
        long l = System.currentTimeMillis();
       heapsort(ints,count);
        l = System.currentTimeMillis() - l;
        s = "排序后 :" + Arrays.toString(ints) + "\r\n" + "比较次数 :" + count[0] + "\r\n" + "移动次数 ：" +
        count[1]
                + "\r\n" + "所用时间 ：" + l + "ms" + "\r\n";
        fileOutputStream.write(s.getBytes(StandardCharsets.UTF_8));
        fileOutputStream.close();*/
        //int[] count = new int[2];

        //bubblesort(ints);
        heapsort(ints, new int[2]);
        System.out.println(Arrays.toString(ints));
    }

    //计数排序
    public static void countsort(int[] a) {
        int max = a[0];
        int min = a[0];
        for (int i : a) {
            if (i > max) {
                max = i;
                if (i < min) {
                    min = i;
                }
            } else {
                if (i < min) {
                    min = i;
                }
            }
        }
        int[] ints = new int[max - min + 1];
        for (int i : a) {
            ints[i - min]++;
        }
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] != 0) {
                for (int i1 = 0; i1 < ints[i]; i1++) {
                    System.out.print(i + min + " ");
                }
            }
        }
    }

    //bubblesort
    public static void bubblesort(int[] a) {
        int length = a.length;
        for (int i = length; i > 0; i--) {
            for (int j = 1; j < i; j++) {
                int k = j - 1;
                if (a[k] > a[j]) {
                    int temp = a[k];
                    a[k] = a[j];
                    a[j] = temp;
                }
            }
            System.out.println(Arrays.toString(a));
        }
    }

    //桶排序
    public static void tongpai(int[] a) {
        int max = a[0];
        int min = a[0];
        for (int i : a) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        int i = (max - min) / 10 != 0 ? (max - min) / 10 : 1;//10个桶
        ArrayList<Integer>[] arrayLists = new ArrayList[10];
        for (int i1 = 0; i1 < arrayLists.length; i1++) {
            arrayLists[i1] = new ArrayList<>();
        }
        for (int i1 : a) {
            int j = (i1 - min) / i;
            if (j == arrayLists.length) {
                arrayLists[--j].add(i1);
            } else {
                arrayLists[j].add(i1);
            }
        }
        for (int k = 0; k < arrayLists.length; k++) {
            if (!arrayLists[k].isEmpty()) {
                arrayLists[k].sort(Comparator.comparingInt(c -> c));
            }
        }
        for (ArrayList<Integer> arrayList : arrayLists) {
            for (Integer integer : arrayList) {
                System.out.print(integer + " ");
            }
        }

    }

    //堆排序
    public static void heapsort(int[] a, int[] count) {
        int lastindex = a.length;
        //建堆
        buildheap(a, lastindex, count);
        //开始构造有序区
        while (lastindex != 1) {
            //swapnum(a[0], a[lastindex - 1]);
            lastindex--;
            a[lastindex] = a[0] ^ a[lastindex];
            a[0] = a[0] ^ a[lastindex];
            a[lastindex] = a[0] ^ a[lastindex];
            //移动了一次
            count[1]++;
            buildheap(a, lastindex, count);
            System.out.println(Arrays.toString(a));
        }

    }

    static void buildheap(int[] a, int length, int[] count) {

        /*for (int i = 0; i < length; i++) {
            if (a[i] > a[0]) {
                a[0] = a[i] ^ a[0];
                a[i] = a[i] ^ a[0];
                a[0] = a[i] ^ a[0];
                swapnum(a[i], a[0]);
            }
        }*/
        for (int i = 0; i < (length - 1) / 2; i++) {
            int j = 2 * i + 1;
            if (a[i] < a[j]) {
                a[j] = a[i] ^ a[j];
                a[i] = a[i] ^ a[j];
                a[j] = a[i] ^ a[j];
                //比较一次移动一次
                count[0]++;
                count[1]++;
                i = -1;
                continue;
                //swapnum(a[i], a[j]);
            }
            if (j + 1 <= length) {
                j++;
                if (a[i] < a[j]) {
                    a[j] = a[i] ^ a[j];
                    a[i] = a[i] ^ a[j];
                    a[j] = a[i] ^ a[j];
                    count[0]++;
                    count[1]++;
                    i = -1;
                    continue;
                    //swapnum(a[i], a[j]);
                }
            }
        }
    }

    //归并排序
    public static void mergeSort(int[] arr, int[] count) {
        int len = arr.length;
        int[] result = new int[len];
        mergeSortRecursive(arr, result, count, 0, len - 1);
    }

    static void mergeSortRecursive(int[] arr, int[] result, int[] count, int start, int end) {
        //开始大于结束
        if (start >= end) {
            return;
        }
        int len = end - start, mid = (len >> 1) + start;//mid=(end+start)/2
        //左边与左边结尾
        int start1 = start, end1 = mid;
        //右边与右边结尾
        int start2 = mid + 1, end2 = end;
        mergeSortRecursive(arr, result, count, start1, end1);
        mergeSortRecursive(arr, result, count, start2, end2);
        int k = start;
        while (start1 <= end1 && start2 <= end2) {
            result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
            count[0]++;
            count[1]++;
        }
        while (start1 <= end1) {
            result[k++] = arr[start1++];
            count[1]++;
        }
        while (start2 <= end2) {
            result[k++] = arr[start2++];
            count[1]++;
        }
        for (k = start; k <= end; k++) {
            arr[k] = result[k];
            count[1]++;
        }
    }

    //快速排序
    public static void quicksort(int[] a, int[] count) {
        int i = 0, j = a.length - 1;
        quicksort_recursive(a, count, i, j);
        //第二种写法
        /*int i = 0, j = a.length ;
        quicksort_recursive2(a, count, i, j);*/
    }

    public static void quicksort_recursive(int[] a, int[] count, int start, int end) {
        if (start >= end) {
            return;
        }
        int i = start, j = end;
        int temp = a[start];


        while (i < j) {
            //从末尾找
            while (i < j && a[j] >= temp) {
                j--;
                count[0]++;
            }
            if (i < j) {
                //先赋值再自增
                a[i++] = a[j];
                count[1]++;
            }
            //从左边找
            while (i < j && a[i] <= temp) {
                i++;
                count[0]++;
            }
            if (i < j) {
                a[j--] = a[i];
                count[1]++;
            }
        }
        a[i] = temp;
        count[1]++;
        quicksort_recursive(a, count, start, i - 1);
        quicksort_recursive(a, count, i + 1, end);
    }

    public static void quicksort_recursive2(int[] a, int[] count, int start, int end) {
        if (start >= end) {
            return;
        }
        int i = start, j = end;
        int temp = a[start];
        while (true) {
            while (a[++i] < temp) {
                ;
            }
            while (a[--j] > temp) {
                ;
            }
            if (i >= j) {
                break;
            } else {
                int temp1 = a[i];
                a[i] = a[j];
                a[j] = temp1;
            }
        }
        a[start] = a[i];
        a[i] = temp;
    }

}
