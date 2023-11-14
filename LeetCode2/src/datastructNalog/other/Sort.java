package datastructNalog.other;

/**
 * @author Valar Morghulis
 * @Date 2023/9/12
 */
public class Sort {

    public static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean swap;
        // 每次需要排序的长度
        for (int i = arr.length - 1; i > 0; i--) {
            swap = false;
            // 从第一个元素到第i个元素
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swap = true;
                }
            }//loop j
            if (swap == false) {
                break;
            }
        }//loop i
    }// method bubbleSort

    /**
     * 选择排序
     *
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        int temp, min = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            min = i;
            // 循环查找最小值
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (min != i) {
                temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            int value = arr[i];
            int position = i;
            while (position > 0 && arr[position - 1] > value) {
                arr[position] = arr[position - 1];
                position--;
            }
            arr[position] = value;
        }//loop i
    }

    /**
     * 归并排序
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        internalMergeSort(arr, temp, 0, arr.length - 1);
    }

    private static void internalMergeSort(int[] arr, int[] temp, int left, int right) {
        //当left==right的时，已经不需要再划分了
        if (left < right) {
            int middle = (left + right) / 2;
            //左子数组
            internalMergeSort(arr, temp, left, middle);
            //右子数组
            internalMergeSort(arr, temp, middle + 1, right);
            //合并两个子数组
            mergeSortedArray(arr, temp, left, middle, right);

        }
    }

    // 合并两个有序子序列
    private static void mergeSortedArray(int arr[], int temp[], int left, int middle, int right) {
        int i = left;
        int j = middle + 1;
        int k = 0;
        while (i <= middle && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= middle) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        //把数据复制回原数组
        for (i = 0; i < k; ++i) {
            arr[left + i] = temp[i];
        }
    }


    /**
     * 快排
     *
     * @param a
     */
    public static void quicksort(int[] a) {
        int i = 0, j = a.length - 1;
        quicksort_recursive(a, i, j);
        //第二种写法
        /*int i = 0, j = a.length ;
        quicksort_recursive2(a, count, i, j);*/
    }

    public static void quicksort_recursive(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int i = start, j = end;
        int temp = a[start];


        while (i < j) {
            //从末尾找
            while (i < j && a[j] >= temp) {
                j--;

            }
            if (i < j) {
                //先赋值再自增
                a[i++] = a[j];

            }
            //从左边找
            while (i < j && a[i] <= temp) {
                i++;

            }
            if (i < j) {
                a[j--] = a[i];

            }
        }
        a[i] = temp;

        quicksort_recursive(a, start, i - 1);
        quicksort_recursive(a, i + 1, end);
    }
}
