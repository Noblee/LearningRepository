import java.util.Arrays;

public class Text {


    public static void sort(int[] nums, int low, int high) {
        if (low >= high) return;

        int lo = low, hi = high;
        int temp = nums[lo];
        while (lo < hi) {
            while (lo < hi && nums[hi] >= temp) hi--;
            while (lo < hi && nums[lo] <= temp) lo++;
            swap(nums, lo, hi);
        }
        nums[low] = nums[hi];
        nums[hi] = temp;
        sort(nums, low, hi - 1);
        sort(nums, hi + 1, high);
    }

    public static void swap(int[] nums, int low, int high) {
        int temp = nums[low];
        nums[low] = nums[high];
        nums[high] = temp;
    }


    public static void main(String[] args) {
        int[] array = new int[]{12, 12312, 1, 1213, 29, -1};
        sort(array, 0, 5);
        System.out.println(Arrays.toString(array));
    }
}
