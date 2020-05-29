package sort;

import java.util.Arrays;

public class QuickSort {


    public static void sort(int[] a, int low, int high) {
        if (low >= high) return;
        int lo = partitionAI(a, low, high);
        sort(a, low, lo - 1);
        sort(a, lo + 1, high);
    }

    public static int partition(int[] a, int low, int high) {
        int pilot = a[low];
        int lo = low, hi = high;
        while (lo < hi) {
            while (lo < hi && pilot <= a[hi]) hi--;
            while (lo < hi && a[lo] <= pilot) lo++;
            swap(a, lo, hi);

        }
        a[low] = a[lo];
        a[lo] = pilot;
        return lo;
    }

    public static int partitionAI(int[] a, int p, int r) {
        int x = a[r];
        int i = p - 1;
        for (int j = p; j <= r - 1; j++) {
            if (a[j] <= x) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }

    public static void main(String[] args) {
        int a[] = new int[]{123123, 1231231212, 1231231, 212, 122313, 832};
        sort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
