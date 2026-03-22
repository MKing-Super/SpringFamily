package pers.mk.interview.algorithm.base;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        // 冒泡排序
//        method1();

        // 选择排序
//        method2();

        // 插入排序
//        method3();

        // 快速排序
//        method4();

        // 归并排序
//        method5();

        // 二分查找
        method6();
    }

    private static void method6(){
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        System.out.println("target = " + 11);
        int i = binarySearch(arr, 11);
        System.out.println("index = " + i);
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;  // 防止溢出
            System.out.println("left = "+left+" right = " +right+" mid = " + mid + " arr[mid] = " + arr[mid]);

            if (arr[mid] == target) {
                return mid;  // 找到目标
            } else if (arr[mid] < target) {
                left = mid + 1;  // 目标在右半部分
            } else {
                right = mid - 1;  // 目标在左半部分
            }
        }

        return -1;  // 未找到
    }

    private static void method5(){
        int[] arr = {5, 2, 8, 3, 9, 1, 6, 4, 7};

        System.out.println("排序前:");
        System.out.println(Arrays.toString(arr));

        mergeSort(arr);

        System.out.println("排序后:");
        System.out.println(Arrays.toString(arr));
    }

    private static void method4(){
        int[] arr = {5, 2, 8, 3, 9, 1, 6, 4, 7};

        System.out.println("排序前:");
        System.out.println(Arrays.toString(arr));

        quickSort(arr);

        System.out.println("排序后:");
        System.out.println(Arrays.toString(arr));
    }

    // 插入排序
    private static void method3(){
        int[] arr = {4,3,2,1};
        int n = arr.length;
        for (int i = 1 ; i < n ; i++){
            int key = arr[i];
            int j = i - 1;
            // 将比key大的元素向后移动
            while (j >= 0 && arr[j] > key){
                arr[j + 1] = arr[j];
                System.out.println(Arrays.toString(arr));
                j--;
            }
            System.out.println("++++++++++");
            arr[j + 1] = key;
            System.out.println(Arrays.toString(arr));
            System.out.println("==============");
        }
        System.out.println("--------------------");
        System.out.println(Arrays.toString(arr));
    }

    // 选择排序
    private static void method2(){
        int[] arr = {4,3,2,1};
        int n = arr.length;
        for (int i = 0 ; i < n - 1 ; i++){
            int minIndex = i;
            for (int j = i + 1 ; j < n ; j++){
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                    System.out.print("i = "+i+" j = " + j + " | ");
                }
            }
            System.out.print(Arrays.toString(arr) + " -> ");
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("---------------------------------");
        System.out.println(Arrays.toString(arr));
    }

    // 冒泡排序
    private static void method1(){
        int[] arr = {3,2,1};
        int n = arr.length;
        for (int i = 0 ; i < n ; i++){
            for (int j = 0 ; j < n - 1 - i ; j++){
                System.out.print("i = "+i+" j = " + j + " : " + Arrays.toString(arr) + " -> ");
                if (arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                System.out.println(Arrays.toString(arr));
            }
        }
        System.out.println("-------------------------------");
        System.out.println(Arrays.toString(arr));
    }


    // 基本选择排序
    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            // 找到未排序部分的最小值索引
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // 将最小值放到已排序部分的末尾
            swap(arr, i, minIndex);
        }
        System.out.println(Arrays.toString(arr));
    }

    // 交换数组中的两个元素
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 快速排序主方法
     */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;  // 边界条件处理
        }
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 递归快速排序
     */
    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;  // 递归终止条件
        }

        // 分区，得到基准位置
        int pivotIndex = partition(arr, left, right);

        // 递归排序左右两部分
        quickSort(arr, left, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, right);
    }

    /**
     * 分区函数 - 使用最后一个元素作为基准
     */
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];  // 选择最后一个元素作为基准
        int i = left - 1;  // 指向小于基准的元素的边界

        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        // 将基准放到正确位置
        swap(arr, i + 1, right);
        return i + 1;
    }

    /**
     * 归并排序主方法
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;  // 边界条件处理
        }
        sort(arr, 0, arr.length - 1);
    }

    /**
     * 递归排序
     */
    private static void sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;  // 递归终止条件
        }

        int mid = left + (right - left) / 2;  // 防止溢出

        // 递归排序左右两部分
        sort(arr, left, mid);
        sort(arr, mid + 1, right);

        // 合并已排序的两部分
        merge(arr, left, mid, right);
    }

    /**
     * 合并两个有序数组
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        // 创建临时数组
        int[] temp = new int[right - left + 1];

        int i = left;      // 左数组起始位置
        int j = mid + 1;   // 右数组起始位置
        int k = 0;         // 临时数组索引

        // 合并两个有序数组
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // 复制左边剩余元素
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // 复制右边剩余元素
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // 将临时数组复制回原数组
        for (int m = 0; m < temp.length; m++) {
            arr[left + m] = temp[m];
        }
    }


}
