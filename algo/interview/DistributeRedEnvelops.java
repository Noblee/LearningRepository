package interview;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;

public class DistributeRedEnvelops {


    public static int[] getRedEnvelops(int redEnvelop, int redEvelopNum) {
        redEnvelop = redEnvelop * 100;
        TreeSet<Integer> randomSet = new TreeSet<>();
        int[] res = new int[redEvelopNum];
        Random randomGenerator = new Random();
        for (int i = 1; i <= redEvelopNum - 1; i++) {
            int random = Math.abs(randomGenerator.nextInt()) % redEnvelop;
            if (!randomSet.contains(random))
                randomSet.add(random);
            else {
                i--;
            }
        }
        int index = 0;
        int last = 0;
        for (int random : randomSet) {
            res[index++] = random - last;
            last = random;
        }
        res[index] = redEnvelop - last;
        return res;
    }

    public static void main(String[] args) {
        Arrays.toString(getRedEnvelops(10, 3));
    }
}
