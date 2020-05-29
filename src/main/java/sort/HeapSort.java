package sort;

import java.util.Arrays;

public class HeapSort {

    public static void adjustHeap(int a[], int i, int length) {
        while (i * 2 + 1 <= length) {
            int temp = i * 2 + 1;
            if (temp + 1 <= length && a[temp + 1] > a[temp]) {
                temp++;
            }
            if (a[temp] > a[i]) {
                swap(a, temp, i);
                i = temp;
            } else {
                break;
            }
        }

    }

    public static void sort(int[] a) {
        for (int i = a.length / 2 - 1; i >= 0; i--) {
            adjustHeap(a, i, a.length - 1);
        }
        for (int i = 0; i < a.length - 1; i++) {
            swap(a, 0, a.length - 1 - i);
            adjustHeap(a, 0, a.length - 2 - i);
        }

    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int a[] = new int[]{123123, 212, 1231231, 1231231, 444, 832};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
