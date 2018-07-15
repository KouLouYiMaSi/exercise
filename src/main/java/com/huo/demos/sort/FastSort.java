package com.huo.demos.sort;

public class FastSort {
    public static void fastSort(int[] a, int low, int high) {
        int i, j, pivot;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        pivot = a[i];
        while (i < j) {
            while (i < j && a[j] >= pivot) {
                j--;
            }
            if (i < j) {
                a[i++] = a[j];
            }
            while (i < j && a[i] <= pivot) {
                i++;
            }
            if (i < j) {
                a[j--] = a[i];
            }
        }
        a[i] = pivot;
        fastSort(a, low, i - 1);
        fastSort(a, i + 1, high);
    }

    public static void fastSort(int[] a) {
        fastSort(a, 0, a.length - 1);
    }

    public static void bubbleSort(int[] a) {
        if (a.length < 2) {
            return;
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public static void insertSort(int[] source) {
        int i, j;
        int insertNode;
        // 从数组的第二个元素开始循环将数组中的元素插入
        for (i = 1; i < source.length; i++) {
            // 设置数组中的第2个元素为第一次循环要插入的数据
            insertNode = source[i];
            j = i - 1;
            // 如果要插入的元素小于第j个元素，就将第j个元素向后移
            while (j >= 0&& insertNode < source[j]) {
                source[j + 1] = source[j];
                j--;
            }
            // 直到要插入的元素不小于第j个元素,将insertNote插入到数组中
            source[j + 1] = insertNode;
        }
    }

    public static void main(String[] args) {
        int[] a = { 5, 5, 9, 3, 4, 8, 9, 6, 5, 8, 6, 4, 4, 4, 749, 4, 12, 5 };
        // fastSort(a);
        // bubbleSort(a);
        insertSort(a);
        for (int i : a) {
            System.out.print(" " + i);
        }
    }
}
