package interview;

public class 快速选择 {
    int quickSelect(int[] nums, int lo, int hi, int k){
        int pilot = nums[lo];
        int high = hi, low = lo;
        while(low<high){
            while(low<high&&pilot>=nums[high]) high--;
            while(low<high&&pilot<=nums[low]) low++;
            int temp = nums[low];
            nums[low] = nums[high];
            nums[high] = temp;
        }
        nums[lo] = nums[low];
        nums[low] = pilot;
        if(low==k+1) return nums[low];
        else if(low<k+1) return quickSelect(nums,lo,low-1,k);
        else return quickSelect(nums,low+1,hi,k);
    }

    public static void main(String[] args) {
    }
}
