package dp;

import java.util.Arrays;

public class ZeroOnePackage {

    /**
     * @param volume  背包大小
     * @param weights 物品重量
     * @param values  物品价值
     * @return 装入物品总价值
     */
    public static int func1(int volume, int[] weights, int[] values) {
        int[][] dp = new int[volume + 1][weights.length + 1];
        for (int i = 1; i <= volume; i++) {
            for (int j = 1; j <= weights.length; j++) {
                if (i - weights[j - 1] >= 0) {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - weights[j - 1]][j - 1] + values[j - 1]);
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[volume][weights.length];
    }


    /**
     * @param rest_volume  背包大小
     * @param weights 物品重量
     * @param values  物品价值
     * @return 装入物品总价值
     */
    public static int func2(int rest_volume, int[] weights, int[] values, int i) {
        if (rest_volume == 0 || i >= values.length) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        if (rest_volume - weights[i] >= 0) {
            max = Math.max(func2(rest_volume - weights[i], weights, values, i + 1) + values[i], max);
        }
        max = Math.max(func2(rest_volume, weights, values, i + 1), max);
        return max;
    }

    public static void main(String[] args) {
        int[] weights = new int[]{2, 2, 6, 5, 4};
        int[] values = new int[]{6, 3, 5, 4, 6};
        System.out.println("输入物品重量： " + Arrays.toString(weights));
        System.out.println("输入物品价值： " + Arrays.toString(values));
        System.out.println("最优结果为： " + func2(10, weights, values, 0));
    }

}
