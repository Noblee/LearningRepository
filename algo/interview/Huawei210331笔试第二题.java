package interview;


/**
 * <p><img src="Huawei210331笔试第二题1.jpg"/></p>
 * <p><img src="Huawei210331笔试第二题2.jpg"/></p>
 *
 */
public class Huawei210331笔试第二题 {
    public static void main(String[] args) {
        System.out.println(func(new int[]{0,0,1, 1, 2, 2, 2, 2, 2}));
    }

    public static int func(int[] array) {
        int[] temp = new int[1001];
        for (int i : array) {
            temp[i]++;
        }
        int ans = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != 0) {
                int a = temp[i] / (i + 1);
                int b = temp[i] % (i + 1) == 0 ? 0 : 1;
                ans += (a + b) * (i + 1);
            }
        }
        return ans;
    }
}
